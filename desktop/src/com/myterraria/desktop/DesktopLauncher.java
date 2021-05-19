package com.myterraria.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.myterraria.Main;

public class DesktopLauncher{

	public static void main(String[] arg){
		Lwjgl3ApplicationConfiguration cfg=new Lwjgl3ApplicationConfiguration();

		cfg.setTitle("Terraria Java Edition");
		cfg.setWindowedMode(1280,720);//2560,1440);
		cfg.setIdleFPS(75);
		cfg.setInitialBackgroundColor(new Color(0,0,0,1));

		new Lwjgl3Application(new Main(),cfg);
	}

}
