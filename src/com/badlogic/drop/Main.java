package com.badlogic.drop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
   public static void main(String[] args) {
      LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
      cfg.title = "Collision Tests";
      cfg.width = 800;
      cfg.height = 480;
      cfg.resizable = false;
      cfg.addIcon("data/Ball.png", Files.FileType.Internal);
      new LwjglApplication(new PongGame(), cfg);
   }
}