package com.CM.agendacm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Ilan on 17-04-16.
 */
public class Message extends Activity{
    String insertUrlmsg = "http://cm.890m.com/postnewsfeed.php";
    EditText idea;
    ImageButton send;
    String msg;
    RequestQueue requestQueue;
    String service;
    String k, check;
    Boolean q = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        Typeface police1 =  Typeface.createFromAsset(getAssets(), "pol1.ttf");

        send = (ImageButton) findViewById(R.id.send);
        idea = (EditText) findViewById(R.id.idee);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        final Context context = getApplicationContext();
        final CharSequence text2 = "Veuillez rentrer une information";
        final int duration = Toast.LENGTH_SHORT;
        int y = FourthFragment.getI();
        if (y == 0) {
            q = true;
            service = "#PCM";
        } else if (y == 1) {
            q = true;
            service = "#Info-Photo";
        } else if (y == 2) {
            q = true;
            service = "#Bar";
        } else if (y == 3) {
            q = true;
            service = "#LASC";
        } else if (y == 4) {
            q = true;
            service = "#BALEF";
        } else if (y == 5) {
            q = true;
            service = "#Event";
        } else if (y == 6) {
            q = true;
            service = "#Info-Comm";
        } else if (y == 7) {
            q = true;
            service = "#RCP";
        }
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
                    DateFormat df = new SimpleDateFormat("dd/MM");
                    String date = df.format(Calendar.getInstance().getTime());
                    final String servicefinal = service +" "+ date;

                    if (q) {
                        String Urlnotif = "http://cm.890m.com/admin.php";
                    StringRequest requestnotif = new StringRequest(Request.Method.POST, Urlnotif, new Response.Listener<String>() {


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
                            parameters.put("nom ", servicefinal);
                            return parameters;
                        }
                    };
                    requestQueue.add(requestnotif);


                        msg = msg.replace("'","\\\'");
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
                                parameters.put("nom", servicefinal);
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
