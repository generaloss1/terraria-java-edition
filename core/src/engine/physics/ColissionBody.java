package engine.physics;

import engine.math.vectors.Vector2f;

public class ColissionBody{


    public float x,y,width,height;
    Vector2f velocity;


    public ColissionBody(float x,float y,float w,float h){
        this.x=x;
        this.y=y;
        width=w;
        height=h;

        velocity=new Vector2f();
    }

    public ColissionBody(ColissionBody b){
        this.x=b.x;
        this.y=b.y;
        this.width=b.width;
        this.height=b.height;
        this.velocity.set(b.velocity);
    }


    public void setPosition(float x,float y){
        this.x=x;
        this.y=y;
    }

    public ColissionBody translate(float tx,float ty){
        x+=tx;
        y+=ty;
        return this;
    }

    public void setVelocity(Vector2f v){
        velocity.set(v);
    }

    public void setVelocity(float x,float y){
        velocity.set(x,y);
    }


}
