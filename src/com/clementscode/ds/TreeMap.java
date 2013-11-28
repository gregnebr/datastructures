package com.clementscode.ds;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {// passing definition of
																		// generic on to other
																		// source
	// that instantiates TreeMap

	private class Node implements Entry<K, V> {
		K key;
		V value;
		Node left;
		Node right;

		public Node(K key, V value, Node left, Node right) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;

		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return String.format("(%s,%s)", key.toString(), value.toString());

		}

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public V setValue(V value) { // change value but return old value
			// TODO Auto-generated method stub
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

	}

	private Node root;
	private int size;

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		size = 0;
		root = null;
	}

	/**
	 * @param key
	 * @param parent
	 * @param current
	 * @return dummy node parent is in left pointer target is in right pointer and is null if not
	 *         found
	 */
	private Node find(K key, Node parent, Node current) {// dummy node caller must check for null //
															// first call is key null root
		int result = key.compareTo(current.key);
		if (result == 0) {
			return new Node(null, null, parent, current); // left is parent, right is current
		} else if (result > 0) {
			if (current.right != null) {
				return find(key, current, current.right);
			} else {
				return new Node(null, null, current, null); // child is missing
			}

		} else {
			if (current.left != null) {
				return find(key, current, current.left);
			} else {
				return new Node(null, null, current, null); // child is missing
			}

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean containsKey(Object arg0) {
		// TODO Auto-generated method stub
		if (!(arg0 instanceof Comparable)) {
			throw new IllegalArgumentException("Arugment must be comparable");
		}
		K key = (K) arg0;
		if (root == null) {
			return false;
		}
		Node found = find(key, null, root);
		return !(found.right == null);
	}

	@Override
	public boolean containsValue(Object arg0) { // walk tree recursively homework test objecdt
												// equality like vector
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() { // returns set of entry objects
		// TODO Auto-generated method stub
		// walk recursively and add all nodes to set
		return null;
	}

	@Override
	public V get(Object arg0) {
		// TODO Auto-generated method stub
		if (!(arg0 instanceof Comparable)) {
			throw new IllegalArgumentException("Arugment must be comparable");
		}
		K key = (K) arg0;
		if (root == null) {
			return null;
		}
		Node dummy = find(key, null, root);
		if (dummy.right == null) {
			return null;
		}
		return dummy.right.value;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return root == null;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K arg0, V arg1) { // returns previous value at key
		// TODO Auto-generated method stub
		if (root == null) {
			root = new Node(arg0, arg1, null, null);
			size++;
			return null;
		}
		Node dummy = find(arg0, null, root);
		if (dummy.right == null) {
			Node parent = dummy.left;
			Node child = new Node(arg0, arg1, null, null);
			if (arg0.compareTo(parent.key) > 0) {
				parent.right = child;
			} else {
				parent.left = child;
			}
			size++;
			return null;
		} else {
			Node found = dummy.right;
			V oldValue = found.value;
			found.value = arg1;
			return oldValue;
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		for (java.util.Map.Entry<? extends K, ? extends V> e : arg0.entrySet()) {
			this.put(e.getKey(), e.getValue());
		}
	}

	@Override
	public V remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return print(0, root);
	}

	private String print(int level, Node n) {
		if (n == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(print(level + 1, n.right));
		for (int i = 0; i < level; i++) {
			sb.append("\t");
		}
		sb.append(n.toString());
		sb.append("\n");
		sb.append(print(level + 1, n.left));
		return sb.toString();
	}

}
