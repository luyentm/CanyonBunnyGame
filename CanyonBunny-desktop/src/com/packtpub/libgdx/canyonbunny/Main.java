package com.packtpub.libgdx.canyonbunny;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class Main {
	
	private static boolean rebuildAtlas = true;
	private static boolean drawDebugOutline = true;
	
	public static void main(String[] args) {
		//  xay dung ra file hinh anhh .png
		if(rebuildAtlas){
			Settings settings = new Settings();
			settings.maxWidth = 1024;
			settings.maxHeight= 1024;
			settings.debug = drawDebugOutline;
			TexturePacker2.process(settings, "assets-raw/images", "../CanyonBunny-android/assets/images", "canyonbunny.pack");
		}
		
		
		
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "CanyonBunny";
		cfg.useGL20 = false;
		cfg.width = 800;
		cfg.height = 400;
		
		new LwjglApplication(new CanyonBunnyMain(), cfg);
	}
}
