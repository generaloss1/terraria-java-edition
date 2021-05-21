package com.myterraria.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Animation{

    public FrameBuffer frameBuffer;
    public int frameIndex;

    public Animation(){
        frameBuffer=new FrameBuffer();
    }

    public void draw(SpriteBatch sb,int x,int y,int w,int h){
        sb.draw(frameBuffer.getFrame(frameIndex),x,y,w,h);
    }

}
