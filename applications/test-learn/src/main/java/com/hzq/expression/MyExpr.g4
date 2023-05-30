grammar MyExpr;
@header {package com.hzq.expression.myexpr;}

//词法一定在语法之前

/*
1. 词法规则中的词汇符号并不被语法规则所引用。
2. 在语法规则中使用了字符串字面量作为终结符，而不是使用词法规则中定义的词汇符号。
3. 在语法规则中使用了通配符，如“.”，不需要引用词法规则中的词汇符号。
4. 在某些版本的 ANTLR 中，词法规则在语法规则之后也可以正常解析。 
总之，虽然有些情况下在语法规则之后定义词法规则也可以正常解析，但是为了保险起见，建议还是按照规范的方式先定义词法规则，再定义语法规则。
*/
AND_: A N D;
OR_: O R;
NOT_: N O T;

COL_: ':' | '=';

HZQ_: H Z Q COL_;

stmt: expr;
expr:
    left=expr opt=AND_ right=expr               #andExpression
    | left=expr opt=OR_ right=expr               #orExpression
    | left=expr opt=NOT_ right=expr               #notExpression
    | left='(' exp=expr right=')'                       #subExpression
    | field=STR_ col=COL_ left='(' exp=expr right=')'   #domainExpression
    | STR_                                              #strExpression
    ;


STR_: [\u0080-\ufffeA-Za-z0-9]+;
SPECIAL_:['./_@%-&,;#?];

fragment A : [aA];
fragment N : [nN];
fragment D : [dD];
fragment O : [Oo];
fragment R : [Rr];
fragment H : [hH];
fragment Z : [Zz];
fragment Q : [Qq];
fragment T : [Tt];

WS: [ \t\r\n] ->skip;

