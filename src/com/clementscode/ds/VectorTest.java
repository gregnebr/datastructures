package com.clementscode.ds;

import junit.framework.Assert;

import org.junit.Test;

public class VectorTest {

	@Test
	public void testAdd() {
		Vector<Integer> v = new Vector<Integer>(8);
		v.add(41);
		Assert.assertEquals(1, v.size());
		Integer res = v.get(0);
		Assert.assertEquals((Integer) 41, res);
	}

}
