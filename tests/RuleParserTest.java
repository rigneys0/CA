package tests;

import static org.junit.Assert.*;

import org.junit.*;

import simulator.RuleParser;


public class RuleParserTest {
	@Test
	public void testXORBits1(){
		assertEquals(RuleParser.xORBits(10),0);
	}
	@Test
	public void testXORBits2(){
		assertEquals(RuleParser.xORBits(46),0);
	}
	@Test
	public void testXORBits3(){
		assertEquals(RuleParser.xORBits(11),1);
	}
	@Test
	public void testXORBits4(){
		assertEquals(RuleParser.xORBits(2),1);
	}
	@Test
	public void testXORBits5(){
		assertEquals(RuleParser.xORBits(756),0);
	}
}
