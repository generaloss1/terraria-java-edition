package engine.math.vectors;

public class Vector3i{



    public int x;
    public int y;
    public int z;



    public Vector3i(int x,int y,int z){
        set(x,y,z);
    }

    public Vector3i(){
       zero();
    }

    public Vector3i(int xyz){
        set(xyz);
    }



    public float length(){
        return (float)Math.sqrt(x*x+y*y+z*z);
    }



    public Vector3i inv(){
        x=-x;
        y=-y;
        z=-z;
        return this;
    }

    public Vector3i invX(){
        x=-x;
        return this;
    }

    public Vector3i invY(){
        y=-y;
        return this;
    }

    public Vector3i invZ(){
        z=-z;
        return this;
    }

    public Vector3i tran(Vector3f v){
        this.x+=v.x;
        this.y+=v.y;
        this.z+=v.z;
        return this;
    }

    public Vector3i tran(Vector3i v){
        this.x+=v.x;
        this.y+=v.y;
        this.z+=v.z;
        return this;
    }

    public Vector3i tran(int x,int y,int z){
        this.x+=x;
        this.y+=y;
        this.z+=z;
        return this;
    }

    public Vector3i tran(float x,float y,float z){
        this.x+=x;
        this.y+=y;
        this.z+=z;
        return this;
    }

    public Vector3i set(float x,float y,float z){
        this.x=Math.round(x);
        this.y=Math.round(y);
        this.z=Math.round(z);
        return this;
    }

    public Vector3i set(float xyz){
        this.x=Math.round(xyz);
        this.y=Math.round(xyz);
        this.z=Math.round(xyz);
        return this;
    }

    public Vector3i set(Vector3i v){
        this.x=v.x;
        this.y=v.y;
        this.z=v.z;
        return this;
    }

    public Vector3i zero(){
        this.x=0;
        this.y=0;
        this.z=0;
        return this;
    }

    public boolean isZero(){
        return x==0 && y==0;
    }



    public Vector3i add(int v){
        x+=v;
        y+=v;
        z+=v;
        return this;
    }

    public Vector3i sub(int v){
        x-=v;
        y-=v;
        z-=v;
        return this;
    }

    public Vector3i mul(int v){
        x*=v;
        y*=v;
        z*=v;
        return this;
    }

    public Vector3i div(int v){
        x/=v;
        y/=v;
        z/=v;
        return this;
    }

    public Vector3i add(Vector3i v){
        x+=v.x;
        y+=v.y;
        z+=v.z;
        return this;
    }

    public Vector3i sub(Vector3i v){
        x-=v.x;
        y-=v.y;
        z-=v.z;
        return this;
    }

    public Vector3i mul(Vector3i v){
        x*=v.x;
        y*=v.y;
        z*=v.z;
        return this;
    }

    public Vector3i div(Vector3i v){
        x/=v.x;
        y/=v.y;
        z/=v.z;
        return this;
    }

    public Vector3i add(int tx,int ty,int tz){
        x+=tx;
        y+=ty;
        z+=tz;
        return this;
    }

    public Vector3i sub(int tx,int ty,int tz){
        x-=tx;
        y-=ty;
        z-=tz;
        return this;
    }

    public Vector3i mul(int tx,int ty,int tz){
        x*=tx;
        y*=ty;
        z*=tz;
        return this;
    }

    public Vector3i div(int tx,int ty,int tz){
        x/=tx;
        y/=ty;
        z/=tz;
        return this;
    }



    public int dot(Vector3i v){
        return x*v.x+y*v.y;
    }



}
