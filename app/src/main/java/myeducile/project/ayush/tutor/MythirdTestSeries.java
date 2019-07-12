package myeducile.project.ayush.tutor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.backendless.Backendless;

import static myeducile.project.ayush.tutor.Myadapter1.sgd;


public class MythirdTestSeries extends AppCompatActivity {

    Button bt1,bt2,bt3,bt4;
    TextView tv1,tv2,tv3;
    CheckBox ch1,ch2,ch3;
    int tt=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myloayout3);

        bt3 = (Button) findViewById(R.id.button2ts);
        bt4 = (Button) findViewById(R.id.buttonts);


        tv1 = (TextView) findViewById(R.id.mytitlets);
        tv3 = (TextView) findViewById(R.id.mycostts);
        String title22 = "";
        String title23 = "";

        final Intent intent = getIntent();
        if (null != intent) {
            title22 = intent.getStringExtra(sgd);
            title23=intent.getStringExtra("des");

        }
        tv1.setText(title22);
        tv3.setText(title23);



        Backendless.setUrl(MyDefaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(), MyDefaults.APPLICATION_ID, MyDefaults.API_KEY);



    }
}
