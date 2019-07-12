package myeducile.project.ayush.tutor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;

import static myeducile.project.ayush.tutor.Myadapter1.sgd;




public class Main2Activity extends AppCompatActivity {

    Button bt1,bt2,bt3,bt4;
    TextView tv1,tv2,tv3;
    CheckBox ch1,ch2,ch3;
    EditText tv;
    String mycost1;
    int tt=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        bt3=(Button)findViewById(R.id.button2);
        bt4=(Button)findViewById(R.id.button);
        tv=(EditText)findViewById(R.id.instructions);


        tv1=(TextView)findViewById(R.id.mytitle);
        tv3=(TextView)findViewById(R.id.mycost);

        ch1=(CheckBox)findViewById(R.id.checkBox1);
        ch2=(CheckBox)findViewById(R.id.checkBox2);
        ch3=(CheckBox)findViewById(R.id.checkBox3);

        tv2=(TextView)findViewById(R.id.count);
        Backendless.setUrl(MyDefaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(), MyDefaults.APPLICATION_ID, MyDefaults.API_KEY);

        String title22 = "";
        final Intent intent = getIntent();
        if (null != intent) {
            title22 = intent.getStringExtra(sgd);

        }
        tv1.setText(title22);


        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent op=new Intent(Main2Activity.this,Myfirstpage.class);
                startActivity(op);
            }
        });


         mycost1=tv3.getText().toString();


        SharedPreferences preferences1 = getSharedPreferences("default2", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = preferences1.edit();
        editor1.putString("text12",title22);
        //editor1.putString("mycost",mycost1);



        editor1.commit();



        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tt++;
                tv2.setText(" "+tt);
                switch (tt) {
                    case 1:
                        tv3.setText("Rs 500");
                        break;
                    case 2:
                        tv3.setText("Rs 600");
                        break;
                    case 3:
                        tv3.setText("Rs 750");
                        break;
                    case 4:
                        tv3.setText("Rs 800");
                        break;
                    case 5:
                        tv3.setText("Rs 800");
                        break;
                    case 6:
                        tv3.setText("Rs 900");
                        break;

                }
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tt--;
                tv2.setText(" "+tt);
                if (tt >= 0) {
                    switch (tt) {
                        case 1:
                            tv3.setText("Rs 500");
                            break;
                        case 2:
                            tv3.setText("Rs 600");
                            break;
                        case 3:
                            tv3.setText("Rs 750");
                            break;
                        case 4:
                            tv3.setText("Rs 800");
                            break;
                        case 5:
                            tv3.setText("Rs 800");
                            break;
                        case 6:
                            tv3.setText("Rs 900");
                            break;

                    }
                }
                else if(tt<0){
                    tt=0;
                    tv2.setText(" "+tt);
                }
            }

        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Backendless.UserService.loggedInUser()!="")
                {
                    String studentas=tv3.getText().toString();
                    SharedPreferences preferences1 = getSharedPreferences("default21", MODE_PRIVATE);
                    SharedPreferences.Editor editor133 = preferences1.edit();
                    editor133.putString("Students",studentas);
                    editor133.commit();
                    Intent it=new Intent(Main2Activity.this,Main4Activity.class);
                    startActivity(it);

                }
                else {

                    Intent it = new Intent(Main2Activity.this, MyLogin.class);
                    startActivity(it);

                }
                String comments=tv.getText().toString();

                SharedPreferences preferences1 = getSharedPreferences("default6", MODE_PRIVATE);
                SharedPreferences.Editor editor12 = preferences1.edit();
                editor12.putString("mycomments",comments);
                editor12.putString("mycost",mycost1);

                editor12.commit();

            }
        });

        /*ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if(checked)
                {
                    tv3.setText("Rs 500");
                }
                else{
                    tv3.setText("Rs 500");
                }
            }
        });
        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if(checked)
                {
                    tv3.setText("Rs 500");

                }
                else{
                    tv3.setText("Rs 500");
                }
            }
        });ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if(checked)
                {
                    tv3.setText("Rs 600");
                }
                else{
                    tv3.setText("Rs 500");
                }
            }
        });
*/
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox1:
                if (checked){
                    tv3.setText("Rs 500");
                }break;
                // Put some meat on the sandwich
            case R.id.checkBox2:
                if (checked){
                    tv3.setText("Rs 500");
                }
                break;

            case R.id.checkBox3:
                if (checked){
                    tv3.setText("Rs 600");
                }
                else{
                    tv3.setText("Rs 500");
                }
                break;
        }
    }
}
