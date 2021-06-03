package engine.physics;

import engine.math.Maths;
import engine.math.vectors.Vector2f;

import java.util.List;

public class Collider{


    public static Vector2f calc(ColissionBody b1,List<ColissionBody> b2){
        if(b1.velocity.x>0&&b1.velocity.y==0)
            return new Vector2f(calcRight(b1,b2),0);
        if(b1.velocity.x<0&&b1.velocity.y==0)
            return new Vector2f(calcLeft(b1,b2),0);
        if(b1.velocity.x==0&&b1.velocity.y>0)
            return new Vector2f(0,calcUp(b1,b2));
        if(b1.velocity.x==0&&b1.velocity.y<0)
            return new Vector2f(0,calcDown(b1,b2));
        if(b1.velocity.x>0&&b1.velocity.y>0)
            return calcRightUp(b1,b2);
        if(b1.velocity.x<0&&b1.velocity.y<0)
            return calcLeftDown(b1,b2);
        if(b1.velocity.x>0&&b1.velocity.y<0)
            return calcRightDown(b1,b2);
        if(b1.velocity.x<0&&b1.velocity.y>0)
            return calcLeftUp(b1,b2);
        return new Vector2f(0,0);
    }

    public static boolean collide(ColissionBody b1,List<ColissionBody> b2){
        return isCollisionDown(b1,b2) || isCollisionUp(b1,b2) || isCollisionLeft(b1,b2) || isCollisionRight(b1,b2);
    }

    public static boolean isCollisionRight(ColissionBody b1,List<ColissionBody> b2){
        return overlaps(new ColissionBody(b1).translate(1,0),b2);
    }

    public static boolean isCollisionLeft(ColissionBody b1,List<ColissionBody> b2){
        return overlaps(new ColissionBody(b1).translate(-1,0),b2);
    }

    public static boolean isCollisionUp(ColissionBody b1,List<ColissionBody> b2){
        return overlaps(new ColissionBody(b1).translate(0,1),b2);
    }

    public static boolean isCollisionDown(ColissionBody b1,List<ColissionBody> b2){
        return overlaps(new ColissionBody(b1).translate(0,-1),b2);
    }

    static Vector2f slide(ColissionBody b1,List<ColissionBody> b2,float vx,float vy,int cx,int cy){

        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)&&overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)){
            return new Vector2f(cx,cy);
        }

        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
        }

        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
        }

        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)&&overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)){
            if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
        }

        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }


        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)){
            if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
        }

        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)){
            if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
            else if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)){
            if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
            else if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
        }
        if(overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
        }
        return new Vector2f(cx,cy);
    }

    public static Vector2f calcRightUp(ColissionBody b1,List<ColissionBody> b2){
        if(b1.velocity.x>b1.velocity.y){
            float k=b1.velocity.y/b1.velocity.x;
            float y=k;
            for(float x=1; x<b1.velocity.x+1; x++){
                if(x>=0&&y>=0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-1);
                    int cy=Math.round(y-k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x+1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                y+=k;
                if(x>=0&&y>=0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-1);
                    int cy=Math.round(y-k);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x+1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else if(b1.velocity.y>b1.velocity.x){
            float k=b1.velocity.x/b1.velocity.y;
            float x=k;
            for(float y=1; y<b1.velocity.y+1; y++){
                if(x>0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-k);
                    int cy=Math.round(y-1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x+k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                x+=k;
                if(x>0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-k);
                    int cy=Math.round(y-1);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x+k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else{
            for(int v=0; v<b1.velocity.x+1; v++){
                if(v>0&&overlaps(new ColissionBody(b1).translate(v,v),b2))
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,v-1,v-1);
            }
        }
        return new Vector2f(b1.velocity.x,b1.velocity.y);
    }

    public static Vector2f calcLeftUp(ColissionBody b1,List<ColissionBody> b2){
        if(-b1.velocity.x>b1.velocity.y){
            float k=-b1.velocity.y/b1.velocity.x;
            float y=0;
            for(float x=0; x>b1.velocity.x-1; x--){
                if(x<0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x+1);
                    int cy=Math.round(y-k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                y+=k;
                if(x<0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x+1);
                    int cy=Math.round(y-k);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x-1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else if(b1.velocity.y>-b1.velocity.x){
            float k=-b1.velocity.x/b1.velocity.y;
            float x=0;
            for(float y=0; y<b1.velocity.y+1; y++){
                if(x<0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x+k);
                    int cy=Math.round(y-1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                x-=k;
                if(x<0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x+k);
                    int cy=Math.round(y-1);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x-k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else{
            for(int v=0; v>b1.velocity.x-1; v--){
                if(v<0&&overlaps(new ColissionBody(b1).translate(v,-v),b2))
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,v+1,-v-1);
            }
        }
        return new Vector2f(b1.velocity.x,b1.velocity.y);
    }

    public static Vector2f calcRightDown(ColissionBody b1,List<ColissionBody> b2){
        if(b1.velocity.x>-b1.velocity.y){
            float k=-b1.velocity.y/b1.velocity.x;
            float y=0;
            for(float x=0; x<b1.velocity.x+1; x++){
                if(x>0&&y<0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-1);
                    int cy=Math.round(y+k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x+1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                y-=k;
                if(x>0&&y<0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-1);
                    int cy=Math.round(y+k);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x+1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else if(-b1.velocity.y>b1.velocity.x){
            float k=-b1.velocity.x/b1.velocity.y;
            float x=0;
            for(float y=0; y>b1.velocity.y-1; y--){
                if(x>0&&y<0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-k);
                    int cy=Math.round(y+1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x+k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                x+=k;
                if(x>0&&y<0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-k);
                    int cy=Math.round(y+1);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x+k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else{
            for(int v=0; v<b1.velocity.x+1; v++){
                if(v>0&&overlaps(new ColissionBody(b1).translate(v,-v),b2))
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,v-1,-v+1);
            }
        }
        return new Vector2f(b1.velocity.x,b1.velocity.y);
    }

    public static Vector2f calcLeftDown(ColissionBody b1,List<ColissionBody> b2){
        if(b1.velocity.x<b1.velocity.y){
            float k=b1.velocity.y/b1.velocity.x;
            float y=0;
            for(float x=0; x>b1.velocity.x-1; x--){
                if(x<0&&y<0&&overlaps(new ColissionBody(b1).translate((int)Math.floor(x),(int)Math.floor(y)),b2)){
                    int cx=Math.round(x+1);
                    int cy=Math.round(y+k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                y-=k;
                if(x<0&&y<0&&overlaps(new ColissionBody(b1).translate((int)Math.floor(x),(int)Math.floor(y)),b2)){
                    int cx=Math.round(x+1);
                    int cy=Math.round(y+k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else if(b1.velocity.y<b1.velocity.x){
            float k=b1.velocity.x/b1.velocity.y;
            float x=0;
            for(float y=0; y>b1.velocity.y-1; y--){
                if(x<0&&y<0&&overlaps(new ColissionBody(b1).translate((int)Math.floor(x),(int)Math.floor(y)),b2)){
                    int cx=Math.round(x+k);
                    int cy=Math.round(y+1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                x-=k;
                if(x<0&&y<0&&overlaps(new ColissionBody(b1).translate((int)Math.floor(x),(int)Math.floor(y)),b2)){
                    int cx=Math.round(x+k);
                    int cy=Math.round(y+1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else{
            for(int v=0; v>b1.velocity.x-1; v--){
                if(v<0&&overlaps(new ColissionBody(b1).translate(v,v),b2))
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,v+1,v+1);
            }
        }
        return new Vector2f(b1.velocity.x,b1.velocity.y);
    }

    public static int calcRight(ColissionBody b1,List<ColissionBody> b2){
        for(int v=0; v<b1.velocity.x+1; v++){
            if(v>0&&overlaps(new ColissionBody(b1).translate(v,0),b2))
                return v-1;
        }
        return (int)b1.velocity.x;
    }

    public static int calcLeft(ColissionBody b1,List<ColissionBody> b2){
        for(int v=0; v>b1.velocity.x-1; v--){
            if(v<0&&overlaps(new ColissionBody(b1).translate(v,0),b2))
                return v+1;
        }
        return (int)b1.velocity.x;
    }

    public static int calcUp(ColissionBody b1,List<ColissionBody> b2){
        for(int v=0; v<b1.velocity.y+1; v++){
            if(v>0&&overlaps(new ColissionBody(b1).translate(0,v),b2))
                return v-1;
        }
        return (int)b1.velocity.y;
    }

    public static int calcDown(ColissionBody b1,List<ColissionBody> b2){
        for(int v=0; v>b1.velocity.y-1; v--){
            if(v<0&&overlaps(new ColissionBody(b1).translate(0,v),b2))
                return v+1;
        }
        return (int)b1.velocity.y;
    }

    public static boolean overlaps(ColissionBody b1,List<ColissionBody> b2){
        for(ColissionBody b:b2){
            if(Maths.overlaps(b1.x,b1.y,b1.width,b1.height,b.x,b.y,b.width,b.height))
                return true;
        }
        
        return false;
    }



    public static Vector2f calc(ColissionBody b1,ColissionBody[] b2){
        if(b1.velocity.x>0&&b1.velocity.y==0)
            return new Vector2f(calcRight(b1,b2),0);
        if(b1.velocity.x<0&&b1.velocity.y==0)
            return new Vector2f(calcLeft(b1,b2),0);
        if(b1.velocity.x==0&&b1.velocity.y>0)
            return new Vector2f(0,calcUp(b1,b2));
        if(b1.velocity.x==0&&b1.velocity.y<0)
            return new Vector2f(0,calcDown(b1,b2));
        if(b1.velocity.x>0&&b1.velocity.y>0)
            return calcRightUp(b1,b2);
        if(b1.velocity.x<0&&b1.velocity.y<0)
            return calcLeftDown(b1,b2);
        if(b1.velocity.x>0&&b1.velocity.y<0)
            return calcRightDown(b1,b2);
        if(b1.velocity.x<0&&b1.velocity.y>0)
            return calcLeftUp(b1,b2);
        return new Vector2f(0,0);
    }

    public static boolean collide(ColissionBody b1,ColissionBody[] b2){
        return isCollisionDown(b1,b2) || isCollisionUp(b1,b2) || isCollisionLeft(b1,b2) || isCollisionRight(b1,b2);
    }

    public static boolean isCollisionRight(ColissionBody b1,ColissionBody[] b2){
        return overlaps(new ColissionBody(b1).translate(1,0),b2);
    }

    public static boolean isCollisionLeft(ColissionBody b1,ColissionBody[] b2){
        return overlaps(new ColissionBody(b1).translate(-1,0),b2);
    }

    public static boolean isCollisionUp(ColissionBody b1,ColissionBody[] b2){
        return overlaps(new ColissionBody(b1).translate(0,1),b2);
    }

    public static boolean isCollisionDown(ColissionBody b1,ColissionBody[] b2){
        return overlaps(new ColissionBody(b1).translate(0,-1),b2);
    }

    static Vector2f slide(ColissionBody b1,ColissionBody[] b2,float vx,float vy,int cx,int cy){

        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)&&overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)){
            return new Vector2f(cx,cy);
        }

        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
        }

        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
        }

        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)&&overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)){
            if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
        }

        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }


        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)){
            if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
        }

        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)){
            if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
            else if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)){
            if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
            else if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
        }
        if(overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
        }
        return new Vector2f(cx,cy);
    }

    public static Vector2f calcRightUp(ColissionBody b1,ColissionBody[] b2){
        if(b1.velocity.x>b1.velocity.y){
            float k=b1.velocity.y/b1.velocity.x;
            float y=k;
            for(float x=1; x<b1.velocity.x+1; x++){
                if(x>=0&&y>=0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-1);
                    int cy=Math.round(y-k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x+1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                y+=k;
                if(x>=0&&y>=0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-1);
                    int cy=Math.round(y-k);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x+1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else if(b1.velocity.y>b1.velocity.x){
            float k=b1.velocity.x/b1.velocity.y;
            float x=k;
            for(float y=1; y<b1.velocity.y+1; y++){
                if(x>0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-k);
                    int cy=Math.round(y-1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x+k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                x+=k;
                if(x>0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-k);
                    int cy=Math.round(y-1);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x+k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else{
            for(int v=0; v<b1.velocity.x+1; v++){
                if(v>0&&overlaps(new ColissionBody(b1).translate(v,v),b2))
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,v-1,v-1);
            }
        }
        return new Vector2f(b1.velocity.x,b1.velocity.y);
    }

    public static Vector2f calcLeftUp(ColissionBody b1,ColissionBody[] b2){
        if(-b1.velocity.x>b1.velocity.y){
            float k=-b1.velocity.y/b1.velocity.x;
            float y=0;
            for(float x=0; x>b1.velocity.x-1; x--){
                if(x<0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x+1);
                    int cy=Math.round(y-k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                y+=k;
                if(x<0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x+1);
                    int cy=Math.round(y-k);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x-1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else if(b1.velocity.y>-b1.velocity.x){
            float k=-b1.velocity.x/b1.velocity.y;
            float x=0;
            for(float y=0; y<b1.velocity.y+1; y++){
                if(x<0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x+k);
                    int cy=Math.round(y-1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                x-=k;
                if(x<0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x+k);
                    int cy=Math.round(y-1);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x-k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else{
            for(int v=0; v>b1.velocity.x-1; v--){
                if(v<0&&overlaps(new ColissionBody(b1).translate(v,-v),b2))
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,v+1,-v-1);
            }
        }
        return new Vector2f(b1.velocity.x,b1.velocity.y);
    }

    public static Vector2f calcRightDown(ColissionBody b1,ColissionBody[] b2){
        if(b1.velocity.x>-b1.velocity.y){
            float k=-b1.velocity.y/b1.velocity.x;
            float y=0;
            for(float x=0; x<b1.velocity.x+1; x++){
                if(x>0&&y<0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-1);
                    int cy=Math.round(y+k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x+1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                y-=k;
                if(x>0&&y<0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-1);
                    int cy=Math.round(y+k);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x+1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else if(-b1.velocity.y>b1.velocity.x){
            float k=-b1.velocity.x/b1.velocity.y;
            float x=0;
            for(float y=0; y>b1.velocity.y-1; y--){
                if(x>0&&y<0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-k);
                    int cy=Math.round(y+1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x+k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                x+=k;
                if(x>0&&y<0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-k);
                    int cy=Math.round(y+1);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x+k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else{
            for(int v=0; v<b1.velocity.x+1; v++){
                if(v>0&&overlaps(new ColissionBody(b1).translate(v,-v),b2))
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,v-1,-v+1);
            }
        }
        return new Vector2f(b1.velocity.x,b1.velocity.y);
    }

    public static Vector2f calcLeftDown(ColissionBody b1,ColissionBody[] b2){
        if(b1.velocity.x<b1.velocity.y){
            float k=b1.velocity.y/b1.velocity.x;
            float y=0;
            for(float x=0; x>b1.velocity.x-1; x--){
                if(x<0&&y<0&&overlaps(new ColissionBody(b1).translate((int)Math.floor(x),(int)Math.floor(y)),b2)){
                    int cx=Math.round(x+1);
                    int cy=Math.round(y+k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                y-=k;
                if(x<0&&y<0&&overlaps(new ColissionBody(b1).translate((int)Math.floor(x),(int)Math.floor(y)),b2)){
                    int cx=Math.round(x+1);
                    int cy=Math.round(y+k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else if(b1.velocity.y<b1.velocity.x){
            float k=b1.velocity.x/b1.velocity.y;
            float x=0;
            for(float y=0; y>b1.velocity.y-1; y--){
                if(x<0&&y<0&&overlaps(new ColissionBody(b1).translate((int)Math.floor(x),(int)Math.floor(y)),b2)){
                    int cx=Math.round(x+k);
                    int cy=Math.round(y+1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                x-=k;
                if(x<0&&y<0&&overlaps(new ColissionBody(b1).translate((int)Math.floor(x),(int)Math.floor(y)),b2)){
                    int cx=Math.round(x+k);
                    int cy=Math.round(y+1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else{
            for(int v=0; v>b1.velocity.x-1; v--){
                if(v<0&&overlaps(new ColissionBody(b1).translate(v,v),b2))
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,v+1,v+1);
            }
        }
        return new Vector2f(b1.velocity.x,b1.velocity.y);
    }

    public static int calcRight(ColissionBody b1,ColissionBody[] b2){
        for(int v=0; v<b1.velocity.x+1; v++){
            if(v>0&&overlaps(new ColissionBody(b1).translate(v,0),b2))
                return v-1;
        }
        return (int)b1.velocity.x;
    }

    public static int calcLeft(ColissionBody b1,ColissionBody[] b2){
        for(int v=0; v>b1.velocity.x-1; v--){
            if(v<0&&overlaps(new ColissionBody(b1).translate(v,0),b2))
                return v+1;
        }
        return (int)b1.velocity.x;
    }

    public static int calcUp(ColissionBody b1,ColissionBody[] b2){
        for(int v=0; v<b1.velocity.y+1; v++){
            if(v>0&&overlaps(new ColissionBody(b1).translate(0,v),b2))
                return v-1;
        }
        return (int)b1.velocity.y;
    }

    public static int calcDown(ColissionBody b1,ColissionBody[] b2){
        for(int v=0; v>b1.velocity.y-1; v--){
            if(v<0&&overlaps(new ColissionBody(b1).translate(0,v),b2))
                return v+1;
        }
        return (int)b1.velocity.y;
    }

    public static boolean overlaps(ColissionBody b1,ColissionBody[] b2){
        for(ColissionBody b:b2){
            if(Maths.overlaps(b1.x,b1.y,b1.width,b1.height,b.x,b.y,b.width,b.height))
                return true;
        }

        return false;
    }




    public static Vector2f calc(ColissionBody b1,ColissionBody b2){
        if(b1.velocity.x>0&&b1.velocity.y==0)
            return new Vector2f(calcRight(b1,b2),0);
        if(b1.velocity.x<0&&b1.velocity.y==0)
            return new Vector2f(calcLeft(b1,b2),0);
        if(b1.velocity.x==0&&b1.velocity.y>0)
            return new Vector2f(0,calcUp(b1,b2));
        if(b1.velocity.x==0&&b1.velocity.y<0)
            return new Vector2f(0,calcDown(b1,b2));
        if(b1.velocity.x>0&&b1.velocity.y>0)
            return calcRightUp(b1,b2);
        if(b1.velocity.x<0&&b1.velocity.y<0)
            return calcLeftDown(b1,b2);
        if(b1.velocity.x>0&&b1.velocity.y<0)
            return calcRightDown(b1,b2);
        if(b1.velocity.x<0&&b1.velocity.y>0)
            return calcLeftUp(b1,b2);
        return new Vector2f(0,0);
    }

    public static boolean collide(ColissionBody b1,ColissionBody b2){
        return isCollisionDown(b1,b2) || isCollisionUp(b1,b2) || isCollisionLeft(b1,b2) || isCollisionRight(b1,b2);
    }

    public static boolean isCollisionRight(ColissionBody b1,ColissionBody b2){
        return overlaps(new ColissionBody(b1).translate(1,0),b2);
    }

    public static boolean isCollisionLeft(ColissionBody b1,ColissionBody b2){
        return overlaps(new ColissionBody(b1).translate(-1,0),b2);
    }

    public static boolean isCollisionUp(ColissionBody b1,ColissionBody b2){
        return overlaps(new ColissionBody(b1).translate(0,1),b2);
    }

    public static boolean isCollisionDown(ColissionBody b1,ColissionBody b2){
        return overlaps(new ColissionBody(b1).translate(0,-1),b2);
    }

    static Vector2f slide(ColissionBody b1,ColissionBody b2,float vx,float vy,int cx,int cy){
        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)&&overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)){
            return new Vector2f(cx,cy);
        }

        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
        }

        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
        }

        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)&&overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)){
            if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
        }

        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }


        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)){
            if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)&&overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
        }

        if(overlaps(new ColissionBody(b1).translate(cx+1,cy),b2)){
            if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
            else if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx-1,cy),b2)){
            if(vy>0)
                return new Vector2f(cx,calcUp(new ColissionBody(b1).translate(cx,cy),b2));
            else if(vy<0)
                return new Vector2f(cx,calcDown(new ColissionBody(b1).translate(cx,cy),b2));
        }
        if(overlaps(new ColissionBody(b1).translate(cx,cy+1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
        }
        if(overlaps(new ColissionBody(b1).translate(cx,cy-1),b2)){
            if(vx>0)
                return new Vector2f(calcRight(new ColissionBody(b1).translate(cx,cy),b2),cy);
            else if(vx<0)
                return new Vector2f(calcLeft(new ColissionBody(b1).translate(cx,cy),b2),cy);
        }
        return new Vector2f(cx,cy);
    }

    public static Vector2f calcRightUp(ColissionBody b1,ColissionBody b2){
        if(b1.velocity.x>b1.velocity.y){
            float k=b1.velocity.y/b1.velocity.x;
            float y=k;
            for(float x=1; x<b1.velocity.x+1; x++){
                if(x>=0&&y>=0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-1);
                    int cy=Math.round(y-k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x+1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                y+=k;
                if(x>=0&&y>=0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-1);
                    int cy=Math.round(y-k);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x+1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else if(b1.velocity.y>b1.velocity.x){
            float k=b1.velocity.x/b1.velocity.y;
            float x=k;
            for(float y=1; y<b1.velocity.y+1; y++){
                if(x>0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-k);
                    int cy=Math.round(y-1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x+k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                x+=k;
                if(x>0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-k);
                    int cy=Math.round(y-1);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x+k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else{
            for(int v=0; v<b1.velocity.x+1; v++){
                if(v>0&&overlaps(new ColissionBody(b1).translate(v,v),b2))
                    return new Vector2f(v-1,v-1);
            }
        }
        return new Vector2f(b1.velocity.x,b1.velocity.y);
    }

    public static Vector2f calcLeftUp(ColissionBody b1,ColissionBody b2){
        if(-b1.velocity.x>b1.velocity.y){
            float k=-b1.velocity.y/b1.velocity.x;
            float y=0;
            for(float x=0; x>b1.velocity.x-1; x--){
                if(x<0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x+1);
                    int cy=Math.round(y-k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                y+=k;
                if(x<0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x+1);
                    int cy=Math.round(y-k);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x-1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else if(b1.velocity.y>-b1.velocity.x){
            float k=-b1.velocity.x/b1.velocity.y;
            float x=0;
            for(float y=0; y<b1.velocity.y+1; y++){
                if(x<0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x+k);
                    int cy=Math.round(y-1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                x-=k;
                if(x<0&&y>0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x+k);
                    int cy=Math.round(y-1);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y+1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x-k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else{
            for(int v=0; v>b1.velocity.x-1; v--){
                if(v<0&&overlaps(new ColissionBody(b1).translate(v,-v),b2))
                    return new Vector2f(v+1,-v-1);
            }
        }
        return new Vector2f(b1.velocity.x,b1.velocity.y);
    }

    public static Vector2f calcRightDown(ColissionBody b1,ColissionBody b2){
        if(b1.velocity.x>-b1.velocity.y){
            float k=-b1.velocity.y/b1.velocity.x;
            float y=0;
            for(float x=0; x<b1.velocity.x+1; x++){
                if(x>0&&y<0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-1);
                    int cy=Math.round(y+k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x+1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                y-=k;
                if(x>0&&y<0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-1);
                    int cy=Math.round(y+k);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x+1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else if(-b1.velocity.y>b1.velocity.x){
            float k=-b1.velocity.x/b1.velocity.y;
            float x=0;
            for(float y=0; y>b1.velocity.y-1; y--){
                if(x>0&&y<0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-k);
                    int cy=Math.round(y+1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x+k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                x+=k;
                if(x>0&&y<0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y)),b2)){
                    int cx=Math.round(x-k);
                    int cy=Math.round(y+1);
                    if(cy>0&&cx==0&&overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&overlaps(new ColissionBody(b1).translate(Math.round(x+k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else{
            for(int v=0; v<b1.velocity.x+1; v++){
                if(v>0&&overlaps(new ColissionBody(b1).translate(v,-v),b2))
                    return new Vector2f(v-1,-v+1);
            }
        }
        return new Vector2f(b1.velocity.x,b1.velocity.y);
    }

    public static Vector2f calcLeftDown(ColissionBody b1,ColissionBody b2){
        if(b1.velocity.x<b1.velocity.y){
            float k=b1.velocity.y/b1.velocity.x;
            float y=0;
            for(float x=0; x>b1.velocity.x-1; x--){
                if(x<0&&y<0&&overlaps(new ColissionBody(b1).translate((int)Math.floor(x),(int)Math.floor(y)),b2)){
                    int cx=Math.round(x+1);
                    int cy=Math.round(y+k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                y-=k;
                if(x<0&&y<0&&overlaps(new ColissionBody(b1).translate((int)Math.floor(x),(int)Math.floor(y)),b2)){
                    int cx=Math.round(x+1);
                    int cy=Math.round(y+k);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-k)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-1),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else if(b1.velocity.y<b1.velocity.x){
            float k=b1.velocity.x/b1.velocity.y;
            float x=0;
            for(float y=0; y>b1.velocity.y-1; y--){
                if(x<0&&y<0&&overlaps(new ColissionBody(b1).translate((int)Math.floor(x),(int)Math.floor(y)),b2)){
                    int cx=Math.round(x+k);
                    int cy=Math.round(y+1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
                x-=k;
                if(x<0&&y<0&&overlaps(new ColissionBody(b1).translate((int)Math.floor(x),(int)Math.floor(y)),b2)){
                    int cx=Math.round(x+k);
                    int cy=Math.round(y+1);
                    if(cy>0&&cx==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x),Math.round(y-1)),b2))
                        cy=0;
                    if(cx>0&&cy==0&&!overlaps(new ColissionBody(b1).translate(Math.round(x-k),Math.round(y)),b2))
                        cx=0;
                    return slide(b1,b2,b1.velocity.x,b1.velocity.y,cx,cy);
                }
            }
        }else{
            for(int v=0; v>b1.velocity.x-1; v--){
                if(v<0&&overlaps(new ColissionBody(b1).translate(v,v),b2))
                    return new Vector2f(v+1,v+1);
            }
        }
        return new Vector2f(b1.velocity.x,b1.velocity.y);
    }

    public static int calcRight(ColissionBody b1,ColissionBody b2){
        for(int v=0; v<b1.velocity.x+1; v++){
            if(v>0&&overlaps(new ColissionBody(b1).translate(v,0),b2))
                return v-1;
        }
        return (int)b1.velocity.x;
    }

    public static int calcLeft(ColissionBody b1,ColissionBody b2){
        for(int v=0; v>b1.velocity.x-1; v--){
            if(v<0&&overlaps(new ColissionBody(b1).translate(v,0),b2))
                return v+1;
        }
        return (int)b1.velocity.x;
    }

    public static int calcUp(ColissionBody b1,ColissionBody b2){
        for(int v=0; v<b1.velocity.y+1; v++){
            if(v>0&&overlaps(new ColissionBody(b1).translate(0,v),b2))
                return v-1;
        }
        return (int)b1.velocity.y;
    }

    public static int calcDown(ColissionBody b1,ColissionBody b2){
        for(int v=0; v>b1.velocity.y-1; v--){
            if(v<0&&overlaps(new ColissionBody(b1).translate(0,v),b2))
                return v+1;
        }
        return (int)b1.velocity.y;
    }

    public static boolean overlaps(ColissionBody b1,ColissionBody b2){
        return Maths.overlaps(b1.x,b1.y,b1.width,b1.height,b2.x,b2.y,b2.width,b2.height);
    }
    
    
    
}