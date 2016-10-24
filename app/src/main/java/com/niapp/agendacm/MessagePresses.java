package com.niapp.agendacm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pandipandas on 05-09-16.
 */
public class MessagePresses extends Activity{
    String insertUrlmsg = "http://cm.890m.com/pressespost.php";
    EditText idea;
    ImageButton send;
    String msg;
    RequestQueue requestQueue;
    String k, check;
    Boolean q = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_presses);
    final Spinner section = (Spinner)findViewById(R.id.listset1);
    String[] section_list = new String[]{"MEDI", "DENT", "VETE","BIME"};
    final ArrayAdapter<String> section_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, section_list);
    section.setAdapter(section_adapter);

    final Spinner annee = (Spinner)findViewById(R.id.listset2);
    String[] annee_list = new String[]{"BA1", "BA2", "BA3","MA1", "MA2", "MA3"};
    final ArrayAdapter<String> annee_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, annee_list);
    annee.setAdapter(annee_adapter);

        send = (ImageButton) findViewById(R.id.send);
        idea = (EditText) findViewById(R.id.idee);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        final Context context = getApplicationContext();
        final CharSequence text2 = "Veuillez rentrer une information";
        final int duration = Toast.LENGTH_SHORT;
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                check = idea.getText().toString();

                if(check.isEmpty()){
                    Toast toast = Toast.makeText(context, text2, duration);
                    toast.show();
                    q = false;
                }

                msg = check;
                msg = msg.replace("'","\\\'");

                String text1 = annee.getSelectedItem().toString();
                String text2 = section.getSelectedItem().toString();
                System.out.println(text1);
                System.out.println(text2);

                final String groupgen = text2 + "-" + text1;

                if (q) {

                    //Instanciation de la request
                    StringRequest request = new StringRequest(Request.Method.POST, insertUrlmsg, new Response.Listener<String>() {


                        @Override
                        public void onResponse(String response) {

                            System.out.println(response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        //Method pour envoyer le texte
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parameters = new HashMap<String, String>();
                            parameters.put("msg", msg);
                            parameters.put("group", groupgen);
                            return parameters;
                        }
                    };
                    requestQueue.add(request);


                    //On passe sur la ScrollingActivity
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }


        });
}


}
