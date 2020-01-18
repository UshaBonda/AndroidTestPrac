package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.GridView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ArrayList<String>> dataArray;
    ArrayList<String> itemArray;
    GridView gridView;
    ArrayList<String> imageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView=findViewById(R.id.gridView);
        String url = "http://starlord.hackerearth.com/studio";

        // Setup 1 MB disk-based cache for Volley
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);

        // Use HttpURLConnection as the HTTP client
        Network network = new BasicNetwork(new HurlStack());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        parseJson(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                new AlertDialog.Builder(MainActivity.this).setTitle("SOng Studio")
                        .setMessage("Server Error")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

            }
        });

        // Instantiate the RequestQueue with the cache and network, start the request
        // and add it to the queue
        RequestQueue queue = new RequestQueue(cache, network);
        queue.start();
        queue.add(stringRequest);
    }

    public void parseJson(Object response) {
        JSONArray jsonArray;
        dataArray = new ArrayList<>();
        imageArray=new ArrayList<>();
        try {
            jsonArray = new JSONArray(response.toString());
            if (jsonArray.length() != 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    itemArray=new ArrayList<>();
                    itemArray.add(jsonArray.getJSONObject(i).optString("song"));
                    itemArray.add(jsonArray.getJSONObject(i).optString("url"));
                    itemArray.add(jsonArray.getJSONObject(i).optString("artists"));
                    itemArray.add(jsonArray.getJSONObject(i).optString("cover_image"));
                    imageArray.add(jsonArray.getJSONObject(i).optString("cover_image"));
                    dataArray.add(itemArray);
                }
                CustomAdapter adapter=new CustomAdapter();
                gridView.setAdapter(adapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

