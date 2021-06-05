package engine.physics;

import engine.math.vectors.Vector2f;

public class ColissionBody{


    public float x,y,width,height;
    public Vector2f velocity;


    public ColissionBody(float x,float y,float w,float h){
        velocity=new Vector2f();
        this.x=x;
        this.y=y;
        width=w;
        height=h;
    }

    public ColissionBody(ColissionBody b){
        velocity=new Vector2f();
        x=b.x;
        y=b.y;
        width=b.width;
        height=b.height;
        velocity.set(b.velocity);
    }


    public void setPosition(float x,float y){
        this.x=x;
        this.y=y;
    }

    public ColissionBody translate(Vector2f v){
        x+=v.x;
        y+=v.y;
        return this;
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
