package com.myterraria;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import engine.Assets;
import engine.tiledmap.TiledMap;

public class Player{


    public static int w,h,draw_x,draw_y;
    public static float x,y;
    public static boolean useItem,lookside;
    public static int timer,uitimer,animationStage=1,frame,hframe;


    public static void setPosition(float px,float py,TiledMap map){
        x=px;
        y=py;
        updateDrawPosition(map);
    }

    public static void translatePositio(float tx,float ty,TiledMap map){
        x+=tx;
        y+=ty;
        updateDrawPosition(map);
    }

    public static void updateDrawPosition(TiledMap map){
        draw_x=Math.round(map.layer(3).tiles_offset_x*x);
        draw_y=Math.round(map.layer(3).tiles_offset_y*y);
    }

    public static void draw(SpriteBatch sb,TiledMap map){
        int layer=3;

        w=Math.round(map.layer(layer).tiles_offset_x*2*(32f/32));
        h=Math.round(map.layer(layer).tiles_offset_y*3*(52f/50));

        int fw=32;
        int fh=50;
        int fx=4;
        int fy=4;
        //SHIRT
        sb.setColor(1f,1f,1f,1);
        sb.draw(Assets.getTexture("shirt"),draw_x,draw_y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*hframe,fw,fh,lookside,false);
        //sb.draw(new TextureRegion(Assets.getTexture("shirt"),fx,fy+56*hframe,fw,fh),x,y,w,h);

        sb.setColor(1f,0.85f,0.8f,1);
        sb.draw(Assets.getTexture("undershirt"),draw_x,draw_y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*hframe,fw,fh,lookside,false);
        //HANDS
        sb.setColor(0.9875f,0.78f,0.63f,1);
        sb.draw(Assets.getTexture("hands"),draw_x,draw_y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*hframe,fw,fh,lookside,false);

        //HEAD
        sb.setColor(0.9875f,0.78f,0.63f,1);
        sb.draw(Assets.getTexture("head"),draw_x,draw_y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*(useItem?0:frame),fw,fh,lookside,false);
        //EYES
        sb.setColor(0.05f,0.34f,0.84f,1);
        sb.draw(Assets.getTexture("eyes"),draw_x,draw_y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*(useItem?0:frame),fw,fh,lookside,false);

        sb.setColor(1,1,1,1);
        sb.draw(Assets.getTexture("eyes2"),draw_x,draw_y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*(useItem?0:frame),fw,fh,lookside,false);
        //HAIR
        sb.setColor(0.2f,0.2f,0.2f,1);
        sb.draw(Assets.getTexture("hair1"),draw_x,draw_y,w/2f,h/2f,w,h,1,1,0,fx,fy+((animationStage==1&&frame>=6)?56*(useItem?0:frame-6):0),fw,fh,lookside,false);

        //HANDS2,3
        sb.setColor(1f,1f,1f,1);
        sb.draw(Assets.getTexture("hands2"),draw_x,draw_y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*hframe,fw,fh,lookside,false);

        sb.setColor(0.9875f,0.78f,0.63f,1);
        sb.draw(Assets.getTexture("hands3"),draw_x,draw_y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*hframe,fw,fh,lookside,false);

        //LEGS
        sb.setColor(1f,1f,1f,1);
        sb.draw(Assets.getTexture("pants"),draw_x,draw_y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*frame,fw,fh,lookside,false);

        sb.setColor(1f,0.85f,0.8f,1);
        sb.draw(Assets.getTexture("shoes"),draw_x,draw_y,w/2f,h/2f,w,h,1,1,0,fx,fy+56*frame,fw,fh,lookside,false);

        sb.setColor(1,1,1,1);

        if(animationStage==1){
            timer++;
            if(timer>4){
                timer=0;

                if(frame<6)
                    frame=6;
                else
                    frame++;
                if(frame>19)
                    frame=6;
            }
        }else if(animationStage==2)
            frame=5;
        else
            frame=0;

        if(useItem){
            uitimer++;
            if(uitimer>4){
                uitimer=0;

                if(hframe<1||hframe>4){
                    hframe=1;
                }else{
                    hframe++;
                    if(hframe>4)
                        hframe=1;
                }
            }
        }else
            hframe=frame;
    }


}
