package com.myterraria.interfaces;

import java.util.HashMap;

public class ItemManager{

    public static HashMap<Integer,Item> items=new HashMap<>();

    public static void defineItem(int id,FrameBuffer fb){
        items.put(id,new Item(id,fb));
    }

    public static void defineItem(Item i) {
        items.put(i.id,i);
    }

    public static Value getTag(int id,String tag_id){
        return ItemManager.getItem(id).tags.get(tag_id);
    }

    public static Item getItem(int id){
        return items.get(id);
    }

}
