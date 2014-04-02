package com.packtpub.libgdx.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.packtpub.libgdx.canyonbunny.game.Assets;

// 1 doi tuong hoan toan cu the chua cac thong so cu the, cach ve hinh 

public class Rock extends AbstractGameObject {

	private TextureRegion regEdge;
	private TextureRegion middleEdge;
	private int length;

	public Rock() {
		init();
	}

	private void init() {
		dimension.set(1, 1.5f);
		regEdge = Assets.instance.rock.edge;
		middleEdge = Assets.instance.rock.middle;
		setLength(1);
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void increaseLength(int amount) {
		setLength(this.length + amount);

	}

	@Override
	public void render(SpriteBatch batch) {
		TextureRegion reg = null;  // thuc chat la mot con tro ma thoi
		float relX = 0;
		float relY = 0;
		
		reg = regEdge;
		relX -= dimension.x/4;
		batch.draw(reg.getTexture(),
				position.x + relX, position.y + relY, 
				origin.x, origin.y,
				dimension.x/4, dimension.y ,
				scale.x, scale.y,
				rotation,
				reg.getRegionX(),
				reg.getRegionY(),
				reg.getRegionWidth(),
				reg.getRegionHeight(),
				false,
				false);
		// draw middle rock
		relX = 0;
		reg = middleEdge; // gan cho no 1 doi tuong moi
		
		for(int i=0; i< length; i++){
			// khi  muon ve cai gi thi phai goi batch.draw trong do batch la
			// mot doi tuong SpriteBatch
			// VE thoi
			batch.draw(reg.getTexture(),
				position.x + relX,position.y + relY,
				origin.x, origin.y,
				dimension.x, dimension.y,
				scale.x, scale.y,
				rotation,
				reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(),
				false, false);
			// cap nhat lai rel (day coi nhu  la cap nhat lai vi tri de ve tren truc Ox thoi)
			relX += dimension.x;
		}
		
		// draw right edge => don gian la t lay doi xung thoi
		reg = regEdge;
		batch.draw(reg.getTexture(),
				position.x + relX, position.y + relY,
				origin.x + dimension.x / 8, origin.y,
				dimension.x / 4, dimension.y,
				scale.x, scale.y,
				rotation,
				reg.getRegionX(), reg.getRegionY(),
				reg.getRegionWidth(), reg.getRegionHeight(),
				true, false); // chu y la co 1 lan flip
		
	}
}
