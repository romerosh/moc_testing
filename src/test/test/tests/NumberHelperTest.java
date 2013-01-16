package test.tests;

import static org.junit.Assert.*;
import helpers.NumberHelper;

import org.junit.Test;

public class NumberHelperTest {

	@Test
	public void roundTest() {
		double result;
		
		result = NumberHelper.round(1.3334,3);
		assertEquals(1.333, result,0.0000001);
		
		result = NumberHelper.round(1.35,1);
		assertEquals(1.4, result,0.0000001);
		
		result = NumberHelper.round(1.35,3);
		assertEquals(1.35, result,0.0000001);
		
		result = NumberHelper.round(1.35,0);
		assertEquals(1, result,0.0000001);
		

		result = NumberHelper.round(1.35,-1);
		assertEquals(1.35, result,0.0000001);
		
		result = NumberHelper.round(-1.35,0);
		assertEquals(-1, result,0.0000001);
		
		result = NumberHelper.round(-1.35,1);
		assertEquals(-1.4, result,0.0000001);
		
	}

}
