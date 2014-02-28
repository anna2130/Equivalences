package parser;

import org.antlr.v4.runtime.tree.TerminalNode;

import parser.ExprParser.ExprContext;

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
	
    @Override 
    public void enterEXPR(ExprParser.EXPRContext ctx) { 
    	ExprContext e = ctx.expr();
        System.out.println("EXPR -> " + e.getChildCount() + " i: " + index); // needs loop
    }
    
	@Override public void exitEXPR(ExprParser.EXPRContext ctx) { }
    
    @Override 
    public void enterIMPLIES(ExprParser.IMPLIESContext ctx) { 
        java.util.List<ExprContext> e = ctx.expr();
        System.out.println("IMPLIES -> " + e.get(0).getText() + ", " + e.get(1).getText() + " i: " + Integer.toBinaryString(index));
        
        tree.addNode(new BinaryOperator(index, binary.size(), "->"));
		System.out.println(tree.toString());
        
        binary.push(true);
        index <<= 1;
    }
    
	@Override 
	public void exitIMPLIES(ExprParser.IMPLIESContext ctx) {
		System.out.println("Exit implies " + Integer.toBinaryString(index));
		index = index >> 1;
		if (binary.pop())
			index++;
		System.out.println(index);
	}
    
    @Override 
    public void enterNOT(ExprParser.NOTContext ctx) { 
    	ExprContext e = ctx.expr();
        System.out.println("NOT -> " + e.getChild(0).getText() + " i: " + Integer.toBinaryString(index)); 
        
        tree.addNode(new UnaryOperator(index, binary.size(), "!"));
		System.out.println(tree.toString());

        binary.push(false);
        index <<= 1;
    }
    
	@Override public void exitNOT(ExprParser.NOTContext ctx) {
		System.out.println("Exit not " + Integer.toBinaryString(index));
		if (binary.pop())
			index++;
		System.out.println(index);
	}
    
    @Override 
    public void enterBINOP_(ExprParser.BINOP_Context ctx) { 
        java.util.List<ExprContext> e = ctx.expr();
        System.out.println("BINOP -> " + e.get(0).getText() + ", " + e.get(1).getText() + " i: " + Integer.toBinaryString(index));

        // TODO: Distinguish between & and |
        tree.addNode(new BinaryOperator(index, binary.size(), "&"));
		System.out.println(tree.toString());

        binary.push(true);
        index <<= 1;
    }
    
    @Override 
    public void exitBINOP_(ExprParser.BINOP_Context ctx) {
    	System.out.println("Exit binop " + Integer.toBinaryString(index));
		index >>= 1;
		if (binary.pop())
			index++;
		System.out.println(index);
    }
    
    @Override 
    public void enterATOM_(ExprParser.ATOM_Context ctx) { 
    	TerminalNode e = ctx.ATOM();
        System.out.println("ATOM -> " + e.getText() + " i: " +  Integer.toBinaryString(index)); 

        tree.addNode(new Atom(index, binary.size(), e.getText()));
		System.out.println(tree.toString());
    }
    
	@Override public void exitATOM_(ExprParser.ATOM_Context ctx) {
		System.out.println("Exit atom " + Integer.toBinaryString(index));
		index = index >> 1;
		if (binary.peek())
			index++;
		System.out.println(index);
	}
}