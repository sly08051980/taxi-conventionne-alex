package com.slyfly1.taxiconventionne77.calendrier;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.slyfly1.taxiconventionne77.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class ConfirmAddress extends DialogFragment implements
        View.OnClickListener, OnMapReadyCallback {

    public Activity c;
    public Dialog d;
    public Button yes, no;

    private GoogleMap mMap;
    MapView mapView;
    Double Lat;
    Double Long;
    String Address;
    TextView myAddress;
    Button SelectBtn;
    Button ChangeBtn;
    Selected mCallback;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lat = getArguments().getDouble("lat");
        Long = getArguments().getDouble("long");
        Address = getArguments().getString("address");

    }
    MapFragment mapFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.custom_confirm_address, container, false);
        myAddress=(TextView)v.findViewById(R.id.myAddress);
        SelectBtn=(Button) v.findViewById(R.id.Select);
        ChangeBtn=(Button) v.findViewById(R.id.Change);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapp);
        mapFragment.getMapAsync(this);
        // Toast.makeText(getActivity(),mNum,Toast.LENGTH_LONG).show();

        SelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onSelect(true);
                getFragmentManager().beginTransaction().remove(mapFragment).commit();
                dismiss();
            }
        });
        ChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onSelect(false);
                getFragmentManager().beginTransaction().remove(mapFragment).commit();
                dismiss();
            }
        });
        getDialog().setCanceledOnTouchOutside(true);
        return v;

    }

    public interface Selected {
        void onSelect(Boolean value);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity){
            a = (Activity) context;
            mCallback = (Selected) a;
        }

    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        getFragmentManager().beginTransaction().remove(mapFragment).commit();

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        dismiss();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        myAddress.setText(Address);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(Lat,Long));

        markerOptions.title(Address);
        mMap.clear();
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                new LatLng(Lat,Long), 16f);
        mMap.animateCamera(location);
        mMap.addMarker(markerOptions);
        Log.d("status","success");
    }

}