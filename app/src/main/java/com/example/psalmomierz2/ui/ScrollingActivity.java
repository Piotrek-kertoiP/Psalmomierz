package com.example.psalmomierz2.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.psalmomierz2.R;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class ScrollingActivity extends AppCompatActivity {

    private TextView mainViewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        mainViewText = findViewById(R.id.main_view_text);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("Psalmomierz", MODE_PRIVATE);
        String savedListAsString = sh.getString("used_psalms_list", "");
        List<String> usedPsalmsAsStringsList = Arrays.asList(savedListAsString.split(","));

        List<Boolean> usedPsalms = new LinkedList<>();
        for(String x : usedPsalmsAsStringsList){
            usedPsalms.add(Boolean.parseBoolean(x));
        }

        String savedMainViewTxt = sh.getString("main_view_text", "dupa");
        savedMainViewTxt += "10";
        mainViewText.setText(savedMainViewTxt);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        SharedPreferences sh = getSharedPreferences("Psalmomierz", MODE_PRIVATE);
        SharedPreferences.Editor shEditor = sh.edit();
        shEditor.putString("main_view_text", mainViewText.getText().toString());
        shEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
