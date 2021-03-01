package io.android.avatar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
        ImageView dtlsImg;
        TextView id, name, email;
        Avatar avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        dtlsImg = findViewById(R.id.dtls_img);
        id = findViewById(R.id.dtls_id);
        name = findViewById(R.id.dtls_name);
        email = findViewById(R.id.dtls_email);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        avatar = (Avatar) bundle.get("avatar");
        id.setText(avatar.getId()+"");
        name.setText(avatar.getfName()+" "+avatar.getlName());
        email.setText(avatar.getEmail());
        Picasso.get().load(avatar.getAvatar()).into(dtlsImg);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}