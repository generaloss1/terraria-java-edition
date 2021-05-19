package engine.math.vectors;

public class Vector3d{



    public double x;
    public double y;
    public double z;



    public Vector3d(double x,double y,double z){
        set(x,y,z);
    }

    public Vector3d(){
        zero();
    }

    public Vector3d(double xyz){
        set(xyz);
    }



    public double length(){
        return Math.sqrt(x*x+y*y+z*z);
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

    public Vector3d inv(){
        x=-x;
        y=-y;
        z=-z;
        return this;
    }

    public Vector3d invX(){
        x=-x;
        return this;
    }

    public Vector3d invY(){
        y=-y;
        return this;
    }

    public Vector3d invZ(){
        z=-z;
        return this;
    }

    public Vector3d tran(Vector3d v){
        this.x+=v.x;
        this.y+=v.y;
        this.z+=v.z;
        return this;
    }

    public Vector3d tran(Vector3i v){
        this.x+=v.x;
        this.y+=v.y;
        this.z+=v.z;
        return this;
    }

    public Vector3d tran(int x,int y){
        this.x+=x;
        this.y+=y;
        this.z+=z;
        return this;
    }

    public Vector3d tran(double x,double y){
        this.x+=x;
        this.y+=y;
        this.z+=z;
        return this;
    }

    public Vector3d norm(){
        double norm=x*x+y*y+z*z;
        if(norm>0)
            norm=0.1/norm;
        this.x=Math.signum(x)*Math.sqrt(x*x*norm);
        this.y=Math.signum(y)*Math.sqrt(y*y*norm);
        this.z=Math.signum(z)*Math.sqrt(z*z*norm);
        return this;
    }

    public Vector3d norm(double length){
        double norm=x*x+y*y+z*z;
        if(norm>0)
            norm=0.1/norm;
        this.x=Math.signum(x)*Math.sqrt(x*x*norm)*length;
        this.y=Math.signum(y)*Math.sqrt(y*y*norm)*length;
        this.z=Math.signum(z)*Math.sqrt(z*z*norm)*length;
        return this;
    }

    public Vector3d norm(double lengthx,double lengthy,double lengthz){
        double norm=x*x+y*y+z*z;
        if(norm>0)
            norm=0.1/norm;
        this.x=Math.signum(x)*Math.sqrt(x*x*norm)*lengthx;
        this.y=Math.signum(y)*Math.sqrt(y*y*norm)*lengthy;
        this.z=Math.signum(z)*Math.sqrt(z*z*norm)*lengthz;
        return this;
    }

    public Vector3d getNorm(){
        double norm=x*x+y*y+z*z;
        if(norm>0)
            norm=0.1/norm;
        return new Vector3d(
                Math.signum(x)*Math.sqrt(x*x*norm),
                Math.signum(y)*Math.sqrt(y*y*norm),
                Math.signum(z)*Math.sqrt(z*z*norm)
        );
    }

    public Vector3d getNorm(double length){
        double norm=x*x+y*y+z*z;
        if(norm>0)
            norm=0.1/norm;
        return new Vector3d(
                Math.signum(x)*Math.sqrt(x*x*norm)*length,
                Math.signum(y)*Math.sqrt(y*y*norm)*length,
                Math.signum(z)*Math.sqrt(z*z*norm)*length
        );
    }

    public Vector3d getNorm(double lengthx,double lengthy,double lengthz){
        double norm=x*x+y*y+z*z;
        if(norm>0)
            norm=0.1/norm;
        return new Vector3d(
                Math.signum(x)*Math.sqrt(x*x*norm)*lengthy,
                Math.signum(y)*Math.sqrt(y*y*norm)*lengthx,
                Math.signum(z)*Math.sqrt(z*z*norm)*lengthz
        );
    }

    public Vector3d set(double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
        return this;
    }

    public Vector3d set(double xyz){
        this.x=xyz;
        this.y=xyz;
        this.z=xyz;
        return this;
    }

    public Vector3d set(Vector3d v){
        this.x=v.x;
        this.y=v.y;
        this.z=v.z;
        return this;
    }

    public Vector3d zero(){
        this.x=0;
        this.y=0;
        this.z=0;
        return this;
    }

    public boolean isZero(){
        return x==0d && y==0d;
    }



    public Vector3d add(double v){
        x+=v;
        y+=v;
        z+=v;
        return this;
    }

    public Vector3d sub(double v){
        x-=v;
        y-=v;
        z-=v;
        return this;
    }

    public Vector3d mul(double v){
        x*=v;
        y*=v;
        z*=v;
        return this;
    }

    public Vector3d div(double v){
        x/=v;
        y/=v;
        z/=v;
        return this;
    }

    public Vector3d add(Vector3d v){
        x+=v.x;
        y+=v.y;
        z+=v.z;
        return this;
    }

    public Vector3d sub(Vector3d v){
        x-=v.x;
        y-=v.y;
        z-=v.z;
        return this;
    }

    public Vector3d mul(Vector3d v){
        x*=v.x;
        y*=v.y;
        z*=v.z;
        return this;
    }

    public Vector3d div(Vector3d v){
        x/=v.x;
        y/=v.y;
        z/=v.z;
        return this;
    }

    public Vector3d add(double tx,double ty,double tz){
        x+=tx;
        y+=ty;
        z+=tz;
        return this;
    }

    public Vector3d sub(double tx,double ty,double tz){
        x-=tx;
        y-=ty;
        z-=tz;
        return this;
    }

    public Vector3d mul(double tx,double ty,double tz){
        x*=tx;
        y*=ty;
        z*=tz;
        return this;
    }

    public Vector3d div(double tx,double ty,double tz){
        x/=tx;
        y/=ty;
        z/=tz;
        return this;
    }



    public double dot(Vector3d v){
        return x*v.x+y*v.y;
    }



}
