// Generated from Expr.g4 by ANTLR 4.1
package parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprParser#neg}.
	 * @param ctx the parse tree
	 */
	void enterNeg(@NotNull ExprParser.NegContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#neg}.
	 * @param ctx the parse tree
	 */
	void exitNeg(@NotNull ExprParser.NegContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExprParser#impl}.
	 * @param ctx the parse tree
	 */
	void enterImpl(@NotNull ExprParser.ImplContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#impl}.
	 * @param ctx the parse tree
	 */
	void exitImpl(@NotNull ExprParser.ImplContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExprParser#disj}.
	 * @param ctx the parse tree
	 */
	void enterDisj(@NotNull ExprParser.DisjContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#disj}.
	 * @param ctx the parse tree
	 */
	void exitDisj(@NotNull ExprParser.DisjContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExprParser#conj}.
	 * @param ctx the parse tree
	 */
	void enterConj(@NotNull ExprParser.ConjContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#conj}.
	 * @param ctx the parse tree
	 */
	void exitConj(@NotNull ExprParser.ConjContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExprParser#prop}.
	 * @param ctx the parse tree
	 */
	void enterProp(@NotNull ExprParser.PropContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#prop}.
	 * @param ctx the parse tree
	 */
	void exitProp(@NotNull ExprParser.PropContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExprParser#pos}.
	 * @param ctx the parse tree
	 */
	void enterPos(@NotNull ExprParser.PosContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#pos}.
	 * @param ctx the parse tree
	 */
	void exitPos(@NotNull ExprParser.PosContext ctx);
}