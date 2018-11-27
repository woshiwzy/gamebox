package com.gamebox.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adapter.GameGsonAdapter;
import com.common.util.AssertTool;
import com.common.util.Tool;
import com.domain.GameGson;
import com.google.gson.Gson;
import com.yanzhenjie.andserver.sample.R;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class GameListActivity extends Activity {


    private ListView listViewGame;
    private List<GameGson> gamelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        listViewGame = findViewById(R.id.listViewGame);

        String gameJsons = AssertTool.getAssertStringContent(this, "games.json");

        gamelist = parseString2List(gameJsons, GameGson.class);

        GameGsonAdapter gameGsonAdapter = new GameGsonAdapter(this, gamelist);

        listViewGame.setAdapter(gameGsonAdapter);
        listViewGame.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoGameDetail(gamelist.get(position));
            }
        });
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
