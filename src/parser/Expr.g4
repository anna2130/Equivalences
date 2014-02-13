grammar Expr;	

prog: expr NEWLINE;

expr
	: '(' expr ')'
	| '!' expr
	| expr ('|' | '&') expr
	| expr '->' expr
	| ATOM
	| NEWLINE
	;
	
ATOM: ('a'..'z');

NEWLINE: [\r\n]+;