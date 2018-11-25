package com.glhf.bomberball;

import com.badlogic.gdx.graphics.Texture;

public class BombRangeBoost extends GameObject implements Bonus{
    // attributes
    private int bomb_range_boost;

    // constructor
    public BombRangeBoost(int position_x, int position_y, Texture appearance, int life) {
        super(position_x,position_y,appearance, life);
        bomb_range_boost=0;
    }
}