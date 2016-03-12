package com.eilabs.tictactoe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager mManager;
    private Channel mChannel;
    private DevicesActivity mActivity;
    ArrayAdapter<String> deviceListAdapter;
    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, Channel channel,
                                       DevicesActivity activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mActivity = activity;
        deviceListAdapter = new ArrayAdapter<String>(mActivity.getBaseContext(), android.R.layout.simple_expandable_list_item_1, peers);
        final ListView listView = (ListView)mActivity.findViewById(android.R.id.list);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            // Check to see if Wi-Fi is enabled and notify appropriate activity
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                // Wifi P2P is enabled
                //Toast.makeText(mActivity.getApplicationContext(),"WI FI Enabled",Toast.LENGTH_SHORT).show();
            } else {
                // Wi-Fi P2P is not enabled
                Toast.makeText(mActivity.getApplicationContext(),"WI FI NOT Enabled",Toast.LENGTH_SHORT).show();
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            Toast.makeText(mActivity.getApplicationContext(),"Calling RequestPeers",Toast.LENGTH_SHORT).show();
            if (mManager != null) {
                mManager.requestPeers(mChannel, myPeerListListener);

            }
            // Call WifiP2pManager.requestPeers() to get a list of current peers
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            // Respond to new connection or disconnections

        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            // Respond to this device's wifi state changing

       }
    }
    private List peers = new ArrayList();
    private WifiP2pManager.PeerListListener myPeerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peerList) {

            Toast.makeText(mActivity.getApplicationContext(),"onPeersAvailable",Toast.LENGTH_SHORT).show();

            // Out with the old, in with the new.
            peers.clear();
          //  peers.addAll(peerList.get());
            for (WifiP2pDevice bt : peerList.getDeviceList())
                peers.add(bt.deviceName);

            ListView listView = (ListView) mActivity.findViewById(android.R.id.list);
            listView.setAdapter(deviceListAdapter);

            if (peers.size() == 0) {
               // Toast.makeText(this, "No devices found",Toast.LENGTH_SHORT);
                return;
            }
        }
    };
}