package com.example.activitystudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.regex.*;

public class MainActivity extends Activity {
    String Tag = "console";
    String save;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(Tag, "Create");
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.e(Tag, "Start");
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        Log.e(Tag, "Restart");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.e(Tag, "Resume");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.e(Tag, "Pause");
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.e(Tag, "Stop");
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.e(Tag, "Destroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openActivity2(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
        Log.e(Tag, "click");
    }

    public void clickButton(View v) {
        if (save == null) {
            save = "";
        }
        if (save.contains("=") && v.getId() != R.id.buttonEquals) {
            save = "";
        }
        switch (v.getId()) {
            case R.id.Button0:
                save += "0";
                break;
            case R.id.Button1:
                save += "1";
                break;
            case R.id.Button2:
                save += "2";
                break;
            case R.id.Button3:
                save += "3";
                break;
            case R.id.Button4:
                save += "4";
                break;
            case R.id.Button5:
                save += "5";
                break;
            case R.id.Button6:
                save += "6";
                break;
            case R.id.Button7:
                save += "7";
                break;
            case R.id.Button8:
                save += "8";
                break;
            case R.id.Button9:
                save += "9";
                break;

            case R.id.ButtonBack:
                if (save == null || save.equals(""))
                    break;
                if (save.charAt(save.length() - 1) == '(')
                    count--;
                else if (save.charAt(save.length() - 1) == ')')
                    count++;
                save = save.substring(0, save.length() - 1);
                break;
            case R.id.ButtonBrackets:
                if (count < 0) {
                    save = "格式错误";
                    count = 0;
                    break;
                }
                if (count == 0) {
                    save += '(';
                    count++;
                } else {
                    String pattern = ".*[+-/*]$";
                    if (save.matches(pattern)) {
                        save += '(';
                        count++;
                    } else {
                        save += ')';
                        count--;
                    }
                }
//                if (save.charAt(save.length() - 1) != '(' && count > 0) {
//                    save += '(';
//                    count++;
//                } else {
//                    save += ')';
//                    count--;
//                }
                break;
            case R.id.ButtonC:
                save = null;
                count = 0;
                break;
            case R.id.ButtonAdd:
                save += "+";
                break;
            case R.id.ButtonDevide:
                save += "/";
                break;
            case R.id.ButtonMinus:
                save += "-";
                break;
            case R.id.ButtonMultiply:
                save += "*";
                break;
            case R.id.buttonEquals:
                if (save.contains("="))
                    break;
                String result = Calculate.doString(save);//脱里面的括号
                result = Calculate.doString('(' + result + ')'); //整体计算
                save += "=" + result;
                break;
            case R.id.ButtonPoint:
                save += ".";
                break;
            case R.id.ButtonChange:
                //TODO: todo
                break;
        }
        TextView txt = (TextView) findViewById(R.id.TextView1);
        if (save == null) {
            txt.setText("");
        } else {
            txt.setText(save);
        }
    }
}
