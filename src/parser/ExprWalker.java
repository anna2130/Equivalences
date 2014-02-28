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
	
	public ExprWalker(FormationTree tree) {
		this.tree = tree;
		index = 0;
		binary = new Stack<Boolean>();
	}
	
	public void remove() {
		index >>= 1;
        binary.pop();
	}
	
	public void addBinary() {
        if (!binary.empty()) {
        	index <<= 1;
			if (binary.peek()) {
				index++;
			}
        }
		binary.push(true);
	}
	
	public void addUnary() {
		if (!binary.empty()) {
        	index <<= 1;
        }
		binary.push(false);
	}
	
    @Override 
    public void enterEXPR(ExprParser.EXPRContext ctx) { 
    	ExprContext_ e = ctx.expr();
        System.out.println("EXPR -> " + e.getChildCount() + " i: " + index); // needs loop
    }
    
	@Override public void exitEXPR(ExprParser.EXPRContext ctx) { }
    
    @Override 
    public void enterIMPLIES(ExprParser.IMPLIESContext ctx) {
        tree.addNode(new BinaryOperator(index, binary.size(), "->"));

        addBinary();
		System.out.println("Enter IMPLIES " + "	" + index + "-" + binary.size());
    }
    
	@Override 
	public void exitIMPLIES(ExprParser.IMPLIESContext ctx) {
		remove();
		System.out.println("Exit IMPLIES " + "	" + index + "-" + binary.size());
	}
    
    @Override 
    public void enterNOT(ExprParser.NOTContext ctx) { 
        tree.addNode(new UnaryOperator(index, binary.size(), "!"));

        addUnary();
		System.out.println("Enter NOT " + "	" + index + "-" + binary.size());
    }
    
	@Override public void exitNOT(ExprParser.NOTContext ctx) {
		remove();
		System.out.println("Exit NOT " + "	" + index + "-" + binary.size());
	}
    
    @Override 
    public void enterBINOP_(ExprParser.BINOP_Context ctx) { 
        java.util.List<ExprContext_> e = ctx.expr();
        
        // Works out if | or & -- Probably an easier way of doing this
        int i = ctx.getText().indexOf(e.get(1).getText()) - 1;
        String c = ctx.getText().charAt(i) + "";
        tree.addNode(new BinaryOperator(index, binary.size(), c));

        addBinary();
		System.out.println("Enter BINOP " + "	" + index + "-" + binary.size());
    }
    
    @Override 
    public void exitBINOP_(ExprParser.BINOP_Context ctx) {
    	remove();
		System.out.println("Exit BINOP " + "	" + index + "-" + binary.size());
    }
    
    @Override 
    public void enterATOM_(ExprParser.ATOM_Context ctx) { 
    	TerminalNode e = ctx.ATOM();
        tree.addNode(new Atom(index, binary.size(), e.getText()));

        addUnary();
		System.out.println("Enter ATOM = " + ctx.ATOM().getText() + "	" + index + "-" + binary.size());
    }
    
	@Override public void exitATOM_(ExprParser.ATOM_Context ctx) {
		remove();
		System.out.println("Exit ATOM = " + ctx.ATOM().getText() + "	" + index + "-" + binary.size());
	}
}