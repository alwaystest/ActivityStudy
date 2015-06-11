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
import android.widget.TextView;

import java.util.regex.*;

public class MainActivity extends Activity {
	String Tag = "consol";
	String save, flag;
	double num1 = 0, num2 = 0;
	FixCapacityStack<Double> vals;
	FixCapacityStack<String> ops;
	String pattern = "\\d+\\.*\\d*";
	Pattern r = Pattern.compile(pattern);
	

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
		vals = new FixCapacityStack<Double>();
		ops = new FixCapacityStack<String>();
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
			TextView input = (TextView) findViewById(R.id.TextView);
			input.setText(save);
			save = null;	//free memory
		}
		Log.e(Tag, "Resume");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		TextView input = (TextView) findViewById(R.id.TextView);
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
			save = save.substring(0, save.length()-1);//待支持stack back
			break;
			//TODO: confirm
		// case R.id.ButtonBrackets :
		// break;
		case R.id.ButtonC:
			save = null;
			vals = new FixCapacityStack<Double>();
			ops = new FixCapacityStack<String>();
			break;
		case R.id.ButtonAdd:
			flag = "add";
			save += "+";
			ops.push("+");
			break;
		case R.id.ButtonDevide:
			flag = "devide";
			save += "/";
			ops.push("/");
			break;
		case R.id.ButtonMinus:
			flag = "minus";
			save += "-";
			ops.push("-");
			break;
		case R.id.ButtonMultiply:
			flag = "multiply";
			save += "*";
			ops.push("*");
			break;
		case R.id.buttonEquals:
			if(save.indexOf("=") >= 0)
				break;
			save += "=";
			Matcher m = r.matcher(save);
			if(m.find())
				num1 = Double.parseDouble(m.group());
			if(m.find())
				num2 = Double.parseDouble(m.group());
			if (flag == null || flag.equals("")) {
				save += save.substring(0, save.length());
				System.out.println(save);
			}else if (flag.equals("add")) {
//				while(m.find()){
//					System.out.println(m.groupCount());
//					System.out.println(m.group());
//					System.out.println(m.group(1));
//					num1 = Double.parseDouble(m.group(0));
//					num2 = Double.parseDouble(m.group(1));
//				}
				save += Calculate.add(num1, num2);
				flag = null;
			} else if (flag.equals("minus")) {
				save += Calculate.minus(num1, num2);
				flag = null;
			} else if (flag.equals("multiply")) {
				save += Calculate.multiply(num1, num2);
				flag = null;
			} else if (flag.equals("devide")) {
				save += Calculate.devide(num1, num2);
				flag = null;
			}
			break;
		case R.id.ButtonPoint:
			save += ".";//小数不支持正则
			break;
		case R.id.ButtonChange:
			break;
		}
		TextView txt = (TextView) findViewById(R.id.TextView);
		if(save == null){
			txt.setText("");
		}else{
		txt.setText(save);
		}
	}
}
