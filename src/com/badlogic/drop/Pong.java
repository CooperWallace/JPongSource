package com.badlogic.drop;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/* Name for the pong game should be J-Pong.
 *  - Name change pending - 
 * 
 * TODO
 * - Make correct Artwork. - Done
 * - Randomize the ball start velocity. DONE
 * - Make difficulty harder on each time won. - Done
 * 			- ReBalance with new textures
 * 			- Display Difficulty name.
 * - Add a Cheat Screen/Secret Screen - Done
 * 		-Branch off and make Artwork for secret screen
 * 
 *  
 * 
 * */

public class Pong implements Screen {
	final PongGame game;
   Texture side1; Texture side2;
   Texture side3; Texture side4;
   Texture Bucket; 
   Texture Flipper; Texture Flipper2;
   SpriteBatch batch; 
   OrthographicCamera camera;
   Rectangle bucket; 
   Rectangle flipper;
   Rectangle flipper2;
   BitmapFont font;
   Array<Rectangle> raindrops;
   boolean b = true;
   boolean d = false;
   String score = PS + " : " + CPUS; // String that displays the score 
   Texture Scoreboard;
   public static int PS; public static int CPUS; // Score, and Player score, and CPU score.
   
   public static int GamesWon = 0;
   int speed; // Speed of Bucket
   int FlipperSpeedPS = 300; // Slipper Speed
   int CPUFlipper = 250;
   
   
   
   
   
   
private Random random;

   public Pong(final PongGame gam) {
	   randomizeVelocity();
	   // Randomizes which way the object starts going on launch.
	   
	   this.game = gam;
      // load the images for the droplet and the bucket, 64x64 pixels each
      Bucket = new Texture(Gdx.files.internal("data/ball.png"));
      Flipper = new Texture(Gdx.files.internal("data/flipper1.png"));
      Flipper2 = new Texture(Gdx.files.internal("data/flipper2.png"));
      font = new BitmapFont();
      
      // create the camera and the SpriteBatch
      camera = new OrthographicCamera();
      camera.setToOrtho(false, 800, 480);
      batch = new SpriteBatch();
      
      
      //creates new Rectangle for flipper1
      flipper = new Rectangle();
      flipper.width = 9;
      flipper.height = 60;
      flipper.x = 0;
      flipper.y = 400 / 2;
      
      //creates new Rectangle for Flipper2 which is the CPU.
      flipper2 = new Rectangle();
      flipper2.width = 9;
      flipper2.height = 60;
      flipper2.x = 800 - 9;
      flipper2.y = 400 / 2;
      
      // create a Rectangle to logically represent the bucket
      bucket = new Rectangle();
      bucket.x = 400; // center the bucket horizontally
      bucket.y = 240; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
      bucket.width = 32;
      bucket.height = 32;

   }

   public void randomizeVelocity(){
	   random = new Random();
	   int i = random.nextInt(2)+1;
	   if(i == 1){b = false;}
	   if(i == 2){b = true;}
	   i = random.nextInt(4)+3;
	   if(i == 3){d = false;}
	   if(i == 4){d = true;}
	   
	   
   }
   
   @Override
   public void render(float delta) {
	   testcheat();
	   
      // clear the screen with a dark blue color. The
      // arguments to glClearColor are the red, green
      // blue and alpha component in the range [0,1]
      // of the color to be used to clear the screen.
      Gdx.gl.glClearColor(0, 0f, 0.2f, 1);
      Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
      // tell the camera to update its matrices.
      camera.update();

      // tell the SpriteBatch to render in the
      // coordinate system specified by the camera.
      game.batch.setProjectionMatrix(camera.combined);

      score = PS + " : " + CPUS;
      
      
      // begin a new batch and draw the bucket and
      // all drops
      batch.begin();
      font.draw(batch, score, Gdx.graphics.getWidth() / 2 - 40, 460);
      font.draw(batch, "Play to 5", Gdx.graphics.getWidth() / 2 - 60, 440);
      batch.draw(Flipper2, flipper2.x, flipper2.y);
      batch.draw(Flipper, flipper.x, flipper.y);
      batch.draw(Bucket, bucket.x, bucket.y);
      batch.end();
      
      if(PS == 5){GamesWon++; bucket.setCenter(400, 100); game.setScreen(new GameOver(game));}
      if(CPUS == 5){bucket.setCenter(400, 100); game.setScreen(new GameOver(game));}
      DifficultyRating();

      // The speed of which the bucket goes, randomized.
      if(!d){bucket.y -= speed * Gdx.graphics.getDeltaTime();}
      else{bucket.y += speed * Gdx.graphics.getDeltaTime();}
      if(!b){bucket.x -= speed * Gdx.graphics.getDeltaTime();}
      else{bucket.x += speed * Gdx.graphics.getDeltaTime();}

      // Makes the bucket go in the opposite direction of what it hits. Whether it be the x boundaries or y boundaries
      if(bucket.x < 0) {bucket.x = 0; b = true; CPUS++; score = PS + " : " + CPUS; reset();}  // If the CPU scores
      if(bucket.x > 800 - 32){bucket.x = (800 - 64); b = false; PS++; reset(); score = PS + " : " + CPUS;} // If the player scores
      if(bucket.y < 0) {bucket.y = 0; d = true;}
      if(bucket.y > 480 - 32){bucket.y = (480 - 64); d = false;}
      
      //User input for Flipper on LEft side. Which way it moves and how fast it moves.
      if(Gdx.input.isKeyPressed(Keys.UP)){flipper.y += FlipperSpeedPS * Gdx.graphics.getDeltaTime();}
      if(Gdx.input.isKeyPressed(Keys.DOWN)){flipper.y -= FlipperSpeedPS * Gdx.graphics.getDeltaTime();}
      
      //CPU for chasing the object, Only moves once the object is moving towards it. Aswell as how fast it moves
      if(bucket.y > flipper2.y && b){flipper2.y += CPUFlipper * Gdx.graphics.getDeltaTime();}
      if(bucket.y < flipper2.y && b){flipper2.y -= CPUFlipper * Gdx.graphics.getDeltaTime();}
      
      //If the 'object' hits the left bumps its suppose to rebound back.
      if(flipper.overlaps(bucket)){b = true;}
      if(flipper2.overlaps(bucket)){ b = false;}
      
      if(flipper.y > 480 - 64) flipper.y = 480 - 64;
      if(flipper.y < 0){flipper.y = 0;}
      
   }
   @Override
   public void dispose() {
   }
   
   public void reset(){
	   bucket.y = 240 - 64;
	   bucket.x = 400-9;
	   
   }
   
   public void DifficultyRating(){
	   if(GamesWon == 0){speed = 400;} // Starting Speed - Baby Mode
	   else if(GamesWon == 1){speed = 400;} // Speeds up the Bucket - Normal Mode
	   else if(GamesWon == 2){speed = 500; FlipperSpeedPS = 400; CPUFlipper = 380;} // Speeds up the Flipper AND Bucket - Challenge me
	   else if(GamesWon == 3){speed = 560; FlipperSpeedPS = 500; CPUFlipper = 450;} // Speeds up the Flipper and the Bucket - Alright, This is challenging
	   else if(GamesWon == 4){speed = 600; FlipperSpeedPS = 510; CPUFlipper = 500;}// Carpal Tunnel Incoming
	   else if(GamesWon == 5){speed = 750; FlipperSpeedPS = 610; CPUFlipper = 600;} // WHY WOULD YOU DO THIS
   }

   
   @Override
   public void resize(int width, int height) {
   }

   @Override
   public void pause() {
   }

   @Override
   public void resume() {
   }
@Override
public void hide() {
	// TODO Auto-generated method stub
	
}
	
@Override
public void show() {
	// TODO Auto-generated method stub
	
}

	public void testcheat(){
		// HEY WHAT ARE YOU DOING LOOKING AT THIS, GET OUTTA HERE

		if(Gdx.input.isKeyPressed(Keys.R) && Gdx.input.isKeyPressed(Keys.E) && Gdx.input.isKeyPressed(Keys.X)){
				game.setScreen(new Secret(game));
		}
	}
}
