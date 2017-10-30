/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 *
 */
package asgn1Tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import asgn1Election.Candidate;
import asgn1Election.CandidateIndex;

import asgn1Election.ElectionException;
import asgn1Election.SimpleElection;
import asgn1Election.VoteCollection;
import asgn1Election.VoteList;
import asgn1Util.NumbersException;


/**
* <p>JUnit Test for SimpleElection</p>
* 
* @author Fong 
* 
*/
public class SimpleElectionTests {
	/** VoteList size */
	private static final int VOTELIST_SZIE = 3;

	/** New SimpleElection object */
	private SimpleElection _simpleElection;	
	
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
	 * Setup Simple Election
	 * 
	 * @throws ElectionException if <code>NOT inRange(numCandidates)</code>
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpSimpleElection() throws ElectionException{
		// Create a new SimpleElection that has 3 candidates
		_simpleElection = new SimpleElection("MinMorgulValeSimple");			
			
		// Create a new VoteCollection
		_voteCollection = new VoteCollection(2);

		// Create a new VoteList
		_voteList = new VoteList(VOTELIST_SZIE);

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
	 * Test public String findWinner() but without the string. 
	 * So, _cand.getVoteCount() is used as a replacement
	 * Test method for {@link asgn1Election.SimpleElection#findWinner()}.
	 */
	@Test
	public void testFindWinnerWithoutString() {
		_voteList.addPref(3);
		_voteList.addPref(2);
		_voteList.addPref(1);		
		_voteCollection.includeFormalVote(_voteList);
		
		//Add another VoteList
		_voteList=new VoteList(VOTELIST_SZIE);
		_voteList.addPref(2);
		_voteList.addPref(3);
		_voteList.addPref(1);
		_voteCollection.includeFormalVote(_voteList);
		
		// Add another VoteList
		_voteList = new VoteList(VOTELIST_SZIE);
		_voteList.addPref(1);
		_voteList.addPref(2);
		_voteList.addPref(3);
		_voteCollection.includeFormalVote(_voteList);
		_voteCollection.countPrimaryVotes(_cds);
		
		//Candidate 3 is winner
		assertEquals(1, _cand1.getVoteCount());
		assertEquals(0, _cand2.getVoteCount());
		assertEquals(2, _cand3.getVoteCount());			
	}
	
	/**
	 * Test public String findWinner() as a whole program. 
	 * Test method for {@link asgn1Election.SimpleElection#findWinner()}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election or vote file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testFindWinnerWithString() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_simpleElection.loadDefs();
		_simpleElection.loadVotes();
		String checkWinner = "Results for election: MinMorgulValeSimple" + "\n" + "Enrolment: 25" + "\n" + "\n"
				+ "Shelob              Monster Spider Party          (MSP)" + "\n"
				+ "Gorbag              Filthy Orc Party              (FOP)" + "\n"
				+ "Shagrat             Stinking Orc Party            (SOP)" + "\n" + "\n" + "\n"
				+ "Counting primary votes; 3 alternatives available" + "\n" + "\n" + "Simple election: MinMorgulValeSimple"
				+ "\n" + "\n" + "Shelob (MSP)                 8" + "\n" + "Gorbag (FOP)                 8" + "\n"
				+ "Shagrat (SOP)                3" + "\n" + "\n" + "Informal                     4" + "\n" + "\n"
				+ "Votes Cast                  23" + "\n" + "\n" + "\n"
				+ "Candidate Shelob (Monster Spider Party) is the winner with 8 votes..." + "\n";
		
		assertEquals(checkWinner, _simpleElection.findWinner());		
	}

	/**
	 * Test public boolean isFormal(Vote v) by addPref() numbers within 1 and numCandidate 3.
	 * Program returns true.
	 * Test method for {@link asgn1Election.SimpleElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election or vote file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalPrefWithin() throws FileNotFoundException,ElectionException, IOException, NumbersException {
		_simpleElection.loadDefs();
		_simpleElection.loadVotes();
		_voteList.addPref(1);
		_voteList.addPref(2);
		_voteList.addPref(3);		
		
		assertEquals(true,_simpleElection.isFormal(_voteList));
	}
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * by addPref() of negative numbers.  	 
	 * Program returns false.
	 * Test method for {@link asgn1Election.SimpleElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election or vote file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails  
	 */
	@Test
	public void testIsFormalPrefNegative() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_simpleElection.loadDefs();
		_simpleElection.loadVotes();
		_voteList.addPref(-11);
		_voteList.addPref(-20);
		_voteList.addPref(-1);
		
		assertEquals(false,_simpleElection.isFormal(_voteList));
	}
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * by addPref() of number above numCandidate of 3.
	 * Program returns false.
	 * Test method for {@link asgn1Election.SimpleElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election or vote file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalPrefAbove() throws FileNotFoundException, ElectionException, IOException, NumbersException {	
		_simpleElection.loadDefs();
		_simpleElection.loadVotes();
		_voteList.addPref(4);
		_voteList.addPref(5);
		_voteList.addPref(6);
		
		assertEquals(false,_simpleElection.isFormal(_voteList));
	}
	
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * by addPref() of number below numCandidates of 1.
	 * Program returns false.
	 * Test method for {@link asgn1Election.SimpleElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election or vote file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalPrefBelow() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_simpleElection.loadDefs();
		_simpleElection.loadVotes();
		_voteList.addPref(0);
		_voteList.addPref(0);
		_voteList.addPref(0);
		
		assertEquals(false,_simpleElection.isFormal(_voteList));
	}
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * with no Pref.
	 * Program returns false.
	 * Test method for {@link asgn1Election.SimpleElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election or vote file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalNoPref() throws FileNotFoundException, ElectionException, IOException, NumbersException {	
		_simpleElection.loadDefs();
		_simpleElection.loadVotes();
		
		assertEquals(false,_simpleElection.isFormal(_voteList));
	}
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * with valid and invalid Pref.
	 * Program returns false.
	 * Test method for {@link asgn1Election.SimpleElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election or vote file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails  
	 */
	@Test
	public void testIsFormalMixedPref() throws FileNotFoundException, ElectionException, IOException, NumbersException {	
		_simpleElection.loadDefs();
		_simpleElection.loadVotes();
		_voteList.addPref(1);
		_voteList.addPref(20);
		_voteList.addPref(5);
		
		assertEquals(false,_simpleElection.isFormal(_voteList));
	}

	/**
	 * Test public boolean isFormal(Vote v)
	 * to check if vote has preference of 1.
	 * Program returns false.
	 * Test method for {@link asgn1Election.SimpleElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election or vote file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalHasPrefOne() throws FileNotFoundException, ElectionException, IOException, NumbersException {	
		_simpleElection.loadDefs();
		_simpleElection.loadVotes();
		_voteList.addPref(2);
		_voteList.addPref(3);		
		
		assertEquals(false,_simpleElection.isFormal(_voteList));
	}	
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * to check if vote has double or more preference of 1.
	 * Program returns false.
	 * Test method for {@link asgn1Election.SimpleElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election or vote file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalHasDoublePrefOne() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_simpleElection.loadDefs();
		_simpleElection.loadVotes();		
		_voteList.addPref(1);
		_voteList.addPref(1);
		_voteList.addPref(3);
		
		assertEquals(false,_simpleElection.isFormal(_voteList));
	}
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * to check if vote has double or more preference of 2,3,4,5,etc.
	 * Program returns false.
	 * Test method for {@link asgn1Election.SimpleElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election or vote file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalHasDoublePrefth() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_simpleElection.loadDefs();
		_simpleElection.loadVotes();		
		_voteList.addPref(1);
		_voteList.addPref(2);
		_voteList.addPref(2);
		
		assertEquals(true,_simpleElection.isFormal(_voteList));
	}

	/**
	 * Protected method.
	 * Test method for {@link asgn1Election.SimpleElection#clearWinner(int)}.
	 */
	@Test
	public void testClearWinnerDontTest() {
		assertTrue(true);
	}
	
	/**
	 * Confirms program will print "MinMorgulVale - Simple Voting".
	 * Test method for {@link asgn1Election.SimpleElection#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals(_simpleElection.toString(), "MinMorgulValeSimple - Simple Voting");
	}
}
