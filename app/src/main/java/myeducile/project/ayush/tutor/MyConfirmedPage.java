package myeducile.project.ayush.tutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MyConfirmedPage extends AppCompatActivity {

    TextView tv;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_confirmed_page);
        tv=(TextView)findViewById(R.id.textView16);
        iv=(ImageView)findViewById(R.id.imageView);
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        iv.setAnimation(myanim);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oo=new Intent(MyConfirmedPage.this,Myfirstpage.class);
                startActivity(oo);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent op=new Intent(getApplicationContext(),Myfirstpage.class);
        startActivity(op);
    }
}
