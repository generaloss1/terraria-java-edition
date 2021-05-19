package engine.math.vectors;

public class Vector2d{



    public double x;
    public double y;



    public Vector2d(double x,double y){
        this.x=x;
        this.y=y;
    }

    public Vector2d(){
        this.x=0;
        this.y=0;
    }

    public Vector2d(double xy){
        this.x=xy;
        this.y=xy;
    }



    public double length(){
        return Math.hypot(x,y);
    }



    public int x(){
        return (int)Math.round(x);
    }

    public int y(){
        return (int)Math.round(y);
    }

    public Vector2d inv(){
        x=-x;
        y=-y;
        return this;
    }

    public Vector2d invX(){
        x=-x;
        return this;
    }

    public Vector2d invY(){
        y=-y;
        return this;
    }


    public Vector2d norm(){
        double norm=x*x+y*y;
        if(norm>0)
            norm=0.1/norm;
        this.x=Math.signum(x)*Math.sqrt(x*x*norm);
        this.y=Math.signum(y)*Math.sqrt(y*y*norm);
        return this;
    }

    public Vector2d norm(double length){
        double norm=x*x+y*y;
        if(norm>0)
            norm=0.1/norm;
        this.x=Math.signum(x)*Math.sqrt(x*x*norm)*length;
        this.y=Math.signum(y)*Math.sqrt(y*y*norm)*length;
        return this;
    }

    public Vector2d norm(double lengthx,double lengthy){
        double norm=x*x+y*y;
        if(norm>0)
            norm=0.1/norm;
        this.x=Math.signum(x)*Math.sqrt(x*x*norm)*lengthx;
        this.y=Math.signum(y)*Math.sqrt(y*y*norm)*lengthy;
        return this;
    }

    public Vector2d getNorm(){
        double norm=x*x+y*y;
        if(norm>0)
            norm=0.1/norm;
        return new Vector2d(Math.signum(x)*Math.sqrt(x*x*norm),Math.signum(y)*Math.sqrt(y*y*norm));
    }

    public Vector2d getNorm(double length){
        double norm=x*x+y*y;
        if(norm>0)
            norm=0.1/norm;
        return new Vector2d(Math.signum(x)*Math.sqrt(x*x*norm)*length,Math.signum(y)*Math.sqrt(y*y*norm)*length);
    }

    public Vector2d getNorm(double lengthx,double lengthy){
        double norm=x*x+y*y;
        if(norm>0)
            norm=0.1/norm;
        return new Vector2d(Math.signum(x)*Math.sqrt(x*x*norm)*lengthy,Math.signum(y)*Math.sqrt(y*y*norm)*lengthx);
    }

    public float angle(){
        return (float)(Math.atan2(y,x)*180/Math.PI+180);
    }

    public Vector2d set(double x,double y){
        this.x=x;
        this.y=y;
        return this;
    }

    public Vector2d set(double xy){
        this.x=xy;
        this.y=xy;
        return this;
    }

    public Vector2d set(Vector2d v){
        this.x=v.x;
        this.y=v.y;
        return this;
    }

    public Vector2d zero(){
        this.x=0;
        this.y=0;
        return this;
    }

    public boolean isZero(){
        return x==0d && y==0d;
    }

    public Vector2d setAngleDeg(float a){
        this.x=Math.cos(a*Math.PI/180);
        this.y=Math.sin(a*Math.PI/180);
        return this;
    }

    public Vector2d setAngleRad(float a){
        this.x=Math.cos(a);
        this.y=Math.sin(a);
        return this;
    }

    public Vector2d add(double v){
        x+=v;
        y+=v;
        return this;
    }

    public Vector2d sub(double v){
        x-=v;
        y-=v;
        return this;
    }

    public Vector2d mul(double v){
        x*=v;
        y*=v;
        return this;
    }

    public Vector2d div(double v){
        x/=v;
        y/=v;
        return this;
    }

    public Vector2d add(Vector2d v){
        x+=v.x;
        y+=v.y;
        return this;
    }

    public Vector2d sub(Vector2d v){
        x-=v.x;
        y-=v.y;
        return this;
    }

    public Vector2d mul(Vector2d v){
        x*=v.x;
        y*=v.y;
        return this;
    }

    public Vector2d div(Vector2d v){
        x/=v.x;
        y/=v.y;
        return this;
    }



    public Vector2d add(double tx,double ty){
        x+=tx;
        y+=ty;
        return this;
    }

    public Vector2d sub(double tx,double ty){
        x-=tx;
        y-=ty;
        return this;
    }

    public Vector2d mul(double tx,double ty){
        x*=tx;
        y*=ty;
        return this;
    }

    public Vector2d div(double tx,double ty){
        x/=tx;
        y/=ty;
        return this;
    }



    public double dot(Vector2d v){
        return x*v.x+y*v.y;
    }



}
