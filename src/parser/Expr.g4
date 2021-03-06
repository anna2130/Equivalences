grammar Expr;	

prog: expr;

expr: '(' expr ')' 			#EXPR
	| '!' expr				#NOT
	| expr BINOP expr		#BINOP_
	| expr '->' expr		#IMPLIES
	| ATOM					#ATOM_
	;

BINOP: ('&' | '|');
ATOM:  ('a'..'z');