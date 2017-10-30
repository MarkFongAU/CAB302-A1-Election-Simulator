/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import asgn1Election.CandidateIndex;
import asgn1Election.ElectionException;
import asgn1Election.Vote;
import asgn1Election.VoteList;

/**
*
* <p>JUnit Test for VoteList</p>
* 
* @author Fong
* 
*/
public class VoteListTests {
	/** VoteList size */
	private static final int VOTE_LISTSIZE = 5;
	
	/** New VoteList object */
	private VoteList _voteList;
	
	/**
	 * Initializing Constructor with 5 to test.  
	 * 
	 */
	@Before
	public void setUpVoteList() {
		_voteList= new VoteList(VOTE_LISTSIZE);
	}

	/** 
	 * Confirms addPref(int index) only allows numCandidates of input.
	 * Test method for {@link asgn1Election.VoteList#addPref(int)}.
	 */
	@Test
	public void testAddPref() {
		_voteList.addPref(1);
		_voteList.addPref(2);
		_voteList.addPref(3);
		_voteList.addPref(4);
		_voteList.addPref(5);
		
		assertFalse(_voteList.addPref(6));	
	}
	
	/**
	 * Confirms copied VoteList can insert negative Index.
	 * Test method for {@link asgn1Election.VoteList#addPref(int)}.
	 */
	@Test
	public void testAddNegativePref() {
		_voteList.addPref(-1);	
		
		assertEquals("-1 ",_voteList.toString());
	}
	
	/** 
	 * Confirms copied VoteList can insert new index of "6 7 8 9 10 ".
	 * Test method for {@link asgn1Election.VoteList#copyVote()}. 
	 */
	@Test
	public void testCopyVote() {
		_voteList.addPref(1);
		_voteList.addPref(2);
		_voteList.addPref(3);
		_voteList.addPref(4);
		_voteList.addPref(5);
		
		Vote v = _voteList.copyVote();	
		
		v.addPref(6);
		v.addPref(7);
		v.addPref(8);
		v.addPref(9);
		v.addPref(10);		
		
		assertEquals("6 7 8 9 10 ",v.toString());
	}
	
	/** 
	 * Confirms copied VoteList can insert new index of "1 2 3 4 5 " 
	 * which was inserted previously on the original VoteList.
	 * Test method for {@link asgn1Election.VoteList#copyVote()}.
	 */
	@Test
	public void testCopyVoteRepeatIndex() {
		_voteList.addPref(1);
		_voteList.addPref(2);
		_voteList.addPref(3);
		_voteList.addPref(4);
		_voteList.addPref(5);
		
		Vote v = _voteList.copyVote();		
		
		v.addPref(1);
		v.addPref(2);
		v.addPref(3);
		v.addPref(4);
		v.addPref(5);		
		
		assertEquals("1 2 3 4 5 ",v.toString());
	}
	
	/** 
	 * Confirms program gets Preference of integer 3.
	 * Test method for {@link asgn1Election.VoteList#getPreference(int)}. 
	 */
	@Test
	public void testGetPreference() {
		_voteList.addPref(3);
		_voteList.addPref(2);
		_voteList.addPref(1);
		
		CandidateIndex i = _voteList.getPreference(3);
		assertEquals("1",i.toString());
	}
	
	/**
	 * Confirms program can invert voteList.
	 * Test method for {@link asgn1Election.VoteList#invertVote()}.
	 */
	@Test
	public void testInvertVote() {
		_voteList.addPref(4);
		_voteList.addPref(1);
		_voteList.addPref(5);
		_voteList.addPref(2);
		_voteList.addPref(3);
		
		Vote v = _voteList.invertVote();
		assertEquals("2 4 5 1 3 ",v.toString());
	}
	
	/**
	 * Confirms program can remove element in voteList.
	 * Test method for {@link asgn1Election.VoteList#iterator()}.
	 */
	@Test
	public void testIteratorRemove() {				
		_voteList.addPref(5);
		_voteList.addPref(1);
		_voteList.addPref(3);
		_voteList.addPref(2);
		_voteList.addPref(4);		
		
		Iterator<Integer> current = _voteList.iterator();
		current.next();
		current.next();
		current.remove();		
		assertEquals("5 3 2 4 ",_voteList.toString());
	}
	
	/**
	 * Confirms program can iterate in voteList.
	 * Test method for {@link asgn1Election.VoteList#iterator()}.
	 */
	@Test
	public void testIteratorNext() {				
		_voteList.addPref(5);
		_voteList.addPref(1);
		_voteList.addPref(3);
		_voteList.addPref(2);
		_voteList.addPref(4);	
		
		Iterator<Integer> current = _voteList.iterator();
		//iterate 2 times
		current.next();
		int getValue=current.next();				
		assertEquals(1 ,getValue);
	}
	
	/**
	 * Confirms program can check for element with hasNext() in voteList.
	 * Test method for {@link asgn1Election.VoteList#iterator()}.
	 */
	@Test
	public void testIteratorHasNext() {				
		_voteList.addPref(5);
		_voteList.addPref(1);
				
		Iterator<Integer> current = _voteList.iterator();
		//iterate 2 times
		current.next();
		boolean checkNext=current.hasNext();
		assertEquals(true ,checkNext);
		current.next();	
		checkNext=current.hasNext();
		assertEquals(false ,checkNext);
	}

	/** 
	 * Confirms program can print string that has addPref of "1 2 3 4 5 ".
	 * Test method for {@link asgn1Election.VoteList#toString()}. 
	 */
	@Test
	public void testToString() {
		_voteList.addPref(1);
		_voteList.addPref(2);
		_voteList.addPref(3);
		_voteList.addPref(4);
		_voteList.addPref(5);				
		
		assertEquals("1 2 3 4 5 ",_voteList.toString());
	}

}
