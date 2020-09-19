package BSTtest;

import static org.junit.Assert.*;
import org.junit.*;
import BST.*;

public class AddCredits13Test {
	
	private AddCredits13 addCredInstacne;
	
	@Before
	public void setUp(){
		addCredInstacne = new AddCredits13();
	}

	@Test
	public void testInsert() {
		addCredInstacne.insert("A", 10);
		addCredInstacne.insert("B", 20);
		addCredInstacne.insert("A", 30);
		addCredInstacne.insert("C", 20);
		addCredInstacne.insert("D", 40);	
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testLookUp() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddToAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testMaxCreditClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
