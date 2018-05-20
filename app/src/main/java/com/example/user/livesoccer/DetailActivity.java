package com.example.user.livesoccer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static com.example.user.livesoccer.MainActivity.EXTRA_DESCRIPTION;
import static com.example.user.livesoccer.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String descriptionName = intent.getStringExtra(EXTRA_DESCRIPTION);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewDescription = findViewById(R.id.text_view_detail);
        Button button = findViewById(R.id.back);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewDescription.setText(descriptionName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast myToast = Toast.makeText(getApplicationContext(),"back", Toast.LENGTH_LONG);
                myToast.show();
                OnItemClick();

            }
        });
    }

    public void OnItemClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
