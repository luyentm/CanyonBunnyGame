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
		dimention.set(1, 1.5f);
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
		TextureRegion reg = null;
		float relX = 0;
		float relY = 0;
		
		reg = regEdge;
		relX -= dimention.x/4;
		batch.draw(reg.getTexture(),
				position.x + relX, position.y + relY, 
				origin.x, origin.y,
				dimention.x/4, dimention.y ,
				scale.x, scale.y,
				rotation,
				reg.getRegionX(),
				reg.getRegionY(),
				reg.getRegionWidth(),
				reg.getRegionHeight(),
				false,
				false);
		
	}
}
