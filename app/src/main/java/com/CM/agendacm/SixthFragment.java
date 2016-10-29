package com.CM.agendacm;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.Objects;


/**
 * Created by Nathan on 16-08-16.
 */
public class SixthFragment extends Fragment {
    ImageButton refresh;
    ImageButton drawer;
    String showUrl = "http://cm.890m.com/PressesShow.php";
    TextView result;
    RequestQueue requestQueue;
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.presses_layout, container, false);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        final DrawerLayout draw = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawer = (ImageButton)myView.findViewById(R.id.drawer);
        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                draw.openDrawer(Gravity.LEFT);

            }
        });

        final Spinner section = (Spinner)myView.findViewById(R.id.listpick1);
        String[] section_list = new String[]{"MEDI", "DENT", "VETE","BIME"};
        final ArrayAdapter<String> section_adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, section_list);
        section.setAdapter(section_adapter);

        final Spinner annee = (Spinner)myView.findViewById(R.id.listpick2);
        String[] annee_list = new String[]{"BA1", "BA2", "BA3","MA1", "MA2", "MA3"};
        final ArrayAdapter<String> annee_adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, annee_list);
        annee.setAdapter(annee_adapter);

        annee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> annee_adapter, View view, int position, long id) {
                String text1 = annee_adapter.getSelectedItem().toString();
                String text2 = section.getSelectedItem().toString();
                System.out.println(text1);
                System.out.println(text2);
                final String groupgen = text2 + "-" + text1;
                System.out.println(groupgen);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                try {

                    JSONArray presses = response.getJSONArray("presses");
                    ArrayList<Item> itemspr = new ArrayList<>();
                    for (int i = 0; i < presses.length(); i++) {

                        JSONObject presse = presses.getJSONObject(i);
                        String group = presse.getString("group");
                        String message = presse.getString("message");
                    if(Objects.equals(groupgen, group)) {
                        Item msg = new Item(message, group);
                        itemspr.add(msg);
                    }



                    }
                    BindDictionary<Item> dictionary = new BindDictionary<>();
                    dictionary.addStringField(R.id.tvName, new StringExtractor<Item>() {
                        @Override
                        public String getStringValue(Item itemspr, int position) {
                            return itemspr.getName();
                        }
                    });
                    dictionary.addStringField(R.id.tvDate, new StringExtractor<Item>() {
                        @Override
                        public String getStringValue(Item itemspr, int position) {
                            return "" + itemspr.getDate();
                        }
                    });

                    FunDapter adapter = new FunDapter(SixthFragment.this.getActivity(), itemspr, R.layout.item_layout, dictionary);

                    ListView lvItem = (ListView)myView.findViewById(R.id.list_itempr);
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
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
          section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> section_adapter, View view, int position, long id) {
                String text1 = annee.getSelectedItem().toString();
                String text2 = section_adapter.getSelectedItem().toString();
                System.out.println(text1);
                System.out.println(text2);
                final String groupgen = text2 + "-" + text1;
                System.out.println(groupgen);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                try {

                    JSONArray presses = response.getJSONArray("presses");
                    ArrayList<Item> itemspr = new ArrayList<>();
                    for (int i = 0; i < presses.length(); i++) {

                        JSONObject presse = presses.getJSONObject(i);
                        String group = presse.getString("group");
                        String message = presse.getString("message");
                    if(Objects.equals(groupgen, group)) {
                        Item msg = new Item(message, group);
                        itemspr.add(msg);
                    }



                    }
                    BindDictionary<Item> dictionary = new BindDictionary<>();
                    dictionary.addStringField(R.id.tvName, new StringExtractor<Item>() {
                        @Override
                        public String getStringValue(Item itemspr, int position) {
                            return itemspr.getName();
                        }
                    });
                    dictionary.addStringField(R.id.tvDate, new StringExtractor<Item>() {
                        @Override
                        public String getStringValue(Item itemspr, int position) {
                            return "" + itemspr.getDate();
                        }
                    });

                    FunDapter adapter = new FunDapter(SixthFragment.this.getActivity(), itemspr, R.layout.item_layout, dictionary);

                    ListView lvItem = (ListView)myView.findViewById(R.id.list_itempr);
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
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return myView;


    }




}