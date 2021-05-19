package engine.tiledmap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileData{

	public int data;
	public TextureRegion texture;
	public float width,height,offset_x,offset_y;
	
	public TileData(int tdata,TextureRegion t,float w,float h,float ox,float oy){
		data=tdata;
		texture=t;
		width=w;
		height=h;
		offset_x=ox;
		offset_y=oy;
	}

}
