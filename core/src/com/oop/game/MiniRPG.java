package com.oop.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oop.game.Screens.BaseGame;
import com.oop.game.Screens.BaseScreen;
import com.oop.game.Screens.GameScreen;
import com.oop.game.Screens.MainMenuScreen;

public class MiniRPG extends Game {
	Game game;

	@Override
	public void create () {
		MainMenuScreen gs = new MainMenuScreen((BaseGame) game);
		setScreen(gs);
	}


}
