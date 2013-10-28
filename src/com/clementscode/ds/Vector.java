package com.clementscode.ds;

public class Vector<T> {

	private Object[] data;
	private int pointer = 0;

	public Vector(int initialSize) {
		data = new Object[initialSize];
	}

	public void add(T item) {
		data[pointer++] = item;
	}

	public T get(int index) {
		return (T) data[pointer - 1];
	}

	public int size() {
		return pointer;
	}

}
