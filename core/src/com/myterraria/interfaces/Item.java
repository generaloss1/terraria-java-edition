package com.myterraria.interfaces;

import java.util.ArrayList;
import java.util.List;

public class Item{

   public Animation animation;
   public int id;
   public List<Value> attributes;
   public float ox,oy,w,h;

    public Item(int id,FrameBuffer fb){
        this.id=id;
        animation=new Animation(fb);
        attributes=new ArrayList<>();

        ox=0f;
        oy=0f;
        w=1f;
        h=1f;
    }

    public Item(int id,FrameBuffer fb,float size){
        this.id=id;
        animation=new Animation(fb);
        attributes=new ArrayList<>();

        ox=size/2;
        oy=size/2;
        w=size;
        h=size;
    }

    public Item(int id,FrameBuffer fb,float x,float y,float ow,float oh){
        this.id=id;
        animation=new Animation(fb);
        attributes=new ArrayList<>();

        ox=x;
        oy=y;
        w=ow;
        h=oh;
    }

    public Item(int id,Animation a){
        this.id=id;
        animation=a;
        attributes=new ArrayList<>();
    }

    public Item addAttribute(Value v){
        attributes.add(v);
        return this;
    }

}
