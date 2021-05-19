package engine.math;

import com.badlogic.gdx.math.MathUtils;
import engine.math.vectors.*;

import java.awt.*;

public class Maths{
  
  
  
      public static float pi=(float)Math.PI;



      public static float distance(float x1,float y1,float x2,float y2){
        return (float)Math.hypot(x1-x2,y1-y2);
      }

      public static double distance(double x1,double y1,double x2,double y2){
        return Math.hypot(x1-x2,y1-y2);
      }

      public static float distance(Vector2i v1, Vector2i v2){
        return (float)Math.hypot(v1.x-v2.x,v1.y-v2.y);
      }

      public static float distance(Vector2f v1, Vector2f v2){
        return (float)Math.hypot(v1.x-v2.x,v1.y-v2.y);
      }

      public static float distance(Vector2d v1, Vector2d v2){
        return (float)Math.hypot(v1.x-v2.x,v1.y-v2.y);
      }



      public static Vector2f normDegAngle(float a){
        return new Vector2f((float)Math.cos(a*pi/180f),(float)Math.sin(a*pi/180f));
      }

      public static Vector2d normDegAngle(double a){
        return new Vector2d(Math.cos(a*pi/180f),Math.sin(a*pi/180f));
      }



      public static Vector2f norm(float x,float y){
          float norm=x*x+y*y;
            if(norm>0)
              norm=0.1f/norm;
            return new Vector2f(
              Math.signum(x)*(float)Math.sqrt(x*x*norm),
              Math.signum(y)*(float)Math.sqrt(y*y*norm)
            );
      }

      public static Vector2d norm(double x,double y){
        double norm=x*x+y*y;
        if(norm>0)
          norm=0.1/norm;
        return new Vector2d(
          Math.signum(x)*Math.sqrt(x*x*norm),
          Math.signum(y)*Math.sqrt(y*y*norm)
        );
      }



      public static Vector2f norm(float x,float y,float length){
        float norm=x*x+y*y;
        if(norm>0)
          norm=0.1f/norm;
        return new Vector2f(
          Math.signum(x)*(float)Math.sqrt(x*x*norm)*length,
          Math.signum(y)*(float)Math.sqrt(y*y*norm)*length
        );
      }

      public static Vector2d norm(double x,double y,double length){
        double norm=x*x+y*y;
        if(norm>0)
          norm=0.1/norm;
        return new Vector2d(
          Math.signum(x)*Math.sqrt(x*x*norm)*length,
          Math.signum(y)*Math.sqrt(y*y*norm)*length
        );
      }



      public static float angle(float x,float y){
        return (float)Math.atan2(y,x)*180/pi+180;
      }

      public static double angle(double x,double y){
        return Math.atan2(y,x)*180/pi+180;
      }



      public static float angleBetweenVectors(Vector2f v1,Vector2f v2){
        return angle(v1.x-v2.x,v1.y-v2.y);
      }

      public static double angleBetweenVectors(Vector2d v1,Vector2d v2){
        return angle(v1.x-v2.x,v1.y-v2.y);
      }



      public static int module(int i){
        return i<0?-i:i;
      }

      public static float module(float i){
        return i<0?-i:i;
      }

      public static double module(double i){
        return i<0?-i:i;
      }



      public static Vector2f norm(float x1,float y1,float x2,float y2){
        float x=x2-x1;
        float y=y2-y1;
        float norm=x*x+y*y;
        if(norm>0)
          norm=0.1f/norm;
        return new Vector2f(
          Math.signum(x)*(float)Math.sqrt(x*x*norm),
          Math.signum(y)*(float)Math.sqrt(y*y*norm)
        );
      }

      public static Vector2d norm(double x1,double y1,double x2,double y2){
        double x=x2-x1;
        double y=y2-y1;
        double norm=x*x+y*y;
        if(norm>0)
          norm=0.1/norm;
        return new Vector2d(
          Math.signum(x)*Math.sqrt(x*x*norm),
          Math.signum(y)*Math.sqrt(y*y*norm)
        );
      }



      public static Vector2f norm(Vector2f v1,Vector2f v2){
        float x=v2.x-v1.x;
        float y=v2.y-v1.y;
        float norm=x*x+y*y;
        if(norm>0)
          norm=0.1f/norm;
        return new Vector2f(
          Math.signum(x)*(float)Math.sqrt(x*x*norm),
          Math.signum(y)*(float)Math.sqrt(y*y*norm)
        );
      }

      public static Vector2d norm(Vector2d v1,Vector2d v2){
        double x=v2.x-v1.x;
        double y=v2.y-v1.y;
        double norm=x*x+y*y;
        if(norm>0)
          norm=0.1/norm;
        return new Vector2d(
          Math.signum(x)*Math.sqrt(x*x*norm),
          Math.signum(y)*Math.sqrt(y*y*norm)
        );
      }

      public static Vector2f norm(Vector2i v1,Vector2i v2){
        int x=v2.x-v1.x;
        int y=v2.y-v1.y;
        float norm=x*x+y*y;
        if(norm>0)
          norm=0.1f/norm;
        return new Vector2f(
          Math.signum(x)*(float)Math.sqrt(x*x*norm),
          Math.signum(y)*(float)Math.sqrt(y*y*norm)
        );
      }



      public static boolean overlaps(Rectangle r1,Rectangle r2){
        return r1.x<r2.x+r2.width && r1.x+r1.width>r2.x && r1.y<r2.y+r2.height && r1.y+r1.height>r2.y;
      }



      public static float clamp(float v,float min,float max){
        return Math.max(min,Math.min(v,max));
      }



      public static int random(int min,int max){
        return (int)(Math.random()*(max-min)+min);
      }

      public static float random(float min,float max){
        return (float)Math.random()*(max-min)+min;
      }



      public static float dot(Vector3f v1, Vector3f v2){
        return v1.x*v2.x+v1.y*v2.y+v1.z*v2.z;
      }

      public static double dot(Vector3d v1, Vector3d v2){
        return v1.x*v2.x+v1.y*v2.y+v1.z*v2.z;
      }

      public static float dot(Vector2f v1,Vector2f v2){
        return v1.x*v2.x+v1.y*v2.y;
      }

      public static double dot(Vector2d v1,Vector2d v2){
        return v1.x*v2.x+v1.y*v2.y;
      }

      public static float dot(float x1,float y1,float x2,float y2){
          return x1*x2+y1*y2;
      }

      public static double dot(double x1,double y1,double x2,double y2){
          return x1*x2+y1*y2;
      }





      public static Vector3f cross(Vector3f v1,Vector3f v2){
        return new Vector3f(
                v1.y*v2.z-v1.z*v2.y,
                v1.z*v2.x-v1.x*v2.z,
                v1.x*v2.y-v1.y*v2.x
        );
      }

      public static Vector3d cross(Vector3d v1,Vector3d v2){
        return new Vector3d(
                v1.y*v2.z-v1.z*v2.y,
                v1.z*v2.x-v1.x*v2.z,
                v1.x*v2.y-v1.y*v2.x
        );
      }



      public static long randomSeed(int c){
        if(c>0){
            String seed="";
            for(int i=0; i<c; i++){
                if(i==0)
                    seed+=random(1,9);
                else
                    seed+=random(0,9);
            }
            return Long.parseLong(seed);
        }
        return 0;
      }



        public static Vector2f rotateMatrix(float x,float y,float a){
            float nx=(float)(x*Math.cos(a/180f*pi)-y*Math.sin(a/180f*pi));
            float ny=(float)(x*Math.sin(a/180f*pi)+y*Math.cos(a/180f*pi));
            return new Vector2f(nx,ny);
        }

        public static Vector2f rotateMatrix(Vector2f v,float a){
            float nx=(float)(v.x*Math.cos(a/180f*pi)-v.y*Math.sin(a/180f*pi));
            float ny=(float)(v.x*Math.sin(a/180f*pi)+v.y*Math.cos(a/180f*pi));
            return new Vector2f(nx,ny);
        }

        public static Vector2d rotateMatrix(double x,double y,float a){
            double nx=x*Math.cos(a/180f*pi)-y*Math.sin(a/180f*pi);
            double ny=x*Math.sin(a/180f*pi)+y*Math.cos(a/180f*pi);
            return new Vector2d(nx,ny);
        }

        public static Vector2d rotateMatrix(Vector2d v,float a){
            double nx=v.x*Math.cos(a/180f*pi)-v.y*Math.sin(a/180f*pi);
            double ny=v.x*Math.sin(a/180f*pi)+v.y*Math.cos(a/180f*pi);
            return new Vector2d(nx,ny);
        }

        public static Vector2f rotateMatrix(float x,float y,Vector2f o,float a){
            float nx=(float)((x-o.x)*Math.cos(a/180f*pi)-(y-o.y)*Math.sin(a/180f*pi))+o.x;
            float ny=(float)((x-o.x)*Math.sin(a/180f*pi)+(y-o.y)*Math.cos(a/180f*pi))+o.y;
            return new Vector2f(nx,ny);
        }

        public static Vector2f rotateMatrix(Vector2f v,Vector2f o,float a){
            float nx=(float)((v.x-o.x)*Math.cos(a/180f*pi)-(v.y-o.y)*Math.sin(a/180f*pi))+o.x;
            float ny=(float)((v.x-o.x)*Math.sin(a/180f*pi)+(v.y-o.y)*Math.cos(a/180f*pi))+o.y;
            return new Vector2f(nx,ny);
        }

        public static Vector2d rotateMatrix(double x,double y,Vector2d o,float a){
            double nx=(x-o.x)*Math.cos(a/180f*pi)-(y-o.y)*Math.sin(a/180f*pi)+o.x;
            double ny=(x-o.x)*Math.sin(a/180f*pi)+(y-o.y)*Math.cos(a/180f*pi)+o.y;
            return new Vector2d(nx,ny);
        }

        public static Vector2d rotateMatrix(Vector2d v,Vector2d o,float a){
            double nx=(v.x-o.x)*Math.cos(a/180f*pi)-(v.y-o.y)*Math.sin(a/180f*pi)+o.x;
            double ny=(v.x-o.x)*Math.sin(a/180f*pi)+(v.y-o.y)*Math.cos(a/180f*pi)+o.y;
            return new Vector2d(nx,ny);
        }

        public static Vector2f rotateMatrix(float x,float y,float ox,float oy,float a){
            float nx=(float)((x-ox)*Math.cos(a/180f*pi)-(y-oy)*Math.sin(a/180f*pi))+ox;
            float ny=(float)((x-ox)*Math.sin(a/180f*pi)+(y-oy)*Math.cos(a/180f*pi))+oy;
            return new Vector2f(nx,ny);
        }

        public static Vector2f rotateMatrix(Vector2f v,float ox,float oy,float a){
            float nx=(float)((v.x-ox)*Math.cos(a/180f*pi)-(v.y-oy)*Math.sin(a/180f*pi))+ox;
            float ny=(float)((v.x-ox)*Math.sin(a/180f*pi)+(v.y-oy)*Math.cos(a/180f*pi))+oy;
            return new Vector2f(nx,ny);
        }

        public static Vector2d rotateMatrix(double x,double y,double ox,double oy,float a){
            double nx=(x-ox)*Math.cos(a/180f*pi)-(y-oy)*Math.sin(a/180f*pi)+ox;
            double ny=(x-ox)*Math.sin(a/180f*pi)+(y-oy)*Math.cos(a/180f*pi)+oy;
            return new Vector2d(nx,ny);
        }
  
  
}
