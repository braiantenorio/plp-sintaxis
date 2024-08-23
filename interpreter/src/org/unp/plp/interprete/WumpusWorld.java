package org.unp.plp.interprete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WumpusWorld {

  private Map<String, Cell> unique_items = new HashMap<>();
  private String[][] campo;

  public List<String> vecinos(Cell cell) {
    List<String> vecinos = new ArrayList<>();

    vecinos.add(campo[cell.getRow() - 1][cell.getColumn()]);
    vecinos.add(campo[cell.getRow()][cell.getColumn() - 1]);
    vecinos.add(campo[cell.getRow()][cell.getColumn() + 1]);
    vecinos.add(campo[cell.getRow() + 1][cell.getColumn()]);
    return vecinos;
  }

  public void crear(int x, int y) {
    campo = new String[x + 2][y + 2]; // a todas las cells habria que sumarle +1,+1

    // TODO: REVISAR
    for (int i = 0; i < x; i++) {
      campo[0][i] = "bup";
    }

    for (int i = 0; i < y; i++) {
      campo[y][i] = "bup";
    }

    for (int i = 0; i < x; i++) {
      campo[i][0] = "bup";
    }

    for (int i = 0; i < y; i++) {
      campo[i][x] = "bup";
    }

    // llenar lados con bups
  }

  public void put(String item, Set<Cell> cells) {

  }

  public void put(Item item, Cell cells) {

  }

  public void print(){
    for (String[] strings : campo) {
      for (String string : strings) {
        if (!string.equals("bup")) {
          
          System.out.println(string);
        }
        
      }  
      
    }
  }

}
