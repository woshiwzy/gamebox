package com.gamebox.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.common.util.AssertTool;
import com.common.util.Tool;
import com.domain.Game;
import com.domain.GameGson;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseGameActivity extends Activity {

    protected List<GameGson> gamelist;

    public static final String EXIST = "exist";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        String gameJsons = AssertTool.getAssertStringContent(this, "games.json");

        gamelist = parseString2List(gameJsons, GameGson.class);
        super.onCreate(savedInstanceState);

    }

    public void gotoGameDetail(GameGson game) {
        Intent intent = new Intent();
        intent.putExtra("game", game);
        if (game.isIsvertial()) {
            Tool.startActivity(this, GameVerticalActivity.class, intent);
        } else {
            Tool.startActivity(this, GameHoritalActivity.class, intent);
        }

    }


    public void gotoGameDetail(String gameName){
        for(GameGson game:gamelist){
            if(game.getName().equals(gameName)){
                gotoGameDetail(game);
                break;
            }
        }
    }



    public <T> List<T> parseString2List(String json, Class clazz) {
        Type type = new ParameterizedTypeImpl(clazz);
        List<T> list = new Gson().fromJson(json, type);
        return list;
    }

    private class ParameterizedTypeImpl implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

}
