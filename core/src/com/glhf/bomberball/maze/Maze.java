package com.glhf.bomberball.maze;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Textures;
import com.glhf.bomberball.gameobject.Bomb;
import com.glhf.bomberball.gameobject.GameObject;
import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Random;

public class Maze {

    private String title;
    private int height;
    private int width;
    private Vector2[] positionStart;
    private Vector2 positionEnd;
    private GameObject[][] tab;
    private static Gson gson;
    private long seed; //défini les variations des textures

    public Maze() {
//        title = "Classic";
//        height = 11;
//        width = 13;
//        positionStart = new Vector2[4];
//        positionStart[0]= new Vector2(1,1);
//        positionStart[1]= new Vector2(1,10);
//        positionStart[2]= new Vector2(12,1);
//        positionStart[3]= new Vector2(12,10);
//        positionEnd = new Vector2(6,5);
//        tab = new GameObject[width][height];
//        tab[0][0] = new Bomb(0,0,1);
//        tab[0][1] = new ActiveEnemy(1,0,3);
        if(gson==null)createGson();
    }

    public GameObject getGameObjectAt(int pos_x,int pos_y)
    {
        return tab[pos_x][pos_y];
    }


    public void draw(SpriteBatch batch){
        Random rand = new Random(seed);
        for(int y=0; y<height; y++) {
            for (int x = 0; x < width; x++) {
                GameObject object = tab[x][y];
                int posX = x * Constants.BOX_WIDTH;
                int posY = y * Constants.BOX_HEIGHT;
                Texture texture = Textures.get(Constants.FLOOR_TEXTURE_NAME+"_"+rand.nextInt(Constants.NB_FLOOR_VARIATION));
                batch.draw(texture, posX, -posY);
                if(object != null) {
                    object.draw(batch);
                }
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public static Maze fromJsonFile(String filename) {
        Maze m;
        if(gson==null)createGson();
        try {
            m = gson.fromJson(new FileReader(new File(Constants.PATH_MAZE+filename)), Maze.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("ERROR : "+e.getMessage());
        }
        return m;
    }

    public static Maze fromJson(String str) {
        return gson.fromJson(str, Maze.class);
    }

    public String toJson(){
        return gson.toJson(this);
    }

    private static void createGson() {
        gson = new GsonBuilder()
                .registerTypeAdapter(GameObject.class, new PropertyBasedInterfaceMarshal())
                .registerTypeAdapter(Bomb.class, new PropertyBasedInterfaceMarshal()).create();
    }

    public static class PropertyBasedInterfaceMarshal implements JsonSerializer<Object>, JsonDeserializer<Object> {
        private static Gson gson = new Gson();
        private static final String CLASS_META_KEY = "_class";

        @Override
        public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObj = (JsonObject) jsonElement;
            String className = jsonObj.get(CLASS_META_KEY).getAsString();
            try {
                Class<?> clz = Class.forName(className);
                GameObject o = (GameObject) gson.fromJson(jsonElement, clz);
                String[] s = className.split("[.]");
                o.setAppearance(Textures.get(s[s.length-1]));
                return o;
            } catch (ClassNotFoundException e) {
                throw new JsonParseException(e);
            }
        }


        @Override
        public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            System.out.println(object);
            JsonElement jsonEle = gson.toJsonTree(object);
            jsonEle.getAsJsonObject().addProperty(CLASS_META_KEY, object.getClass().getCanonicalName());
            return jsonEle;
        }

    }

    // destruction of GameObject when dead
    public void handleDestruction(){
        int i, j;
        for(i=0; i>-getHeight(); i--){
            for(j=0; j<getWidth(); j++){
                if(! getGameObjectAt(i,j).isAlive()){
                    tab[i][j]=null;
                }
            }
        }
    }
}
