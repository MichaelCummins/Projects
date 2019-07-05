#Compiler

This project is a recursive descent parser that uses a LL(1) grammar.

Program ::== Block $  
Block ::== { StatementList}  
StatementList ::== Statement StatementList  
              ::== Epsilon  
Statement ::== PrintStatement  
          ::== AssignmentStatement  
          ::== VarDecl  
          ::== WhileStatement  
          ::== IfStatement  
          ::== Block  
PrintStatement ::== print (Expr)  
AssignmentStatement ::== Id = Expr  
WhileStatement ::== while BooleanExpr Block  
IfStatement ::== if BooleanExpr Block  
Expr ::== IntExpr  
     ::== StringExpr  
     ::== BooleanExpr  
     ::== Id  
IntExpr ::== digit intop Expr  
        ::== digit  
StringExpr ::== " CharList "  
BooleanExpr ::== (Expr boolop Expr)  
            ::== boolval  
Id ::== char  
CharList ::== char CharList  
         ::== space CharList  
         ::== Epsilon  
type ::== int | string | boolean  
char ::== a | b | c | ... | z  
space ::== the space character  
digit ::== 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 0  
boolop ::== == | !=  
boolval ::== false | true  
intop ::== +  

== is test for equality  
= is assignment  
Curly braces denote new scope  
Comments are bounded by /* and are ignored by the lexer */  
