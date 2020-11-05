package com.example.asmfinal.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmfinal.R;
import com.example.asmfinal.fargment.Vnexpress;
import com.example.asmfinal.model.ListTitleRss;

import java.util.ArrayList;

public class RCV_ListRSS extends RecyclerView.Adapter<RCV_ListRSS.ViewHolder> {
    private Context context;
    private ArrayList<ListTitleRss> list = new ArrayList<>();

    public RCV_ListRSS() {
    }

    public RCV_ListRSS(Context context, ArrayList<ListTitleRss> list) {
        this.context = context;
        this.list = list;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, link;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleRss);
            image = itemView.findViewById(R.id.imgRss);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int posisiont = getLayoutPosition();
            ListTitleRss listTitleRss = list.get(posisiont);
            String link = listTitleRss.getLink();

            //Chuyển sang frament mới

            AppCompatActivity activity  =(AppCompatActivity)v.getContext();
            Vnexpress kh = new Vnexpress();
            //Truyền link sang fragment
            Bundle bundle = new Bundle();
            bundle.putString("link", link);
            kh.setArguments(bundle);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.navHostFragment, kh)
                    .addToBackStack(null)
                    .commit();
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_list_rss, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListTitleRss rss = list.get(position);
        holder.title.setText(rss.getTitle());
        holder.image.setImageResource(rss.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}

