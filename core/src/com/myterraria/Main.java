package com.myterraria;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.myterraria.interfaces.*;
import engine.Assets;
import engine.Camera2D;
import engine.Mouse;
import engine.Timer;
import engine.math.Maths;
import engine.math.vectors.Vector2f;
import engine.tiledmap.TileManager;
import engine.tiledmap.TiledMap;

public class Main implements ApplicationListener{



	public SpriteBatch sb;
	public Camera2D cam;
	public int timer1=200;

	public static TiledMap map;
	public TileManager tileManager;
	public static int tile_pixel_size=32;//21,32,43
	public long seed=Maths.randomSeed(8);

	public float time=0;

	public ToolBar toolBar;

	public static Timer timer;
	public int minutes,hours;

	public Mouse mouse;



	public void create(){
		sb=new SpriteBatch();
		cam=new Camera2D();
		mouse=new Mouse();

		int map_width=54;
		int map_height=54;
		int surface=27;

		tileManager=new TileManager();
		map=new TiledMap();
		map.addLayer(1,map_width,map_height,tile_pixel_size,tile_pixel_size);//стены
		map.addLayer(2,map_width,map_height,tile_pixel_size,tile_pixel_size);//ветки, вершины деревьев, трава
		map.addLayer(3,map_width,map_height,tile_pixel_size,tile_pixel_size);//блоки
		map.setView(2,4);
		map.setView(1,4);
		map.setView(3,4);
		WorldGenerator.generate(map,seed,surface);
		WorldGenerator.isGenerated=true;
		//map.load("/home/user/Downloads/world.wld");

		cam.translatePosition(map_width/2f*tile_pixel_size,surface*tile_pixel_size);
		cam.fullScreen(true);

		LoadResources.loadResources();
		LoadResources.defineTiles(tileManager);

		RecursiveLights.init(map);

		toolBar=new ToolBar(cam,10);
		toolBar.setItem(new ItemStack(2),0);
		toolBar.setItem(new ItemStack(3),1);
		toolBar.setItem(new ItemStack(8),2);
		toolBar.setItem(new ItemStack(9),3);
		toolBar.setItem(new ItemStack(26),4);
		toolBar.setItem(new ItemStack(30),5);
		toolBar.setItem(new ItemStack(121),6);
		toolBar.setItem(new ItemStack(520),7);

		timer=new Timer();

		Vector2f v=new Vector2f(1,1).norm();
		System.out.println(v.angleDeg());
		System.out.println(v.angleRad());
	}



	public void render(){
		float delt=Gdx.graphics.getDeltaTime()*75;
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update(sb);
		sb.begin();

		if(WorldGenerator.isGenerated){
			//sb.setColor(1,1-time/100f,1-time/100f,1);
			sb.draw(Assets.getTexture("Background_0"),cam.x,cam.y,cam.width,cam.height);
			//sb.setColor(1,1,1,1);

			time+=delt;
			ShaderProgram shader=Assets.getShader("shader");
			shader.setUniformf("timer1",time);
			//sb.setShader(shader);
			map.draw(tileManager,sb,cam);
			sb.setShader(null);

			toolBar.draw(sb,cam);

			RecursiveLights.update(map,cam);

			minutes=(int)timer.getSeconds()-60*(int)Math.floor(timer.getSeconds()/60f);
			hours=(int)timer.getMinutes()-24*(int)Math.floor(timer.getMinutes()/24f);

			if(timer1>0)
				timer1-=delt;
			if(timer1>0){
				sb.setColor(1,1,1,timer1/200f);
				sb.draw(Assets.getTexture("black_pixel"),cam.x,cam.y,cam.x+cam.width,cam.y+cam.height);
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
	}


	public boolean f3_pressed,show_f3_info,prev_f3_pressed;


	public void controls(float delt){
		int tx=(int)(Gdx.input.getX()+cam.x)/tile_pixel_size;
		int ty=(int)(Gdx.graphics.getHeight()-Gdx.input.getY()+cam.y)/tile_pixel_size;

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


			sb.setShader(Assets.getShader("inv_shader"));

			font.draw(sb,text1,cam.x+20,cam.y+cam.height-font.getCache().addText(text1,0,0).height);
			font.draw(sb,text2,cam.x+20,cam.y+cam.height-font.getCache().addText(text2,0,0).height-40);
			font.draw(sb,text3,cam.x+20,cam.y+cam.height-font.getCache().addText(text3,0,0).height-40*2);
			font.draw(sb,text4,cam.x+20,cam.y+cam.height-font.getCache().addText(text4,0,0).height-40*3);
			font.draw(sb,text5,cam.x+20,cam.y+cam.height-font.getCache().addText(text5,0,0).height-40*4);
			font.draw(sb,text6,cam.x+20,cam.y+cam.height-font.getCache().addText(text6,0,0).height-40*5);

			sb.setShader(null);
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.F11))
			cam.fullScreen(!cam.fullscreen);

		if(Gdx.input.isTouched()){

			if(mouse.left){
				if(!TiledMapUtils.setTile(map,cam,3,tx,ty,0))
					if(!TiledMapUtils.setTile(map,cam,2,tx,ty,0))
						TiledMapUtils.setTile(map,cam,1,tx,ty,0);

			}
			if(mouse.right){
				ItemStack item=toolBar.getSelectedItem();
				if(item!=null)
					TiledMapUtils.setTile(map,cam,3,tx,ty,(Integer)ItemManager.getItem(item.id).attributes.get(0).getValue());
			}
		}

		int cam_speed=10;
		if(Gdx.input.isKeyPressed(Input.Keys.W))
			cam.translatePosition(0,(int)(cam_speed*delt));
		if(Gdx.input.isKeyPressed(Input.Keys.A))
			cam.translatePosition(-(int)(cam_speed*delt),0);
		if(Gdx.input.isKeyPressed(Input.Keys.S))
			cam.translatePosition(0,-(int)(cam_speed*delt));
		if(Gdx.input.isKeyPressed(Input.Keys.D))
			cam.translatePosition((int)(cam_speed*delt),0);

		if(Gdx.input.isKeyPressed(Input.Keys.EQUALS))
			cam.translateScale(-8*delt);
		if(Gdx.input.isKeyPressed(Input.Keys.MINUS))
			cam.translateScale(8*delt);

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
