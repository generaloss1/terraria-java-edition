package com.myterraria.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation{

    public FrameBuffer frameBuffer;
    public int frameIndex;

    public Animation(){
        frameBuffer=new FrameBuffer();
    }

    public Animation(FrameBuffer fb){
        frameBuffer=fb;
    }

    public TextureRegion currentFrame(){
        return frameBuffer.getFrame(frameIndex);
    }

    public int size(){
        return frameBuffer.size();
    }

    public void next(){
        frameIndex++;
        if(frameIndex>=size())
            frameIndex=0;
    }

    /*public void draw(SpriteBatch sb,int x,int y,int w,int h){
        sb.draw(frameBuffer.getFrame(frameIndex),x,y,w,h);
    }*/

}
