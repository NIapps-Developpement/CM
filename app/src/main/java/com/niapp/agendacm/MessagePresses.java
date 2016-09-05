package com.niapp.agendacm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by pandipandas on 05-09-16.
 */
public class MessagePresses extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_presses);
    Spinner section = (Spinner)findViewById(R.id.listset1);
    String[] section_list = new String[]{"MEDI", "DENT", "VETE","BIME"};
    ArrayAdapter<String> section_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, section_list);
    section.setAdapter(section_adapter);

    Spinner annee = (Spinner)findViewById(R.id.listset2);
    String[] annee_list = new String[]{"BA1", "BA2", "BA3","MA1", "MA2", "MA3"};
    ArrayAdapter<String> annee_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, annee_list);
    annee.setAdapter(annee_adapter);
}
}
