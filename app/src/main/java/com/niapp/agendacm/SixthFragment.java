package com.niapp.agendacm;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
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
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Nathan on 16-08-16.
 */
public class SixthFragment extends Fragment {
    ImageButton refresh;
    ImageButton drawer;
    String showUrl = "http://cm.890m.com/newsfeedshow.php";
    TextView result;
    RequestQueue requestQueue;
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.presses_layout, container, false);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        final DrawerLayout draw = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        Spinner section = (Spinner)getActivity().findViewById(R.id.listpick1);
        String[] section_list = new String[]{"MEDI", "DENT", "VETE","BIME"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, section_list);
        section.setAdapter(adapter);
        Spinner année = (Spinner)getActivity().findViewById(R.id.listpick1);
        String[] année_list = new String[]{"BA1", "BA2", "BA3","MA1", "MA2", "MA3"};
        année.setAdapter(adapter);
        return myView;
        }




}