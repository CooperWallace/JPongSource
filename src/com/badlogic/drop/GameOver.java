package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameOver implements Screen{
	final PongGame game;
	OrthographicCamera camera;
	String winlose = "";
	String Contiune = "";
	int ContiuneX = 0;
	int DiffHeight;
	String Difficulty = "Difficulty: ";
	
	public GameOver(final PongGame gam){
		game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
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

	@Override
	public void render(float delta) {
		
		
	// Clear the screen and set BG to Green	
		Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	camera.update();
	game.batch.setProjectionMatrix(camera.combined);
	
	
	game.batch.begin();
	game.font.setColor(Color.WHITE);
	game.font.draw(game.batch, Difficulty, 330, DiffHeight);
	game.font.draw(game.batch, winlose, 333, 280);
	game.font.draw(game.batch, Contiune, ContiuneX, 260);
	game.batch.end();
	
	
	
	// Conditions if the Computer wins against the player
	if(Pong.CPUS == 5){winlose = "You have Lost!"; 
	Contiune = "Press 'R' To Replay!"; 
	ContiuneX = 320;
	DiffHeight = 320;
	game.batch.begin();
	game.font.setColor(Color.RED);
	game.font.draw(game.batch, "Game Over", 340, 300);
	game.batch.end();
	if(Gdx.input.isKeyPressed(Keys.R)){game.setScreen(new Pong(game));
	Pong.CPUS = 0; 
	Pong.PS = 0; 
	dispose();}
	}
	
	// Conditions if the player wins against the computer.
	if(Pong.PS == 5){winlose = "You have Won!"; 
	Contiune = "Press 'Enter' to Contiune to next Difficulty!"; 
	ContiuneX = 250;
	DiffHeight = 300;
	if(Gdx.input.isKeyPressed(Keys.ENTER)){game.setScreen(new Pong(game)); Pong.CPUS = 0; Pong.PS = 0; dispose();}}
	
	
	// Display the Game Difficulty in a string dependant on how many times they have won.
	if(Pong.GamesWon == 0){Difficulty = "Difficulty: Easy";}
	else if(Pong.GamesWon == 1){Difficulty = "Difficulty: Normal";}
	else if(Pong.GamesWon == 2){Difficulty = "Difficulty: Challenge Me";}
	else if(Pong.GamesWon == 3){Difficulty = "Difficulty: Alright, This is challenging";}
	else if (Pong.GamesWon == 4){Difficulty = "Difficulty: Where'd it go!?";}
	else if (Pong.GamesWon == 5){Difficulty = "Difficulty: WHY WOULD YOU DO THIS";}
	else{}
	
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

}
