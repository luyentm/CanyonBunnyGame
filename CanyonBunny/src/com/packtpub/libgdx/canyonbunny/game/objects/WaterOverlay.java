package com.packtpub.libgdx.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.packtpub.libgdx.canyonbunny.game.Assets;

public class WaterOverlay extends AbstractGameObject{
	private TextureRegion regWaterOverlay;
	private float length;
	
	public WaterOverlay(float length){
		this.length = length;
		init();
	}
	
	public void init(){
		dimension.set(length*10,3);
		regWaterOverlay = Assets.instance.levelDecoration.waterOverlay;
		origin.x = -dimension.x/2;
	}
	
	
	
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion reg =null;
		reg = regWaterOverlay;
		
	}
	
	
}
