package com.clementscode.ds;

public class Vector<T> {

	private static final int EXP_THRESH = 1024;

	private Object[] data;
	private int pointer = 0;

	public Vector(int initialSize) {
		data = new Object[initialSize];
	}

	public void add(T item) {
		if (data.length == 0) {
			data = new Object[8];
		}
		if (pointer >= data.length) {
			grow();
		}
		data[pointer++] = item;
	}

	private void grow() {
		int growBy = data.length;
		if (data.length > EXP_THRESH) {
			growBy /= 2;
		}
		Object[] tmp = new Object[data.length + growBy];
		for (int i = 0; i < this.pointer; ++i) {
			tmp[i] = this.data[i];
		}
		this.data = tmp;
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		return (T) data[pointer - 1];
	}

	public int size() {
		return pointer;
	}

}
