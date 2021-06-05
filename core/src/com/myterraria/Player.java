package com.myterraria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import engine.Assets;
import engine.Camera2D;
import engine.math.Maths;
import engine.math.vectors.Vector2f;
import engine.physics.ColissionBody;
import engine.physics.Collider;
import engine.tiledmap.TiledMap;

import java.util.ArrayList;
import java.util.List;

public class Player{


    public int draw_width,draw_height,draw_x,draw_y;
    public ColissionBody rect;
    public int timer1,timer2,counter1,legs_frame,hands_frameX,hands_frameY,y_offset,shirt_frameX;
    public boolean armor_legs,armor,armor_head,jump_animation,fall_animation,walk_animation,useItem_animation,lookside,jump;

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


    public void update(TiledMap map,Camera2D cam,float delt){
        float k1=0.004f;
        float k2=0.01f;
        float k3=0.02f;
        float k4=0.7f;

        //           COLLIDER

        float ths=64;
        List<ColissionBody> b2=getCollideTiled(map,ths);
        float hbwk=34f/40;
        float hbhk=46f/48/8;
        ColissionBody b=new ColissionBody(rect.x*ths+hbwk*ths/2,rect.y*ths+hbhk*ths,rect.width*ths*hbwk,rect.height*ths-ths*hbhk);
        b.velocity.set(rect.velocity).mul(ths);

        Vector2f v=Collider.calc(b,b2);
        v.div(ths);
        translatePosition(v,map);

        //          CONTROLS

        if(Gdx.input.isKeyPressed(Input.Keys.A) && !Collider.isCollisionLeft(b,b2)){
            rect.velocity.x-=k2*delt;
            lookside=true;
            walk_animation=true;
        }else if(Gdx.input.isKeyPressed(Input.Keys.D) && !Collider.isCollisionRight(b,b2)){
            rect.velocity.x+=k2*delt;
        }

        //          JUMP

        if(Collider.isCollisionDown(b,b2)){
            jump=false;
            rect.velocity.y=0;
        }else{
            rect.velocity.y-=k3;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            if(!jump){
                if(Collider.isCollisionDown(b,b2)){
                    jump=true;
                    rect.velocity.y=k4;
                }
            }
        }

        //           VELOCITY

        if(rect.velocity.x>0)
            rect.velocity.x-=k1;
        if(rect.velocity.x<0)
            rect.velocity.x+=k1;
        if(rect.velocity.x>=-k1 && rect.velocity.x<=k1)
            rect.velocity.x=0;

        //            ANIMATIONS

        if(rect.velocity.x>0)
            lookside=false;
        else if(rect.velocity.x<0)
            lookside=true;

        if(Collider.isCollisionUp(b,b2)){
            if(rect.velocity.y>0)
                rect.velocity.y=0;
        }
        if(Collider.isCollisionRight(b,b2)){
            if(rect.velocity.x>0)
                rect.velocity.x=0;
        }
        if(Collider.isCollisionLeft(b,b2)){
            if(rect.velocity.x<0)
                rect.velocity.x=0;
        }
        if(Collider.isCollisionDown(b,b2)){
            jump_animation=false;
            fall_animation=false;
            if(rect.velocity.x==0){
                walk_animation=false;
            }else
                walk_animation=true;
        }else{
            walk_animation=false;
            if(rect.velocity.y>0){
                jump_animation=true;
                fall_animation=false;
            }else if(rect.velocity.y<0){
                fall_animation=true;
                jump_animation=false;
            }else{
                fall_animation=false;
                jump_animation=false;
            }
        }
    }


    public Color color_mul(Color c1, Color c2){
        return new Color(c1.r*c2.r,c1.g*c2.g,c1.b*c2.b,c1.a*c2.a);
    }

    public Color color_mix(Color c1,Color c2){
        return new Color((c1.r+c2.r)/2f,(c1.g+c2.g)/2f,(c1.b+c2.b)/2f,(c1.a+c2.a)/2f);
    }


    public void draw(SpriteBatch sb,TiledMap map,float delt){
        int layer=3;

        draw_width=Math.round(map.layer(layer).tiles_offset_x*rect.width*(40f/(2*16)));
        draw_height=Math.round(map.layer(layer).tiles_offset_y*rect.height*(52f/(3*16)));

        int fw=40;
        int fh=50;
        int fx=0;
        int fy=4;

        int x=Math.round(rect.x);
        int y=Math.round(rect.y);

        float[][][] cm=map.layer(3).colormap;
        Color c_00=new Color(1,1,1,1),c_10=new Color(1,1,1,1),c_01=new Color(1,1,1,1),c_11=new Color(1,1,1,1),c_02=new Color(1,1,1,1),c_12=new Color(1,1,1,1);
        if(map.layer(3).inBounds(x,y))
            c_00=new Color(cm[x][y][0],cm[x][y][1],cm[x][y][2],1);
        if(map.layer(3).inBounds(x+1,y))
            c_10=new Color(cm[x+1][y][0],cm[x+1][y][1],cm[x+1][y][2],1);
        if(map.layer(3).inBounds(x,y+1))
            c_01=new Color(cm[x][y+1][0],cm[x][y+1][1],cm[x][y+1][2],1);
        if(map.layer(3).inBounds(x+1,y+1))
            c_11=new Color(cm[x+1][y+1][0],cm[x+1][y+1][1],cm[x+1][y+1][2],1);
        if(map.layer(3).inBounds(x,y+2))
            c_02=new Color(cm[x][y+2][0],cm[x][y+2][1],cm[x][y+2][2],1);
        if(map.layer(3).inBounds(x+1,y+2))
            c_12=new Color(cm[x+1][y+2][0],cm[x+1][y+2][1],cm[x+1][y+2][2],1);

        Color legs_c=color_mix(c_00,c_10);
        Color armor_c=color_mix(c_01,c_11);
        Color head_c=color_mix(c_02,c_12);

        //                         LEGS
        if(!armor_legs){
            sb.setColor(color_mul(skin_color,legs_c));
            sb.draw(Assets.getTexture("Player_0_10"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*legs_frame,fw,fh,lookside,false);

            sb.setColor(color_mul(new Color(0.4f,0.4f,0.4f,1),legs_c));
            sb.draw(Assets.getTexture("Player_0_11"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*legs_frame,fw,fh,lookside,false);

            //BOOTS
            sb.setColor(legs_c);
            sb.draw(Assets.getTexture("Player_0_12"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*legs_frame,fw,fh,lookside,false);
        }else{
            sb.setColor(legs_c);
            sb.draw(Assets.getTexture("Armor_Legs_127"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*legs_frame,fw,fh,lookside,false);
        }

        //                           BODY

        // BACK WRIST
        sb.setColor(color_mul(skin_color,armor_c));
        sb.draw(Assets.getTexture("Player_0_7"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*hands_frameX,fy+56*(hands_frameY+2)+2*y_offset,fw,fh,lookside,false);

        if(!armor){
            //HAND
            sb.setColor(color_mul(skin_color,armor_c));
            sb.draw(Assets.getTexture("Player_0_7"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*hands_frameX,fy+56*hands_frameY+2*y_offset,fw,fh,lookside,false);

            //SHIRT
            sb.setColor(color_mul(new Color(1f,1f,1f,1),armor_c));
            sb.draw(Assets.getTexture("Player_0_6"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*shirt_frameX,fy+2*y_offset,fw,fh,lookside,false);

            //SLEEVE
            sb.setColor(color_mul(new Color(0.4f,0.4f,0.4f,1),armor_c));
            sb.draw(Assets.getTexture("Player_0_8"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*hands_frameX,fy+56*hands_frameY+2*y_offset,fw,fh,lookside,false);
        }else{
            int armor_id=187;

            sb.setColor(armor_c);
            sb.draw(Assets.getTexture("Armor_"+armor_id),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*shirt_frameX,fy+2*y_offset,fw,fh,lookside,false);

            if(!fall_animation){
                sb.setColor(armor_c);
                sb.draw(Assets.getTexture("Armor_"+armor_id),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*hands_frameX,fy+56*hands_frameY+2*y_offset,fw,fh,lookside,false);
            }

            sb.setColor(armor_c);
            sb.draw(Assets.getTexture("Armor_"+armor_id),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56+2*y_offset,fw,fh,lookside,false);

            if(fall_animation){
                sb.setColor(armor_c);
                sb.draw(Assets.getTexture("Armor_"+armor_id),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx+40*hands_frameX,fy+56*hands_frameY+2*y_offset,fw,fh,lookside,false);
            }
        }

        //                                HEAD

        sb.setColor(color_mul(skin_color,head_c));
        sb.draw(Assets.getTexture("Player_0_0"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*(useItem_animation?0:legs_frame),fw,fh,lookside,false);

        //EYES
        sb.setColor(color_mul(new Color(0.682f,0.721f,0.709f,1),head_c));
        sb.draw(Assets.getTexture("Player_0_2"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*(useItem_animation?0:legs_frame),fw,fh,lookside,false);

        sb.setColor(color_mul(new Color(1,1,1,1),head_c));
        sb.draw(Assets.getTexture("Player_0_1"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*(useItem_animation?0:legs_frame),fw,fh,lookside,false);

        //HAIR
        if(!armor_head){
            sb.setColor(color_mul(hair_color,head_c));
            sb.draw(Assets.getTexture("Hair_29"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+((walk_animation && legs_frame>=6)?56*(useItem_animation?0:legs_frame-6):0),fw,fh,lookside,false);
        }else{
            sb.setColor(head_c);
            sb.draw(Assets.getTexture("Armor_Head_185"),draw_x,draw_y,draw_width/2f,draw_height/2f,draw_width,draw_height,1,1,0,fx,fy+56*legs_frame,fw,fh,lookside,false);
        }

        //              WRIST
        sb.setColor(color_mul(skin_color,armor_c));
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
                if(timer1>1/Maths.module(rect.velocity.x*3.14)*delt){
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


    private List<ColissionBody> getCollideTiled(TiledMap map,float ths){
        int x=Math.round(rect.x);
        int y=Math.round(rect.y);
        int vx=Math.round(rect.velocity.x);
        int vy=Math.round(rect.velocity.y);
        List<ColissionBody> b2=new ArrayList<>();
        for(int i=x+Math.min(vx,0)-1; i<x+rect.width+Math.max(0,vx)+1; i++){
            for(int j=y+Math.min(vy,0)-1; j<y+rect.height+Math.max(0,vy)+1; j++){
                if(map.layer(3).inBounds(i,j)){
                    int t=map.layer(3).getTileId(i,j);
                    if(t!=0 && t!=4){
                        b2.add(new ColissionBody(i*ths,j*ths,ths,ths));
                    }
                }
            }
        }
        return b2;
    }


}
