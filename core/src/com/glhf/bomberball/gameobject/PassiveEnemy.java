package com.glhf.bomberball.gameobject;


import com.glhf.bomberball.utils.Directions;

import java.util.ArrayList;

public class PassiveEnemy extends Enemy {

    public PassiveEnemy(String skin, int life, int initial_moves, int strength, ArrayList<Directions> way) {
        super(skin, life, initial_moves, strength);
        this.setWay(way);
    }

    @Override
    public void createAI() {

    }

    @Override
    public void updateAI() {

    }
}
