package com.neeral.mystanford;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private final static Set<String> valid = new HashSet<String>(Arrays.asList("X", "Y", "N", "O", "SE", "C"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_bus_schedule);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bus_routes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0, false); // this prevents OnItemSelectedListener from firing at startup
        // https://stackoverflow.com/questions/2562248/how-to-keep-onitemselected-from-firing-off-on-a-newly-instantiated-spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                launchWebview(parent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void launchWebview(View view) {
        final Intent i = new Intent(this, MyWebView.class);

        int url = -1;
        switch (view.getId()) {
            case R.id.button_campus:
                url = R.string.url_campus_map;
                break;
            case R.id.button_livebus:
                url = R.string.url_bus_map;
                break;
            case R.id.button_dining:
                url = R.string.url_dining_hours;
                break;
            case R.id.button_gym:
                url = R.string.url_gym_hours;
                break;
            case R.id.spinner_bus_schedule:
                final String route =  (String) ((Spinner) findViewById(R.id.spinner_bus_schedule)).getSelectedItem();
                url = R.string.url_bus_schedule_prefix;
                i.putExtra("route", route);
                break;

        }

        i.putExtra("url", url);
        startActivity(i);
    }
}
