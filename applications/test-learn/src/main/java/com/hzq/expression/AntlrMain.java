package com.hzq.expression;

import com.google.common.collect.Lists;
import com.hzq.expression.myexpr.MyExprLexer;
import com.hzq.expression.myexpr.MyExprParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Huangzq
 * @description
 * @date 2023/5/10 20:22
 */
public class AntlrMain {

    public static void main(String[] args) {
        MyExprLexer testLexer = new MyExprLexer(CharStreams.fromString("c not (d or (h not(x or z)))"));
        CommonTokenStream commonTokenStream = new CommonTokenStream(testLexer);
        MyExprParser testParser = new MyExprParser(commonTokenStream);

        MyExprParser.ExprContext expr = testParser.expr();

        List<String> list = parser(expr);

        System.out.println(expr.toStringTree());
        System.out.println(list);
    }

    private static List<String> parser(MyExprParser.ExprContext exprContext){
        List<String> list = new ArrayList<>();
        if (exprContext instanceof MyExprParser.AndExpressionContext){
            MyExprParser.AndExpressionContext andOrExpressionContext = (MyExprParser.AndExpressionContext) exprContext;
            MyExprParser.ExprContext left = andOrExpressionContext.left;
            MyExprParser.ExprContext right = andOrExpressionContext.right;
            Token opt = andOrExpressionContext.opt;
            TerminalNode and = andOrExpressionContext.AND_();

            List<String> leftList =  parser(left);
            List<String> rightList =  parser(right);

            Set<String> sets = new HashSet<>();
            for (String leftStr : leftList) {
                for (String rightStr : rightList) {
                    sets.add(leftStr+","+rightStr);
                }
            }
            list.addAll(sets);

            System.out.println("AndExpr 组装"+opt.getText());

        }else if (exprContext instanceof MyExprParser.OrExpressionContext){
            MyExprParser.OrExpressionContext andOrExpressionContext = (MyExprParser.OrExpressionContext) exprContext;
            MyExprParser.ExprContext left = andOrExpressionContext.left;
            MyExprParser.ExprContext right = andOrExpressionContext.right;
            Token opt = andOrExpressionContext.opt;
            TerminalNode or = andOrExpressionContext.OR_();

            List<String> leftList =  parser(left);
            List<String> rightList =  parser(right);

            Set<String> sets = new HashSet<>(leftList);
            sets.addAll(rightList);

            list.addAll(sets);

            System.out.println("OrExpr 组装"+opt.getText());

        }else if (exprContext instanceof MyExprParser.NotExpressionContext){
            MyExprParser.NotExpressionContext andOrExpressionContext = (MyExprParser.NotExpressionContext) exprContext;
            MyExprParser.ExprContext left = andOrExpressionContext.left;
            MyExprParser.ExprContext right = andOrExpressionContext.right;
            Token opt = andOrExpressionContext.opt;
            TerminalNode or = andOrExpressionContext.NOT_();

            List<String> leftList =  parser(left);
            List<String> rightList =  parser(right);

            Set<String> sets = new HashSet<>();

            String testStr = "";
            Set<String> rightSets = new HashSet<>();
            for (String rightStr : rightList) {
                String[] split = rightStr.split(",");

                if(CollectionUtils.isEmpty(rightSets)){
                    if(split.length==1){
                        rightSets.add(split[0].contains("!") ? split[0].replace("!","") : "!"+split[0]);
                    }else{
                        rightSets.addAll(Arrays.stream(split).map(o->o.contains("!") ? o.replace("!","") : "!"+o).collect(Collectors.toSet()));
                    }
                }else{
                    Set<String> tmpSets = new HashSet<>();
                    for (String rightSet : rightSets) {
                        for (String s : split) {
                            String s1 = s.contains("!") ? s.replace("!", "") : ("!" + s);
                            tmpSets.add(rightSet+","+s1);
                        }
                    }
                    rightSets = tmpSets;
                }


                testStr += StringUtils.isEmpty(testStr)? "!("+rightStr+")" :",!("+rightStr+")";
            }

            System.out.println(testStr);


            for (String leftStr : leftList) {
                for (String rightSet : rightSets) {
                    sets.add(leftStr+","+rightSet);
                }
            }
            list.addAll(sets);

            System.out.println("NotExpr 组装"+opt.getText());

        }else if(exprContext instanceof MyExprParser.SubExpressionContext){
            MyExprParser.SubExpressionContext subExpressionContext = (MyExprParser.SubExpressionContext) exprContext;
            MyExprParser.ExprContext exp = subExpressionContext.exp;
            System.out.println("SubExpr 组装");
            return parser(exp);
        }else if(exprContext instanceof MyExprParser.StrExpressionContext){
            MyExprParser.StrExpressionContext strExpressionContext = (MyExprParser.StrExpressionContext) exprContext;
            TerminalNode terminalNode = strExpressionContext.STR_();
            System.out.println("TerminalNode:"+strExpressionContext.getText());
            return Lists.newArrayList(terminalNode.getText());
        }else {
            System.out.println("不支持");
        }

        return list;
    }


}
