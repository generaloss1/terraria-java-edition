package engine.tiledmap;

import java.util.*;

public class Tile{

	public int id;
	public HashMap<Integer,TileData> datas=new HashMap<>();

	public Tile(int tid){
		id=tid;
	}
	
}
