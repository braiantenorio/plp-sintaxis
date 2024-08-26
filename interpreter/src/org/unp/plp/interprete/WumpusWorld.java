package org.unp.plp.interprete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WumpusWorld {

  private Map<String, Cell> uniqueItems = new HashMap<>();
  private String[][] field;
  private int filas;
  private int columnas;

  public List<String> vecinos(Cell cell) {
    List<String> vecinos = new ArrayList<>();
    vecinos.add(field[cell.getRow() - 1][cell.getColumn()]);
    vecinos.add(field[cell.getRow()][cell.getColumn() - 1]);
    vecinos.add(field[cell.getRow()][cell.getColumn() + 1]);
    vecinos.add(field[cell.getRow() + 1][cell.getColumn()]);
    return vecinos;
  }

  //mejor saquemos esto
  public void crear(int x, int y) {
    field = new String[x + 2][y + 2]; // a todas las cells habria que sumarle +1,+1

    // llenamos lados de bup
    // TODO: REVISAR
   /* 
    for (int i = 0; i < x; i++) {
      field[0][i] = "bup";
    }

    for (int i = 0; i < y; i++) {
      field[y][i] = "bup";
    }

    for (int i = 0; i < x; i++) {
      field[i][0] = "bup";
   }

    for (int i = 0; i < y; i++) {
      field[i][x] = "bup";
    }
*/

  }

  //maybe sumar 1 a las filas y 1  a las columnas por las paredes?
  public void put(String item, Set<Cell> cells) {
    for (Cell cell : cells) {
      field[cell.getRow()][cell.getColumn()] = item;
    }
  }

  public void put(String item, Cell cell) {
    // buscar el viejo
    Cell last_ubi = uniqueItems.get(item);
    if(last_ubi!=null){
      field[last_ubi.getRow()][last_ubi.getColumn()] = null; // TODO: null o ""
    }
    field[cell.getRow()][cell.getColumn()] = item;
    uniqueItems.put(item, cell);
    field[last_ubi.getRow()][last_ubi.getColumn()] = item; 
  }

  public void print(){
    for (String[] strings : field) {
      for (String string : strings) {
        if (!string.equals("bup")) 
          System.out.println(string);
      }     
    }
  }



  public int[][] getConstant(int constant) {
    int[][] matrix =  new int[this.columnas][this.filas];
    for (int[] aux : matrix) {
      for (int x : aux) {
        x= constant;
      }

    }
    return matrix;
  }

  public int[][] getI() {
    int[][] matrix =  new int[this.columnas][this.filas];
    for (int[] aux : matrix) {
      for (int i = 0; i < this.filas; i++) {
        aux[i] = i+1;
      }

    }
    return matrix;
    
  }

  public int[][] getJ() {
    int[][] matrix =  new int[this.columnas][this.filas];
    for (int[] aux : matrix) {
      for (int x : aux) {
      }

    }
    return matrix;
    
  }



  /*
   * i
   * 1  1 1 1
   * 2  2 2 2
   * 3  3 3 3
   * 4  4 4 4
   * 
   * j
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   * 
   *  metodo con binary operator para ><== y todo eso
   * izq y der son matrices
   * 
   * 2 for para ambas matrices y un if.
   * en el if le pusheo a un set un new cell
   * 
   * bipredicate que llama a test
   * 
   * el otro usar binaryoperator que llama apply
   * 
   * 
   * 
   * 
   * tambien hacer un metodo para la suma pero que haga operaciones de +*-
   * */


}
