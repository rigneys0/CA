package tests;

import static org.junit.Assert.*;

import org.junit.*;

import simulator.CA;


public class TestCA {
	@Test
	public void testParseRule1(){
		CA automaton = new CA(10,1);
		int centerVal = automaton.parseRule(15);
		assertEquals(centerVal,0);
	}
	@Test
	public void testParseRule2(){
		CA automaton = new CA(938,1);
		int centerVal = automaton.parseRule(314);
		assertEquals(centerVal,1);
	}
	@Test
	public void testParseRule3(){
		CA automaton = new CA(217,1);
		int centerVal = automaton.parseRule(147);
		assertEquals(centerVal,1);
	}
	@Test
	public void testParseRule4(){
		CA automaton = new CA(187,1);
		int centerVal = automaton.parseRule(56);
		assertEquals(centerVal,1);
	}
	@Test
	public void testParseRule5(){
		CA automaton = new CA(0,1);
		int centerVal = automaton.parseRule(191);
		assertEquals(centerVal,1);
	}
	@Test
	public void testParseRule6(){
		CA automaton = new CA(254,1);
		int centerVal = automaton.parseRule(128);
		assertEquals(centerVal,0);
	}
	@Test
	public void testParseRule7(){
		CA automaton = new CA(374,1);
		int centerVal = automaton.parseRule(0);
		assertEquals(centerVal,0);
	}
	@Test
	public void testParseRule8(){
		CA automaton = new CA(5,1);
		int centerVal = automaton.parseRule(2);
		assertEquals(centerVal,1);
	}
	@Test
	public void testParseRule9(){
		CA automaton = new CA(63,1);
		int centerVal = automaton.parseRule(63);
		assertEquals(centerVal,0);
	}
	@Test
	public void testParseRule10(){
		CA automaton = new CA(85,1);
		int centerVal = automaton.parseRule(10);
		assertEquals(centerVal,0);
	}
}
