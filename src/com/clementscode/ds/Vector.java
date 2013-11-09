package com.clementscode.ds;

import java.util.Arrays;
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
		return findIndex(o) >= 0;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object item : c) {
			if (!contains(item)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return pointer > 0;
	}

	@Override
	public Iterator<T> iterator() {
		Iterator<T> rval = new Iterator<T>() {
			private int current = 0;

			@Override
			public boolean hasNext() {
				return pointer > current;

			}

			@Override
			public T next() {

				return (T) data[current++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return rval;
	}

	private int findIndex(Object o) {
		for (int i = 0; i < pointer; i++) {
			if (o.equals(data[i])) {
				return i;
			}

		}
		return -1;
	}

	@Override
	public boolean remove(Object o) {
		int j = findIndex(o);
		if (j == -1) {
			return false;
		}
		for (int i = j; i < pointer - 1; i++) {
			data[i] = data[i + 1];
		}
		pointer--;
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		boolean changed = false;
		for (Object item : c) {
			if (remove(item)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean changed = false;
		for (int i = 0; i < pointer;) {
			if (!c.contains(data[i])) {
				changed = remove(data[i]);
			} else {
				i++;
			}
		}
		return changed;
	}

	@Override
	public Object[] toArray() {
		Object[] rval = new Object[size()];
		for (int i = 0; i < rval.length; i++) {
			rval[i] = data[i];
		}
		return rval;
	}

	@Override
	public <T2> T2[] toArray(T2[] a) {
		if (a.length < size()) {
			a = Arrays.copyOf(a, size());
		}
		for (int i = 0; i < size(); ++i) {
			a[i] = (T2) data[i];
		}
		return a;
	}

}
