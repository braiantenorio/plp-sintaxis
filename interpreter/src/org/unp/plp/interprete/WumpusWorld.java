package org.unp.plp.interprete;


public class WumpusWorld {
	
	// Aquí va el código del mundo

	static private Entity[][] world;

	public String setWorld(int x, int y) {
		world = new Entity[x][y];
		return "Mundo seteado correctamente";
	}

	public String addGold(int x, int y) {
		return "Oro agregado correctamente";
	}
	public String addHero(int x, int y) {
		return "Heroe agregado correctamente";
		
	}
	public String addPit(int x, int y) {
		return "Pit agregado correctamente";
		
	}
	public String addWumpus(int x, int y) {
		return "Wumpus agregado correctamente";
		
	}


}
