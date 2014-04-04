package com.packtpub.libgdx.canyonbunny.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
//This class is able to store the position, dimension, origin, scale factor, and angle
//of rotation of a game object.
// tom lai la no tao 1 cai doi tuong, trong do luu tru tat ca cac thong tin ve no
// nhu vi tri, chieu, toa do, do scale, xoay may do ne..
public abstract class AbstractGameObject {
	public Vector2 position;
	public Vector2 dimension;
	public Vector2 origin;
	public Vector2 scale;
	public float rotation;
	
	public AbstractGameObject(){
		position = new  Vector2();
		dimension = new Vector2(1,1);
		origin = new Vector2();
		scale =  new Vector2(1,1);
		rotation = 0;
	}
	
	public void update(float deltaTime){}
	public abstract void render(SpriteBatch batch);
}
