package com.example.activitystudy;

import java.util.Objects;

public class FixCapacityStack<Item> {
	private Item[] a;
	private int N;
	public FixCapacityStack(){
		a = (Item[]) new Object[2];
		N = 0;
	}
	
	public void resize(int max){
		Item[] tmp = (Item[]) new Object[max];
		for(int i = 0; i < N; i++){
			tmp[i] = a[i];
		}
		a = tmp;
	}
	
	public int size(){
		return N;
	}
	
	public boolean isEmpty(){
		return N == 0;
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
	public static void main(String[] args){
		FixCapacityStack<String> fx = new FixCapacityStack<String>();
		fx.push("hello");
	}
}
