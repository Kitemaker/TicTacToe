package com.eilabs.tictactoe;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.eilabs.tictactoe.MESSAGE";
    private BluetoothAdapter adapter =BluetoothAdapter.getDefaultAdapter();
    IntentFilter filter =new IntentFilter();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }


    public void SelectGameMode(View view)
    {
     Button btn =(Button)view;
        if(btn.getText()== getString(R.string.text_btnSingleDevice))
        {
            Intent intent =new Intent(this,MainActivity.class);
          //  String message = getString(R.string.action_physics);
            intent.putExtra(EXTRA_MESSAGE, "");
            startActivity(intent);
        }
        else if(btn.getText()==getString(R.string.text_DoubleDevice))
        {
            filter.addAction(BluetoothDevice.ACTION_FOUND);
            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

            registerReceiver(mReceiver, filter);
            adapter.startDiscovery();
        }

    }
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                //discovery starts, we can show progress dialog or perform other tasks
                Toast.makeText(getApplicationContext(), "ACTION_DISCOVERY_STARTED " , Toast.LENGTH_SHORT).show();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //discovery finishes, dismis progress dialog
                Toast.makeText(getApplicationContext(), "ACTION_DISCOVERY_FINISHED ", Toast.LENGTH_SHORT).show();
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                Toast.makeText(getApplicationContext(), "Found device " + device.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);

        super.onDestroy();
    }

}
