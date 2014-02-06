package parser;

import java.util.List;
import java.util.ListIterator;
import java.lang.reflect.Method;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;



public class Compiler {
	public static void main(String args[]) {
		Compiler compiler = new Compiler();
		compiler.compile("100+2*34\n");
	}
	
	public RuleContext compile(String expr) {
		CharStream input = new ANTLRInputStream(expr);
		ExprLexer lexer = new ExprLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ExprParser parser = new ExprParser(tokens);
		
		//tokens.fill();
		
		//for(int i = 100; i > 0; --i)
		//	System.out.println(parser.consume());

		ParserRuleContext tree = parser.prog();
		
		System.out.println(tree.toStringTree(parser));
		
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
		
		System.out.println("Done");
		return null;
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