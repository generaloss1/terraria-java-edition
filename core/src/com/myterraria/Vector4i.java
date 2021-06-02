package com.myterraria;

import engine.math.vectors.Vector2i;

public class Vector4i{

    public int x,y,z,d;

    public Vector4i(int x,int y,int z,int d){
        this.x=x;
        this.y=y;
        this.z=z;
        this.d=d;
    }

    public Vector4i(Vector2i xy, Vector2i zd){
        x=xy.x;
        y=xy.y;
        z=zd.x;
        d=zd.y;
    }

}
