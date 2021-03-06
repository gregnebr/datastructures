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

	private TreeMap<Integer, String> create() {
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		map.put(5, "five");
		map.put(7, "seven");
		map.put(3, "three");
		map.put(8, "eight");
		map.put(6, "six");
		map.put(4, "four");
		map.put(2, "two");
		return map;
	}

	@Test
	public void testPrint() {
		TreeMap<Integer, String> map = create();
		System.out.println(map);
	}

	@Test
	public void testContainsVal() {
		TreeMap<Integer, String> map = create();
		Assert.assertEquals(true, map.containsValue("seven"));
		Assert.assertEquals(false, map.containsValue("not there"));
	}

	@Test
	public void testRemove() {
		TreeMap<Integer, String> map = create();
		map.remove(8);
		System.out.println(map.toString());
		map.remove(6);
		System.out.println(map.toString());
		map.remove(3);
		System.out.println(map.toString());
		map.remove(5);
		System.out.println(map.toString());
	}
}
