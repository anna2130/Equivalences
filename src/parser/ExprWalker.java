package parser;

import org.antlr.v4.runtime.tree.TerminalNode;

import parser.ExprParser.ExprContext_;

import java.util.Stack;

import treeBuilder.Atom;
import treeBuilder.BinaryOperator;
import treeBuilder.FormationTree;
import treeBuilder.UnaryOperator;

public class ExprWalker extends ExprBaseListener {
	
	private FormationTree tree;
	private Stack<Boolean> binary;
	private int index;
	private boolean increment;
	
	public ExprWalker(FormationTree tree) {
		this.tree = tree;
		index = 0;
		binary = new Stack<Boolean>();
	}
	
	public void remove() {
		index >>= 1;
        binary.pop();
        
	    if (!binary.empty()) {
	    	increment = binary.peek();
		}
	}
	
	public void addBinary() {
        index <<= 1;
        if (increment) {
        	index++;
        	increment = false;
        }
		binary.push(true);
	}
	
	public void addUnary() {
    	index <<= 1;
        if (increment) {
        	index++;
        	increment = false;
        }
		binary.push(false);
	}
	
	public int depth() {
		return binary.size() - 1;
	}
	
    @Override public void enterEXPR(ExprParser.EXPRContext ctx) {}
    
	@Override public void exitEXPR(ExprParser.EXPRContext ctx) {}
    
    @Override 
    public void enterIMPLIES(ExprParser.IMPLIESContext ctx) {
        addBinary();
        tree.addNode(new BinaryOperator(index, depth(), "->"));
    }
    
	@Override 
	public void exitIMPLIES(ExprParser.IMPLIESContext ctx) {
		remove();
	}
    
    @Override 
    public void enterNOT(ExprParser.NOTContext ctx) { 
        addUnary();
        tree.addNode(new UnaryOperator(index, depth(), "!"));
    }
    
	@Override public void exitNOT(ExprParser.NOTContext ctx) {
		remove();
	}
    
    @Override 
    public void enterBINOP_(ExprParser.BINOP_Context ctx) { 
        addBinary();
        
        java.util.List<ExprContext_> e = ctx.expr();
        // Works out if | or & -- Probably an easier way of doing this
        int i = ctx.getText().indexOf(e.get(1).getText()) - 1;
        System.out.println(e.get(1).getText());
        String c = ctx.getText().charAt(i) + "";
        
        tree.addNode(new BinaryOperator(index, depth(), c));
    }
    
    @Override 
    public void exitBINOP_(ExprParser.BINOP_Context ctx) {
    	remove();
    }
    
    @Override 
    public void enterATOM_(ExprParser.ATOM_Context ctx) { 
        addUnary();
    	TerminalNode e = ctx.ATOM();
        tree.addNode(new Atom(index, depth(), e.getText()));
    }
    
	@Override public void exitATOM_(ExprParser.ATOM_Context ctx) {
		remove();
	}
}