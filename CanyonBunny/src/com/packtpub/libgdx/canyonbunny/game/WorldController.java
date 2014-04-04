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

public class WorldController extends InputAdapter {
	private static final String TAG = WorldController.class.getName();
	public Sprite[] testSprites; // khai bao mang chua cac sprite
	public int selectedSprite; // luu lai so thu tu duoc chon cua sprite
	public CameraHelper cameraHelper;

	public WorldController() {
		init();

	}

	private void init() {
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		initTestObject();
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.R) {
			init();
			Gdx.app.debug(TAG, "Game World reseted");
		} else if (keycode == Keys.SPACE) {
			selectedSprite = (selectedSprite + 1) % testSprites.length;
			Gdx.app.debug(TAG, "Sprite #" + selectedSprite
					+ "selected LuyenProXimang");
			if (cameraHelper.hasTarget()) {
				cameraHelper.setTarget(testSprites[selectedSprite]);
			}
		} else if (keycode == Keys.ENTER) {
			cameraHelper.setTarget(cameraHelper.hasTarget() ? null
					: testSprites[selectedSprite]);
			Gdx.app.debug(TAG,
					"Camera follow enable: " + cameraHelper.hasTarget());
		}
		return false;
	}

	private void initTestObject() {
		testSprites = new Sprite[5];
		Array<TextureRegion> regions = new Array<TextureRegion>();
		regions.add(Assets.instance.bunny.head);
		regions.add(Assets.instance.feather.feather);
		regions.add(Assets.instance.goldCoin.goldCoin);
		for(int i=0; i<testSprites.length; i++){
			Sprite spr = new Sprite(regions.random());
			spr.setSize(1,1);
			spr.setOrigin(spr.getWidth()/2.0f, spr.getHeight()/2.0f);
			float randomX = MathUtils.random(-2.0f,2.0f);
			float randomY = MathUtils.random(-2.0f,2.0f);
			spr.setPosition(randomX, randomY);
			testSprites[i]  = spr;
					
		}
		
		selectedSprite = 0;
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
		updateTestObject(deltaTime);
		cameraHelper.update(deltaTime);
	}

	private void handleDebugInput(float deltaTime) {
		if( Gdx.app.getType() != ApplicationType.Desktop){
			return;
		}
		//float movement = 5*deltaTime;
		float sprMoveSpeed= 5*deltaTime;
		if(Gdx.input.isKeyPressed(Keys.A)){
			moveSelectedSprite(-sprMoveSpeed,0);	// 2 thong so la khoang cach so voi no. chu ko phai la thong so toa do
		}
		
		if(Gdx.input.isKeyPressed(Keys.D)){
			moveSelectedSprite(sprMoveSpeed,0);
		}
		
		if(Gdx.input.isKeyPressed(Keys.W)){
			moveSelectedSprite(0,sprMoveSpeed);
		}
		
		if(Gdx.input.isKeyPressed(Keys.S)){
			moveSelectedSprite(0,-sprMoveSpeed);
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

	private void updateTestObject(float deltaTime) {
		float rotation = testSprites[selectedSprite].getRotation();
		rotation += 90 * deltaTime;
		rotation %= 360;
		testSprites[selectedSprite].setRotation(rotation);

	}

	private void moveSelectedSprite(float x, float y) {
		testSprites[selectedSprite].translate(x, y);
	}

}
