package com.clementscode.ds;

import junit.framework.Assert;

import org.junit.Test;

public class MapTest {

	@Test
	public void testPut() {
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		Assert.assertEquals(0, map.size());
		Assert.assertTrue(map.isEmpty());
		map.put(5, 5);
		Assert.assertEquals(1, map.size());
		Assert.assertTrue(!map.isEmpty());
		Integer result = map.get((Integer) 5);
		Assert.assertEquals(result, (Integer) 5);
		result = map.get(-1);
		Assert.assertTrue(result == null);
		map.put(1, 1);
		map.put(10, 10);
		Assert.assertEquals(3, map.size());
		Assert.assertTrue(!map.isEmpty());
		Integer old = map.put(5, -1);
		Assert.assertEquals((Integer) 5, old);
		result = map.get(5);
		Assert.assertEquals((Integer) (-1), result);
	}
}
