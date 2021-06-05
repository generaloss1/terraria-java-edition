package com.myterraria;

import engine.math.SimplexNoise;
import engine.tiledmap.TiledMap;

import java.util.Random;

public class WorldGenerator{


    public static boolean isGenerated;
    public static String stage="";
    public static float percents=0;
    public static String lang="en";


    public static void generate(final TiledMap map,final long seed,final int surface){
        generator=new Random(seed);
        
        final int l=3,l2=1;

        Thread thread=new Thread(new Runnable(){
            public void run(){
                stage=lang=="en"?"Generate surface":"Генерация поверхности";
                percents=0;
                SimplexNoise noise=new SimplexNoise();
                noise.genGrad(seed);
                for(int x=0; x<map.layer(l).width; x++){
                    percents+=100f/map.layer(l).width;
                    double ng=noise.generate((float)(x*(1.0/100)*2),x*(1.0f/50))*5 + noise.generate(x*(1.0f/50)*2,x*(1.0f/15))*2;
                    int py=(int)(ng+surface+new SimplexNoise().genGrad(seed).generate(x*(1.0f/200),x*(1.0f/200))*15);
                    map.setTile(l,x,py,1,0);
                    for(int y=py-1;y>=0; y--)
                        map.setTile(l,x,y,2,0);
                }
                stage=lang=="en"?"Generate walls":"Генерация стен";
                percents=0;
                for(int x=0; x<map.layer(l).width; x++){
                    percents+=100f/map.layer(l).width;
                    double ng=noise.generate((float)(x*(1.0/100)*2),x*(1.0f/50))*5 + noise.generate(x*(1.0f/50)*2,x*(1.0f/15))*2;
                    int py=(int)(ng+surface+new SimplexNoise().genGrad(seed).generate(x*(1.0f/200),x*(1.0f/200))*15);
                    for(int y=py-1;y>2; y--)
                        map.setTile(l2,x,y-3,6,0);

                    //double ng3=noise.generate((float)(x*(1.0/175)),(float)(x*(1.0/55)))*14;
                    //int py2=(int)((ng3+ng)/2+40);
                }
                stage=lang=="en"?"Generate stone deposits":"Генерация залежей камня";
                percents=0;
                for(int x=0; x<map.layer(l).width; x++){
                    percents+=100f/map.layer(l).width;
                    double ng=noise.generate((float)(x*(1.0/100)*2),x*(1.0f/50))*5 + noise.generate(x*(1.0f/50)*2,x*(1.0f/15))*2;
                    int py=(int)(ng+surface+new SimplexNoise().genGrad(seed).generate(x*(1.0f/200),x*(1.0f/200))*15);
                    for(int y=py;y>=0; y--){
                        double ng3=(
                                new SimplexNoise().genGrad(seed).generate(x*(1.0f/42),y*(1.0f/42))*0.5 +
                                        new SimplexNoise().genGrad(seed+1).generate(x*(1.0f/21),y*(1.0f/21))*0.3 +
                                        new SimplexNoise().genGrad(seed+2).generate(x*(1.0f/10),y*(1.0f/10))*0.1
                        )/3;

                        if(ng3>0.15)
                            map.setTile(l,x,y,3,0);
                    }
                }

                stage=lang=="en"?"Generate caves":"Генерация пещер";
                percents=0;
                for(int x=0; x<map.layer(l).width; x++){
                    percents+=100f/map.layer(l).width;
                    double ng=noise.generate((float)(x*(1.0/100)*2),x*(1.0f/50))*5 + noise.generate(x*(1.0f/50)*2,x*(1.0f/15))*2;
                    int py=(int)(ng+surface+new SimplexNoise().genGrad(seed).generate(x*(1.0f/200),x*(1.0f/200))*15);
                    for(int y=py;y>=0; y--){
                        double ng3=(
                                new SimplexNoise().genGrad(seed).generate(x*(1.0f/300),y*(1.0f/300)) +
                                        new SimplexNoise().genGrad(seed+1).generate(x*(1.0f/70),y*(1.0f/70)) +
                                        new SimplexNoise().genGrad(seed+2).generate(x*(1.0f/25),y*(1.0f/25))
                        )/3;
                        //map.layer(3).blackmap[x][y]=(float)ng3;
                        if(ng3>0.35)
                            map.setTile(l,x,y,0,0);
                        if(ng3>0.3)
                            if(map.getTileId(1,x,y)!=0)
                                map.setTileId(1,x,y,65);
                    }
                }

                stage=lang=="en"?"Generate other caves":"Генерация других пещер";
                percents=0;
                int last_cave_x=0;
                //generator=new Random(seed);
                for(int x=0; x<map.layer(l).width; x++){
                    percents+=100f/map.layer(l).width/2f;
                    if(randomBoolean(0.05f) && x-last_cave_x>=50){
                        last_cave_x=x;
                        for(int y=map.layer(l).height; y>0; y--){
                            if(map.getTileId(l,x,y)!=0){
                                int cave_lenght=random(50,200);
                                for(int lg=0; lg<cave_lenght; lg++){
                                    int py=y-lg;
                                    int px=Math.round(x+noise.generate((float)((x+py)*(1.0/100)*2),(x+py)*(1.0f/50))*5 + noise.generate((x+py)*(1.0f/50)*2,(x+py)*(1.0f/15))*2+new SimplexNoise().genGrad(seed).generate((x+py)*(1.0f/200),(x+py)*(1.0f/200))*100);
                                    int r=Math.round((noise.generate((x+py)*(1.0f/50),(x+py)*(1.0f/50))+noise.generate((x+py)*(1.0f/20),(x+py)*(1.0f/20)))/2f*3+6);
                                    for(int i=-r-1; i<=r+1; i++){
                                        for(int j=-r-1; j<=r+1; j++){
                                            if(Math.hypot(i,j)<=r+1){
                                                map.setTileId(l,px+i,py+j,1);
                                                if(map.getTileId(1,px+i,py+j)!=0)
                                                    map.setTileId(1,px+i,py+j,65);
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                last_cave_x=0;
                //generator=new Random(seed);
                for(int x=0; x<map.layer(l).width; x++){
                    percents+=100f/map.layer(l).width/2f;
                    if(randomBoolean(0.01f) && x-last_cave_x>=50){
                        last_cave_x=x;
                        for(int y=map.layer(l).height; y>0; y--){
                            if(map.getTileId(l,x,y)!=0){
                                int cave_lenght=random(50,200);
                                for(int lg=0; lg<cave_lenght; lg++){
                                    int py=y-lg;
                                    int px=Math.round(x+noise.generate((float)((x+py)*(1.0/100)*2),(x+py)*(1.0f/50))*5 + noise.generate((x+py)*(1.0f/50)*2,(x+py)*(1.0f/15))*2+new SimplexNoise().genGrad(seed).generate((x+py)*(1.0f/200),(x+py)*(1.0f/200))*100);
                                    int r=Math.round((noise.generate((x+py)*(1.0f/50),(x+py)*(1.0f/50))+noise.generate((x+py)*(1.0f/20),(x+py)*(1.0f/20)))/2f*3+6);
                                    for(int i=-r; i<=r; i++){
                                        for(int j=-r; j<=r; j++){
                                            if(Math.hypot(i,j)<=r){
                                                map.setTileId(l,px+i,py+j,0);
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                }

                for(int i=0; i<map.layer(l).width; i++){
                    for(int j=0; j<map.layer(l).height; j++){
                        if(map.getTileId(l,i,j)==1 && map.getTileId(l,i+1,j)!=0 && map.getTileId(l,i,j+1)!=0 && map.getTileId(l,i-1,j)!=0 && map.getTileId(l,i,j-1)!=0)
                            map.setTileId(l,i,j,2);
                    }
                }

                stage=lang=="en"?"Setting tiles":"Настройка плиток";
                percents=0;
                for(int i=0; i<map.layer(l).width; i++)
                    for(int j=0; j<map.layer(l).height; j++){
                        percents+=100f/(map.layer(l).width*map.layer(l).height);
                        map.setTileData(l,i,j, TiledMapUtils.data(map,l,i,j));
                    }
                stage=lang=="en"?"Setting walls":"Настройка стен";
                percents=0;
                for(int i=0; i<map.layer(l2).width; i++)
                    for(int j=0; j<map.layer(l2).height; j++){
                        percents+=100f/(map.layer(l).width*map.layer(l).height);
                        map.setTileData(l2,i,j, TiledMapUtils.data(map,l2,i,j));
                    }
                stage=lang=="en"?"Generate trees":"Генерация деревьев";
                percents=0;
                int prev_tree_x=0;
                for(int x=0; x<map.layer(l).width; x++){
                    percents+=100f/map.layer(l).width;
                    for(int y=map.layer(l).height-1; y>0; y--){
                        if(map.getTileId(l,x,y)==1){
                            if(randomBoolean(0.2f) && x-prev_tree_x>2){
                                spawn_tree(map,x,y+1);
                                prev_tree_x=x;
                            }else{
                                if(new SimplexNoise().genGrad(seed).generate(x*(1.0f/42),x*(1.0f/42))>0.5){
                                    int i=Math.round(new SimplexNoise().genGrad(seed+1).generate(x*(1.0f/42),x*(1.0f/42))*7);
                                    int t=random(20+3*i,20+3*(i+1));
                                    map.setTile(2,x,y+1,7,t);
                                }else{
                                    int t=random(6,19);
                                    if(randomBoolean(0.9f))
                                        t=random(0,5);
                                    map.setTile(2,x,y+1,7,t);
                                }
                            }
                            break;
                        }
                    }
                }
                stage=lang=="en"?"Done":"Готово";
                percents=100;
                Main.timer.start();
                Main.timer.setMinutes(12);
                isGenerated=true;
                float px=map.layer(3).width/2f,py=0;
                for(int y=map.layer(3).height; y>0; y--){
                    if(map.getTileId(3,(int)px,y)!=0 || map.getTileId(3,(int)px+1,y)!=0){
                        py=y+1;
                        break;
                    }
                }
                Main.player.rect.setPosition(px,py);
            }
        });
        thread.start();
    }


    public static void spawn_tree(TiledMap map,int x,int y){
        int l=3;//блоки
        int l2=2;//деревья
        if(map.getTileId(l2,x-1,y)==0 && map.getTileId(l2,x+1,y)==0 && map.getTileId(l2,x-1,y-1)==1 && map.getTileId(l2,x+1,y-1)==1){
            int side=random(0,2);
            if(side==0){
                map.setTile(l2,x-1,y,5,18);
                map.setTile(l2,x,y,5,20);
                map.setTile(l2,x+1,y,5,17);
            }else if(side==1){
                map.setTile(l2,x,y,5,16);
                map.setTile(l2,x+1,y,5,17);
            }else if(side==2){
                map.setTile(l2,x,y,5,19);
                map.setTile(l2,x-1,y,5,18);
            }
        }else if(map.getTileId(l2,x-1,y)==0 && map.getTileId(l2,x-1,y-1)==1){
            map.setTile(l2,x,y,5,19);
            map.setTile(l2,x-1,y,5,18);
        }else if(map.getTileId(l2,x+1,y)==0 && map.getTileId(l2,x+1,y-1)==1){
            map.setTile(l2,x,y,5,16);
            map.setTile(l2,x+1,y,5,17);
        }else
            map.setTile(l2,x,y,5,0);
        int height=random(5,20);
        int prev_rbranch=-1;
        int prev_lbranch=-1;
        for(int j=1; j<height; j++){
            if(randomBoolean(0.2f)){
                int side=random(0,2);
                if(side==0 && j-prev_lbranch>1 && j-prev_rbranch>1){
                    int[] tm={2,13};
                    int i=random(0,1);
                    int t=tm[i];
                    map.setTile(l2,x,y+j,5,t);
                    int[] tm2={28,30,32,3};
                    int t2=random(0,3);
                    map.setTile(l2,x-1,y+j,5,tm2[t2]);
                    int[] tm3={29,31,33,12};
                    int t3=random(0,3);
                    map.setTile(l2,x+1,y+j,5,tm3[t3]);
                    prev_lbranch=j;
                    prev_rbranch=j;
                }else if(side==1 && j-prev_lbranch>1){
                    map.setTile(l2,x,y+j,5,4);
                    int[] tm2={28,30,32,3};
                    int t2=random(0,3);
                    map.setTile(l2,x-1,y+j,5,tm2[t2]);
                    prev_lbranch=j;
                }else if(side==2 && j-prev_rbranch>1){
                    map.setTile(l2,x,y+j,5,11);
                    int[] tm3={29,31,33,12};
                    int t3=random(0,3);
                    map.setTile(l2,x+1,y+j,5,tm3[t3]);
                    prev_rbranch=j;
                }else{
                    int[] tm={1,8,9,10};
                    int t=random(0,3);
                    map.setTile(l2,x,y+j,5,tm[t]);
                }
            }else{
                int[] tm={1,8,9,10};
                int t=random(0,3);
                map.setTile(l2,x,y+j,5,tm[t]);
            }
        }
        int[] tm={24,25,26,27};
        int i=random(0,3);
        int t=tm[i];
        map.setTile(l2,x,y+height,5,t);
    }



    public static Random generator;
    
    public static float random(){
        return generator.nextFloat();
    }

    public static float random(float min,float max){
        return generator.nextFloat()*(max-min)+min;
    }

    public static int random(int min,int max){
        return generator.nextInt(max-min+1)+min;
    }

    public static boolean randomBoolean(){
        return generator.nextBoolean();
    }

    public static boolean randomBoolean(float chance){
        return generator.nextFloat()<chance;
    }
    


}
