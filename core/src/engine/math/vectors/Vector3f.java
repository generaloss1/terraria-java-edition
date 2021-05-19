package engine.math.vectors;

public class Vector3f{



    public float x;
    public float y;
    public float z;



    public Vector3f(float x,float y,float z){
        set(x,y,z);
    }

    public Vector3f(){
        zero();
    }

    public Vector3f(float xyz){
        set(xyz);
    }



    public float length(){
        return (float)Math.sqrt(x*x+y*y+z*z);
    }



    public int x(){
        return (int)Math.round(x);
    }

    public int y(){
        return (int)Math.round(y);
    }

    public int z(){
        return (int)Math.round(z);
    }

    public Vector3f inv(){
        x=-x;
        y=-y;
        z=-z;
        return this;
    }

    public Vector3f invX(){
        x=-x;
        return this;
    }

    public Vector3f invY(){
        y=-y;
        return this;
    }

    public Vector3f invZ(){
        z=-z;
        return this;
    }

    public Vector3f tran(Vector3f v){
        this.x+=v.x;
        this.y+=v.y;
        this.z+=v.z;
        return this;
    }

    public Vector3f tran(Vector3i v){
        this.x+=v.x;
        this.y+=v.y;
        this.z+=v.z;
        return this;
    }

    public Vector3f tran(int x,int y,int z){
        this.x+=x;
        this.y+=y;
        this.z+=z;
        return this;
    }

    public Vector3f tran(float x,float y,float z){
        this.x+=x;
        this.y+=y;
        this.z+=z;
        return this;
    }

    public Vector3f norm(){
        float norm=x*x+y*y+z*z;
        if(norm>0)
            norm=0.1f/norm;
        this.x=Math.signum(x)*(float)Math.sqrt(x*x*norm);
        this.y=Math.signum(y)*(float)Math.sqrt(y*y*norm);
        this.z=Math.signum(z)*(float)Math.sqrt(z*z*norm);
        return this;
    }

    public Vector3f norm(float length){
        float norm=x*x+y*y+z*z;
        if(norm>0)
            norm=0.1f/norm;
        this.x=Math.signum(x)*(float)Math.sqrt(x*x*norm)*length;
        this.y=Math.signum(y)*(float)Math.sqrt(y*y*norm)*length;
        this.z=Math.signum(z)*(float)Math.sqrt(z*z*norm)*length;
        return this;
    }

    public Vector3f norm(float lengthx,float lengthy,float lengthz){
        float norm=x*x+y*y+z*z;
        if(norm>0)
            norm=0.1f/norm;
        this.x=Math.signum(x)*(float)Math.sqrt(x*x*norm)*lengthx;
        this.y=Math.signum(y)*(float)Math.sqrt(y*y*norm)*lengthy;
        this.z=Math.signum(z)*(float)Math.sqrt(z*z*norm)*lengthz;
        return this;
    }

    public Vector3f getNorm(){
        float norm=x*x+y*y+z*z;
        if(norm>0)
            norm=0.1f/norm;
        return new Vector3f(Math.signum(x)*(float)Math.sqrt(x*x*norm),Math.signum(y)*(float)Math.sqrt(y*y*norm),Math.signum(z)*(float)Math.sqrt(z*z*norm));
    }

    public Vector3f getNorm(float length){
        float norm=x*x+y*y+z*z;
        if(norm>0)
            norm=0.1f/norm;
        return new Vector3f(Math.signum(x)*(float)Math.sqrt(x*x*norm)*length,Math.signum(y)*(float)Math.sqrt(y*y*norm)*length,Math.signum(z)*(float)Math.sqrt(z*z*norm)*length);
    }

    public Vector3f getNorm(float lengthx,float lengthy,float lengthz){
        float norm=x*x+y*y+z*z;
        if(norm>0)
            norm=0.1f/norm;
        return new Vector3f(Math.signum(x)*(float)Math.sqrt(x*x*norm)*lengthy,Math.signum(y)*(float)Math.sqrt(y*y*norm)*lengthx,Math.signum(z)*(float)Math.sqrt(z*z*norm)*lengthz);
    }

    public Vector3f set(float x,float y,float z){
        this.x=x;
        this.y=y;
        this.z=z;
        return this;
    }

    public Vector3f set(float xyz){
        this.x=xyz;
        this.y=xyz;
        this.z=xyz;
        return this;
    }

    public Vector3f set(Vector3f v){
        this.x=v.x;
        this.y=v.y;
        this.z=v.z;
        return this;
    }

    public Vector3f zero(){
        this.x=0;
        this.y=0;
        this.z=0;
        return this;
    }

    public boolean isZero(){
        return x==0f && y==0f;
    }



    public Vector3f add(float v){
        x+=v;
        y+=v;
        z+=v;
        return this;
    }

    public Vector3f sub(float v){
        x-=v;
        y-=v;
        z-=v;
        return this;
    }

    public Vector3f mul(float v){
        x*=v;
        y*=v;
        z*=v;
        return this;
    }

    public Vector3f div(float v){
        x/=v;
        y/=v;
        z/=v;
        return this;
    }

    public Vector3f add(Vector3f v){
        x+=v.x;
        y+=v.y;
        z+=v.z;
        return this;
    }

    public Vector3f sub(Vector3f v){
        x-=v.x;
        y-=v.y;
        z-=v.z;
        return this;
    }

    public Vector3f mul(Vector3f v){
        x*=v.x;
        y*=v.y;
        z*=v.z;
        return this;
    }

    public Vector3f div(Vector3f v){
        x/=v.x;
        y/=v.y;
        z/=v.z;
        return this;
    }

    public Vector3f add(float tx,float ty,float tz){
        x+=tx;
        y+=ty;
        z+=tz;
        return this;
    }

    public Vector3f sub(float tx,float ty,float tz){
        x-=tx;
        y-=ty;
        z-=tz;
        return this;
    }

    public Vector3f mul(float tx,float ty,float tz){
        x*=tx;
        y*=ty;
        z*=tz;
        return this;
    }

    public Vector3f div(float tx,float ty,float tz){
        x/=tx;
        y/=ty;
        z/=tz;
        return this;
    }



    public float dot(Vector3f v){
        return x*v.x+y*v.y;
    }



}
