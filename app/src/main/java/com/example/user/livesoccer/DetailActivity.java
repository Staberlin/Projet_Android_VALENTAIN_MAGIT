package com.example.user.livesoccer;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static com.example.user.livesoccer.MainActivity.EXTRA_DATE;
import static com.example.user.livesoccer.MainActivity.EXTRA_DESCRIPTION;
import static com.example.user.livesoccer.MainActivity.EXTRA_PUB_URL;
import static com.example.user.livesoccer.MainActivity.EXTRA_TITLE;
import static com.example.user.livesoccer.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String TitleName = intent.getStringExtra(EXTRA_TITLE);
        String DescriptionName = intent.getStringExtra(EXTRA_DESCRIPTION);
        String Date = intent.getStringExtra(EXTRA_DATE);
        final String url = intent.getStringExtra(EXTRA_PUB_URL);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewTitle = findViewById(R.id.text_view_title);
        TextView textViewDescription = findViewById(R.id.text_view_description);
        TextView textViewDate = findViewById(R.id.text_view_date);
        Button button = findViewById(R.id.back);
        Button button1 = findViewById(R.id.link);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewTitle.setText(TitleName);
        textViewDescription.setText(DescriptionName);
        textViewDate.setText(Date);
        button1.setText(url);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnItemClick();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(browserIntent);
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_menu,menu);
        return true;
    }

    private void handleActionBiers(){
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        DownloadManager downloadManager;

        downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);

        Uri uri = Uri.parse(imageUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Long reference = downloadManager.enqueue(request);
    }

    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.quitter:
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            case R.id.download:
                handleActionBiers();
                return(true);
            case R.id.credits:
                Toast myToast = Toast.makeText(getApplicationContext(),"Android project made by Mr.VALENTAIN and Mr.MAGIT", Toast.LENGTH_LONG);
                myToast.show();
                return(true);
            case R.id.about:
                Toast myToastAbout = Toast.makeText(getApplicationContext(),"API: The Sport Bible", Toast.LENGTH_LONG);
                myToastAbout.show();
                return(true);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void OnItemClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
