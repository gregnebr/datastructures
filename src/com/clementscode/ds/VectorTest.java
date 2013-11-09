package com.clementscode.ds;

import java.util.Arrays;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Test;

public class VectorTest {

	@Test
	public void testAdd() {
		Vector<Integer> v = new Vector<Integer>(0);
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
		Vector<Integer> v = new Vector<Integer>(0);
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
		Vector<Integer> v = new Vector<Integer>(2);
		v.add(42);
		v.add(89);
		Assert.assertEquals(true, v.contains(42));
		Assert.assertEquals(false, v.contains(0));
		Assert.assertEquals(true, v.containsAll(Arrays.asList(89, 42)));
		Assert.assertEquals(false, v.containsAll(Arrays.asList(89, 42, 9)));
	}
}
