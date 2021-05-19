package com.myterraria;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import engine.Assets;
import engine.tiledmap.TileManager;

public class LoadResources{

    public static void loadResources(){
        Pixmap pixmap=new Pixmap(1,1,Pixmap.Format.RGBA4444);
        pixmap.setColor(0,0,0,1);
        pixmap.fill();
        Assets.loadTexture(new Texture(pixmap),"black_pixel");

        Assets.loadTTF("FiraCode.ttf","font1",48,new Color(1,1,1,1));
        Assets.loadTTF("FiraCodeBold.ttf","font2",32,new Color(0.5f,0.5f,0.5f,1));

        Assets.loadTexture("Background_0.png","Background_0");

        Assets.loadTexture("Inventory_Back.png","Inventory_Back");
        Assets.loadTexture("Inventory_Back7.png","Inventory_Back7");
        Assets.loadTexture("Inventory_Back10.png","Inventory_Back10");
        Assets.loadTexture("Inventory_Back14.png","Inventory_Back14");
        Assets.loadTexture("Inventory_Back17.png","Inventory_Back17");

        Assets.loadTexture("Tiles_0.png","Tiles_0");
        Assets.loadTexture("Tiles_1.png","Tiles_1");
        Assets.loadTexture("Tiles_2.png","Tiles_2");
        Assets.loadTexture("Tiles_3.png","Tiles_3");
        Assets.loadTexture("Tiles_4.png","Tiles_4");
        Assets.loadTexture("Tiles_5.png","Tiles_5");
        Assets.loadTexture("Wall_1.png","Wall_1");
        Assets.loadTexture("Wall_2.png","Wall_2");
        Assets.loadTexture("Tree_Branches_6.png","Tree_Branches_6");
        Assets.loadTexture("Tree_Tops_6.png","Tree_Tops_6");
    }

    public static void defineTiles(TileManager tileManager){
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
        tileManager.addTileDataReg(5,25,"Tree_Tops_6",82*0,80*0,80,80,80f/16,80f/16,-32f/16,0);
        tileManager.addTileDataReg(5,26,"Tree_Tops_6",82*1,80*0,80,80,80f/16,80f/16,-32f/16,0);
        tileManager.addTileDataReg(5,27,"Tree_Tops_6",82*2,80*0,80,80,80f/16,80f/16,-32f/16,0);
        tileManager.addTileDataReg(5,28,"Tree_Branches_6",42*0,42*0,40,40,20f/8,20f/8,-12f/8,-6f/8);
        tileManager.addTileDataReg(5,29,"Tree_Branches_6",42*1,42*0,40,40,20f/8,20f/8,-0f/8,-6f/8);
        tileManager.addTileDataReg(5,30,"Tree_Branches_6",42*0,42*1,40,40,20f/8,20f/8,-12f/8,-6f/8);
        tileManager.addTileDataReg(5,31,"Tree_Branches_6",42*1,42*1,40,40,20f/8,20f/8,-0f/8,-6f/8);
        tileManager.addTileDataReg(5,32,"Tree_Branches_6",42*0,42*2,40,40,20f/8,20f/8,-12f/8,-6f/8);
        tileManager.addTileDataReg(5,33,"Tree_Branches_6",42*1,42*2,40,40,20f/8,20f/8,-0f/8,-6f/8);


    }

}
