// Generated from D:/coderepo/spring-cloud-demo/applications/test-learn/src/main/java/com/hzq/expression\MyExpr.g4 by ANTLR 4.12.0
package com.hzq.expression.myexpr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MyExprParser}.
 */
public interface MyExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MyExprParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(MyExprParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyExprParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(MyExprParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(MyExprParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(MyExprParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(MyExprParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(MyExprParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code strExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStrExpression(MyExprParser.StrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code strExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStrExpression(MyExprParser.StrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(MyExprParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(MyExprParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code domainExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDomainExpression(MyExprParser.DomainExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code domainExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDomainExpression(MyExprParser.DomainExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSubExpression(MyExprParser.SubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSubExpression(MyExprParser.SubExpressionContext ctx);
}