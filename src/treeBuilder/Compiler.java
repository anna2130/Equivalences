package treeBuilder;

import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import parser.ExprLexer;
import parser.ExprParser;
import parser.ExprWalker;
import treeManipulation.RuleApplicator;
import treeManipulation.RuleSelector;

public class Compiler {
	public static void main(String args[]) {
		Compiler compiler = new Compiler();
		String s = "(r&q)&(p&s)";
//		FormationTree tree = compiler.compile("!p|q->(p->q&r)");
//		FormationTree tree = compiler.compile("(!p|(s&t))&q");
//		FormationTree tree = compiler.compile("q&(!p|(s&t))");
//		FormationTree tree = compiler.compile("((r->s)&(!p&q))|t");
		FormationTree tree = compiler.compile(s);
		System.out.println(tree);
		
		Node node = tree.findNode(0, 0);
		
		RuleSelector rs = new RuleSelector();
		BitSet bs = rs.getApplicableRules(tree, node);
		System.out.println(rs.rulesToString(bs, tree, node));
		
		System.out.println(tree);
		
//		RuleApplicator ra = new RuleApplicator();
//		ra.applyAndSimplification(tree, 0, 1);
//		System.out.println(tree);
	}
	
	public FormationTree compile(String expr) {
		CharStream input = new ANTLRInputStream(expr);
		ExprLexer lexer = new ExprLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ExprParser parser = new ExprParser(tokens);
		
		FormationTree tree = new FormationTree();
		
		ParseTree parseTree = parser.prog();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new ExprWalker(tree), parseTree);

        return tree;
	}
}