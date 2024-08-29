package org.unp.plp.interprete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;

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

  public void crear(int x, int y) {
    field = new String[x][y];
    this.filas = x;
    this.columnas = y;
  }

  public void put(String item, Set<Cell> cells) {
    for (Cell cell : cells) {
      field[cell.getRow()][cell.getColumn()] = item;
    }
  }

  public void put(String item, Cell cell) {
    // buscar el viejo
    Cell last_ubi = uniqueItems.get(item);
    if (last_ubi != null) {
      field[last_ubi.getRow()][last_ubi.getColumn()] = null;
    }
    field[cell.getRow()][cell.getColumn()] = item;
    uniqueItems.put(item, cell);
  }

  public void rem(String item, Set<Cell> cells) {
    for (Cell cell : cells) {
      if (field[cell.getRow()][cell.getColumn()].equals(item))
        field[cell.getRow()][cell.getColumn()] = null;
    }
  }

  public void rem(String item, Cell cell) {
    // buscar el viejo
    Cell last_ubi = uniqueItems.get(item);
    if (last_ubi != null) {
      field[last_ubi.getRow()][last_ubi.getColumn()] = null;
      uniqueItems.remove(item);
    }
  }
  

  public void print() {
    System.out.println("world" + "," + this.filas + "," + this.columnas);
    for (int i = 0; i < field.length; i++) {
      for (int j = 0; j < field[i].length; j++) {
        if (field[i][j]!=null)
        System.out.println(field[i][j] + "," + (i + 1) + "," + (j + 1));
      }
    }
  }

  public int[][] getConstant(int constant) {
    int[][] matrix = new int[this.filas][this.columnas];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        matrix[i][j] = constant;
      }
    }
    return matrix;
  }

  public int[][] getI() {
    int[][] matrix = new int[this.filas][this.columnas];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        matrix[i][j] = i + 1;
      }

    }
    return matrix;

  }

  public int[][] getJ() {
    int[][] matrix = new int[this.filas][this.columnas];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        matrix[i][j] = j + 1;
      }
    }

    return matrix;

  }


  public int[][] applyBinaryOperator(int[][] matrix1, int[][] matrix2, BinaryOperator<Integer> operator) {
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = operator.apply(matrix1[i][j], matrix2[i][j]);
            }
        }
        return result;
    }


    public Set<Cell> applyBiPredicate(int[][] matrix1, int[][] matrix2, BiPredicate<Integer,Integer> operator) {
      Set<Cell> result = new HashSet<>();
      int rows = matrix1.length;
      int cols = matrix1[0].length;

      for (int i = 0; i < rows; i++) {
          for (int j = 0; j < cols; j++) {
            if (operator.test(matrix1[i][j], matrix2[i][j]))
              result.add(new Cell(i, j));
          }
      }
      return result;
  }
  /*
   * i
   * 1 1 1 1
   * 2 2 2 2
   * 3 3 3 3
   * 4 4 4 4
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
   * metodo con binary operator para ><== y todo eso
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
   */

}
