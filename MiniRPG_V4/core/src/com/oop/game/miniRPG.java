package com.oop.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oop.game.Screens.MainMenuScreen;
import com.oop.game.Screens.PlayGameScreen;

public class miniRPG extends Game {
	Game game;

	@Override
	public void create() {

		PlayGameScreen screen = new PlayGameScreen(this);
		setScreen(screen);
	}

}

