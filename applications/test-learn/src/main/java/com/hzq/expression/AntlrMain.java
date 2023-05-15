package com.hzq.expression;

import com.hzq.expression.myexpr.MyExprLexer;
import com.hzq.expression.myexpr.MyExprParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

/**
 * @author Huangzq
 * @description
 * @date 2023/5/10 20:22
 */
public class AntlrMain {

    public static void main(String[] args) {
        MyExprLexer testLexer = new MyExprLexer(CharStreams.fromString("(啊 or 人) and asd"));
        CommonTokenStream commonTokenStream = new CommonTokenStream(testLexer);
        MyExprParser testParser = new MyExprParser(commonTokenStream);

        MyExprParser.ExprContext expr = testParser.expr();

        parser(expr);

        System.out.println(expr.toStringTree());
    }

    private static void parser(MyExprParser.ExprContext exprContext){
        if (exprContext instanceof MyExprParser.AndOrExpressionContext){
            MyExprParser.AndOrExpressionContext andOrExpressionContext = (MyExprParser.AndOrExpressionContext) exprContext;
            MyExprParser.ExprContext left = andOrExpressionContext.left;
            MyExprParser.ExprContext right = andOrExpressionContext.right;
            Token opt = andOrExpressionContext.opt;

            parser(left);
            parser(right);

            System.out.println("AndOrExpr 组装");

        }else if(exprContext instanceof MyExprParser.SubExpressionContext){
            MyExprParser.SubExpressionContext subExpressionContext = (MyExprParser.SubExpressionContext) exprContext;
            MyExprParser.ExprContext exp = subExpressionContext.exp;
            parser(exp);
            System.out.println("SubExpr 组装");
        }else if(exprContext instanceof MyExprParser.StrExpressionContext){
            MyExprParser.StrExpressionContext strExpressionContext = (MyExprParser.StrExpressionContext) exprContext;
            TerminalNode terminalNode = strExpressionContext.STR_();
            System.out.println("TerminalNode:"+terminalNode.getText());
        }else {
            System.out.println("不支持");
        }
    }


}
