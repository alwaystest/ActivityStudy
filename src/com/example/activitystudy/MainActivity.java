package com.example.activitystudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import java.util.regex.*;

public class MainActivity extends Activity {
	String Tag = "consol";
	String save, flag;
	double num1 = 0, num2 = 0;
	String pattern1 = "\\d+\\.?\\d*[*/]\\d+\\.?\\d*";
	String pattern2 = "\\d+\\.?\\d*[+-]\\d+\\.?\\d*";
	Pattern p1 = Pattern.compile(pattern1);
	Pattern p2 = Pattern.compile(pattern2);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.e(Tag, "Create");
		EditText txt = (EditText) findViewById(R.id.editText1);
		txt.setCursorVisible(false);
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
		if (save != null && !save.equals("")) {
			EditText input = (EditText) findViewById(R.id.editText1);
			input.setText(save);
			save = null;	//free memory
		}
		Log.e(Tag, "Resume");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		EditText input = (EditText) findViewById(R.id.editText1);
		if (!input.getText().equals("")) {
			save = input.getText().toString();
		}
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
		if(save.indexOf("=") >= 0 && v.getId() != R.id.buttonEquals){
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
			if(save == null || save.equals(""))
				break;
			save = save.substring(0, save.length()-1);
			break;
			//TODO: confirm
		// case R.id.ButtonBrackets :
		// break;
		case R.id.ButtonC:
			save = null;
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
			if(save.indexOf("=") >= 0)
				break;
			String result = new String(save);
			Matcher m = p1.matcher(result);
			while(m.find()){
				String tmp = m.group();
				if(tmp.indexOf("/") != -1){
					String[] num = tmp.split("/");
					Double tmpresult = Calculate.devide(Double.parseDouble(num[0]),Double.parseDouble(num[1]));
					result = result.replaceFirst(tmp, tmpresult.toString());
				}
				else{
					String[] num = tmp.split("\\*");
					Double tmpresult = Calculate.multiply(Double.parseDouble(num[0]),Double.parseDouble(num[1]));
					result = result.replaceFirst(Pattern.quote(tmp), tmpresult.toString());
				}
				m = p1.matcher(result);
			}
			m = p2.matcher(result);
			while(m.find()){
				String tmp = m.group();
				if(tmp.indexOf("+") != -1){
					String[] num = tmp.split("\\+");
					Double tmpresult = Calculate.add(Double.parseDouble(num[0]),Double.parseDouble(num[1]));
					result = result.replaceFirst(Pattern.quote(tmp), tmpresult.toString());
				}
				else{
					String[] num = tmp.split("-");
					Double tmpresult = Calculate.minus(Double.parseDouble(num[0]),Double.parseDouble(num[1]));
					result = result.replaceFirst(tmp, tmpresult.toString());
				}
				m = p2.matcher(result);
			}
			save +="=" + result;
			break;
		case R.id.ButtonPoint:
			save += ".";
			break;
		case R.id.ButtonChange:
			break;
		}
		EditText txt = (EditText) findViewById(R.id.editText1);
		if(save == null){
			txt.setText("");
		}else{
		txt.setText(save);
		}
	}
}
