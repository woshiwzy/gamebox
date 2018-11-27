package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.domain.Game;
import com.yanzhenjie.andserver.sample.R;

import java.util.ArrayList;

public class GameAdapter extends BaseAdapter {


    private LayoutInflater layoutInflater;
    private ArrayList<Game> arrayListGames;

    public GameAdapter(Context context, ArrayList<Game> arrayListGames) {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayListGames = arrayListGames;
    }

    @Override
    public int getCount() {
        return arrayListGames.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListGames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.item_game, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Game game = arrayListGames.get(position);

        viewHolder.textViewGameName.setText(game.getResultName().replace("game_",""));

        return convertView;
    }


    class ViewHolder {

        public TextView textViewGameName;

        public ViewHolder(View view) {
            textViewGameName = view.findViewById(R.id.textViewGameName);
        }
    }

}
