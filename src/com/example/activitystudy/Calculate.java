package com.example.activitystudy;

public class Calculate {

	static public double add(double first, double second){
		return first + second;
	}
	
	static public double minus(double first, double second){
		return first - second;
	}
	
	static public double multiply(double first, double second){
		return first * second;
	}
	
	static public double devide(double first, double second){
		if(second == 0)
			return 0;
		else
			return first / second;
	}
}
