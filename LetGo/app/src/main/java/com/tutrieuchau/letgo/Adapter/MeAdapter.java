package com.tutrieuchau.letgo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tutrieuchau.letgo.Model.Me;
import com.tutrieuchau.letgo.R;
import com.tutrieuchau.letgo.Service.LoadImageService;

import java.util.ArrayList;

/**
 * Created by ductam on 2017/07/13.
 */

public class MeAdapter extends BaseAdapter implements View.OnClickListener{
    private Context context;
    private ArrayList<Me>listMe;
    private LayoutInflater inflater;
    private Activity activity;
    public MeAdapter(Context context, ArrayList<Me> listMe){
        this.context = context;
        this.listMe = listMe;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listMe.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null){
            // initial
            view = inflater.inflate(R.layout.list_row_me,null);
            TextView txtMeAccountName = (TextView) view.findViewById(R.id.txtMeAccountName);
            TextView txtMePostTime = (TextView) view.findViewById(R.id.txtMePostTime);
            TextView txtMePostPlace = (TextView) view.findViewById(R.id.txtMePostPlace);
            ImageView btnMeMenu = (ImageView) view.findViewById(R.id.btnMeMenu);
            TextView txtMeContent = (TextView) view.findViewById(R.id.txtMeContent);
            LinearLayout btnMeComment = (LinearLayout)view.findViewById(R.id.btnMeComment);
            LinearLayout btnMeShare = (LinearLayout)view.findViewById(R.id.btnMeShare);
            LinearLayout btnMeLike = (LinearLayout)view.findViewById(R.id.btnMeLike);
            ImageView imgMeThumbnails = (ImageView) view.findViewById(R.id.imgMeThumbnails);
            ImageView imgMeMedia = (ImageView) view.findViewById(R.id.imgMeMedia);

            //set data
            Me me = listMe.get(position);
            txtMeAccountName.setText(me.accountName);
            txtMePostTime.setText(me.postTime);
            txtMePostPlace.setText(me.postPlace);
            txtMeContent.setText(me.content);
            // load thumbnails
            new LoadImageService(imgMeThumbnails).execute(me.thumbnails);
            // load media
            new LoadImageService(imgMeMedia).execute(me.media);
            btnMeMenu.setOnClickListener(this);
            btnMeComment.setOnClickListener(this);
            btnMeLike.setOnClickListener(this);
            btnMeShare.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if(context instanceof com.tutrieuchau.letgo.Activity.Me){
            ((com.tutrieuchau.letgo.Activity.Me) context).onClick(v);
        }
    }
}
