package com.eilabs.tictactoe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.eilabs.tictactoe.R;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static android.widget.Toast.*;

public class DevicesActivity extends AppCompatActivity {
   // private BluetoothAdapter adapter =BluetoothAdapter.getDefaultAdapter();
    IntentFilter filter =new IntentFilter();
    List<String> deviceList= new ArrayList<String>();
    ArrayAdapter<String> deviceListAdapter;
    String deviceClicked;
    IntentFilter mIntentFilter;
    //Wi Fi
    WifiP2pManager mManager;
    android.net.wifi.p2p.WifiP2pManager.Channel mChannel;
    WiFiDirectBroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        deviceListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, deviceList);
       final ListView listView = (ListView) findViewById(android.R.id.list);


        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(), "Discover Peers Succcess", LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int reasonCode) {
                Toast.makeText(getApplicationContext(), "Discover Peers Fails", LENGTH_SHORT).show();

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                deviceClicked = (String) listView.getItemAtPosition(position);
                //Toast.makeText(getBaseContext(), elementClicked.getElement(), Toast.LENGTH_SHORT).show();
                makeText(getBaseContext(), deviceClicked, LENGTH_SHORT);
            }
        });

            }

            @Override
            public void onDestroy() {
                unregisterReceiver(mReceiver);
                super.onDestroy();

            }


    /* register the broadcast receiver with the intent values to be matched */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }
    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

        }
