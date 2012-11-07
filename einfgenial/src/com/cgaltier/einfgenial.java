package com.cgaltier;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class einfgenial implements ApplicationListener {

	//private SpriteBatch batch;
	//private Texture texture;
	//private Sprite sprite;
	public CGame m_CGame ;
	public CRenderer m_cRenderer ;
	@Override
	public void create() {		

		
		m_CGame = new CGame (1);
		m_cRenderer = new CRenderer(m_CGame);
		

		
		//batch = new SpriteBatch();
		
		//texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		//texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		//TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
		//sprite = new Sprite(region);
		//sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		//sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		//sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
	}

	@Override
	public void dispose() {
		//batch.dispose();
		//texture.dispose();
	}

	@Override
	public void render() {		
		m_cRenderer.start();
		m_cRenderer.renderBoardGame(m_CGame);
		
		//batch.setProjectionMatrix(camera.combined);
		//batch.begin();
		//sprite.draw(batch);
		//batch.end();
		m_cRenderer.end();
	}

	@Override
	public void resize(int width, int height) 
	{
		Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
