package com.example.asmfinal.fargment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.asmfinal.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment {
    Button btnDN, btnCT, btnTN;
    private GoogleMap mMap;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;


            UiSettings uisetting = mMap.getUiSettings();
            uisetting.setCompassEnabled(true);
            uisetting.setZoomControlsEnabled(true);
            uisetting.setScrollGesturesEnabled(true);
            uisetting.setTiltGesturesEnabled(true);

            uisetting.setMyLocationButtonEnabled(true);
            // Add a marker in Sydney and move the camera
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);
            LatLng cs2 = new LatLng(10.824901, 106.6725885);
            mMap.addMarker(new MarkerOptions().position(cs2).title("Marker in Cơ sở 2 Nguyễn kiệm"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cs2, 17));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        btnDN = view.findViewById(R.id.btnDN);
        btnCT = view.findViewById(R.id.btnCT);
        btnTN = view.findViewById(R.id.btnTN);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LatLng toado = new LatLng(16.0756721, 108.167584);
                Marker danang = mMap.addMarker(
                        new MarkerOptions()
                                .position(toado)
                                .title("Fpoly Đà Nẵng")
                                .snippet("Cao dang Fpoly Đà Nẵng xin  chào các bạn")
                                .icon(BitmapDescriptorFactory.defaultMarker(
                                        BitmapDescriptorFactory.HUE_YELLOW)));


                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(toado, 17));

            }
        });
        btnCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LatLng toado = new LatLng(10.0267108, 105.7572724);
                Marker danang = mMap.addMarker(
                        new MarkerOptions()
                                .position(toado)
                                .title("Fpoly Cần Thơ")
                                .snippet("Cao dang Fpoly Cần thơ xin chao ban")
                                .icon(BitmapDescriptorFactory.defaultMarker(
                                        BitmapDescriptorFactory.HUE_BLUE)));


                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(toado, 17));

            }
        });
        btnTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng toado = new LatLng(21.0185937, 105.7671758);
                Marker TayNguyen = mMap.addMarker(
                        new MarkerOptions()
                                .position(toado)
                                .title("Fpoly Hà Nội")
                                .snippet("Cao Đẳng FPOLY Hà Nội Xin chào Các bạn")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                );
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(toado, 17));
            }
        });
        return view;
    }



}
