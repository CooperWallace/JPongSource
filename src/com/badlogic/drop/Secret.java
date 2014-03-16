package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

/*This is the screen that will show up when 'REX' is held down on the keyboard
 * The purpose of this is to show some cool stuff and some credits
 * This may be changed to a menu that you can open when you are in the 'GameOver.java' portion of the program
 * 
 */



public class Secret implements Screen{
	final PongGame game;
	OrthographicCamera camera;
	
	public Secret(final PongGame gam){
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}

	
	@Override
	public void render(float arg0) {
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	camera.update();
		game.batch.begin();
		game.font.draw(game.batch, "Oh No! Did I break it!?", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight()  / 2);
		game.batch.end();
		
		if(Gdx.input.isKeyPressed(Keys.ENTER)){game.setScreen(new Pong(game));}
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}
