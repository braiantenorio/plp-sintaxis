package org.unp.plp.interprete;


public class WumpusWorld {
	
	// Aquí va el código del mundo

	static private Entity[][] world;

	public String setWorld(int x, int y) {
		world = new Entity[x][y];
		return "Mundo seteado correctamente";
	}

}
