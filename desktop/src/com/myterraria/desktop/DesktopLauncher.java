package com.myterraria.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.myterraria.Main;

public class DesktopLauncher{

	public static void main(String[] arg){
		LwjglApplicationConfiguration cfg=new LwjglApplicationConfiguration();

		cfg.title="Terraria Java Edition";
		cfg.width=1280;//2560
		cfg.height=720;//1440
		cfg.foregroundFPS=75;
		cfg.initialBackgroundColor=new Color(0,0,0,1);

		new LwjglApplication(new Main(),cfg);
	}

}
