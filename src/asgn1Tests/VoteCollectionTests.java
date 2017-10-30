/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import asgn1Election.Candidate;
import asgn1Election.CandidateIndex;
import asgn1Election.ElectionException;
import asgn1Election.VoteCollection;
import asgn1Election.VoteList;

/**
*
* <p>JUnit Test for VoteCollection</p>
* 
* @author Fong 
* 
*/
public class VoteCollectionTests {
	/** New VoteCollection object */
	private VoteCollection _voteCollection;
	
	/** New VoteList object */
	private VoteList _voteList;	
	
	/** New TreeMap object */
	private TreeMap<CandidateIndex, Candidate> _cds;
	
	/** New CandidateIndex objects */
	private CandidateIndex _cIndex1;
	private CandidateIndex _cIndex2;
	private CandidateIndex _cIndex3;	
	
	/** New Candidate objects*/
	private Candidate _cand1;
	private Candidate _cand2;
	private Candidate _cand3;
	
	/**
	 * Initializing Constructors to test.
	 * 
	 * @throws ElectionException if <code>NOT inRange(numCandidates)</code>
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpVoteCollection() throws ElectionException {
		// Create a new VoteCollection
		_voteCollection = new VoteCollection(2);

		// Create a new VoteList
		_voteList = new VoteList(3);

		// Create a tree map, let Candidate be String
		_cds = new TreeMap<CandidateIndex, Candidate>();

		// Create Candidate Indexes
		_cIndex1 = new CandidateIndex(1);
		_cIndex2 = new CandidateIndex(2);
		_cIndex3 = new CandidateIndex(3);

		// Create Candidates
		_cand1 = new Candidate("Shelob", "Monster Spider Party", "MSP", 0);
		_cand2 = new Candidate("Gorbag", "Filthy Orc Party", "FOP", 0);
		_cand3 = new Candidate("Shagrat", "Stinking Orc Party", "SOP", 0);

		// Put elements to the map
		_cds.put(_cIndex1, _cand1);
		_cds.put(_cIndex2, _cand2);
		_cds.put(_cIndex3, _cand3);
	}
	
	
	/**
	 * Confirms program reject numCandidates more than 15.
	 * Test method for {@link asgn1Election.VoteCollection#VoteCollection(int)}.
	 * 
	 * @throws ElectionException if <code>NOT inRange(numCandidates)</code>
	 */
	@Test(expected=ElectionException.class)
	public void testVoteCollectionAboveVoteCollectionRange() throws ElectionException{
		_voteCollection = new VoteCollection(16);
	}
	
	/**
	 * Confirms program reject numCandidates less than 1.
	 * Test method for {@link asgn1Election.VoteCollection#VoteCollection(int)}.
	 * 
	 * @throws ElectionException if <code>NOT inRange(numCandidates)</code>
	 */
	@Test(expected=ElectionException.class)
	public void testVoteCollectionBelowVoteCollectionRange() throws ElectionException{
		_voteCollection = new VoteCollection(0);
	}
	
	/**
	 * Test public void countPrefVotes(TreeMap<CandidateIndex, Candidate> cds, CandidateIndex elim)
	 * by adding 2 dummy votes.
	 * Firstly the countPrimaryVotes(_cds) is count,
	 * then the countPrefVotes(_cds, _cIndex3) is count,
	 * which distribute Candidate 3 votes to Candidate 2.
	 * 
	 * Confirms each candidate has 1 vote.
	 * Test method for {@link asgn1Election.VoteCollection#countPrefVotes(java.util.TreeMap, asgn1Election.CandidateIndex)}.
	 */
	@Test
	public void testCountPrefVotes() {
		_voteList.addPref(3);
		_voteList.addPref(2);
		_voteList.addPref(1);		
		_voteCollection.includeFormalVote(_voteList);
		
		//Add another VoteList
		_voteList=new VoteList(3);
		_voteList.addPref(1);
		_voteList.addPref(3);
		_voteList.addPref(2);
		_voteCollection.includeFormalVote(_voteList);		
		_voteCollection.countPrimaryVotes(_cds);
		
		assertEquals(1, _cand1.getVoteCount());
		assertEquals(0, _cand2.getVoteCount());
		assertEquals(1, _cand3.getVoteCount());
		
		//Remove Candidate 3 for this example
		_voteCollection.countPrefVotes(_cds, _cIndex3);
		
		//Vote of candidate 3 has been transfer to Candidate 2
		assertEquals(1, _cand1.getVoteCount());
		assertEquals(1, _cand2.getVoteCount());
		assertEquals(1, _cand3.getVoteCount());		
	}
	
	/**
	 * Test public void countPrimaryVotes(TreeMap<CandidateIndex, Candidate> cds)	
	 * by inserting a vote using includeFormalVote(_voteList).
	 * The vote will be placed in the cIndex where the First pref is found.
	 * Confirms _cand3 has vote of 1.
	 * Test method for {@link asgn1Election.VoteCollection#countPrimaryVotes(java.util.TreeMap)}.
	 */
	@Test
	public void testCountPrimaryVotes() {		
		_voteList.addPref(3);
		_voteList.addPref(2);
		_voteList.addPref(1);
		
		_voteCollection.includeFormalVote(_voteList);		
		_voteCollection.countPrimaryVotes(_cds);
		assertEquals(0, _cand1.getVoteCount());	
		assertEquals(0, _cand2.getVoteCount());	
		assertEquals(1, _cand3.getVoteCount());	
	}

	/**
	 * Test public void emptyTheCollection() by adding a vote using includeFormalVote() 1 time and then emptyTheCollection().
	 * Confirms program clears _voteCollection where getFormalCount() equals 0.
	 * Test method for {@link asgn1Election.VoteCollection#emptyTheCollection()}.
	 */
	@Test
	public void testEmptyTheCollection() {
		_voteList.addPref(3);
		_voteList.addPref(2);
		_voteList.addPref(1);
		
		_voteCollection.includeFormalVote(_voteList);	
		_voteCollection.emptyTheCollection();
		assertEquals(0, _voteCollection.getFormalCount());
	}

	/**
	 * Confirms program can update Formal vote Count.
	 * Test method for {@link asgn1Election.VoteCollection#getFormalCount()}.
	 */
	@Test
	public void testGetFormalCount() {
		_voteList.addPref(3);
		_voteList.addPref(2);
		_voteList.addPref(1);
		
		_voteCollection.includeFormalVote(_voteList);
		assertEquals(1, _voteCollection.getFormalCount());		
	}
	
	/**
	 * Confirms program can update informal vote Count.
	 * Test method for {@link asgn1Election.VoteCollection#getInformalCount()}.
	 */
	@Test
	public void testGetInformalCount() {		
		_voteCollection.updateInformalCount();
		_voteCollection.updateInformalCount();
		
		assertEquals(2, _voteCollection.getInformalCount());		
	}
	
	/**
	 * Confirms program can get formal votes count.
	 * Test method for {@link asgn1Election.VoteCollection#includeFormalVote(asgn1Election.Vote)}.
	 */
	@Test
	public void testIncludeFormalVote() {
		_voteList.addPref(3);
		_voteList.addPref(2);
		_voteList.addPref(1);
		_voteCollection.includeFormalVote(_voteList);
		
		//isFormal(v); not tested in the method
		_voteList.addPref(4);
		_voteList.addPref(2);
		_voteList.addPref(1);
		_voteCollection.includeFormalVote(_voteList);
		assertEquals(2, _voteCollection.getFormalCount());		
	}

	/**
	 * Confirms program can update informal vote count.
	 * Test method for {@link asgn1Election.VoteCollection#updateInformalCount()}.
	 */
	@Test
	public void testUpdateInformalCount() {
		_voteCollection.updateInformalCount();
		
		assertEquals(1, _voteCollection.getInformalCount());
	}

}
