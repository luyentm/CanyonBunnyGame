package com.packtpub.libgdx.canyonbunny.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.packtpub.libgdx.canyonbunny.util.Contants;

public class WorldRenderer implements Disposable {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private WorldController worldController;
	
	public WorldRenderer(WorldController worldController){
		this.worldController = worldController;
		init();
	}
	private void init(){
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Contants.VIEWPORT_WIDTH, Contants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();
	}
	public void render(){
		renderTestObjects();
	}
	private void renderTestObjects() {
		worldController.cameraHelper.applyTo(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for( Sprite sprite: worldController.testSprites){
			sprite.draw(batch);
		}
		batch.end();
	}
	public void resize(int width, int height){
		camera.viewportWidth=(Contants.VIEWPORT_WIDTH/height)*width;
		camera.update();
		
	}
	@Override
	public void dispose() {
		batch.dispose();
	}
	
	
}
