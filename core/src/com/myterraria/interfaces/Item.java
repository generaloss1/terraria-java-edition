package com.myterraria.interfaces;

import java.util.HashMap;

public class Item{

   public Animation animation;
   public int id;
   public HashMap<String,Value> tags;
   public float ox,oy,w,h;

    public Item(int id,FrameBuffer fb){
        this.id=id;
        animation=new Animation(fb);
        tags=new HashMap<>();

        ox=0f;
        oy=0f;
        w=1f;
        h=1f;
    }

    public Item(int id,FrameBuffer fb,float size){
        this.id=id;
        animation=new Animation(fb);
        tags=new HashMap<>();

        float k=(float)fb.getFrame(0).getRegionWidth()/fb.getFrame(0).getRegionHeight();
        ox=0.5f-size/2*k;
        oy=0.5f-size/2;
        w=size*k;
        h=size;
    }

    public Item(int id,FrameBuffer fb,float x,float y,float ow,float oh){
        this.id=id;
        animation=new Animation(fb);
        tags=new HashMap<>();

        ox=x;
        oy=y;
        w=ow;
        h=oh;
    }

    public Item(int id,Animation a){
        this.id=id;
        animation=a;
        tags=new HashMap<>();
    }

    public Item addTag(String id,Value v){
        tags.put(id,v);
        return this;
    }

}
