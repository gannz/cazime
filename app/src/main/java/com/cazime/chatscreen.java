package com.cazime;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class chatscreen extends AppCompatActivity {
    public chatAdapter adapter;
    public ArrayList<ChatMessage> chatlist = new ArrayList<>();
    ListView msgListView;
    ImageView submitButton,emojibutton;
    EditText textmmsg;
    TextView name,onlinestatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatscreen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
        name=(TextView) findViewById(R.id.actionbar_title);
        onlinestatus=(TextView) findViewById(R.id.onlinestatus);
        name.setText("Linda Natasha");
        onlinestatus.setText("online");
        textmmsg=(EditText) findViewById(R.id.lv);
        msgListView = (ListView) findViewById(R.id.lv);
        adapter = new chatAdapter(chatscreen.this, chatlist);
        submitButton = (ImageView) findViewById(R.id.submit_btn);
        msgListView.setAdapter(adapter);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String message = textmmsg.getEditableText().toString();

                sendTextMessage(message);


            }
        });
        emojibutton=(ImageView) findViewById(R.id.emoji_btn);
        emojibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(chatscreen.this,signup.class);
                startActivity(intent);
            }
        });
    }


    public void sendTextMessage(String message) {

            if (message.trim().length() > 0) {
                final ChatMessage chatMessage = new ChatMessage(message, true);
                chatMessage.setTime(CommonMethods.getCurrentTime());
                chatMessage.setDate(CommonMethods.getCurrentDate());
                textmmsg.setText("");
                adapter.add(chatMessage);

            }
        }

    public static class CommonMethods {
        static SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        static SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");

        public static String getCurrentTime() {
            Calendar c=Calendar.getInstance();
            return  format.format(c.getTime());
        }
        public static String  getCurrentDate()  {
            Calendar c = Calendar.getInstance();

            return dateFormat.format(c.getTime());

        }


    }

    }

