package com.glhf.bomberball.menu;

import com.badlogic.gdx.Gdx;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.GUI.Button;
import com.glhf.bomberball.Game;
import com.glhf.bomberball.Graphics;


public class StateMainMenu extends State {
    //Attributes
    private Button solo;
    private Button multi;
    private Button editor;
    private Button parametres;
    private Button quit;

    boolean solob = false;
    //Constructor
    public StateMainMenu(String name){
        super(name);
        this.settings();
    }

    /*Loading textures*/
    public void settings(){
        //BoutonSolo
        solo = new Button(160, 400, 400, 100, "BoutonSolo", true);
        //BoutonMulti
        multi = new Button(160, 300, 400, 100, "BoutonMulti", true);
        //BoutonEditeur
        editor = new Button(160, 200, 400, 100, "BoutonEditeur", true);
        //BoutonParametres
        parametres = new Button(160, 100, 400, 100, "BoutonParametres", true);
        //BoutonQuitter
        quit = new Button(160, 0, 400, 100, "BoutonQuitter", true);
    }

    public void draw(){
        solo.draw(batch);
        multi.draw(batch);
        editor.draw(batch);
        parametres.draw(batch);
        quit.draw(batch);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        y = Constants.APP_HEIGHT - y;
        if(solo.contains(x, y)) {
            State state = new StateSoloMenu("Menu Solo");
            Game.setState(state);
            //TODO: On lance le jeu solo
        }

        if(editor.contains(x,y)) {

        }

        if(multi.contains(x, y))
        {
            State state = new StateMultiMenu("Menu Multi");
            Game.setState(state);
        }

        if(parametres.contains(x, y))
        {
            State state = new StateSettingsMenu("Menu Paramètres");
            Game.setState(state);
        }

        if(quit.contains(x,y)){
            Gdx.app.exit();
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y){
        y = Constants.APP_HEIGHT - y;

        solo.alternativeSprite(x, y);

        multi.alternativeSprite(x, y);

        parametres.alternativeSprite(x, y);

        editor.alternativeSprite(x, y);

        quit.alternativeSprite(x, y);

        return false;
    }

}
