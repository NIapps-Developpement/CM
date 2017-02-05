package com.CM.agendacm;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Nathan on 16-08-16.
 */
public class FifthFragment extends Fragment {

    View myView;
    ImageButton drawer;
    ImageButton pfizer;
    ImageButton uber;
    ImageButton lpm;
    ImageButton classpro;
    ImageButton jupiler;
    ImageButton noiko;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.sponsors, container, false);
        TextView comite = (TextView) myView.findViewById(R.id.comite);
        final DrawerLayout draw = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawer = (ImageButton)myView.findViewById(R.id.drawer);
    pfizer  = (ImageButton)myView.findViewById(R.id.pfizer);
        jupiler = (ImageButton)myView.findViewById(R.id.jupiler);
        lpm = (ImageButton)myView.findViewById(R.id.lpm);
        noiko = (ImageButton)myView.findViewById(R.id.noiko);
        uber = (ImageButton)myView.findViewById(R.id.uber);
        classpro = (ImageButton)myView.findViewById(R.id.classpro);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "pol_med.ttf");
        comite.setTypeface(type);
        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                draw.openDrawer(Gravity.LEFT);

            }
        });
        pfizer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://goo.gl/G0vqxE"));
                startActivity(i);
            }
        });
        lpm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://goo.gl/Hhg6Un"));
                startActivity(i);
            }
        });
        uber.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://goo.gl/HfidSz"));
                startActivity(i);
            }
        });
        classpro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://goo.gl/2sjyy6"));
                startActivity(i);
            }
        });
        noiko.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://goo.gl/IX5Q8Z"));

                startActivity(i);
            }
        });
        jupiler.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://goo.gl/dlHNpC"));

                startActivity(i);
            }
        });
        return myView;
    }
}