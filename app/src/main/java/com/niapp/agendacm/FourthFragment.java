package com.niapp.agendacm;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ilan on 17-08-16.
 */
public class FourthFragment extends Fragment {
    View myView;
    ImageButton but;
    ImageButton drawer;
    EditText pin;
    RequestQueue requestQueue;
    String mdpUrl = "http://cm.890m.com/login.php";
    CharSequence mdptry;
    public static int i;
    public static int a;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.login, container, false);
        final DrawerLayout draw = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        drawer = (ImageButton)myView.findViewById(R.id.drawer);
        final Vibrator v = (Vibrator) this.getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        but = (ImageButton)myView.findViewById(R.id.sendpin);
        pin = (EditText) myView.findViewById(R.id.pin);
        drawer.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {

                                          draw.openDrawer(Gravity.LEFT);

                                      }
                                  });
        //Set variables for Toast
        final Context context = getActivity().getApplicationContext();
        final CharSequence text = "Le code pin est incorrect";
        final int duration = Toast.LENGTH_SHORT;
        final Toast toast = Toast.makeText(context, text, duration);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdptry = pin.getText().toString();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        mdpUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {
                            JSONArray mdpget = response.getJSONArray("mdp");
                            for (i = 0; i < mdpget.length(); i++) {

                                JSONObject mdp = mdpget.getJSONObject(i);

                                String mdpgood = mdp.getString("Mdp");
                                System.out.println(mdpgood);
                                if (mdpgood.equals(mdptry)) {
                                    if(i==8){
                                        startActivity(new Intent(FourthFragment.this.getActivity(), MessagePresses.class));
                                    }
                                    else {
                                        startActivity(new Intent(FourthFragment.this.getActivity(), Message.class));
                                        System.out.println("Loop Value = " + i);
                                        setI(i);
                                        v.cancel();
                                        toast.cancel();
                                    }
                                }
                                else {
                                    v.vibrate(500);
                                    toast.show();
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.append(error.getMessage());

                    }
                });
                requestQueue.add(jsonObjectRequest);

            }
        });

        return myView;
    }
    public static int getI(){
        return a;
    }
    public void setI(int i){
        a = i;
    }
}

