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

public class GameListActivity extends BaseGameActivity {


    private ListView listViewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        listViewGame = findViewById(R.id.listViewGame);

        GameGsonAdapter gameGsonAdapter = new GameGsonAdapter(this, gamelist);

        listViewGame.setAdapter(gameGsonAdapter);
        listViewGame.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoGameDetail(gamelist.get(position));
            }
        });


    }



}
