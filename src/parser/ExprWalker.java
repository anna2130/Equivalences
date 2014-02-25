package parser;

import java.util.BitSet;

import org.antlr.v4.runtime.tree.TerminalNode;

import parser.ExprParser.ExprContext;
import treeBuilder.BinaryOperator;
import treeBuilder.FormationTree;

public class ExprWalker extends ExprBaseListener {
	
	private FormationTree tree;
	private BitSet currentIndex;
	
	public ExprWalker(FormationTree tree) {
		this.tree = tree;
		currentIndex = new BitSet();
		currentIndex.set(1);
	}

    @Override 
    public void enterEXPR(ExprParser.EXPRContext ctx) { 
    	ExprContext e = ctx.expr();
        System.out.println("EXPR -> " + e.getChildCount() + " i: " + currentIndex.toString()); // needs loop
    }
    
	@Override public void exitEXPR(ExprParser.EXPRContext ctx) { }
    
    @Override 
    public void enterIMPLIES(ExprParser.IMPLIESContext ctx) { 
        java.util.List<ExprContext> e = ctx.expr();
        if(tree.addNode(new BinaryOperator(currentIndex, "->"))) {
        	System.out.println("Added root: " + tree.toString());
        	System.out.println("Length: " + currentIndex.length());
        	currentIndex.set(2);
        	System.out.println("Length: " + currentIndex.length());
        }
        
        System.out.println("IMPLIES -> " + e.get(0).getText() + ", " + e.get(1).getText() + " i: " + currentIndex.toString());
    }
    
	@Override 
	public void exitIMPLIES(ExprParser.IMPLIESContext ctx) {
		
	}
    
    @Override 
    public void enterNOT(ExprParser.NOTContext ctx) { 
    	ExprContext e = ctx.expr();
        System.out.println("NOT -> " + e.getChild(0).getText() + " i: " + currentIndex.toString()); 
    }
    
	@Override public void exitNOT(ExprParser.NOTContext ctx) { }
    
    @Override 
    public void enterBINOP_(ExprParser.BINOP_Context ctx) { 
        java.util.List<ExprContext> e = ctx.expr();
        System.out.println("BINOP -> " + e.get(0).getText() + ", " + e.get(1).getText() + " i: " + currentIndex.toString());
    }
    
    @Override 
    public void exitBINOP_(ExprParser.BINOP_Context ctx) {
    	System.out.println("Exit binop");
    }
    
    @Override 
    public void enterATOM_(ExprParser.ATOM_Context ctx) { 
    	TerminalNode e = ctx.ATOM();
        System.out.println("ATOM -> " + e.getText() + " i: " + currentIndex.toString()); 
    }
    
	@Override public void exitATOM_(ExprParser.ATOM_Context ctx) { }
}