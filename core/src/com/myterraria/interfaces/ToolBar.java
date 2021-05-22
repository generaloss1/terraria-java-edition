package com.myterraria.interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import engine.Assets;
import engine.Camera2D;
import engine.tiledmap.TileManager;

public class ToolBar implements Interface{

    public ToolBar(Camera2D cam,int s){
        size=s;
        cell_size=Math.round(cam.height/12);
        offset=Math.round((cell_size*1.5f)/10.5f);

        x=cell_size/2;


        items=new ItemStack[size];
    }

    public int cell_size;
    public int size;
    public int offset;
    public int x,y;
    public int selected_cell_position;

    public ItemStack[] items;


    public void draw(SpriteBatch sb,Camera2D cam){
        y=Math.round(cam.height)-offset-cell_size-cell_size;
        for(int i=0; i<10; i++)
            if(Gdx.input.isKeyJustPressed(i==9?7:i+8))
                selected_cell_position=i;

        for(int i=0; i<size; i++){
            sb.setColor(1,1,1,0.9f);
            sb.draw(Assets.getTexture("Inventory_Back"+(i==selected_cell_position?"14":"")),cam.x+x+cell_size*i+offset*i,cam.y+y,cell_size,cell_size);
            sb.setColor(1,1,1,1);
            ItemStack itemStack=getItem(i);
            if(itemStack!=null){
                Item item=ItemManager.getItem(itemStack.id);
                if(item!=null){
                    FrameBuffer fb=item.frameBuffer;
                    if(fb!=null){
                        TextureRegion texture=fb.getFrame(0);
                        if(texture!=null)
                            sb.draw(texture,cam.x+x+cell_size*i+offset*i,cam.y+y,cell_size,cell_size);
                    }
                }
            }
        }
    }

    public void setItem(ItemStack item,int x){
        items[x]=item;
    }

    public ItemStack getSelectedItem(){
        return items[selected_cell_position];
    }

    public ItemStack getItem(int position){
        return items[position];
    }

}
