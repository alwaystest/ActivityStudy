package com.example.activitystudy;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {


	static public double add(double first, double second) {
		return first + second;
	}

	static public double minus(double first, double second) {
		return first - second;
	}

	static public double multiply(double first, double second) {
		return first * second;
	}

	static public double devide(double first, double second) {
		if (second == 0)
			return 0;
		else
			return first / second;
	}
	static public String doString(String ex){

		String pattern = "\\(.+?\\)";//懒惰匹配
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(ex);
		while(m.find()){
			String tmp = m.group();
			String tmpresult = doStringInside(tmp.substring(1,tmp.length()-1));
			System.out.println(tmp);
			ex = ex.replaceFirst(Pattern.quote(tmp),tmpresult);
			m = p.matcher(ex);
		}
		return ex;
	}
	static public String doStringInside(String ex){
		String pattern1 = "\\d+\\.?\\d*[*/]\\d+\\.?\\d*";
		String pattern2 = "\\d+\\.?\\d*[+-]\\d+\\.?\\d*";
		Pattern p1 = Pattern.compile(pattern1);
		Pattern p2 = Pattern.compile(pattern2);
		String result = ex;
		Matcher m = p1.matcher(result);
		while (m.find()) {
			String tmp = m.group();
			if (tmp.contains("/")) {
				String[] num = tmp.split("/");
				Double tmpresult = Calculate.devide(Double.parseDouble(num[0]), Double.parseDouble(num[1]));
				result = result.replaceFirst(Pattern.quote(tmp), tmpresult.toString());
			} else {
				String[] num = tmp.split("\\*");
				Double tmpresult = Calculate.multiply(Double.parseDouble(num[0]), Double.parseDouble(num[1]));
				result = result.replaceFirst(Pattern.quote(tmp), tmpresult.toString());
			}
			m = p1.matcher(result);
		}
		m = p2.matcher(result);
		while (m.find()) {
			String tmp = m.group();
			if (tmp.contains("+")) {
				String[] num = tmp.split("\\+");
				Double tmpresult = Calculate.add(Double.parseDouble(num[0]), Double.parseDouble(num[1]));
				result = result.replaceFirst(Pattern.quote(tmp), tmpresult.toString());
			} else {
				String[] num = tmp.split("-");
				Double tmpresult = Calculate.minus(Double.parseDouble(num[0]), Double.parseDouble(num[1]));
				result = result.replaceFirst(Pattern.quote(tmp), tmpresult.toString());
			}
			m = p2.matcher(result);
		}
		return result;
	}
}
