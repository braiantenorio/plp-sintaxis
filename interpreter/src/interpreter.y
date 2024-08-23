// fuente byaccj para una calculadora sencilla


%{
  import java.io.*;
  import java.util.List;
  import java.util.ArrayList;
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
  : CONSTANT NL {System.out.println("constante: "+ $1); $$ = $1;}
  | PUT item IN celdas NL {System.out.println("put de: "+ $2);}
  | REM item IN celdas NL {System.out.println("rem de: "+ $2);}
  | PUT unique_item IN celda NL {System.out.println("put de item unico");} 
  | REM unique_item IN celda NL {System.out.println("rem de item unico");}
  | 'print' WORLD NL {world.print();}
  ; 

celdas
  : '[' CONSTANT ',' CONSTANT ']'
  | '[' aux ',' aux ':' condition_list']'
  ;

item : PIT ;

unique_item : GOLD | HERO | WUMPUS ;

aux: '?' | CONSTANT ;

condition_list : condition ',' condition_list
  | condition ;

relation
  : expr op_rel expr
  ;

expr
  : expr op_add term
  | term
  ;

term 
  : term op_mult factor
  | factor
  ;

factor
  : CONSTANT
  | var
  ;

var : 'i' | 'j';
op_add : 'x' | '-' ; 
op_mult : '*' | '/' ;
op_rel : '==' | '<' ;

world_statement
  : WORLD CONSTANT 'x' CONSTANT NL {world.crear((int)$2,(int)$4);}
  ;

%%

  /** referencia al analizador léxico
  **/
  private Lexer lexer ;

  private WumpusWorld world;

  /** constructor: crea el Interpreteranalizador léxico (lexer)
  **/
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

  /** invocada cuando se produce un error
  **/
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
