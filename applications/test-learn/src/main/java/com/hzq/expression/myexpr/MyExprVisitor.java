// Generated from D:/coderepo/spring-cloud-demo/applications/test-learn/src/main/java/com/hzq/expression\MyExpr.g4 by ANTLR 4.12.0
package com.hzq.expression.myexpr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MyExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MyExprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MyExprParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(MyExprParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code strExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrExpression(MyExprParser.StrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andOrExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOrExpression(MyExprParser.AndOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link MyExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubExpression(MyExprParser.SubExpressionContext ctx);
}