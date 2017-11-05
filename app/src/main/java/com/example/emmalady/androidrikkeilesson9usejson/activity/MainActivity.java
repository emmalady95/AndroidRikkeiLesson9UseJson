package com.example.emmalady.androidrikkeilesson9usejson.activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.emmalady.androidrikkeilesson9usejson.R;
import com.example.emmalady.androidrikkeilesson9usejson.adapter.ItemAdapter;
import com.example.emmalady.androidrikkeilesson9usejson.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String DATA_UML = "https://jsonplaceholder.typicode.com/posts";
    public static final String KEY_BODY = "Body";
    public RecyclerView mRcContent;
    public List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isNetworkConnected()) {
            new getDataAsyncTask().execute();
        }
    }

    private class getDataAsyncTask extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Void... params) {
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(DATA_UML);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK){
                    BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String line;
                    while((line = bf.readLine()) != null){
                        sb.append(line);
                    }
                    bf.close();
                    Log.d("Data Receiver", sb.toString());
                    return new JSONArray(sb.toString());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //return null;
            return null;
        }
        @Override
        protected void onPostExecute(JSONArray jsonArray){
            super.onPostExecute(jsonArray);
            if(jsonArray != null){
                //itemList.clear();

                Toast.makeText(MainActivity.this, "NOT NULL", Toast.LENGTH_LONG).show();
                for(int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject json = jsonArray.getJSONObject(i);
                        String id = json.getString("id");
                        String title = json.getString("title");
                        String content = json.getString("body");
                        itemList.add(new Item(id, title, content));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }else{
                Toast.makeText(MainActivity.this, "NULL ROI!!!!", Toast.LENGTH_LONG).show();
            }
            if(itemList.size() > 0){
                mRcContent = (RecyclerView) findViewById(R.id.rc_content);
                //ItemAdapter itemAdapter = new ItemAdapter(itemList);
                //mRcContent.setAdapter(itemAdapter);
                //mRcContact.setAdapter(new ContactAdapter(contactList, new ContactAdapter.OnItemClickListener()
                mRcContent.setAdapter(new ItemAdapter(itemList, new ItemAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Item item, int position) {
                        Intent i = new Intent(MainActivity.this, ItemDetailActivity.class);
                        item = itemList.get(position);
                        String content = item.getContent();
                        i.putExtra(KEY_BODY, content);
                        startActivity(i);
                    }
                }));
                mRcContent.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                        LinearLayoutManager.VERTICAL, false));
            }else{
                Toast.makeText(MainActivity.this, "NO DATA FOUND", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private boolean isNetworkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null && networkInfo.isConnected();
    }
}
