package com.myterraria;

import engine.Camera2D;
import engine.tiledmap.TileManager;
import engine.tiledmap.TiledMap;
import engine.tiledmap.TMLayer;

public class TiledMapUtils{

    public static void defineTile(TileManager tileManager,int id,String t,int wc,int hc,int w,int h){
        int tw=8;
        int th=8;
        int to=1;
        for(int e=0; e<wc; e++)
            for(int r=0; r<hc; r++)
                tileManager.addTileDataReg(id,e+r*wc,t,e*(tw+to),r*(th+to),tw,th,w,h);
    }

    public static void setWorldData(TiledMap map,int li){
        TMLayer l=map.layer(li);
        for(int x=0; x<l.width; x++)
            for(int y=0; y<l.height; y++)
                l.setTileData(x,y,data(map,li,x,y));
    }

    public static int data(TiledMap map,int l,int ax,int ay){
        if(ax>=0&&ax<map.layer(l).width&&ay>=0&&ay<map.layer(l).height&&map.getTileId(l,ax,ay)!=0){

            int tile=map.getTileId(l,ax,ay);

            if(gtd(map,l,ax-1,ay,tile)>0&&gtd(map,l,ax+1,ay,tile)>0&&gtd(map,l,ax,ay+1,tile)>0&&gtd(map,l,ax,ay-1,tile)>0)//3;3
                return 10;

            if(gtd(map,l,ax+1,ay,tile)>0){//2;1
                if(gtd(map,l,ax,ay+1,tile)>0){//2;4
                    if(gtd(map,l,ax,ay-1,tile)>0)//2;3
                        return 9;
                    if(gtd(map,l,ax-1,ay,tile)>0)//3;4
                        return 14;
                    return 13;
                }
                if(gtd(map,l,ax-1,ay,tile)>0){//3;1
                    if(gtd(map,l,ax,ay-1,tile)>0)//3;2
                        return 6;
                    return 2;
                }
                if(gtd(map,l,ax,ay-1,tile)>0)//2;2
                    return 5;
                return 1;
            }

            if(gtd(map,l,ax-1,ay,tile)>0){//4;1
                if(gtd(map,l,ax,ay-1,tile)>0){//4;2
                    if(gtd(map,l,ax,ay+1,tile)>0)//4;3
                        return 11;
                    return 7;
                }
                if(gtd(map,l,ax,ay+1,tile)>0)//4;4
                    return 15;
                return 3;
            }

            if(gtd(map,l,ax,ay-1,tile)>0){//1;2
                if(gtd(map,l,ax,ay+1,tile)>0){//1;3
                    if(gtd(map,l,ax+1,ay,tile)>0)//2;3
                        return 9;
                    return 8;
                }
                return 4;
            }

            if(gtd(map,l,ax,ay+1,tile)>0)//1;4
                return 12;

        }

        return 0;//1;1
    }

    private static int gtd(TiledMap map,int l,int x,int y,int t){
        if(x>=0 && x<map.layer(l).width && y>=0 && y<map.layer(l).height){
            if(t==4)
                return -3;
            int gt=map.getTileId(l,x,y);
            if(gt!=0 && gt!=4)
                return map.getTileId(l,x,y);
            else return -2;
        }else return -1;
    }

    public static boolean setTile(final TiledMap map,Camera2D cam,int l,int x,int y,int id){
        if(map.getTileId(l,x,y)!=id && map.inBounds(l,x,y)){
            if(id==6)
                l=1;
            if(map.getTileId(l,x,y)==4){
                for(int i=0; i<RecursiveLights.lights.size(); i++){
                    int[] lg=RecursiveLights.lights.get(i);
                    if(lg[0]==x && lg[1]==y){
                        RecursiveLights.lights.remove(i);
                        break;
                    }
                }
            }
            map.setTileId(l,x,y,id);
            if(map.getTileId(l,x,y)!=5 && map.getTileId(l,x,y)!=7)
                map.setTileData(l,x,y,TiledMapUtils.data(map,l,x,y));
            if(map.getTileId(l,x+1,y)!=5 && map.getTileId(l,x+1,y)!=7)
                map.setTileData(l,x+1,y,TiledMapUtils.data(map,l,x+1,y));
            if(map.getTileId(l,x,y+1)!=5 && map.getTileId(l,x,y+1)!=7)
                map.setTileData(l,x,y+1,TiledMapUtils.data(map,l,x,y+1));
            if(map.getTileId(l,x-1,y)!=5 && map.getTileId(l,x-1,y)!=7)
                map.setTileData(l,x-1,y,TiledMapUtils.data(map,l,x-1,y));
            if(map.getTileId(l,x,y-1)!=5 && map.getTileId(l,x,y-1)!=7)
                map.setTileData(l,x,y-1,TiledMapUtils.data(map,l,x,y-1));
            if(id==4){
                RecursiveLights.lights.add(new int[]{x,y,6,22});
            }
            RecursiveLights.updateScreenLights(map,cam);
            return true;
        }
        return false;
    }

}
