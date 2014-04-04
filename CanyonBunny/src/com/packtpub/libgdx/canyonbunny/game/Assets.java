package com.packtpub.libgdx.canyonbunny.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;
import com.packtpub.libgdx.canyonbunny.util.Contants;

public class Assets implements Disposable, AssetErrorListener{
	
	public static final String TAG = Assets.class.getName();	// lay ten cua class
	
	public static final Assets instance = new Assets();
	private AssetManager assetManager;
	
	public AssetBunny bunny;
	public AssetRock rock;
	public AssetGoldCoin goldCoin;
	public AssetLevelDecoration levelDecoration;
	public AssetFeather feather;
	
	
	private Assets(){}		// singleton
	
	public void init(AssetManager assetManager){
		this.assetManager = assetManager;	// khoi tao doi tuong
		assetManager.setErrorListener(this);
		assetManager.load(Contants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		assetManager.finishLoading();	// load xong moi chay qua duoc
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);// lay so luong phan tu da load tu assets
		
		for(String a: assetManager.getAssetNames()){
			Gdx.app.debug(TAG, "asset: " + a);
		}
		
		TextureAtlas atlas = assetManager.get(Contants.TEXTURE_ATLAS_OBJECTS);
		for(Texture t: atlas.getTextures()){
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		// tao ra
		bunny = new AssetBunny(atlas);
		goldCoin = new AssetGoldCoin(atlas);
		feather = new AssetFeather(atlas);
		levelDecoration = new AssetLevelDecoration(atlas);
		rock = new AssetRock(atlas);
		
	}

	

	@Override	
	public void dispose() {
		assetManager.dispose();
		
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "could loader",(Exception) throwable);
		
	}
	// cai dau ne
	public class AssetBunny {
		public final AtlasRegion head;
		public AssetBunny(TextureAtlas atlas){
			head = atlas.findRegion("bunny_head");
		}
		
	}
	// day la da
	public class AssetRock{
		public final AtlasRegion edge;
		public final AtlasRegion middle;
		public AssetRock(TextureAtlas atlas){
			edge = atlas.findRegion("rock_edge");
			middle = atlas.findRegion("rock_middle");
		}
	}
	
	public class AssetGoldCoin{
		public final AtlasRegion goldCoin;
		public AssetGoldCoin(TextureAtlas atlas) {
			goldCoin = atlas.findRegion("item_gold_coin");
		}
	}
	public class AssetFeather{
		public final AtlasRegion feather;
		public AssetFeather(TextureAtlas atlas) {
			feather = atlas.findRegion("item_feather");
		}
	}
	// trang tri
	
	public class AssetLevelDecoration{
		public final AtlasRegion cloud01;
		public final AtlasRegion cloud02;
		public final AtlasRegion cloud03;
		public final AtlasRegion mountainRight;
		public final AtlasRegion mountainLeft;
		public final AtlasRegion waterOverlay;
		public AssetLevelDecoration(TextureAtlas atlas) {
			cloud01 = atlas.findRegion("cloud01");
			cloud02 = atlas.findRegion("cloud02");
			cloud03 = atlas.findRegion("cloud03");
			mountainRight = atlas.findRegion("mountainRight");
			mountainLeft = atlas.findRegion("mountainleft");
			waterOverlay = atlas.findRegion("waterOverlay");
			
		}
	}
	
	
}























