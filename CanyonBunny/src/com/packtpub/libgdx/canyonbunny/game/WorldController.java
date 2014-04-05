// class nay bao gom tat ca nhung gi lien quan den tinh vat ly cua game
// nhu xu ly su kien, va cham

package com.packtpub.libgdx.canyonbunny.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.packtpub.libgdx.canyonbunny.util.CameraHelper;
import com.packtpub.libgdx.canyonbunny.util.Constants;

public class WorldController extends InputAdapter {
	private static final String TAG = WorldController.class.getName();
	public CameraHelper cameraHelper;
	public Level level;
	public int lives;
	public int score;

	public WorldController() {
		init();

	}
	
	private void initLevel(){
		score =0;
		level = new Level(Constants.LEVEL_01);
	}
	
	private void init() {
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		lives = Constants.LIVES_START;
		initLevel();
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.R) {
			init();
			Gdx.app.debug(TAG, "Game World resetted");
		} 
		return false;
	}


	private Pixmap createProceduralPixmap(int width, int height) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(1, 1, 0, 0.5f);
		pixmap.fill(); // to mau hoan toan cho cai pixmap do
		pixmap.setColor(1, 1, 0, 1);
		pixmap.setColor(1, 1, 0, 1);

		pixmap.drawLine(0, 0, width, height);
		pixmap.drawLine(width, 0, 0, height);
		pixmap.setColor(0, 1, 1, 1);
		pixmap.drawRectangle(0, 0, width, height);

		return pixmap;
	}

	public void update(float deltaTime) {
		handleDebugInput(deltaTime);
		cameraHelper.update(deltaTime);
	}

	private void handleDebugInput(float deltaTime) {
		if( Gdx.app.getType() != ApplicationType.Desktop){
			return;
		}
	
		
		// thiet lap cho camera chay theo khi minh di chuyen em no :v
		
		float camMoveSpeed = 5*deltaTime;
		
		float camMoveSpeedAccelerationFactor = 5;
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
			camMoveSpeed*=camMoveSpeedAccelerationFactor;
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			moveCamera(-camMoveSpeed,0);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			moveCamera(camMoveSpeed,0);
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			moveCamera(0,camMoveSpeed);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			moveCamera(0,-camMoveSpeed);
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			cameraHelper.setPosition(0, 0);
		}
		// camera Control Zoom
		float camZoomSpeed = 1*deltaTime;
		float camZoomSpeedAccelerationFactor=  5;
		
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
			camZoomSpeed *= deltaTime;
			
		}
		if(Gdx.input.isKeyPressed(Keys.COMMA)){
			cameraHelper.addZoom(camMoveSpeed);
		}
		
		if(Gdx.input.isKeyPressed(Keys.SLASH)){
			cameraHelper.addZoom(-camMoveSpeed);
		}
		
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			cameraHelper.setZoom(1);
		}	
		
	}

	private void moveCamera(float x, float y) {
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
				
		cameraHelper.setPosition(x, y);
	}
}
