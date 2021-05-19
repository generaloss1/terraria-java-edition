package engine.math.vectors;

public class Vector2i{



    public int x;
    public int y;



    public Vector2i(int x,int y){
        this.x=x;
        this.y=y;
    }



    public float length(){
        return (float)Math.hypot(x,y);
    }



    public Vector2i inv(){
        x=-x;
        y=-y;
        return this;
    }

    public Vector2i invX(){
        x=-x;
        return this;
    }

    public Vector2i invY(){
        y=-y;
        return this;
    }

    public Vector2i set(float x,float y){
        this.x=Math.round(x);
        this.y=Math.round(y);
        return this;
    }

    public Vector2i set(float xy){
        this.x=Math.round(xy);
        this.y=Math.round(xy);
        return this;
    }

    public Vector2i set(Vector2i v){
        this.x=v.x;
        this.y=v.y;
        return this;
    }

    public Vector2i zero(){
        this.x=0;
        this.y=0;
        return this;
    }

    public boolean isZero(){
        return x==0 && y==0;
    }

    public Vector2i add(int v){
        x+=v;
        y+=v;
        return this;
    }

    public Vector2i sub(int v){
        x-=v;
        y-=v;
        return this;
    }

    public Vector2i mul(int v){
        x*=v;
        y*=v;
        return this;
    }

    public Vector2i div(int v){
        x/=v;
        y/=v;
        return this;
    }



    public Vector2i add(Vector2i v){
        x+=v.x;
        y+=v.y;
        return this;
    }

    public Vector2i sub(Vector2i v){
        x-=v.x;
        y-=v.y;
        return this;
    }

    public Vector2i mul(Vector2i v){
        x*=v.x;
        y*=v.y;
        return this;
    }

    public Vector2i div(Vector2i v){
        x/=v.x;
        y/=v.y;
        return this;
    }



    public Vector2i add(int tx,int ty){
        x+=tx;
        y+=ty;
        return this;
    }

    public Vector2i sub(int tx,int ty){
        x-=tx;
        y-=ty;
        return this;
    }

    public Vector2i mul(int tx,int ty){
        x*=tx;
        y*=ty;
        return this;
    }

    public Vector2i div(int tx,int ty){
        x/=tx;
        y/=ty;
        return this;
    }



    public int dot(Vector2i v){
        return x*v.x+y*v.y;
    }



}
