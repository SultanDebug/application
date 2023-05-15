grammar Test;

@header {
    package com.hzq.expression.parser;
}

stmt: expr;
expr:
    andOrExpr         #expression
//    | strExpr         #strExpression
    ;

andOrExpr:
    STR_ AND_ STR_
    ;
AND_: A N D;
//strExpr: STR_;

STR_: [\u0080-\ufffeA-Za-z0-9]+;

fragment A : [aA];
fragment N : [nN];
fragment D : [dD];


WS : [ \t\u000C\r\n]+ -> skip;

SHEBANG : '#' '!' ~('\n'|'\r')* -> channel(HIDDEN);