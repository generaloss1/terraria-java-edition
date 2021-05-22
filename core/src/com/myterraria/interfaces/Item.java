package com.myterraria.interfaces;

import java.util.ArrayList;
import java.util.List;

public class Item{

   public FrameBuffer frameBuffer;
   public int id;
   public List<Value> attributes;

    public Item(int id,FrameBuffer fb){
        this.id=id;
        frameBuffer=fb;
        attributes=new ArrayList<>();
    }

    public Item addAttribute(Value v){
        attributes.add(v);
        return this;
    }

}
