package com.clementscode.ds;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {// passing definition of
																		// generic on to other
																		// source
	// that instantiates TreeMap

	private class Node implements Entry<K, V>, Comparable<Node> {
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
			return String.format("(%s,%s)", key.toString(), value.toString());

		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) { // change value but return old value
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		@Override
		public int compareTo(Node o) {
			return key.compareTo(o.key);
		}

	} // end node class

	private abstract class Visitor {
		public abstract boolean visit(int level, Node n);

	}

	private Node root;
	private int size;

	@Override
	public void clear() {
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
		if (!(arg0 instanceof Comparable)) { // if not comparable must search whole tree
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
	public boolean containsValue(final Object arg0) { // walk tree recursively homework test objecdt
		// equality like vector
		Visitor v = new Visitor() {

			@Override
			public boolean visit(int level, Node n) {
				return !arg0.equals(n.getValue());
			}

		}; // closes declaration
		return !walkDesc(0, root, v); // walkDesc returns false if item was found
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() { // returns set of entry objects
		// walk recursively and add all nodes to set
		final Set<Entry<K, V>> rval = new TreeSet<Entry<K, V>>();
		Visitor v = new Visitor() {

			@Override
			public boolean visit(int level, Node n) {
				rval.add(n);
				return true;
			}
		};
		walkDesc(0, root, v);
		return rval;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(Object arg0) {
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
		return root == null;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K arg0, V arg1) { // returns previous value at key
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
		// verify that arg0 exists in the tree
		if (!containsKey(arg0)) {
			return null; // ignore call to method if arg0 is not in the tree ?? print message that
							// arg0 is not in tree??
		}
		// if arg0 is a leaf just remove it
		K key = (K) arg0; // legal but java incorporated generics later ... user of this map uses
							// keys
		Node dummy = find(key, null, root);
		Node parent = dummy.left;
		Node found = dummy.right;

		if (parent == null) { // / dummy.right or found is the root;
			return removeRoot();
		}
		V rval = found.value; // if arg0 is a node with just one leaf move the leaf up to replace
								// the arg0 node
		int result = parent.key.compareTo(found.key);
		Node replacement;

		if (found.right == null) {
			replacement = found.left; // move left leaf to parent node that is being removed
		} else if (found.left == null) {
			replacement = found.right;
		} else {
			Node temp = found.left;
			replacement = findReplacement(found);
			replacement.left = temp;
		}
		if (result < 0) { // this tree only allows one value at a time, two puts of same key replace
							// first
			// found is on the right
			parent.right = replacement;
		} else {
			parent.left = replacement;
		}
		size--;
		return rval;
	}

	private Node findReplacement(Node target) {
		return goLeft(target.right, target);
	}

	private Node goLeft(Node testNode, Node parent) {
		Node rval = testNode;
		while (rval.left != null) {
			parent = rval;
			rval = rval.left;
		}
		parent.left = null;
		return rval;
	}

	private V removeRoot() {
		V rval = root.value;
		if (root.left == null && root.right == null) {
			root = null;

		} else if (root.left == null) {
			root = root.right;
		} else if (root.right == null) {
			root = root.left;
		} else {
			Node temp = root.left;
			root = findReplacement(root);
			root.left = temp;
		}
		size--;
		return rval;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		// just like keyset, but build a LinkedList of values to return
		return null;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		Visitor v = new Visitor() {

			@Override
			public boolean visit(int level, Node n) {
				for (int i = 0; i < level; i++) {
					sb.append("\t");
				}
				sb.append(n.toString());
				sb.append("\n");
				return true; // continue walk
			}
		}; // end of Visitor dont delete
		walkDesc(0, root, v);
		return sb.toString();
	}

	private boolean walkDesc(int level, Node n, Visitor v) {
		if (n == null) {
			return true;
		}
		if (!walkDesc(level + 1, n.right, v)) {
			return false; // dont continue the walk
		}
		if (!v.visit(level, n)) {
			return false;
		}
		return walkDesc(level + 1, n.left, v);

	}

	@Deprecated
	private String print(int level, Node n) {
		if (n == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder(); // one Stringbuilder for each node wasteful
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
