package com.myterraria;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.myterraria.interfaces.FrameBuffer;
import com.myterraria.interfaces.Item;
import com.myterraria.interfaces.ItemManager;
import com.myterraria.interfaces.Value;
import engine.Assets;
import engine.tiledmap.TileManager;

import java.util.ArrayList;

public class LoadResources{

    public static void loadResources(){
        Pixmap pixmap=new Pixmap(1,1,Pixmap.Format.RGBA4444);
        pixmap.setColor(0,0,0,1);
        pixmap.fill();
        Assets.loadTexture(new Texture(pixmap),"black_pixel");

        //FONTS

        Assets.loadTTF("fonts/FiraCode.ttf","font1",48,new Color(1,1,1,1));
        Assets.loadTTF("fonts/FiraCodeBold.ttf","font2",32,new Color(0.5f,0.5f,0.5f,1));
        Assets.loadTTF("fonts/andy_bold.ttf","font3",32,new Color(0.5f,0.5f,0.5f,1));
        Assets.loadTTF("fonts/minecraft.ttf","font4",32,new Color(0.5f,0.5f,0.5f,1));
        Assets.loadTTF("fonts/standart.ttf","font5",32,new Color(0.5f,0.5f,0.5f,1));

        //BACKGROUNDS

        Assets.loadTexture("background/Background_0.png","Background_0");

        //UI

        Assets.loadTexture("ui/Inventory_Back.png","Inventory_Back");
        Assets.loadTexture("ui/Inventory_Back7.png","Inventory_Back7");
        Assets.loadTexture("ui/Inventory_Back10.png","Inventory_Back10");
        Assets.loadTexture("ui/Inventory_Back14.png","Inventory_Back14");
        Assets.loadTexture("ui/Inventory_Back17.png","Inventory_Back17");
        Assets.loadTexture("ui/Cursor_0.png","Cursor_0");

        //ITEMS

        Assets.loadTexture("items/Item_0.png","Item_0");
        Assets.loadTexture("items/Item_2.png","Item_2");
        Assets.loadTexture("items/Item_3.png","Item_3");
        Assets.loadTexture("items/Item_8.png","Item_8");
        Assets.loadTexture("items/Item_9.png","Item_9");
        Assets.loadTexture("items/Item_26.png","Item_26");
        Assets.loadTexture("items/Item_30.png","Item_30");
        Assets.loadTexture("items/Item_121.png","Item_121");
        Assets.loadTexture("items/Item_520.png","Item_520");
        Assets.loadTexture("items/Item_521.png","Item_521");
        Assets.loadTexture("items/Item_547.png","Item_547");
        Assets.loadTexture("items/Item_548.png","Item_548");
        Assets.loadTexture("items/Item_549.png","Item_549");
        Assets.loadTexture("items/Item_575.png","Item_575");
        Assets.loadTexture("items/Item_757.png","Item_757");
        Assets.loadTexture("items/Item_1326.png","Item_1326");
        Assets.loadTexture("items/Item_2763.png","Item_2763");
        Assets.loadTexture("items/Item_2764.png","Item_2764");
        Assets.loadTexture("items/Item_2765.png","Item_2765");
        Assets.loadTexture("items/Item_2786.png","Item_2786");
        Assets.loadTexture("items/Item_4956.png","Item_4956");
        Assets.loadTexture("items/Item_5005.png","Item_5005");

        //TILES

        Assets.loadTexture("tiles/Tiles_0.png","Tiles_0");
        Assets.loadTexture("tiles/Tiles_1.png","Tiles_1");
        Assets.loadTexture("tiles/Tiles_2.png","Tiles_2");
        Assets.loadTexture("tiles/Tiles_3.png","Tiles_3");
        Assets.loadTexture("tiles/Tiles_4.png","Tiles_4");
        Assets.loadTexture("tiles/Tiles_5.png","Tiles_5");
        Assets.loadTexture("tiles/Tiles_30.png","Tiles_30");
        Assets.loadTexture("tiles/Wall_1.png","Wall_1");
        Assets.loadTexture("tiles/Wall_2.png","Wall_2");
        Assets.loadTexture("tiles/Tree_Branches_0.png","Tree_Branches_0");
        Assets.loadTexture("tiles/Tree_Branches_6.png","Tree_Branches_6");
        Assets.loadTexture("tiles/Tree_Tops_0.png","Tree_Tops_0");
        Assets.loadTexture("tiles/Tree_Tops_6.png","Tree_Tops_6");

        //SHADERS

        Assets.loadShader("shaders/inv_shader","inv_shader");
        Assets.loadShader("shaders/shader","shader");

    }

    public static void defineTiles(TileManager tileManager){

        //ATTRIBUTES 1 - tileid

        ItemManager.defineItem(0,new Item(0,new FrameBuffer().addFrame("Item_0")));
        ItemManager.defineItem(2,new Item(2,new FrameBuffer().addFrame("Item_2")).addAttribute(new Value<>(2)));
        ItemManager.defineItem(3,new Item(3,new FrameBuffer().addFrame("Item_3")).addAttribute(new Value<>(3)));
        ItemManager.defineItem(8,new Item(8,new FrameBuffer().addFrame("Item_8")).addAttribute(new Value<>(4)));
        ItemManager.defineItem(9,new Item(9,new FrameBuffer().addFrame("Item_9")).addAttribute(new Value<>(30)));
        ItemManager.defineItem(26,new Item(26,new FrameBuffer().addFrame("Item_26")).addAttribute(new Value<>(6)));
        ItemManager.defineItem(30,new Item(30,new FrameBuffer().addFrame("Item_30")).addAttribute(new Value<>(8)));
        ItemManager.defineItem(121,new Item(121,new FrameBuffer().addFrame("Item_121")).addAttribute(new Value<>(0)));
        ItemManager.defineItem(520,new Item(520,new FrameBuffer().addFrame("Item_520")).addAttribute(new Value<>(0)));
        ItemManager.defineItem(521,new Item(521,new FrameBuffer().addFrame("Item_521")).addAttribute(new Value<>(0)));
        ItemManager.defineItem(547,new Item(547,new FrameBuffer().addFrame("Item_547")).addAttribute(new Value<>(0)));
        ItemManager.defineItem(548,new Item(548,new FrameBuffer().addFrame("Item_548")).addAttribute(new Value<>(0)));
        ItemManager.defineItem(549,new Item(549,new FrameBuffer().addFrame("Item_549")).addAttribute(new Value<>(0)));
        ItemManager.defineItem(575,new Item(575,new FrameBuffer().addFrame("Item_575")).addAttribute(new Value<>(0)));
        ItemManager.defineItem(1326,new Item(1326,new FrameBuffer().addFrame("Item_1326")).addAttribute(new Value<>(0)));
        ItemManager.defineItem(2763,new Item(2763,new FrameBuffer().addFrame("Item_2763")).addAttribute(new Value<>(0)));
        ItemManager.defineItem(2764,new Item(2764,new FrameBuffer().addFrame("Item_2764")).addAttribute(new Value<>(0)));
        ItemManager.defineItem(2765,new Item(2765,new FrameBuffer().addFrame("Item_2765")).addAttribute(new Value<>(0)));
        ItemManager.defineItem(2786,new Item(2786,new FrameBuffer().addFrame("Item_2786")).addAttribute(new Value<>(0)));
        ItemManager.defineItem(4956,new Item(4956,new FrameBuffer().addFrame("Item_4956")).addAttribute(new Value<>(0)));
        ItemManager.defineItem(5005,new Item(5005,new FrameBuffer().addFrame("Item_5005")).addAttribute(new Value<>(0)));

        tileManager.addTileDataReg(1,0,"Tiles_2",18*9,18*3,16,16);
        tileManager.addTileDataReg(1,1,"Tiles_2",18*9,18*0,16,16);
        tileManager.addTileDataReg(1,2,"Tiles_2",18*6,18*4,16,16);
        tileManager.addTileDataReg(1,3,"Tiles_2",18*12,18*0,16,16);
        tileManager.addTileDataReg(1,4,"Tiles_2",18*6,18*0,16,16);
        tileManager.addTileDataReg(1,5,"Tiles_2",18*0,18*3,16,16);
        tileManager.addTileDataReg(1,6,"Tiles_2",18*1,18*0,16,16);
        tileManager.addTileDataReg(1,7,"Tiles_2",18*1,18*3,16,16);
        tileManager.addTileDataReg(1,8,"Tiles_2",18*5,18*0,16,16);
        tileManager.addTileDataReg(1,9,"Tiles_2",18*0,18*0,16,16);
        tileManager.addTileDataReg(1,10,"Tiles_2",18*1,18*1,16,16);
        tileManager.addTileDataReg(1,11,"Tiles_2",18*4,18*0,16,16);
        tileManager.addTileDataReg(1,12,"Tiles_2",18*6,18*3,16,16);
        tileManager.addTileDataReg(1,13,"Tiles_2",18*0,18*4,16,16);
        tileManager.addTileDataReg(1,14,"Tiles_2",18*1,18*2,16,16);
        tileManager.addTileDataReg(1,15,"Tiles_2",18*1,18*4,16,16);

        tileManager.addTileDataReg(2,0,"Tiles_0",18*9,18*3,16,16);
        tileManager.addTileDataReg(2,1,"Tiles_0",18*9,18*0,16,16);
        tileManager.addTileDataReg(2,2,"Tiles_0",18*6,18*4,16,16);
        tileManager.addTileDataReg(2,3,"Tiles_0",18*12,18*0,16,16);
        tileManager.addTileDataReg(2,4,"Tiles_0",18*6,18*0,16,16);
        tileManager.addTileDataReg(2,5,"Tiles_0",18*0,18*3,16,16);
        tileManager.addTileDataReg(2,6,"Tiles_0",18*1,18*0,16,16);
        tileManager.addTileDataReg(2,7,"Tiles_0",18*1,18*3,16,16);
        tileManager.addTileDataReg(2,8,"Tiles_0",18*5,18*0,16,16);
        tileManager.addTileDataReg(2,9,"Tiles_0",18*0,18*0,16,16);
        tileManager.addTileDataReg(2,10,"Tiles_0",18*1,18*1,16,16);
        tileManager.addTileDataReg(2,11,"Tiles_0",18*4,18*0,16,16);
        tileManager.addTileDataReg(2,12,"Tiles_0",18*6,18*3,16,16);
        tileManager.addTileDataReg(2,13,"Tiles_0",18*0,18*4,16,16);
        tileManager.addTileDataReg(2,14,"Tiles_0",18*1,18*2,16,16);
        tileManager.addTileDataReg(2,15,"Tiles_0",18*1,18*4,16,16);

        tileManager.addTileDataReg(3,0,"Tiles_1",18*9,18*3,16,16);
        tileManager.addTileDataReg(3,1,"Tiles_1",18*9,18*0,16,16);
        tileManager.addTileDataReg(3,2,"Tiles_1",18*6,18*4,16,16);
        tileManager.addTileDataReg(3,3,"Tiles_1",18*12,18*0,16,16);
        tileManager.addTileDataReg(3,4,"Tiles_1",18*6,18*0,16,16);
        tileManager.addTileDataReg(3,5,"Tiles_1",18*0,18*3,16,16);
        tileManager.addTileDataReg(3,6,"Tiles_1",18*1,18*0,16,16);
        tileManager.addTileDataReg(3,7,"Tiles_1",18*1,18*3,16,16);
        tileManager.addTileDataReg(3,8,"Tiles_1",18*5,18*0,16,16);
        tileManager.addTileDataReg(3,9,"Tiles_1",18*0,18*0,16,16);
        tileManager.addTileDataReg(3,10,"Tiles_1",18*1,18*1,16,16);
        tileManager.addTileDataReg(3,11,"Tiles_1",18*4,18*0,16,16);
        tileManager.addTileDataReg(3,12,"Tiles_1",18*6,18*3,16,16);
        tileManager.addTileDataReg(3,13,"Tiles_1",18*0,18*4,16,16);
        tileManager.addTileDataReg(3,14,"Tiles_1",18*1,18*2,16,16);
        tileManager.addTileDataReg(3,15,"Tiles_1",18*1,18*4,16,16);

        tileManager.addTileDataReg(30,0,"Tiles_30",18*9,18*3,16,16);
        tileManager.addTileDataReg(30,1,"Tiles_30",18*9,18*0,16,16);
        tileManager.addTileDataReg(30,2,"Tiles_30",18*6,18*4,16,16);
        tileManager.addTileDataReg(30,3,"Tiles_30",18*12,18*0,16,16);
        tileManager.addTileDataReg(30,4,"Tiles_30",18*6,18*0,16,16);
        tileManager.addTileDataReg(30,5,"Tiles_30",18*0,18*3,16,16);
        tileManager.addTileDataReg(30,6,"Tiles_30",18*1,18*0,16,16);
        tileManager.addTileDataReg(30,7,"Tiles_30",18*1,18*3,16,16);
        tileManager.addTileDataReg(30,8,"Tiles_30",18*5,18*0,16,16);
        tileManager.addTileDataReg(30,9,"Tiles_30",18*0,18*0,16,16);
        tileManager.addTileDataReg(30,10,"Tiles_30",18*1,18*1,16,16);
        tileManager.addTileDataReg(30,11,"Tiles_30",18*4,18*0,16,16);
        tileManager.addTileDataReg(30,12,"Tiles_30",18*6,18*3,16,16);
        tileManager.addTileDataReg(30,13,"Tiles_30",18*0,18*4,16,16);
        tileManager.addTileDataReg(30,14,"Tiles_30",18*1,18*2,16,16);
        tileManager.addTileDataReg(30,15,"Tiles_30",18*1,18*4,16,16);

        for(int i=0; i<45; i++)
            tileManager.addTileDataReg(7,i,"Tiles_3",18*i,22*0,16,20,1,10f/8,0,-2f/8);

        tileManager.addTileDataReg(4,0,"Tiles_4",24*0,24*0,20,20);
        tileManager.addTileDataReg(4,1,"Tiles_4",24*1,24*0,20,20);
        tileManager.addTileDataReg(4,2,"Tiles_4",24*2,24*0,20,20);
        tileManager.addTileDataReg(4,3,"Tiles_4",24*3,24*0,20,20);
        tileManager.addTileDataReg(4,4,"Tiles_4",24*4,24*0,20,20);
        tileManager.addTileDataReg(4,5,"Tiles_4",24*5,24*0,20,20);

        tileManager.addTileDataReg(6,0,"Wall_2",36*9,36*3,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,1,"Wall_2",36*9,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,2,"Wall_2",36*6,36*4,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,3,"Wall_2",36*12,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,4,"Wall_2",36*6,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,5,"Wall_2",36*0,36*3,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,6,"Wall_2",36*1,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,7,"Wall_2",36*1,36*3,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,8,"Wall_2",36*5,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,9,"Wall_2",36*0,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,10,"Wall_2",36*1,36*1,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,11,"Wall_2",36*4,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,12,"Wall_2",36*6,36*3,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,13,"Wall_2",36*0,36*4,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,14,"Wall_2",36*1,36*2,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(6,15,"Wall_2",36*1,36*4,32,32,2,2,-1f/2,-1f/2);

        tileManager.addTileDataReg(8,0,"Wall_1",36*9,36*3,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,1,"Wall_1",36*9,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,2,"Wall_1",36*6,36*4,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,3,"Wall_1",36*12,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,4,"Wall_1",36*6,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,5,"Wall_1",36*0,36*3,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,6,"Wall_1",36*1,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,7,"Wall_1",36*1,36*3,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,8,"Wall_1",36*5,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,9,"Wall_1",36*0,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,10,"Wall_1",36*1,36*1,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,11,"Wall_1",36*4,36*0,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,12,"Wall_1",36*6,36*3,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,13,"Wall_1",36*0,36*4,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,14,"Wall_1",36*1,36*2,32,32,2,2,-1f/2,-1f/2);
        tileManager.addTileDataReg(8,15,"Wall_1",36*1,36*4,32,32,2,2,-1f/2,-1f/2);

        tileManager.addTileDataReg(5,0,"Tiles_5",22*0,22*0,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,1,"Tiles_5",22*1,22*0,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,2,"Tiles_5",22*2,22*0,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,3,"Tiles_5",22*3,22*0,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,4,"Tiles_5",22*4,22*0,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,5,"Tiles_5",22*5,22*0,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,6,"Tiles_5",22*6,22*0,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,7,"Tiles_5",22*7,22*0,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,8,"Tiles_5",22*0,22*3,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,9,"Tiles_5",22*1,22*3,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,10,"Tiles_5",22*2,22*3,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,11,"Tiles_5",22*3,22*3,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,12,"Tiles_5",22*4,22*3,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,13,"Tiles_5",22*5,22*3,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,14,"Tiles_5",22*6,22*3,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,15,"Tiles_5",22*7,22*3,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,16,"Tiles_5",22*0,22*6,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,17,"Tiles_5",22*1,22*6,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,18,"Tiles_5",22*2,22*6,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,19,"Tiles_5",22*3,22*6,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,20,"Tiles_5",22*4,22*6,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,21,"Tiles_5",22*5,22*6,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,22,"Tiles_5",22*6,22*6,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,23,"Tiles_5",22*7,22*6,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,24,"Tiles_5",22*0,22*9,20,20,10f/8,10f/8,-1f/8,-2f/8);
        tileManager.addTileDataReg(5,25,"Tree_Tops_0",82*0,80*0,80,80,80f/16,80f/16,-32f/16,0);
        tileManager.addTileDataReg(5,26,"Tree_Tops_0",82*1,80*0,80,80,80f/16,80f/16,-32f/16,0);
        tileManager.addTileDataReg(5,27,"Tree_Tops_0",82*2,80*0,80,80,80f/16,80f/16,-32f/16,0);
        tileManager.addTileDataReg(5,28,"Tree_Branches_0",42*0,42*0,40,40,20f/8,20f/8,-12f/8,-6f/8);
        tileManager.addTileDataReg(5,29,"Tree_Branches_0",42*1,42*0,40,40,20f/8,20f/8,-0f/8,-6f/8);
        tileManager.addTileDataReg(5,30,"Tree_Branches_0",42*0,42*1,40,40,20f/8,20f/8,-12f/8,-6f/8);
        tileManager.addTileDataReg(5,31,"Tree_Branches_0",42*1,42*1,40,40,20f/8,20f/8,-0f/8,-6f/8);
        tileManager.addTileDataReg(5,32,"Tree_Branches_0",42*0,42*2,40,40,20f/8,20f/8,-12f/8,-6f/8);
        tileManager.addTileDataReg(5,33,"Tree_Branches_0",42*1,42*2,40,40,20f/8,20f/8,-0f/8,-6f/8);


    }

}
