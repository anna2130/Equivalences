grammar Expr;	

prop: impl;

//equiv: impl '<->' impl;				//p <=> q   becomes  Equivalent (P, Q)

impl: disj ('->' impl)+
	| disj;								//p => q    becomes  Implies (P, Q)

disj: conj ('|' disj)+
	| conj;								//p | q    becomes  Or (P, Q)

conj: neg ('&' conj)+
	| neg;								//p & q    becomes  And (P, Q)

neg: '!' pos | pos; 					//! p      becomes  Not (P)

pos: IDENT | '(' prop ')';				//ident   becomes  Literal (true, ident)

IDENT: ('a'..'z');