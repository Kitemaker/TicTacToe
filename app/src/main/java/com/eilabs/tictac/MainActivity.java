package com.eilabs.tictac;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private int clickCounter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        RelativeLayout layout =(RelativeLayout)findViewById(R.id.layout_root);
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout.getLayoutParams();
//        if(layout.getWidth()<=layout.getHeight()) {
//
//            params.height = params.width;
//            layout.setLayoutParams(params);
//        }
//        else{
//            params.width=params.height;
//            layout.setLayoutParams(params);
//        }


        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void OnTileClicked(View view)
    {
        TextView tview =(TextView)view;
         clickCounter=clickCounter+1 ;
        if(clickCounter==1)
        {WriteX(tview);}
            else if(clickCounter==2)
        {WriteZero(tview);}
        else if(clickCounter<10)
        {
            if((clickCounter%2)>0)
            {WriteX(tview);}
            else
            {WriteZero(tview);}

        }
        }
    public void WriteX(TextView view)
        {
            view.setText(getString(R.string.text_cross));
        }
    public void WriteZero(TextView view)
        {
            view.setText(getString(R.string.text_zero));

        }

}
