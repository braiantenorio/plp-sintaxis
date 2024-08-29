// fuente byaccj para una calculadora sencilla

%{
  import java.io.*;
  import java.util.List;
  import java.util.ArrayList;
  import java.util.Set;
  import java.util.HashSet;
  import java.util.function.BinaryOperator;
  import java.util.function.BiPredicate;
%}


// lista de tokens por orden de prioridad

%token NL         // nueva línea
%token CONSTANT   // constante
%token WORLD
%token PUT
%token REM
%token PIT
%token GOLD
%token HERO
%token WUMPUS
%token X
%token IN
%token LBRACKET
%token RBRACKET
%token COMMA
%token PRINT
%token EQ  // ==
%token GEQ // >=
%token LEQ // <=

%%

program
  : world_statement statement_list        // Lista de sentencias
  |                                       // Programa vacio
  ;

statement_list
  : statement                // Unica sentencia
  | statement statement_list // Sentencia,y lista
  ;

statement
  : PUT item IN celdas NL {world.put((String)$2,(Set<Cell>)$4);}
  | REM item IN celdas NL {world.rem((String)$2,(Set<Cell>)$4);}
  | PUT unique_item IN celda NL {world.put((String)$2,(Cell)$4);} 
  | REM unique_item IN celda NL {world.rem((String)$2,(Cell)$4);}
  | PRINT WORLD NL {world.print();}
  ; 

celdas
  : '[' CONSTANT ',' CONSTANT ']' { Cell celda = new Cell(((int)$2)-1,((int)$4)-1);Set<Cell> set = new HashSet<>();set.add(celda); $$= set;}
  | '[' aux ',' aux ':' relation_list ']' {$$= $6;}
  ;

celda
  : '[' CONSTANT ',' CONSTANT ']' { $$= new Cell(((int)$2)-1,((int)$4)-1);}
  ;

item : PIT ;

unique_item : GOLD | HERO | WUMPUS ;

aux: '?' | CONSTANT ;

relation_list 
  : relation ',' relation_list { 
      Set<Cell> result = (Set<Cell>)$1;
      result.retainAll((Set<Cell>)$3);
      $$ = result;
    }
  | relation {$$ = $1;}
  ;

relation
  : expr op_rel expr {$$ = world.applyBiPredicate((int[][])$1,(int[][])$3,(BiPredicate<Integer,Integer>)$2);}
  ;

expr
  : expr op_add term {$$ = world.applyBinaryOperator((int[][])$1,(int[][])$3,(BinaryOperator<Integer>)$2);}
  | term
  ;

term 
  : term op_mult factor {$$ = world.applyBinaryOperator((int[][])$1,(int[][])$3,(BinaryOperator<Integer>)$2);}
  | factor
  ;

factor
  : CONSTANT { $$ = world.getConstant((int)$1);}
  | var
  ;

var 
  : 'i' {$$ = world.getI();}
  | 'j' {$$= world.getJ();}
  ;

op_add
  : '+' { $$ = Operators.ADD; }
  | '-' { $$ = Operators.SUBTRACT; }
  ;

op_mult
  : '*' { $$ = Operators.MULTIPLY; }
  | '/' { $$ = Operators.DIVIDE; }
  ;

op_rel
  : EQ { $$ = Operators.EQUALS; }
  | '<'  { $$ = Operators.LESS_THAN; }
  | '>'  { $$ = Operators.GREATER_THAN; }
  | GEQ { $$ = Operators.GREATER_THAN_OR_EQUALS; }
  | LEQ { $$ = Operators.LESS_THAN_OR_EQUALS; }
  ;

world_statement
  : WORLD CONSTANT 'x' CONSTANT NL {world.crear((int)$2,(int)$4);}
  ;

%%

  /** referencia al analizador léxico **/
  private Lexer lexer ;

  private WumpusWorld world;

  /** constructor: crea el Interpreteranalizador léxico (lexer) **/
  public Parser(Reader r)
  {
     lexer = new Lexer(r, this);
     world = new WumpusWorld();
  }

  /** esta función se invoca por el analizador cuando necesita el
  *** siguiente token del analizador léxico
  **/
  private int yylex ()
  {
    int yyl_return = -1;

    try
    {
       yylval = new Object();
       yyl_return = lexer.yylex();
    }
    catch (IOException e)
    {
       System.err.println("error de E/S:"+e);
    }

    return yyl_return;
  }

  /** invocada cuando se produce un error **/
  public void yyerror (String descripcion, int yystate, int token)
  {
     System.err.println ("Error en línea "+Integer.toString(lexer.lineaActual())+" : "+descripcion);
     System.err.println ("Token leído : "+yyname[token]);
  }

  public void yyerror (String descripcion)
  {
     System.err.println ("Error en línea "+Integer.toString(lexer.lineaActual())+" : "+descripcion);
     //System.err.println ("Token leido : "+yyname[token]);
  }
