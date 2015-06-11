package com.example.activitystudy;

import java.util.Objects;

public class FixCapacityStack<Item> {
	private Item[] a;
	private int N;
	public void FixCapacityStack(){
		a = (Item[]) new Object[2];
		N = 2;
	}
	
	public void resize(int max){
		Item[] tmp = (Item[]) new Object[max];
		for(int i = 0; i < a.length; i++){
			tmp[i] = a[i];
		}
		a = tmp;
	}
	
	public int size(){
		return N;
	}
	
	public void push(Item item){
		if(a.length == N){
			resize(N * 2);
		}
		a[N++] = item;
	}
	
	public Item pop(){
		if(N > 0 &&N == a.length / 4){
			resize(a.length / 2);
		}
		Item tmp = a[--N];
		a[N] = null;
		return tmp;
	}
}
