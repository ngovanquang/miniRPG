package com.oop.game;

import com.badlogic.gdx.Game;
import com.oop.game.Screens.MainMenuScreen;

public class miniRPG extends Game {

	Game game;
	
	@Override
	public void create () {
		MainMenuScreen menu = new MainMenuScreen(this);
		setScreen(menu);
	}
}

