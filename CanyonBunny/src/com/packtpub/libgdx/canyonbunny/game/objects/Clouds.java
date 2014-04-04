package com.packtpub.libgdx.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Clouds extends AbstractGameObject{
	
	private float length;
	private Array<TextureRegion> regClouds;
	private Array<Cloud> clouds;
	
	private class Cloud extends AbstractGameObject{
		private TextureRegion regCloud;
		
		public Cloud(){}
		
		public void setRegion(TextureRegion region){
			regCloud = region;
		}
		
		@Override
		public void render(SpriteBatch batch) {
			TextureRegion reg = regCloud;
			batch.draw(reg.getTexture(),
					position.x + origin.x, position.y + position.y,
					origin.x, origin.y,
					dimension.x, dimension.y,
					scale.x, scale.y,
					rotation,
					reg.getRegionX(), reg.getRegionY(),
					reg.getRegionWidth(),reg.getRegionHeight(),
					false,false
					);
			
		}
	}
	public Clouds (float length){
		this.length = length;
		init();
	}
	private void init(){
		dimension.set(3.0f, 1.5f);
		regClouds = new Array<TextureRegion>();
		regClouds.add(Assets.instance.levelDecoration.cloud01);
		regClouds.add(Assets.instance.levelDecoration.cloud02);
		regClouds.add(Assets.instance.levelDecoration.cloud03);
		int distFac = 5;
		int numClouds = (int)(length / distFac);
		clouds = new Array<Cloud>(2 * numClouds);
		for (int i = 0; i < numClouds; i++) {
		Cloud cloud = spawnCloud();
		cloud.position.x = i * distFac;
		clouds.add(cloud);
		}
		}
	
	
	@Override
	public void render(SpriteBatch batch) {
		
		
	}
	
}
