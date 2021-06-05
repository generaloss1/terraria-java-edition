package com.myterraria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import engine.Assets;
import engine.Camera2D;
import engine.math.vectors.Vector2f;
import engine.math.vectors.Vector2i;
import engine.physics.ColissionBody;
import engine.physics.Collider;
import engine.tiledmap.TiledMap;

import java.util.ArrayList;
import java.util.List;

public class Player{


    public int draw_width,draw_height,draw_x,draw_y;
    public ColissionBody rect;
    public int timer1,timer2,counter1,legs_frame,hands_frameX,hands_frameY,y_offset,shirt_frameX;

    public boolean armor_legs=true,armor=true,armor_head,jump_animation,fall_animation,walk_animation,useItem_animation,lookside;

    public Color skin_color=new Color(0.600f,0.427f,0.525f,1);
    public Color hair_color=new Color(0.247f,0.152f,0.360f,1);


    public Player(TiledMap map,float x,float y,float w,float h){
        rect=new ColissionBody(x,y,w,h);
        updateDrawPosition(map);
    }


    public void setPosition(float x,float y,TiledMap map){
        rect.setPosition(x,y);
        updateDrawPosition(map);
    }

    public void translatePosition(float x,float y,TiledMap map){
        rect.translate(x,y);
        updateDrawPosition(map);
    }

    private void translatePosition(Vector2f v,TiledMap map){
        rect.translate(v);
        updateDrawPosition(map);
    }

    public void updateDrawPosition(TiledMap map){
        draw_x=Math.round(map.layer(3).tiles_offset_x*rect.x);
        draw_y=Math.round(map.layer(3).tiles_offset_y*rect.y);
    }


    public void update(TiledMap map,Camera2D cam){
        int x=Math.round(rect.x);
        int y=Math.round(rect.y);
        int vx=Math.round(rect.velocity.x);
        int vy=Math.round(rect.velocity.y);
        List<ColissionBody> list=new ArrayList<>();

        for(int i=x+Math.min(vx,0)-1; i<x+rect.width+Math.max(0,vx)+1; i++){
            for(int j=y+Math.min(vy,0)-1; j<y+rect.height+Math.max(0,vy)+1; j++){
                if(map.layer(3).inBounds(i,j)){
                    int t=map.layer(3).getTileId(i,j);
                    if(t!=0 && t!=4){
                        list.add(new ColissionBody(i*100,j*100,100,100));
                    }
                }
            }
        }
        float hbwk=34f/40;
        float hbhk=46f/48/8;

        ColissionBody b=new ColissionBody(rect.x*100+hbwk*50,rect.y*100+hbhk*100,rect.width*100*hbwk,rect.height*100-100*hbhk);
        b.velocity.set(rect.velocity).mul(100);

        Vector2f v=Collider.calc(b,list);
        v.div(100);

        translatePosition(v,map);
    }


    public void draw(SpriteBatch sb,TiledMap map){
        int layer=3;

        draw_width=Math.round(map.layer(layer).tiles_offset_x*rect.width*(40f/(2*16)));
        draw_height=Math.round(map.layer(layer).tiles_offset_y*rect.height*(52f/(3*16)));

        int fw=40;
        int fh=50;
        int fx=0;
        int fy=4;

        if(Gdx.input.isKeyPressed(Input.Keys.C))
            useItem_animation=true;
        else
            useItem_animation=false;

        //                         LEGS
        if(!armor_legs){
            sb.setColor(skin_color);
            sb.draw(Assets.getTexture("Player_0_10"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*legs_frame,fw,fh,lookside,false);

            sb.setColor(0.4f,0.4f,0.4f,1);
            sb.draw(Assets.getTexture("Player_0_11"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*legs_frame,fw,fh,lookside,false);

            //BOOTS
            sb.setColor(1f,1f,1f,1);
            sb.draw(Assets.getTexture("Player_0_12"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*legs_frame,fw,fh,lookside,false);
        }else{
            sb.setColor(1,1,1,1);
            sb.draw(Assets.getTexture("Armor_Legs_127"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*legs_frame,fw,fh,lookside,false);
        }

        //                           BODY

        // BACK WRIST
        sb.setColor(skin_color);
        sb.draw(Assets.getTexture("Player_0_7"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*hands_frameX,fy+56*(hands_frameY+2)+2*y_offset,fw,fh,lookside,false);

        if(!armor){
            //HAND
            sb.setColor(skin_color);
            sb.draw(Assets.getTexture("Player_0_7"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*hands_frameX,fy+56*hands_frameY+2*y_offset,fw,fh,lookside,false);

            //SHIRT
            sb.setColor(1f,1f,1f,1);
            sb.draw(Assets.getTexture("Player_0_6"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*shirt_frameX,fy+2*y_offset,fw,fh,lookside,false);

            //SLEEVE
            sb.setColor(0.4f,0.4f,0.4f,1);
            sb.draw(Assets.getTexture("Player_0_8"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*hands_frameX,fy+56*hands_frameY+2*y_offset,fw,fh,lookside,false);
        }else{
            int armor_id=187;

            sb.setColor(1,1,1,1);
            sb.draw(Assets.getTexture("Armor_"+armor_id),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*shirt_frameX,fy+2*y_offset,fw,fh,lookside,false);

            if(!fall_animation){
                sb.setColor(1,1,1,1);
                sb.draw(Assets.getTexture("Armor_"+armor_id),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*hands_frameX,fy+56*hands_frameY+2*y_offset,fw,fh,lookside,false);
            }

            sb.setColor(1,1,1,1);
            sb.draw(Assets.getTexture("Armor_"+armor_id),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56+2*y_offset,fw,fh,lookside,false);

            if(fall_animation){
                sb.setColor(1,1,1,1);
                sb.draw(Assets.getTexture("Armor_"+armor_id),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*hands_frameX,fy+56*hands_frameY+2*y_offset,fw,fh,lookside,false);
            }
        }

        //                                HEAD

        sb.setColor(skin_color);
        sb.draw(Assets.getTexture("Player_0_0"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*(useItem_animation?0:legs_frame),fw,fh,lookside,false);

        //EYES
        sb.setColor(0.682f,0.721f,0.709f,1);
        sb.draw(Assets.getTexture("Player_0_2"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*(useItem_animation?0:legs_frame),fw,fh,lookside,false);

        sb.setColor(1,1,1,1);
        sb.draw(Assets.getTexture("Player_0_1"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*(useItem_animation?0:legs_frame),fw,fh,lookside,false);

        //HAIR
        if(!armor_head){
            sb.setColor(hair_color);
            sb.draw(Assets.getTexture("Hair_29"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+((walk_animation && legs_frame>=6)?56*(useItem_animation?0:legs_frame-6):0),fw,fh,lookside,false);
        }else{
            sb.setColor(1,1,1,1);
            sb.draw(Assets.getTexture("Armor_Head_185"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*legs_frame,fw,fh,lookside,false);
        }

        //              WRIST
        sb.setColor(skin_color);
        sb.draw(Assets.getTexture("Player_0_5"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*hands_frameX,fy+56*hands_frameY+2*y_offset,fw,fh,lookside,false);

        sb.setColor(1,1,1,1);
        if(jump_animation){
            legs_frame=5;
            hands_frameX=3;
            hands_frameY=1;
            shirt_frameX=0;
            y_offset=0;
        }else if(fall_animation){
            legs_frame=5;
            hands_frameX=2;
            hands_frameY=1;
            shirt_frameX=1;
            y_offset=0;
        }else{
            shirt_frameX=0;

            if(walk_animation){
                if(timer1>1){
                    timer1=0;

                    if(legs_frame<6)
                        legs_frame=6;
                    else
                        legs_frame++;
                    if(legs_frame>19)
                        legs_frame=6;

                }
                timer1++;

                hands_frameX=3+(legs_frame<5?0:Math.round((legs_frame-5f)/5));
                hands_frameY=1;

                if( (legs_frame>6 && legs_frame<10) || (legs_frame>13 && legs_frame<17) )
                    y_offset=1;
                else
                    y_offset=0;
            }else{
                legs_frame=0;
                hands_frameX=2;
                hands_frameY=0;
                counter1=0;
                timer1=0;
                timer2=0;
                y_offset=0;
            }
        }
        if(useItem_animation){
            y_offset=0;
        }

    }


}
