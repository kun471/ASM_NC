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
import com.example.asmfinal.fargment.Fm_WebView;
import com.example.asmfinal.fargment.Vnexpress;
import com.example.asmfinal.model.ListNews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RCV_News extends RecyclerView.Adapter<RCV_News.ViewHolder> {
    private Context context;
    private ArrayList<ListNews> list = new ArrayList<>();

    public RCV_News() {
    }

    public RCV_News(Context context, ArrayList<ListNews> list) {
        this.context = context;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleNews);
            img = itemView.findViewById(R.id.imgNews);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            String link = Vnexpress.list.get(position).getLink();
//            Toast.makeText(context, link,Toast.LENGTH_SHORT).show();

            //Chuyển sang frament mới
            AppCompatActivity activity  =(AppCompatActivity)v.getContext();
            Fm_WebView web = new Fm_WebView();
            //Truyền link sang fragment
            Bundle bundle = new Bundle();
            bundle.putString("link", link);
            web.setArguments(bundle);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.navHostFragment, web)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_news, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListNews listNews = list.get(position);
        holder.title.setText(listNews.getTitle());
        Picasso.with(context).load(list.get(position).getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    //Chuyển link thành hình ảnh
//    public static Bitmap getBitmapFromURL(String src) {
//        try {
//            URL url = new URL(src);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap myBitmap = BitmapFactory.decodeStream(input);
//            return myBitmap;
//        } catch (IOException e) {
//            // Log exception
//            return null;
//        }
//    }
}
