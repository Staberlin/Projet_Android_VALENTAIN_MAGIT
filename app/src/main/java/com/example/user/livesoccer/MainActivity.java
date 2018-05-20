package com.example.user.livesoccer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Adapter.OnItemClickListener{
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_DATE = "date";

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private ArrayList<Item> mItemList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItemList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.quitter:
                finish();
                return(true);
            case R.id.about:
                Toast myToast = Toast.makeText(getApplicationContext(),"VALENTAIN et MAGIT", Toast.LENGTH_LONG);
                myToast.show();
                return(true);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void parseJSON() {
        String url = "https://newsapi.org/v2/top-headlines?sources=the-sport-bible&apiKey=7fff6f6ddf0e479c87fde6d57b7764b7";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject hit = jsonArray.getJSONObject(i);

                        String title = hit.getString("title");
                        String image = hit.getString ("urlToImage");
                        String description = hit.getString ("description");
                        String date = hit.getString ("publishedAt");
                        mItemList.add(new Item(image, title, description,date));
                    }

                    mAdapter = new Adapter(MainActivity.this, mItemList);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(MainActivity.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);

    }

    @Override
    public void OnItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        Item clickedItem = mItemList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getTitle());
        detailIntent.putExtra(EXTRA_DESCRIPTION, clickedItem.getDescription());
        detailIntent.putExtra(EXTRA_DATE, "Publised at :" + clickedItem.getDate());

        startActivity(detailIntent);
    }
}
