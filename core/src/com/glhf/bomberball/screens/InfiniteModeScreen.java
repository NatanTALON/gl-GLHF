package com.glhf.bomberball.screens;

import com.glhf.bomberball.config.GameInfiniteConfig;
import com.glhf.bomberball.config.GameStoryConfig;
import com.glhf.bomberball.maze.Maze;
import com.glhf.bomberball.maze.MazeBuilder;
import com.glhf.bomberball.ui.InfiniteModeUI;

import java.util.ArrayList;

public class InfiniteModeScreen extends MenuScreen {

    //attributes
    public Maze maze;
    private ArrayList<Character> characters; // one player and some enemies

    public InfiniteModeScreen() {
        super();
        GameInfiniteConfig config = GameInfiniteConfig.get();
        maze = MazeBuilder.createInfinityMaze();
        addUI(new InfiniteModeUI(this));
    }


}
