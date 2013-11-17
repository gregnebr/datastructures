package com.clementscode.ds;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class LinkedList<T> implements Collection<T> {

	private class Node {
		T item;
		Node next;

		public Node(T item, Node next) {
			this.item = item;
			this.next = next;
		}

	}

	private Node first;
	private Node last;
	private int count = 0;

	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		if (count == 0) {// first item
			first = new Node(e, null);
			last = first;
		} else {
			last.next = new Node(e, null);// constructor returns address
			last = last.next; // the name last is a pointer
		}
		count++;
		return true; // collection was modified
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
		// TODO Auto-generated method stub
		first = null;
		last = null;
		count = 0;

	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return find(o) != null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		for (Object item : c) {
			if (!contains(item)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub

		return count == 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node curr = first;

			@Override
			public boolean hasNext() {
				return curr != null;
			}

			@Override
			public T next() {
				T rval = curr.item;
				curr = curr.next;
				return rval;
			}

			@Override
			public void remove() {
				LinkedList.this.remove(curr.item);
				curr = curr.next;
			}
		};
	}

	private class NodePair {
		Node prev;
		Node curr;

		public NodePair(Node prev, Node curr) {
			this.prev = prev;
			this.curr = curr;
		}
	}

	private NodePair find(Object o) {
		Node curr = first;
		Node prev = null;
		while (curr != null) { // don't need to test for 0 length list
			if (o.equals(curr.item)) { // item might be null so don't apply .equals to it
				return new NodePair(prev, curr);
			}
			prev = curr;
			curr = curr.next;

		}
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		NodePair found = find(o);
		if (found == null) {
			return false;
		}
		Node prev = found.prev;
		Node curr = found.curr;
		Node next = curr.next;
		if (prev == null) {
			first = next;
			if (curr == last) { // only one item in list
				last = first;
			}
		} else {
			prev.next = next;
			if (curr == last) {
				last = prev;
			}
		}
		count--;
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) { // wont remove duplicates
		// TODO Auto-generated method stub
		boolean status = false;
		for (Object item : c) {
			if (remove(item)) {
				status = true;
			}
		}
		return status;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		boolean changed = false;
		Node curr = first;
		Node prev = null;
		while (curr != null) { // don't need to test for 0 length list
			if (!c.contains(curr.item)) { // item might be null so don't apply .equals to it
				changed = remove(curr.item);
			}
			curr = curr.next;
		}
		return changed;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public Object[] toArray() {
		Object[] rval = new Object[count];
		Node curr = first;
		for (int i = 0; curr != null; ++i) {
			rval[i] = (Object) curr.item;
			curr = curr.next;
		}
		return rval;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size()) {
			a = Arrays.copyOf(a, size());
		}
		Node curr = first;
		for (int i = 0; curr != null; ++i) {
			a[i] = (T) curr.item;
			curr = curr.next;
		}
		return a;
	}

	public T get(int index) {
		Node curr = first;
		for (int i = 0; i < index; ++i) {
			curr = curr.next;
		}
		return curr.item;
	}

}
