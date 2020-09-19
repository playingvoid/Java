package socialNetworkTest;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import socialNetwork.*;

@RunWith(org.junit.experimental.runners.Enclosed.class)
public class SocialNetworkTest {
	
	public static class SocialNetworkSingleTests{
		private SocialNetwork sn;	
		@Before
		public void intialize(){
			sn = new SocialNetwork();
			assertEquals(0L, sn.totalPersons());
			sn.makeFriend(10, 20);
			sn.makeFriend(10, 30);
			sn.makeFriend(10, 40);
			sn.makeFriend(20, 30);
			sn.makeFriend(30, 50);
			sn.makeFriend(50, 40);
			sn.makeFriend(40, 60);
			sn.makeFriend(40, 70);
			sn.makeFriend(70, 10);
		}
		
		@Rule
		public ExpectedException thrown = ExpectedException.none();
		
		@Test
		public void makeFriendWithSameId(){
			thrown.expect(RuntimeException.class);
		    thrown.expectMessage("Can not make friendship between same person");
		    sn.makeFriend(30, 30);
		}
		
		@Test
		public void makeFriendSanity(){
			assertEquals(7L, sn.totalPersons());
			Set<Long> friendOf10 = sn.getDirectFriends(10L);
			Set<Long> friendOf20 = sn.getDirectFriends(20L);
			Set<Long> friendOf30 = sn.getDirectFriends(30L);
			Set<Long> friendOf40 = sn.getDirectFriends(40L);
			Set<Long> friendOf50 = sn.getDirectFriends(50L);
			Set<Long> friendOf60 = sn.getDirectFriends(60L);
			Set<Long> friendOf70 = sn.getDirectFriends(70L);
			
			assertEquals(4, friendOf10.size());
			assertEquals(2, friendOf20.size());
			assertEquals(3, friendOf30.size());
			assertEquals(4, friendOf40.size());
			assertEquals(2, friendOf50.size());
			assertEquals(1, friendOf60.size());
			assertEquals(2, friendOf70.size());
			
			sn.makeFriend(80, 90);
			assertEquals(9L, sn.totalPersons());
			
			sn.makeFriend(70, 90);
			assertEquals(9L, sn.totalPersons());
			
			friendOf70 = sn.getDirectFriends(70L);
			Set<Long> friendOf80 = sn.getDirectFriends(80L);
			Set<Long> friendOf90 = sn.getDirectFriends(90L);
			
			assertTrue(friendOf80.contains(90L));
			assertTrue(friendOf90.contains(80L));
			assertTrue(friendOf10.containsAll(Arrays.asList(20L, 30L, 40L, 70L)));
		}
		
		@Test
		public void removeFriendSanity(){
			Set<Long> friendOf10 = sn.getDirectFriends(10L);
			Set<Long> friendOf20 = sn.getDirectFriends(20L);
			assertTrue(friendOf10.contains(20L));
			assertTrue(friendOf20.contains(10L));
			sn.removeFriends(10L, 20L);
			friendOf10 = sn.getDirectFriends(10L);
			friendOf20 = sn.getDirectFriends(20L);
			assertFalse(friendOf10.contains(20L));
			assertFalse(friendOf20.contains(10L));
			try{
				sn.removeFriends(10L, 20L);
				fail("Remove friend fails");
			} catch(Exception e){
				assertTrue(e instanceof RuntimeException);
				assertEquals("Provided persons are not friends", e.getMessage());
			}
		}
		
		@Test
		public void getDirectFriendWithInvalidId(){
			assertNull(sn.getDirectFriends(100L));
		}
		
		@Test
		public void getIndirectFriendWithInvalidId(){
			assertNull(sn.getIndirectFriends(100L));
		}
		
		@Test
		public void getIndirectFriendSanity(){
			assertEquals(new HashSet<Long>(Arrays.asList(50L, 60L)), sn.getIndirectFriends(10L));
			assertEquals(new HashSet<Long>(Arrays.asList(50L, 20L, 60L, 30L)), sn.getIndirectFriends(70L));
		}

	}
	
	@RunWith(Parameterized.class)
	public static class BadRemoveTest{
		private static final String NOFRNDREC =  "Friend record not found";
		private static final String NOSAMEFRND = "Can not remove friendship between same person";
		private static final String NOTFRND = "Provided persons are not friends";
		private SocialNetwork sn;	
		private String errorMessage;
		private long id1;
		private long id2;
		@Before
		public void intialize(){
			sn = new SocialNetwork();
			assertEquals(0L, sn.totalPersons());
			sn.makeFriend(10, 20);
			sn.makeFriend(10, 30);
			sn.makeFriend(10, 40);
			sn.makeFriend(20, 30);
			sn.makeFriend(30, 50);
			sn.makeFriend(50, 40);
			sn.makeFriend(40, 60);
			sn.makeFriend(40, 70);
			sn.makeFriend(70, 10);
		}

		public BadRemoveTest(Long id1, Long id2, String errorMessage) {
			this.id1 = id1;
			this.id2 = id2;
			this.errorMessage = errorMessage;
		}
		
		@SuppressWarnings("rawtypes")
		@Parameters
		public static Collection inputParameters() {
			return Arrays.asList(new Object[][] { 
				{ 30L, 100L, NOFRNDREC},
				{ 100L, 30L, NOFRNDREC},
				{ 100L, 200L, NOFRNDREC },
				{ 30L, 30L, NOSAMEFRND },
				{ 30L, 70L, NOTFRND }
				});
		}
		
		@Rule
		public ExpectedException thrown = ExpectedException.none();
		@Test
		public void removeFriendWithNonExistenceId(){
			thrown.expect(RuntimeException.class);
		    thrown.expectMessage(errorMessage);
		    sn.removeFriends(id1,id2);
		}
	}
	
}
