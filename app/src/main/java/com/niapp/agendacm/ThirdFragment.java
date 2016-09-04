package com.niapp.agendacm;

import android.app.Fragment;
import android.graphics.Typeface;
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
public class ThirdFragment extends Fragment {

    View myView;
    ImageButton drawer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.contact, container, false);
        TextView comite = (TextView) myView.findViewById(R.id.comite);
        final DrawerLayout draw = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawer = (ImageButton)myView.findViewById(R.id.drawer);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "pol_med.ttf");
        comite.setTypeface(type);
        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                draw.openDrawer(Gravity.LEFT);

            }
        });
        return myView;
    }
}
