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
            Intent intent =new Intent(this,DevicesActivity.class);
            //  String message = getString(R.string.action_physics);
            intent.putExtra(EXTRA_MESSAGE, "");
            startActivity(intent);
        }

    }


}
