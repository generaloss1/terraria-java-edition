package com.myterraria;

import engine.Camera2D;
import engine.math.vectors.Vector2f;
import engine.math.vectors.Vector2i;
import engine.math.vectors.Vector3f;
import engine.tiledmap.TiledMap;

import java.util.ArrayList;
import java.util.List;

public class RecursiveLights{



    public static List<int[]> lights=new ArrayList<>();

    public static int lt=3;
    public static int lw=1;
    public static int[] ignoreTiles={0,1,0,3,4,3};
    public static int[] ignoreTilesU={4,3};

    public static int prevsx,prevsy;

    public static float colormap[][][];

    public static Vector3f color;

    public static void init(TiledMap map){
        colormap=new float[5][map.layer(3).width][map.layer(3).height];
        color=new Vector3f(1,1,1);
    }

    public static void addLight(int x,int y,int t,int a){
        for(int i=0; i<lights.size(); i++){
            int[] lg=lights.get(i);
            if(lg[0]==x && lg[1]==y){
                lights.remove(i);
                break;
            }
        }
        lights.add(new int[]{x,y,t,a});
    }

    public static void removeLight(int x,int y){
        for(int i=0; i<lights.size(); i++){
            int[] lg=lights.get(i);
            if(lg[0]==x && lg[1]==y){
                lights.remove(i);
                break;
            }
        }
    }

    public static void applyLightRec(TiledMap map,int l,int cx,int cy,int mrlt,int mrlw,float lastLight){
        if(lastLight<0)
            return;

        colormap[l][cx][cy]=lastLight;

        float newLight=lastLight;
        boolean bt=true,bw=true;
        for(int i=0; i<ignoreTiles.length; i+=2){
            if(ignoreTiles[i+1]==lt && map.getTileId(ignoreTiles[i+1],cx,cy)==ignoreTiles[i])
                bt=false;
            if(ignoreTiles[i+1]==lw && map.getTileId(ignoreTiles[i+1],cx,cy)==ignoreTiles[i])
                bw=false;
        }

        if(bt && bw){
            newLight-=1f/mrlt;
        }else if(bt){
            newLight-=1f/mrlt;
        }else if(bw){
            newLight-=1f/mrlw;
        }else
            newLight-=1f/mrlw;

        if(cx+1<map.layer(l).width && newLight>colormap[l][cx+1][cy])
            applyLightRec(map,l,cx+1,cy,mrlt,mrlw,newLight);
        if(cy+1<map.layer(l).height && newLight>colormap[l][cx][cy+1])
            applyLightRec(map,l,cx,cy+1,mrlt,mrlw,newLight);
        if(cx-1>=0 && newLight>colormap[l][cx-1][cy])
            applyLightRec(map,l,cx-1,cy,mrlt,mrlw,newLight);
        if(cy-1>=0 && newLight>colormap[l][cx][cy-1])
            applyLightRec(map,l,cx,cy-1,mrlt,mrlw,newLight);
    }



    public static boolean update,inprocess;
    public static Vector2i upd_str,upd_end;



    public static float dayLight=1;



    public static void updateScreenLights(final TiledMap map,final Camera2D cam){
        if(!inprocess){
            inprocess=true;
            new Thread(new Runnable(){
                public void run(){
                    int l=3;
                    Vector2i str=map.layer(3).getStartRender(cam,-10);
                    Vector2i end=map.layer(3).getEndRender(cam,10);
                    for(int x=str.x; x<end.x; x++){
                        for(int y=str.y; y<end.y; y++){
                            colormap[3][x][y]=0;
                            colormap[2][x][y]=0;
                            colormap[1][x][y]=0;
                        }
                    }
                    color.set(1,1,1);
                    for(int x=str.x; x<end.x; x++){
                        for(int y=map.layer(l).height-1; y>-1; y--){
                            boolean b=true;
                            for(int i=0; i<ignoreTilesU.length; i+=2)
                                if(map.getTileId(ignoreTilesU[i+1],x,y)==ignoreTilesU[i])
                                    b=false;
                            if((map.getTileId(1,x,y)!=0 || map.getTileId(3,x,y)!=0) && b){
                                RecursiveLights.applyLightRec(map,3,x,y,6,24,dayLight);
                                RecursiveLights.applyLightRec(map,2,x,y,6,24,dayLight);
                                RecursiveLights.applyLightRec(map,1,x,y,6,24,dayLight);
                                break;
                            }else if(map.getTileId(2,x,y)==5)
                                colormap[2][x][y]=dayLight;
                        }
                    }
                    color.set(1,1,1);
                    for(int i=0; i<lights.size(); i++){
                        int lg[]=lights.get(i);
                        RecursiveLights.applyLightRec(map,3,lg[0],lg[1],lg[2],lg[3],1);
                        RecursiveLights.applyLightRec(map,2,lg[0],lg[1],lg[2],lg[3],1);
                        RecursiveLights.applyLightRec(map,1,lg[0],lg[1],lg[2],lg[3],1);
                    }
                    upd_str=str;
                    upd_end=end;
                    update=true;
                }
            }).start();
        }
    }



    private static void updateLightsOnMap(Vector2i str,Vector2i end,TiledMap map){
        for(int x=str.x; x<end.x; x++)
            for(int y=str.y; y<end.y; y++){
                map.layer(3).colormap[x][y][0]=colormap[1][x][y];
                map.layer(3).colormap[x][y][1]=colormap[1][x][y];
                map.layer(3).colormap[x][y][2]=colormap[1][x][y];

                map.layer(2).colormap[x][y][0]=colormap[2][x][y];
                map.layer(2).colormap[x][y][1]=colormap[2][x][y];
                map.layer(2).colormap[x][y][2]=colormap[2][x][y];

                map.layer(1).colormap[x][y][0]=colormap[3][x][y];
                map.layer(1).colormap[x][y][1]=colormap[3][x][y];
                map.layer(1).colormap[x][y][2]=colormap[3][x][y];
            }
    }



    public static void update(TiledMap map,Camera2D cam){
        Vector2i v=map.layer(3).getStartRender(cam);
        if(v.x!=prevsx || v.y!=prevsy){
            updateScreenLights(map,cam);
            prevsx=v.x;
            prevsy=v.y;
        }

        if(update){
            updateLightsOnMap(upd_str,upd_end,map);
            update=false;
            inprocess=false;
        }
    }



}
