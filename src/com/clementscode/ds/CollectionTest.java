package com.clementscode.ds;

import java.util.Arrays;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Test;

public class CollectionTest {

	@Test
	public void testAdd() {
		LinkedList<Integer> v = new LinkedList<Integer>();
		v.add(41);
		Assert.assertEquals(1, v.size());
		Integer res = v.get(0);
		Assert.assertEquals((Integer) 41, res);
		v.add(42);
		Assert.assertEquals(2, v.size());
		res = v.get(0);
		Assert.assertEquals((Integer) 41, res);
	}

	@Test
	public void testIter() {
		LinkedList<Integer> v = new LinkedList<Integer>();
		v.add(1);
		v.add(2);
		v.add(3);
		Iterator<Integer> iterator = v.iterator();
		Assert.assertEquals(true, iterator.hasNext());
		Assert.assertEquals((Integer) 1, iterator.next());
		Assert.assertEquals(true, iterator.hasNext());
		Assert.assertEquals((Integer) 2, iterator.next());
		Assert.assertEquals(true, iterator.hasNext());
		Assert.assertEquals((Integer) 3, iterator.next());
		Assert.assertEquals(false, iterator.hasNext());
	}

	@Test
	public void testContains() {
		LinkedList<Integer> v = new LinkedList<Integer>();
		v.add(42);
		v.add(89);
		Assert.assertEquals(true, v.contains(42));
		Assert.assertEquals(false, v.contains(0));
		Assert.assertEquals(true, v.containsAll(Arrays.asList(89, 42)));
		Assert.assertEquals(false, v.containsAll(Arrays.asList(89, 42, 9)));
	}

	@Test
	public void testRetain() {
		LinkedList<Integer> v = new LinkedList<Integer>();
		v.add(42);
		v.add(89);
		Assert.assertEquals(true, v.retainAll(Arrays.asList(42)));
		Assert.assertEquals(1, v.size());
		Assert.assertEquals((Integer) 42, v.get(0));
	}

	@Test
	public void testRemove() {
		LinkedList<Integer> v = new LinkedList<Integer>();
		v.add(42);
		v.add(89);
		Assert.assertEquals(false, v.remove(72));
		Assert.assertEquals(2, v.size());
		Assert.assertEquals(true, v.remove(42));
		Assert.assertEquals(1, v.size());
		Assert.assertEquals((Integer) 89, v.get(0));

		v.add(1);
		v.add(2);
		Assert.assertEquals(false, v.removeAll(Arrays.asList(0, -1)));
		Assert.assertEquals(3, v.size());
		Assert.assertEquals(true, v.removeAll(Arrays.asList(0, 2, 89, -1)));
		Assert.assertEquals(1, v.size());
		Assert.assertEquals((Integer) 1, v.get(0));
	}
}
