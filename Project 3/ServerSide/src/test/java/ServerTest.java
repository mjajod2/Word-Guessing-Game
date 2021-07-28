//package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ServerTest {

	Categories c1 = new Categories();
	ThreadedServer.ClientThread cl;

	@Test
	void test1() {
		c1.init1();
		int s = c1.getSize1();
		assertEquals(s, c1.getSize1(), "wrong");
		assertEquals("Rabbit", c1.getCatOneWord(), "wrong");
		System.out.println(cl.checkIfPresent('R', "Animals"));
	}

	@Test
	void test2() {
		c1.init2();
		int s = c1.getSize2();
		assertEquals(s, c1.getSize2(), "wrong");
		assertEquals("Utah", c1.getCatTwoWord(), "wrong");
	}

	@Test
	void test3() {
		c1.init3();
		int s = c1.getSize3();
		assertEquals(s, c1.getSize3(), "wrong");
		assertEquals("Greek", c1.getCatThreeWord(), "wrong");
	}

	@Test
	void testRemove1() {
		c1.init1();
		int s = c1.getSize1();
		assertEquals(s, c1.getSize1(), "wrong");
		assertEquals("Rabbit", c1.getCatOneWord(), "wrong");
		c1.removeCatOneWord();
		s = c1.getSize1();
		assertEquals(s, c1.getSize1(), "wrong");
		assertEquals("Turtle", c1.getCatOneWord(), "wrong");
		c1.removeCatOneWord();
		s = c1.getSize1();
		assertEquals(s, c1.getSize1(), "wrong");
		assertEquals("Alligator", c1.getCatOneWord(), "wrong");
	}

	@Test
	void testRemove2() {
		c1.init2();
		int s = c1.getSize2();
		assertEquals(s, c1.getSize2(), "wrong");
		assertEquals("Utah", c1.getCatTwoWord(), "wrong");
		c1.removeCatTwoWord();
		s = c1.getSize2();
		assertEquals(s, c1.getSize2(), "wrong");
		assertEquals("Idaho", c1.getCatTwoWord(), "wrong");
		c1.removeCatTwoWord();
		s = c1.getSize2();
		assertEquals(s, c1.getSize2(), "wrong");
		assertEquals("California", c1.getCatTwoWord(), "wrong");
	}

	@Test
	void testRemove3() {
		c1.init3();
		int s = c1.getSize3();
		assertEquals(s, c1.getSize3(), "wrong");
		assertEquals("Greek", c1.getCatThreeWord(), "wrong");
		c1.removeCatThreeWord();
		s = c1.getSize3();
		assertEquals(s, c1.getSize3(), "wrong");
		assertEquals("French", c1.getCatThreeWord(), "wrong");
		c1.removeCatThreeWord();
		s = c1.getSize3();
		assertEquals(s, c1.getSize3(), "wrong");
		assertEquals("British", c1.getCatThreeWord(), "wrong");
	}

	@Test
	void testEmpty1() {
		c1 = new Categories();
		int s = c1.getSize1();
		assertEquals(0, s, "wrong");
		c1.init1();
		s = c1.getSize1();
		for (int i = 0; i < s; i++) {
			c1.removeCatOneWord();
		}
		assertEquals(0, c1.getSize1(), "wrong");
	}

	@Test
	void testEmpty2() {
		c1 = new Categories();
		int s = c1.getSize2();
		assertEquals(0, s, "wrong");
		c1.init2();
		s = c1.getSize2();
		for (int i = 0; i < s; i++) {
			c1.removeCatTwoWord();
		}
		assertEquals(0, c1.getSize2(), "wrong");
	}

	@Test
	void testEmpty3() {
		c1 = new Categories();
		int s = c1.getSize3();
		assertEquals(0, s, "wrong");
		c1.init3();
		s = c1.getSize3();
		for (int i = 0; i < s; i++) {
			c1.removeCatThreeWord();
		}
		assertEquals(0, c1.getSize3(), "wrong");
	}

}

