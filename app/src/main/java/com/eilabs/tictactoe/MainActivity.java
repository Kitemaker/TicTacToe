package com.eilabs.tictactoe;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;


public class  MainActivity extends ActionBarActivity {
    private int clickCounter=0;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String textGrid[]= {"1","1","1","1","1","1","1","1","1"};
    private  boolean gameOver=false;
    private String WinCombo="0-0-0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // ClearAll(findViewById(R.id.textView_1));
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
        if (gameOver == false) {
            TextView tview = (TextView) view;
            if (tview.getText().equals(getString(R.string.text_empty))) {
                clickCounter = clickCounter + 1;

                switch (clickCounter) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 9:
                        WriteX(tview);
                        break;

                    case 2:
                    case 4:
                    case 6:
                    case 8:
                        WriteZero(tview);
                        break;

                    default:
                        Log.d(TAG, "Invalid Count");
                        break;

                }
                if (clickCounter == 5 || clickCounter == 7 || clickCounter == 9) {
                    checkForGame(getString(R.string.text_cross));

                } else if (clickCounter == 6 || clickCounter == 8 ) {
                    checkForGame(getString(R.string.text_zero));
                }
            }
        }
    }

    public void checkForGame(String tbvalue)
    {

        TextView resultView=(TextView)(findViewById( R.id.textViewResult))  ;
        try
        {
            if (textGrid[0].equals(tbvalue) && textGrid[1].equals(tbvalue) && textGrid[2].equals(tbvalue)) {
                gameOver = true;
                WinCombo="0-1-2";
            } else if (textGrid[3].equals(tbvalue) && textGrid[4].equals(tbvalue) && textGrid[5].equals(tbvalue)) {
                gameOver = true;
                WinCombo="3-4-5";
            } else if (textGrid[6].equals(tbvalue) && textGrid[7].equals(tbvalue) && textGrid[8].equals(tbvalue)) {
                gameOver = true;
                WinCombo="6-7-8";
            } else if (textGrid[0].equals(tbvalue) && textGrid[3].equals(tbvalue) && textGrid[6].equals(tbvalue)) {
                gameOver = true;
                WinCombo="0-3-6";
            } else if (textGrid[1].equals(tbvalue) && textGrid[4].equals(tbvalue) && textGrid[7].equals(tbvalue)) {
                gameOver = true;
                WinCombo="1-4-7";
            } else if (textGrid[2].equals(tbvalue) && textGrid[5].equals(tbvalue) && textGrid[8].equals(tbvalue)) {
                gameOver = true;
                WinCombo="2-5-8";
            } else if (textGrid[0].equals(tbvalue) && textGrid[4].equals(tbvalue) && textGrid[8].equals(tbvalue)) {
                gameOver = true;
                WinCombo="0-4-8";
            } else if (textGrid[2].equals(tbvalue) && textGrid[4].equals(tbvalue) && textGrid[6].equals(tbvalue)) {
                gameOver = true;
                WinCombo="2-4-6";
            } else {
                gameOver = false;
            }

            if (gameOver == true) {
                resultView.setText("Player \' " + tbvalue + "\' Wins");
                colorTextBox(WinCombo);
            }
            if(clickCounter>=9 && gameOver==false)
            {
                resultView.setText(getString(R.string.text_GameOver));
            }
        }
        catch (Exception e)
        {
            Log.d(TAG,e.getMessage().toString());
        }

    }

    public void WriteX(TextView view)
    {
            view.setText(getString(R.string.text_cross));
            String tboxName[]=getResources().getResourceName(view.getId()).split("_");

            textGrid[Integer.valueOf(tboxName[1])]=getString(R.string.text_cross);
       // Log.d(TAG,"Value of TextBox"+getResources().getResourceName(view.getId())+ "X");
        }
    public void WriteZero(TextView view)
    {
            view.setText(getString(R.string.text_zero));
            String tboxName[]=getResources().getResourceName(view.getId()).split("_");

            textGrid[Integer.valueOf(tboxName[1])]=getString(R.string.text_zero);
          //  Log.d(TAG,"Value of TextBox"+getResources().getResourceName(view.getId())+ "0");

    }

    public void ClearAll(View view)
    {
        TextView resultView=(TextView)(findViewById( R.id.textViewResult))  ;
        resultView.setText(R.string.text_empty);
        clickCounter=0;
        TextView tview;
        gameOver=false;
        for(int i=0;i<9;i++)
        {
            textGrid[i]=getString(R.string.text_empty);
        }

        tview=(TextView)findViewById(R.id.textView_0);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_1);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_2);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_3);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_4);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_5);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_6);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_7);
        tview.setText(R.string.text_empty);
        tview=(TextView)findViewById(R.id.textView_8);
        tview.setText(R.string.text_empty);


    }
    public void colorTextBox(String winCombo)
    {TextView tbView;

        switch (winCombo)
        {
            case "0-1-2":
               // tbView=(TextView)findViewById(R.id.textView_0).setBackground;
                break;
            case "3-4-5":
                break;
            case "6-7-8":
                break;
            case "0-3-6":
                break;
            case "1-4-7":
                break;
            case "2-5-8":
                break;
            case "0-4-8":
                break;
            case "2-4-6":

        }
    }

    }
