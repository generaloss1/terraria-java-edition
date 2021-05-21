package com.myterraria.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import engine.Assets;
import engine.Camera2D;

public class Inventory implements Interface{

    public int cell_size;
    public int width,height;
    public int offset_x,offset_y;
    public int x,y;

    public ItemStack[][] items;


    public Inventory(Camera2D cam,int w,int h){
        width=w;
        height=h;
        cell_size=Math.round(cam.height/20);
        offset_x=Math.round((cell_size*1.5f)/10.5f);
        offset_y=offset_x;

        x=cell_size/2;
        y=Math.round(cam.height)-offset_y*height-cell_size*height-cell_size;

        items=new ItemStack[width][height];
    }


    public void draw(SpriteBatch sb,Camera2D cam){
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                sb.setColor(1,1,1,0.9f);
                sb.draw(Assets.getTexture("Inventory_Back"),cam.x+x+cell_size*i+offset_x*i,cam.y+y+cell_size*j+offset_y*j,cell_size,cell_size);
                sb.setColor(1,1,1,1);
            }
        }
    }

    public void setItem(ItemStack item,int x,int y){
        items[x][y]=item;
    }

}
