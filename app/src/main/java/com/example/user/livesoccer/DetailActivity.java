package com.example.user.livesoccer;

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

import java.net.URI;

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

    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.quitter:
                finish();
                return(true);
            case R.id.credits:
                Toast myToast = Toast.makeText(getApplicationContext(),"Android project made by M.VALENTAIN and M.MAGIT", Toast.LENGTH_LONG);
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
