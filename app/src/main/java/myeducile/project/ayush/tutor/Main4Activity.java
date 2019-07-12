package myeducile.project.ayush.tutor;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Main4Activity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    EditText name,tt,phone,flat,locality,city,pincode;
    Button bt;
    CheckBox k;
    int day,month,year,hour,minute;
    int dayFinal,monthFinal,yearFinal,hourFinal,minuteFinal;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        name=(EditText)findViewById(R.id.editText);
        phone=(EditText)findViewById(R.id.editText2);
        flat=(EditText)findViewById(R.id.editText3);
        locality=(EditText)findViewById(R.id.editText4);
        city=(EditText)findViewById(R.id.editText5);
        pincode=(EditText)findViewById(R.id.editText6);
        Backendless.setUrl( MyDefaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), MyDefaults.APPLICATION_ID, MyDefaults.API_KEY );


        String userId = UserIdStorageFactory.instance().getStorage().get();
        Backendless.UserService.findById(userId, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                name.setText(""+response.getProperty("name"));
                phone.setText(""+response.getProperty("Phone"));
                flat.setText(""+response.getProperty("Address"));

                city.setText(""+response.getProperty("City"));
                pincode.setText(""+response.getProperty("Pincode"));



            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });

        bt=(Button)findViewById(R.id.button6);
        k=(CheckBox)findViewById(R.id.checkBox);

        tt=(EditText)findViewById(R.id.time1);
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                year=c.get(Calendar.YEAR);
                month=c.get(Calendar.MONTH);
                day=c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(Main4Activity.this,Main4Activity.this,year,month,day);
                datePickerDialog.show();

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s1 = name.getText().toString();
                String s2 = phone.getText().toString();
                String s3 = flat.getText().toString();
                String s4 = city.getText().toString();
                String s5 = pincode.getText().toString();
                String s6=tt.getText().toString();

                if (s1.trim().equalsIgnoreCase("") || s2.trim().equalsIgnoreCase("")
                        || s3.trim().equalsIgnoreCase("") ||
                        s4.trim().equalsIgnoreCase("") || s5.trim().equalsIgnoreCase("")
                        || s6.trim().equalsIgnoreCase("")
                        )

                {

                    Toast.makeText(getApplicationContext(), "Required Field Missing", Toast.LENGTH_LONG).show();
                }

                else {

                    Intent intent = new Intent(Main4Activity.this, MyFinalPage.class);
                    String[] myStrings = new String[]{s1, s2};
                    intent.putExtra("strings", myStrings);


                    SharedPreferences preferences = getSharedPreferences("default", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("text1", s1);
                    editor.putString("text2", s2);
                    editor.putString("text3", s3);
                    editor.putString("text4", s4);
                    editor.putString("text5", s5);
                    editor.putString("text6", s6);



                    editor.commit();

                    startActivity(intent);


               /*final HashMap h1=new HashMap<>();
                h1.put("Name",s1);
                h1.put("Phone",s2);





                Backendless.Persistence.of("educile_bookings").save(h1, new AsyncCallback<Map>() {
                    @Override
                    public void handleResponse(Map response) {
                        Toast.makeText(Main4Activity.this,"Successful",Toast.LENGTH_LONG).show();
                        String userId = UserIdStorageFactory.instance().getStorage().get();
                        Backendless.UserService.findById(userId, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                   h1.put("Relatedusers",response.getEmail());

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {

                            }
                        });
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(Main4Activity.this,"Error",Toast.LENGTH_LONG).show();
                    }
                });*/
                }
            }
        });



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_game:
              Backendless.UserService.logout(new AsyncCallback<Void>() {
                  @Override
                  public void handleResponse(Void response) {
                      Toast.makeText(Main4Activity.this, "Logged Out", Toast.LENGTH_LONG).show();

                  }

                  @Override
                  public void handleFault(BackendlessFault fault) {

                  }
              });

        }
        return super.onOptionsItemSelected(item);
    }
    protected void onRestoreInstanceState(Bundle savedState) {


        final EditText textBox =
                (EditText) findViewById(R.id.editText);

        CharSequence userText =
                savedState.getCharSequence("savedText");

        textBox.setText(userText);
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        final EditText textBox =
                (EditText) findViewById(R.id.editText);
        CharSequence userText = textBox.getText();
        outState.putCharSequence("savedText", userText);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                yearFinal=year;
                monthFinal=month+1;
                dayFinal=dayOfMonth;

                Calendar c=Calendar.getInstance();
                hour=c.get(Calendar.HOUR_OF_DAY);
                minute=c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog=new TimePickerDialog(Main4Activity.this,Main4Activity.this,hour,minute, android.text.format.DateFormat.is24HourFormat(this));
                timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        hourFinal=hourOfDay;
        minuteFinal=minute;
        tt.setText("Date:"+yearFinal+"/"+monthFinal+"/"+dayFinal+" Time:"+hourFinal+":"+minuteFinal);
    }
}
