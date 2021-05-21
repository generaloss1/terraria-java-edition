package com.myterraria.interfaces;

import java.util.HashMap;

public class ItemManager{

    public static HashMap<Integer,Item> items=new HashMap<>();

    public static void defineItem(int id,FrameBuffer fb){
        items.put(id,new Item(id,fb));
    }

}
