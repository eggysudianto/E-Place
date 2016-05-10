package com.emergency;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private GoogleMap mMap;
    int PLACE_PICKER_REQUEST = 1;
    GoogleApiClient mGoogleApiClient;
    boolean search=false;
    Toolbar toolbar;
    private GoogleApiClient client;
    LocationManager locationManager = null;
    LocationListener locationListener = null;
    public FloatingActionButton tambalban;
    public FloatingActionButton atm;
    public FloatingActionButton wc;
    final String TAG = "DEBUG";
    Boolean flag = false;
    String latitude;
    String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        //untuk ambil lokasi dan akan di kirimkan ke Activity lain
        tambalban = (FloatingActionButton) findViewById(R.id.action_a);
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        tambalban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = displayGetGPSStatus();
                if (flag) {
                    Log.d(TAG, "onClick: ");

                    locationListener = new MyLocationListeners();
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            5000,
                            10,
                            locationListener
                    );
                    //kalau sudah dapat lokasinya maka munculkan Activity yang di tuju dari floating button
                    //floating buttpn
                    final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.action_a);
                    actionA.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //actionA.setTitle("Action A clicked");
                            //Intent i = new Intent(MainActivity.this, Tambal_banActivity.class);
                            //startActivity(i);
                            Intent myIntent = new Intent(MainActivity.this, Tambal_banActivity.class);
                            myIntent.putExtra("Latitude", latitude);
                            myIntent.putExtra("Longitude", longitude);
                            startActivity(myIntent);
                        }
                    });
                    //floating buttpn

                } else {
                    alertBox("GPS Status", "Your GPS: OFF");
                }
            }
        });


        //untuk ambil lokasi dan akan di kirimkan ke Activity lain
        atm = (FloatingActionButton) findViewById(R.id.action_b);
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = displayGetGPSStatus();
                if (flag) {
                    Log.d(TAG, "onClick: ");

                    locationListener = new MyLocationListeners();
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            5000,
                            10,
                            locationListener
                    );
                    //kalau sudah dapat lokasinya maka munculkan Activity yang di tuju dari floating button
                    //floating buttpn
                    final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.action_b);
                    actionA.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //actionA.setTitle("Action A clicked");
                            //Intent i = new Intent(MainActivity.this, Tambal_banActivity.class);
                            //startActivity(i);
                            Intent myIntent = new Intent(MainActivity.this, ATM_Activity.class);
                            myIntent.putExtra("Latitude", latitude);
                            myIntent.putExtra("Longitude", longitude);
                            startActivity(myIntent);
                        }
                    });
                    //floating buttpn

                } else {
                    alertBox("GPS Status", "Your GPS: OFF");
                }
            }
        });


        //untuk ambil lokasi dan akan di kirimkan ke Activity lain
        wc = (FloatingActionButton) findViewById(R.id.action_c);
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        wc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = displayGetGPSStatus();
                if (flag) {
                    Log.d(TAG, "onClick: ");

                    locationListener = new MyLocationListeners();
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            5000,
                            10,
                            locationListener
                    );
                    //kalau sudah dapat lokasinya maka munculkan Activity yang di tuju dari floating button
                    //floating buttpn
                    final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.action_c);
                    actionA.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //actionA.setTitle("Action A clicked");
                            //Intent i = new Intent(MainActivity.this, Tambal_banActivity.class);
                            //startActivity(i);
                            Intent myIntent = new Intent(MainActivity.this, WC_Activity.class);
                            myIntent.putExtra("Latitude", latitude);
                            myIntent.putExtra("Longitude", longitude);
                            startActivity(myIntent);
                        }
                    });
                    //floating buttpn

                } else {
                    alertBox("GPS Status", "Your GPS: OFF");
                }
            }
        });

        //untuk ambil lokasi dan akan di kirimkan ke Activity lain

        //untuk cek apakah gps sudah aktif atau belum
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))

        {
            buildAlertMessageNoGps();
        }
        //untuk cek apakah gps sudah aktif atau belum

        //memanggil fungsi
        deklarasiWidget();
        setToolbar();
        setNavigationView();
        //memanggil fungsi
    }

    //untuk ambil lokasi dan akan di kirimkan ke Activity lain...
    private void alertBox(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your Device is Disable")
                .setCancelable(false)
                .setTitle("GPS STATUS")
                .setPositiveButton(
                        "GPS ON",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /* this gonna call class of settings then dialog interface disappeared */
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(intent);
                                dialog.cancel();
                            }
                        }
                );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    //untuk ambil lokasi dan akan di kirimkan ke Activity lain...

    //untuk ambil lokasi dan akan di kirimkan ke Activity lain...
    /*----Method to Check GPS is enable or disable ----- */
    private Boolean displayGetGPSStatus() {
        boolean gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        ContentResolver contentResolver = getContentResolver();
//        boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(
//                contentResolver,
//                LocationManager.GPS_PROVIDER
//        );

        if (gpsStatus) {
            return true;
        } else {

            return false;
        }
    }
    //untuk ambil lokasi dan akan di kirimkan ke Activity lain...


    //untuk ambil lokasi dan akan di kirimkan ke Activity lain...
    private class MyLocationListeners implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            longitude = String.valueOf(location.getLongitude());
            Log.d(TAG, "onLocationChanged: " + longitude);

            latitude = String.valueOf(location.getLatitude());
            Log.d(TAG, "onLocationChanged: " + latitude);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
    //untuk ambil lokasi dan akan di kirimkan ke Activity lain...

    //maps
    public void onSearch(View view)
    {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }
    //maps


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    //maps
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setTrafficEnabled(true);
        googleMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(onMyLocationChangeListener);

        // mMap.setPadding(0, 0, 0, 100);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(65.9667, -18.5333))
                .title("Hello world"));


    }
    //maps

    //maps
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                LatLng latLng = new LatLng(place.getLatLng().latitude,place.getLatLng().longitude);
                mMap.addMarker(new MarkerOptions().position(latLng).title(place.getAddress().toString()));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            }
        }
    }
    //maps

    //maps
    private GoogleMap.OnMyLocationChangeListener onMyLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            if(!search) {
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                search=true;
            }
        }
    };
    //maps

    //maps
    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    //maps

    //drawer menu
    private void setNavigationView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
        navigationView.setNavigationItemSelectedListener(naviItemSelect);

        //setContentView(R.layout.header_menu);
        //LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //View vi = inflater.inflate(R.layout.header_menu, null); //log.xml is your file.
        //Intent myIntent = getIntent(); // gets the previously created intent
        //String email = myIntent.getStringExtra("email"); // will return "FirstKeyValue"
        //String avatars = myIntent.getStringExtra("avatar"); // will return "FirstKeyValue"
        //ResourceBundle extras = null;
        //Uri avatar = Uri.parse(extras.getString("avatar"));
        //TextView emailtext = (TextView) vi.findViewById(R.id.emailtext);
        //emailtext.setText("SSSSSS");
        //Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, avatars, Toast.LENGTH_SHORT).show();

    }

    NavigationView.OnNavigationItemSelectedListener naviItemSelect = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            menuItem.setCheckable(true);//membuat tanda hitam setelah di click
            drawerLayout.closeDrawer(GravityCompat.START);
            switch (menuItem.getItemId()) {
                case R.id.home:
                    //cara memanggil ke halaman lain
                    //Snackbar.make(getCurrentFocus(), "Anda Memilih Home", Snackbar.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    return true;
                case R.id.signout:
                    //Snackbar.make(getCurrentFocus(), "Anda Memilih Profile", Snackbar.LENGTH_LONG).show();
                    Intent i1 = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(i1);
                    return true;
                case R.id.address:
                    //Snackbar.make(getCurrentFocus(), "Anda Memilih Address", Snackbar.LENGTH_LONG).show();
                    Intent i2 = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(i2);
                    return true;
                default:
                    return true;
            }
        }
    };
    //drawer menu

    //drawer menu
    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("E-Place");//untuk get title
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void deklarasiWidget() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
    }
    //drawer menu


    //cek gps aktif atau tidak
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("GPS Anda tampaknya dinonaktifkan, apakah Anda ingin mengaktifkannya?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    //cek gps aktif atau tidak


    //jika tekan tombol back maka akan keluar app
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    //jika tekan tombol back maka akan keluar app

    @Override
    public void onClick(View v) {

    }

    /*
    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.emergency/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.emergency/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    */
}
