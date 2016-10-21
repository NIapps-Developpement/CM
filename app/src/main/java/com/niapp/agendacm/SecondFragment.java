package com.niapp.agendacm;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Nathan on 16-08-16.
 */
public class SecondFragment extends Fragment {
    View myView;
    ImageButton drawer;
    RequestQueue requestQueue;
    String showUrl = "http://cm.890m.com/calendarshow.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.second_layout, container, false);
        final DrawerLayout draw = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        drawer = (ImageButton)myView.findViewById(R.id.drawer);
        getCalendar();

        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                draw.openDrawer(Gravity.LEFT);

            }
        });
        return myView ;
    }
    public boolean getCalendar(){
        final Context contextnet = this.getActivity().getApplicationContext();
//Restart du text et instanciation de la request
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
        }
        else {
            Toast toast = Toast.makeText(contextnet, "Aucune connexion internet. Veuillez vous connecter à un réseau avant de réessayer. ", Toast.LENGTH_LONG);
            toast.show();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                showUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());


                try {

                    //Récupération puis lecture des table id et idée avec affichage
                    //par ordre décroissant
                    JSONArray messages = response.getJSONArray("calendar");
                    ArrayList<ItemCal> itemcal = new ArrayList<>();
                    for (int i = 0; i < messages.length(); i++) {

                        JSONObject message = messages.getJSONObject(i);

                        ItemCal cal = new ItemCal(message.getString("name"), message.getString("dates"), message.getString("place"));
                        itemcal.add(cal);


                    }
                    BindDictionary<ItemCal> dictionary = new BindDictionary<>();
                    dictionary.addStringField(R.id.tvName, new StringExtractor<ItemCal>() {
                        @Override
                        public String getStringValue(ItemCal itemcal, int position) {
                            return itemcal.getName();
                        }
                    });
                    dictionary.addStringField(R.id.tvPlace, new StringExtractor<ItemCal>() {
                        @Override
                        public String getStringValue(ItemCal itemcal, int position) {
                            return "" + itemcal.getPlace();
                        }
                    });
                    dictionary.addStringField(R.id.tvDate, new StringExtractor<ItemCal>() {
                        @Override
                        public String getStringValue(ItemCal itemcal, int position) {
                            return "" + itemcal.getDate();
                        }
                    });

                    FunDapter adapter = new FunDapter(SecondFragment.this.getActivity(), itemcal, R.layout.item_layout_cal, dictionary);


                    ListView lvItem = (ListView)myView.findViewById(R.id.list_itemcal);
                    lvItem.setAdapter(adapter);

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

        return true;
    }
}
