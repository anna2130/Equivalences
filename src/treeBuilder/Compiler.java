package treeBuilder;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import parser.ExprLexer;
import parser.ExprParser;
import parser.ExprWalker;
import treeManipulation.RuleApplicator;

public class Compiler {
	public static void main(String args[]) {
		Compiler compiler = new Compiler();
//		FormationTree tree = compiler.compile("!p|q->(p->q&r)");
		FormationTree tree = compiler.compile("(!p|(s&t))&q");
		System.out.println(tree);
		
//		RuleApplicator r = new RuleApplicator();
//		tree = r.applyLeftSimplification(tree, 0, 0);
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

		System.out.println("Done");
        return tree;
		
		//ParserRuleContext t = parser.prog();
		//System.out.println(t.toStringTree(parser));
		
		/*
		String startRuleName = "prog";
		String parserName = "ExprParser";
		
		Class<? extends Parser> parserClass = null; 
		 
		try
		{
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			parserClass = cl.loadClass(parserName).asSubclass(Parser.class);
			
			try {
				
				Method startRule = parserClass.getMethod(startRuleName);
				ParserRuleContext tree = (ParserRuleContext)startRule.invoke(parser, (Object[])null);
				
				System.out.println(tree.toStringTree(parser));
			}
			catch (NoSuchMethodException nsme) {
				System.err.println("No method for rule "+startRuleName+" or it has arguments");
			}
			catch (Exception e)
			{
				System.err.println("Oh fuck: " + e.getMessage());
			}
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("Error: " + e.getMessage());
		}*/
	}
	
	/*protected void process(Lexer lexer, Class<? extends Parser> parserClass, Parser parser, InputStream is, Reader r) throws IOException, IllegalAccessException, InvocationTargetException, PrintException {
		try {
			ANTLRInputStream input = new ANTLRInputStream(r);
			lexer.setInputStream(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);

			tokens.fill();

			if ( showTokens ) {
				for (Object tok : tokens.getTokens()) {
					System.out.println(tok);
				}
			}

			if ( startRuleName.equals(LEXER_START_RULE_NAME) ) return;

			if ( diagnostics ) {
				parser.addErrorListener(new DiagnosticErrorListener());
				parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);
			}

			if ( printTree || gui || psFile!=null ) {
				parser.setBuildParseTree(true);
			}

			if ( SLL ) { // overrides diagnostics
				parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
			}

			parser.setTokenStream(tokens);
			parser.setTrace(trace);

			try {
				Method startRule = parserClass.getMethod(startRuleName);
				ParserRuleContext tree = (ParserRuleContext)startRule.invoke(parser, (Object[])null);

				if ( printTree ) {
					System.out.println(tree.toStringTree(parser));
				}
				if ( gui ) {
					tree.inspect(parser);
				}
				if ( psFile!=null ) {
					tree.save(parser, psFile); // Generate postscript
				}
			}
			catch (NoSuchMethodException nsme) {
				System.err.println("No method for rule "+startRuleName+" or it has arguments");
			}
		}
		finally {
			if ( r!=null ) r.close();
			if ( is!=null ) is.close();
		}
	}*/
}