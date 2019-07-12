package myeducile.project.ayush.tutor;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyContactUs extends AppCompatActivity {

    TextView tv;
    ImageView iv1,iv2,iv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contact_us);
        tv=(TextView)findViewById(R.id.cus);
        iv1=(ImageView)findViewById(R.id.fb);
        iv2=(ImageView)findViewById(R.id.insta);
        iv3=(ImageView)findViewById(R.id.youtube);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(
                        MyContactUs.this,android.Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions( MyContactUs.this, new
                            String[]{android.Manifest.permission.CALL_PHONE}, 0);
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: 7002825163" )));
                }
            }
        });



        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/pages/category/Education/Educile-Tutors-182834268958893/"));
                startActivity(browserIntent);
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/educiletutors/?hl=en"));
                startActivity(browserIntent);
            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/channel/UCmYeOdrxjfuoQeC_QAiXQdA?view_as=subscriber"));
                startActivity(browserIntent);
            }
        });

    }
}
