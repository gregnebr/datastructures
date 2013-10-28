package com.clementscode.ds;

import java.util.Collection;
import java.util.Iterator;

public class Vector<T> implements Collection<T> {

	private static final int EXP_THRESH = 1024;

	private Object[] data;
	private int pointer = 0;

	public Vector(int initialSize) {
		data = new Object[initialSize];
	}

	@Override
	public boolean add(T item) {
		if (data.length == 0) {
			data = new Object[8];
		}
		if (pointer >= data.length) {
			grow();
		}
		data[pointer++] = item;
		return true;
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
		return (T) data[index];
	}

	public int size() {
		return pointer;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		for (T item : c) {
			add(item);
		}
		return true;
	}

	@Override
	public void clear() {
		pointer = 0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		for (int i = 0; i < pointer; i++) {
			if (o.equals(data[i])) {
				return true;
			}

		}

		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return pointer > 0;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		Iterator<T> rval = new Iterator<T>() {
			private int current = 0;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return pointer > current;

			}

			@Override
			public T next() {
				// TODO Auto-generated method stub

				return (T) data[current++];
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException();
			}
		};
		return rval;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

}
