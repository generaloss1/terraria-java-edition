package com.myterraria.interfaces;

import java.util.ArrayList;
import java.util.List;

public class Item{

   public Animation animation;
   public int id;
   public List<Value> attributes;

    public Item(int id,FrameBuffer fb){
        this.id=id;
        animation=new Animation(fb);
        attributes=new ArrayList<>();
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
