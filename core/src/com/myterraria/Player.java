package com.myterraria;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import engine.Assets;

public class Player{


    public static int x,y,w,h;


    public static void draw(SpriteBatch sb){
        int hframe=1;
        int frame=1;
        boolean lookside=false;
        int animationStage=1;

        int fw=36-4;
        int fh=54-4;
        int fx=2+2;
        int fy=0+4;
        //SHIRT
        sb.setColor(1f,1f,1f,1);
        //sb.draw(Assets.getTexture("shirt"),x,y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*hframe,fw,fh,lookside,false);
        sb.draw(new TextureRegion(Assets.getTexture("shirt"),fx,fy+56*hframe,fw,fh),x,y,w,h);

        /*sb.setColor(1f,0.85f,0.8f,1);
        sb.draw(Assets.getTexture("undershirt"),x,y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*hframe,fw,fh,lookside,false);
        //HANDS
        sb.setColor(0.9875f,0.78f,0.63f,1);
        sb.draw(Assets.getTexture("hands"),x,y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*hframe,fw,fh,lookside,false);

        //HEAD
        sb.setColor(0.9875f,0.78f,0.63f,1);
        sb.draw(Assets.getTexture("head"),x,y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*frame,fw,fh,lookside,false);
        //EYES
        sb.setColor(0.05f,0.34f,0.84f,1);
        sb.draw(Assets.getTexture("eyes"),x,y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*frame,fw,fh,lookside,false);

        sb.setColor(1,1,1,1);
        sb.draw(Assets.getTexture("eyes2"),x,y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*frame,fw,fh,lookside,false);
        //HAIR
        sb.setColor(0.2f,0.2f,0.2f,1);
        sb.draw(Assets.getTexture("hair1"),x,y,w/2f,h/2f,w,h,1,1,0,fx,fy+((animationStage==1&&frame>=6)?56*(frame-6):0),fw,fh,lookside,false);

        //HANDS2,3
        sb.setColor(1f,1f,1f,1);
        sb.draw(Assets.getTexture("hands2"),x,y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*hframe,fw,fh,lookside,false);

        sb.setColor(0.9875f,0.78f,0.63f,1);
        sb.draw(Assets.getTexture("hands3"),x,y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*hframe,fw,fh,lookside,false);

        //LEGS
        sb.setColor(1f,1f,1f,1);
        sb.draw(Assets.getTexture("pants"),x,y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*frame,fw,fh,lookside,false);

        sb.setColor(1f,0.85f,0.8f,1);
        sb.draw(Assets.getTexture("shoes"),x,y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*frame,fw,fh,lookside,false);*/

        sb.setColor(1,1,1,1);
    }


}
