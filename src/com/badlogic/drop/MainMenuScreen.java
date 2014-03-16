package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class MainMenuScreen implements Screen{
	final PongGame game;
	OrthographicCamera camera;
	Texture logo;
	Rectangle Logo;
	
	
	public MainMenuScreen(final PongGame gam){
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		logo = new Texture(Gdx.files.internal("data/Title.png"));
		
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
@Override
	public void render(float Delta) {
		Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.draw(logo, 270, 85);
		game.font.draw(game.batch, "Welcome to the JPong! The Classic Remade!", 255, 235);
		game.font.draw(game.batch, "Click any where to Begin!", 310, 200);
		game.font.draw(game.batch, "This software is licensed under the terms of the CC-BY-NC-ND License", 170, 25);
		game.batch.end();
		
		if(Gdx.input.isTouched()){
			game.setScreen(new Pong(game));
		}
		
		}
		
	}




