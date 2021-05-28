package com.myterraria;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.myterraria.interfaces.*;
import engine.Assets;
import engine.Camera2D;
import engine.Mouse;
import engine.Timer;
import engine.tiledmap.TileManager;
import engine.tiledmap.TiledMap;

public class Main implements ApplicationListener{



	public SpriteBatch sb;
	public Camera2D cam;
	public int timer1=200;

	public static TiledMap map;
	public TileManager tileManager;
	public static int tile_pixel_size=32;//21.78,32,43.76
	public long seed=33268463;//Maths.randomSeed(8);//81318082;//

	public float time=0;

	public ToolBar toolBar;

	public static Timer timer;
	public int minutes,hours;

	public Mouse mouse;



	public void create(){
		sb=new SpriteBatch();
		cam=new Camera2D();
		mouse=new Mouse();

		int map_width=500;
		int map_height=200;
		int surface=150;

		//cam.fullScreen(true);
		cam.translatePosition(map_width/2f*tile_pixel_size-Gdx.graphics.getWidth()/2f,surface*tile_pixel_size);

		tileManager=new TileManager();
		map=new TiledMap();
		map.addLayer(1,map_width,map_height,tile_pixel_size,tile_pixel_size);//стены
		map.addLayer(2,map_width,map_height,tile_pixel_size,tile_pixel_size);//ветки, вершины деревьев, трава
		map.addLayer(3,map_width,map_height,tile_pixel_size,tile_pixel_size);//блоки
		map.setView(2,4);
		map.setView(1,4);
		map.setView(3,4);
		WorldGenerator.generate(map,seed,surface);
		//WorldGenerator.isGenerated=true;
		//map.load("/home/user/Downloads/world.wld");

		LoadResources.loadResources();
		LoadResources.defineTilesAndItems(tileManager);

		RecursiveLights.init(map);

		toolBar=new ToolBar(cam,10);
		toolBar.setItem(new ItemStack(2),0);
		toolBar.setItem(new ItemStack(3),1);
		toolBar.setItem(new ItemStack(8),2);
		toolBar.setItem(new ItemStack(9),3);
		toolBar.setItem(new ItemStack(520),4);
		toolBar.setItem(new ItemStack(757),5);
		toolBar.setItem(new ItemStack(1326),6);
		toolBar.setItem(new ItemStack(2763),7);
		toolBar.setItem(new ItemStack(2764),8);
		toolBar.setItem(new ItemStack(2765),9);

		timer=new Timer();

		Player.x=map_width/2f;
		for(int y=map.layer(3).height; y>0; y--){
			if(map.getTileId(3,(int)Player.x,y)!=0){
				Player.y=y+1;
				break;
			}
		}
		Player.updateDrawPosition(map);
	}


	public int timer2;

	public int effect_timer;
	public float effect_force,effect_x;
	public boolean effect;


	public void render(){
		float delt=Gdx.graphics.getDeltaTime()*75;
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.lookAt(Player.draw_x-cam.width/2+Player.w/2f,Player.draw_y-cam.height/2+Player.h/2f);
		cam.update(sb);
		sb.begin();

		if(WorldGenerator.isGenerated){
			//sb.setColor(1,1-time/100f,1-time/100f,1);
			sb.draw(Assets.getTexture("Background_0"),cam.x,cam.y,cam.width,cam.height);
			//sb.setColor(1,1,1,1);


			if(shadows_off){
				int l=3;
				for(int i=0; i<map.layer(l).width; i++)
					for(int j=0; j<map.layer(l).height; j++){
						map.layer(l).colormap[i][j][0]=1;
						map.layer(l).colormap[i][j][1]=1;
						map.layer(l).colormap[i][j][2]=1;
					}
				l=1;
				for(int i=0; i<map.layer(l).width; i++)
					for(int j=0; j<map.layer(l).height; j++){
						map.layer(l).colormap[i][j][0]=1;
						map.layer(l).colormap[i][j][1]=1;
						map.layer(l).colormap[i][j][2]=1;
					}
			}


			time+=delt;
			map.draw(tileManager,sb,cam);
			ShaderProgram shader=Assets.getShader("shader");
			shader.begin();
			shader.setUniformf("u_force",effect_force);
			sb.setShader(shader);
			if(effect){
				map.draw(tileManager,sb,cam,effect_x,0);
				map.draw(tileManager,sb,cam,-effect_x,0);
			}
			shader.end();
			sb.setShader(null);

			Player.draw(sb,map);


			toolBar.draw(sb,cam);

			RecursiveLights.update(map,cam);

			minutes=(int)timer.getSeconds()-60*(int)Math.floor(timer.getSeconds()/60f);
			hours=(int)timer.getMinutes()-24*(int)Math.floor(timer.getMinutes()/24f);

			timer2++;
			if(timer2>4.25*delt){
				timer2=0;
				ItemManager.getItem(520).animation.next();
				ItemManager.getItem(521).animation.next();
				ItemManager.getItem(547).animation.next();
				ItemManager.getItem(548).animation.next();
				ItemManager.getItem(549).animation.next();
				ItemManager.getItem(575).animation.next();
			}

			if(timer1>0)
				timer1-=delt;
			if(timer1>0){
				sb.setColor(1,1,1,timer1/200f);
				sb.draw(Assets.getTexture("black_pixel"),cam.x,cam.y,cam.x+Gdx.graphics.getWidth(),cam.y+Gdx.graphics.getHeight());
				sb.setColor(1,1,1,1);

				BitmapFont font=Assets.getTTF("font1");
				float percents=Float.parseFloat(new String(""+WorldGenerator.percents).substring(0,new String(""+WorldGenerator.percents).lastIndexOf(".")+2));
				String text1=WorldGenerator.stage+" "+percents+"%";
				font.setColor(1,1,1,timer1/200f);
				font.draw(sb,text1,cam.x+cam.width/2-font.getCache().addText(text1,0,0).width/2,cam.y+cam.height/2-font.getCache().addText(text1,0,0).height/2);

				BitmapFont font2=Assets.getTTF("font2");
				font2.setColor(1,1,1,timer1/200f);
				font2.draw(sb,"F3 for more info; 1/2/3/4/6/0 + LMB for set tile;",cam.x+20,cam.y+20+font2.getLineHeight());
			}
		}else{
			BitmapFont font=Assets.getTTF("font1");
			float percents=Float.parseFloat(new String(""+WorldGenerator.percents).substring(0,new String(""+WorldGenerator.percents).lastIndexOf(".")+2));
			String text1=WorldGenerator.stage+" "+percents+"%";
			font.draw(sb,text1,cam.x+cam.width/2-font.getCache().addText(text1,0,0).width/2,cam.y+cam.height/2-font.getCache().addText(text1,0,0).height/2);

			BitmapFont font2=Assets.getTTF("font2");
			font2.setColor(1,1,1,timer1/200f);
			font2.draw(sb,"F3 for more info; 1/2/3/4/6/0 + LMB for set tile;",cam.x+20,cam.y+20+font2.getLineHeight());
		}

		controls(delt);

		sb.end();

		if(effect){
			effect_timer+=delt;
			float force=(float)Math.sin(effect_timer*Math.PI/500f);
			effect_force=force*0.35f;
			effect_x=15*force*(float)Math.sin(time);
			if(effect_timer>500){
				effect=false;
				effect_timer=0;
				effect_force=0;
				effect_x=0;
			}
		}
	}


	public boolean f3_pressed,show_f3_info,prev_f3_pressed,shadows_off;


	public void controls(float delt){
		int tx=(int)Math.floor((Gdx.input.getX()+cam.x)/map.layer(3).tiles_offset_x);
		int ty=(int)Math.floor((Gdx.graphics.getHeight()-Gdx.input.getY()+cam.y)/map.layer(3).tiles_offset_y);

		if(Gdx.input.isKeyJustPressed(Input.Keys.F))
			effect=true;

		if(Gdx.input.isKeyJustPressed(Input.Keys.F7) && !shadows_off){
			shadows_off=true;
			RecursiveLights.updateScreenLights(map,cam);
		}else if(Gdx.input.isKeyJustPressed(Input.Keys.F7)){
			shadows_off=false;
			RecursiveLights.updateScreenLights(map,cam);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.F3)){
			f3_pressed=true;
		}else
			f3_pressed=false;
		if(!prev_f3_pressed && f3_pressed){
			if(show_f3_info)
				show_f3_info=false;
			else
				show_f3_info=true;
		}
		if(prev_f3_pressed!=f3_pressed)
			prev_f3_pressed=f3_pressed;

		if(show_f3_info){
			float usedBytes=Float.parseFloat(new String(""+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()/1048576f)).substring(0,5));
			BitmapFont font=Assets.getTTF("font2");

			String text1="FPS: "+Gdx.graphics.getFramesPerSecond();
			String text2="Seed: "+seed;
			String text3="Memory: "+usedBytes+" Mb";
			String text4="World size: "+map.layer(3).width+", "+map.layer(3).height;
			String text5="Tile x: "+tx+", y: "+ty;
			String text6="Game time: "+hours+":"+minutes;
			String text7="cell: "+toolBar.selected_cell_position;

			sb.setShader(Assets.getShader("inv_shader"));

			font.draw(sb,text1,cam.x+20,cam.y+cam.height-font.getCache().addText(text1,0,0).height);
			font.draw(sb,text2,cam.x+20,cam.y+cam.height-font.getCache().addText(text2,0,0).height-40);
			font.draw(sb,text3,cam.x+20,cam.y+cam.height-font.getCache().addText(text3,0,0).height-40*2);
			font.draw(sb,text4,cam.x+20,cam.y+cam.height-font.getCache().addText(text4,0,0).height-40*3);
			font.draw(sb,text5,cam.x+20,cam.y+cam.height-font.getCache().addText(text5,0,0).height-40*4);
			font.draw(sb,text6,cam.x+20,cam.y+cam.height-font.getCache().addText(text6,0,0).height-40*5);
			font.draw(sb,text7,cam.x+20,cam.y+cam.height-font.getCache().addText(text7,0,0).height-40*6);

			sb.setShader(null);
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.F11))
			cam.fullScreen(!cam.fullscreen);

		if(Gdx.input.isTouched()){

			if(mouse.isRightPressed() && map.getTileId(3,tx,ty)!=0){
				if(map.getTileId(3,tx,ty)==3)
					Assets.getSound("Tink_"+MathUtils.random(0,2)).play();
				else
					Assets.getSound("Dig_"+MathUtils.random(0,2)).play();
				TiledMapUtils.setTile(map,cam,3,tx,ty,0);
			}
			if(mouse.isLeftPressed()){
				ItemStack item=toolBar.getSelectedItem();
				if(item!=null){
					Value item_type=ItemManager.getTag(item.id,"item_type");
					if(item_type.getValue()=="tile" && map.getTileId(3,tx,ty)==0){
						Value tile_id=ItemManager.getTag(item.id,"tile_id");
						TiledMapUtils.setTile(map,cam,3,tx,ty,(Integer)tile_id.getValue());
						//if(map.getTileId(3,tx,ty)==3)
						//	Assets.getSound("Tink_"+MathUtils.random(0,2)).play();
						//else
						Assets.getSound("Dig_"+MathUtils.random(0,2)).play();
					}
				}
			}
		}

		int scrolled=mouse.scrolled();
		toolBar.scroll(scrolled);
		if(scrolled!=0)
			;

		float cam_speed=0.3f;
		if(Gdx.input.isKeyPressed(Input.Keys.W))
			Player.translatePositio(0,cam_speed*delt,map);
		if(Gdx.input.isKeyPressed(Input.Keys.S))
			Player.translatePositio(0,-cam_speed*delt,map);
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			Player.translatePositio(-cam_speed*delt,0,map);
			Player.lookside=true;
			Player.animationStage=1;
		}else if(Gdx.input.isKeyPressed(Input.Keys.D)){
			Player.translatePositio(cam_speed*delt,0,map);
			Player.lookside=false;
			Player.animationStage=1;
		}else
			Player.animationStage=0;

		if(Gdx.input.isKeyPressed(Input.Keys.EQUALS) && map.layer(1).tiles_offset_x<=43.76){
			map.layer(1).tiles_offset_x+=delt/4;
			map.layer(2).tiles_offset_x+=delt/4;
			map.layer(3).tiles_offset_x+=delt/4;

			map.layer(1).tiles_offset_y+=delt/4;
			map.layer(2).tiles_offset_y+=delt/4;
			map.layer(3).tiles_offset_y+=delt/4;
			Player.updateDrawPosition(map);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.MINUS) && map.layer(1).tiles_offset_x>=21.78){
			map.layer(1).tiles_offset_x-=delt/4;
			map.layer(2).tiles_offset_x-=delt/4;
			map.layer(3).tiles_offset_x-=delt/4;

			map.layer(1).tiles_offset_y-=delt/4;
			map.layer(2).tiles_offset_y-=delt/4;
			map.layer(3).tiles_offset_y-=delt/4;
			Player.updateDrawPosition(map);
		}

		/*for(int i=0; i<256; i++){
			if(Gdx.input.isKeyPressed(i))
				System.out.println(i);
		}*/

		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			System.exit(0);

		if(Gdx.input.isKeyJustPressed(Input.Keys.F4)){
			map.save("/home/user/Downloads/world.wld");
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.F5)){
			map.load("/home/user/Downloads/world.wld");
			RecursiveLights.updateScreenLights(map,cam);
		}
	}



	public void dispose(){
		sb.dispose();
		Assets.dispose();
	}

	public void resize(int w,int h){
		cam.resize(w,h);
		RecursiveLights.updateScreenLights(map,cam);
	}

	public void pause(){

	}

	public void resume(){

	}

}
