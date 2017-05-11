package com.cazime;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    EditText name,username,password,confirmpassword;
    static EditText    dob;
    Button signupbtn;
    static String datestring;
    TextInputLayout namelayout,usernamelayout,passwordlayout,cnfpass_layout,doblayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        namelayout=(TextInputLayout) findViewById(R.id.name_layout);
        usernamelayout=(TextInputLayout) findViewById(R.id.username_layout);
        passwordlayout=(TextInputLayout) findViewById(R.id.password_layout);
        cnfpass_layout=(TextInputLayout) findViewById(R.id.cnfpass_layout);
        doblayout=(TextInputLayout) findViewById(R.id.dob_layout);
        namelayout.setHint("Name");
        usernamelayout.setHint("Username");
        passwordlayout.setHint("Password");
        cnfpass_layout.setHint("Confirm Password");


        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.cnfpass);
        dob = (EditText) findViewById(R.id.dob);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment=new datepickerfragment();
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragment.show(fragmentManager,"datepicker");
            }
        });
        signupbtn = (Button) findViewById(R.id.signup);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String un=username.getText().toString();
                String pwd=password.getText().toString();
                String cnfpwd=confirmpassword.getText().toString();
                String db=datestring;
                if(validation(n,un,pwd,db)){
                    if(!pwd.equals(cnfpwd)){
                        Toast.makeText(signup.this,"password do not match..",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Database database=new Database(signup.this);
                        database.insert(n,un,pwd,db);
                    }
                }

            }
        });
    }
    public boolean validation(String name,String un,String pwd,String dob){
        if(name.length()==0){
            namelayout.setError("Enter valid name");
            return false;
        }
        else if(un.length()==0){
            usernamelayout.setError("Enter valid Username");
            return false;
        }
        else if(pwd.length()==0){
            passwordlayout.setError("Enter valid Password");
            return false;
        }
        else if(dob.length()==0){
            doblayout.setError("Enter valid D.O.B");
            return false;
        }
        return true;
    }
    public static class datepickerfragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar=Calendar.getInstance();
            int yr=calendar.get(Calendar.YEAR);
            int mnt=Calendar.get(Calendar.MONTH);
            int day=Calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),this,yr,mnt,day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            datestring=dayOfMonth+"/"+(month+1)+"/"+year;
            dob.setText(datestring);

        }
    }
}
