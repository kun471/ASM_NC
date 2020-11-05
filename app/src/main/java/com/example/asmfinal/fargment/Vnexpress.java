package com.example.asmfinal.fargment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmfinal.R;
import com.example.asmfinal.model.ListNews;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.asmfinal.adapter.RCV_News;
import com.example.asmfinal.model.XMLDOMParser;

/**
 * A simple {@link Fragment} subclass.
 */
public class Vnexpress extends Fragment {
    private RecyclerView rcv;
    public static ArrayList<ListNews> list = new ArrayList<>();
    RCV_News rcv_news;
    String link = "";

    public Vnexpress() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vnexpress, container, false);
        rcv = view.findViewById(R.id.rcvNews);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            link = bundle.getString("link", null);
        }
//        Toast.makeText(getActivity(), link, Toast.LENGTH_SHORT).show();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(layoutManager);
        rcv_news = new RCV_News(getActivity(), list);
        rcv.setAdapter(rcv_news);
        list.clear();
        new ReadRss().execute(link);

        return view;
    }

    private class ReadRss extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListDescription = document.getElementsByTagName("description");
            String tieude = "";
            String link = "";
            String moTa = "";
            String img = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                String cdata = nodeListDescription.item(i + 1).getTextContent();//  bỏ thẻ item đầu tiên của xml
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>"); //pattern định dạng img của thẻ img
                Matcher matcher = p.matcher(cdata);
                if (matcher.find()) {
                    img = matcher.group(1);
                    Log.d("hinh", img);
                }
                Element element = (Element) nodeList.item(i);
                tieude = parser.getValue(element, "title");
//                moTa = parser.getValue(element, "description");
                link = parser.getValue(element, "link");
                list.add(new ListNews(cutTitle(tieude), link,  img));
                super.onPostExecute(s);
            }
            rcv_news.notifyDataSetChanged();
        }
    }

    //Cắt tiêu đề ngắn lại
    public String cutTitle(String title) {

        if (title.length() >= 70) {
            return title.substring(0, 70) + "...";
        }
        return title;

    }


}
