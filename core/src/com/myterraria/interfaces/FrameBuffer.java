package com.myterraria.interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import engine.Assets;

import java.util.ArrayList;
import java.util.List;

public class FrameBuffer{

    public List<TextureRegion> frames;

    public FrameBuffer(){
        frames=new ArrayList<>();
    }

    public FrameBuffer addFrame(String texture){
        frames.add(new TextureRegion(Assets.getTexture(texture)));
        return this;
    }

    public FrameBuffer addFrameReg(String texture,int x,int y,int w,int h){
        frames.add(new TextureRegion(Assets.getTexture(texture),x,y,w,h));
        return this;
    }

    public TextureRegion getFrame(int index){
        return frames.get(index);
    }

    public int size(){
        return frames.size();
    }

}
