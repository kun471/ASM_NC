package com.example.asmfinal.fargment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asmfinal.R;
import com.example.asmfinal.adapter.RCV_ListRSS;
import com.example.asmfinal.model.ListTitleRss;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private RecyclerView rcv;
    public static ArrayList<ListTitleRss> list = new ArrayList<>();
    RCV_ListRSS rcv_listRSS;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        rcv = view.findViewById(R.id.listRss);
        list.clear();
        list.add(new ListTitleRss("VnExpress", "https://vnexpress.net/rss/giao-duc.rss", R.drawable.vnexp));
        list.add(new ListTitleRss("Báo 24H", "https://cdn.24h.com.vn/upload/rss/giaoducduhoc.rss", R.drawable.rss24h));
        list.add(new ListTitleRss("Thanh Niên", "https://vnexpress.net/rss/kinh-doanh.rss", R.drawable.thanhnien));
        list.add(new ListTitleRss("Zing", "https://vnexpress.net/rss/giai-tri.rss", R.drawable.zingnews));


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(layoutManager);
        rcv_listRSS = new RCV_ListRSS(getActivity(), list);
        rcv.setAdapter(rcv_listRSS);


        return view;
    }
}
