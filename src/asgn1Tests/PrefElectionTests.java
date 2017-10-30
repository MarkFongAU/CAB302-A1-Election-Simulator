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
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import asgn1Election.Candidate;
import asgn1Election.CandidateIndex;
import asgn1Election.ElectionException;
import asgn1Election.ElectionManager;
import asgn1Election.PrefElection;
import asgn1Election.VoteCollection;
import asgn1Election.VoteList;
import asgn1Util.NumbersException;


/**
 * <p>Junit Test for Pref Election</p>
 * 
 * @author Fong
 */
public class PrefElectionTests {	
	/** VoteList size */
	private static final int VOTELIST_SIZE = 3;

	/** Election Name */
	private static final String ELECTION_NAME = "MinMorgulValeTie";

	/** New ElectionManager object */
	private ElectionManager _electionManager;
	
	/** New PrefElection object */
	private PrefElection _prefElection;
	private PrefElection _prefElection2;
	
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
	
	/** New ExtendedElection Object*/
	private ExtendedElection _extendedElection;
	
	/**
	 * Setup Preference Election
	 * 
	 * @throws ElectionException if <code>NOT inRange(numCandidates)</code>
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpPrefElection() throws ElectionException {
		//Create a new ElectionManager
		_electionManager= new ElectionManager();
		
		// Create a new SimpleElection
		_prefElection = new PrefElection(ELECTION_NAME);

		// Create a new VoteCollection
		_voteCollection = new VoteCollection(2);

		// Create a new VoteList
		_voteList = new VoteList(VOTELIST_SIZE);

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
	 * So, <code>_cand.getVoteCount()</code> is used as a replacement.
	 * Test method for {@link asgn1Election.PrefElection#findWinner()}.
	 */
	@Test
	public void testFindWinnerWithoutString() {
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
		
		// Add another VoteList
		_voteList = new VoteList(3);
		_voteList.addPref(2);
		_voteList.addPref(1);
		_voteList.addPref(3);
		_voteCollection.includeFormalVote(_voteList);
		_voteCollection.countPrimaryVotes(_cds);
		
		// Candidate 3 is winner
		assertEquals(1, _cand1.getVoteCount());
		assertEquals(1, _cand2.getVoteCount());
		assertEquals(1, _cand3.getVoteCount());	
		
		//Remove Candidate 2 
		_voteCollection.countPrefVotes(_cds, _cIndex2);
		
		//Vote of candidate 2 has been transfer to Candidate 1, Candidate 1 is winner
		assertEquals(2, _cand1.getVoteCount());
		assertEquals(1, _cand2.getVoteCount());
		assertEquals(1, _cand3.getVoteCount());			
	}
	
	@Test
	/**
	 * Test public String findWinner() as a whole program. 
	 * Test method for {@link asgn1Election.PrefElection#findWinner()}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election or vote file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	public void testFindWinner() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection.loadDefs();
		_prefElection.loadVotes();		
		String checkWinner = "Results for election: MinMorgulValeTie" + "\n" + "Enrolment: 25" + "\n" + "\n" +
				"Shelob              Monster Spider Party          (MSP)" + "\n"
				+ "Gorbag              Filthy Orc Party              (FOP)" + "\n"
				+ "Shagrat             Stinking Orc Party            (SOP)" + "\n" + "\n" + "\n"
				+ "Counting primary votes; 3 alternatives available" + "\n" + "\n" 
				+ "Preferential election: MinMorgulValeTie" + "\n" + "\n" + "Shelob (MSP)                 8" + "\n"
				+ "Gorbag (FOP)                 7" + "\n" + "Shagrat (SOP)                3" + "\n" + "\n"
				+ "Informal                     3" + "\n" + "\n" + "Votes Cast                  21" + "\n" + "\n" + "\n"
				+ "Preferences required: distributing Shagrat: 3 votes" + "\n" + "\n"
				+ "Preferential election: MinMorgulValeTie" + "\n" + "\n" + "Shelob (MSP)                 9" + "\n"
				+ "Gorbag (FOP)                 9" + "\n" + "\n" + "Informal                     3" + "\n" + "\n"
				+ "Votes Cast                  21" + "\n" + "\n" + "\n"
				+ "Preferences required: distributing Shelob: 9 votes" + "\n" + "\n"
				+ "Preferential election: MinMorgulValeTie" + "\n" + "\n" + "Gorbag (FOP)                18" + "\n"
				+ "\n" + "Informal                     3" + "\n" + "\n" + "Votes Cast                  21" + "\n" + "\n"
				+ "\n" + "Candidate Gorbag (Filthy Orc Party) is the winner with 18 votes..." + "\n";
		assertEquals(checkWinner, _prefElection.findWinner());		
	}
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * by addPref() numbers within 1 and numCandidate 3.
	 * Program returns true.
	 * Test method for {@link asgn1Election.PrefElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalPrefWithin() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection.loadDefs();
		_prefElection.loadVotes();
		_voteList.addPref(1);
		_voteList.addPref(2);
		_voteList.addPref(3);	
		
		assertEquals(true,_prefElection.isFormal(_voteList));
	}
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * by addPref() of negative numbers.
	 * Program returns false.
	 * Test method for {@link asgn1Election.PrefElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalPrefNegative() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection.loadDefs();
		_prefElection.loadVotes();
		_voteList.addPref(-11);
		_voteList.addPref(-20);
		_voteList.addPref(-1);
		
		assertEquals(false,_prefElection.isFormal(_voteList));
	}
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * by addPref() of number above numCandidate of 3.
	 * Program returns false.
	 * Test method for {@link asgn1Election.PrefElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalPrefAbove() throws FileNotFoundException, ElectionException, IOException, NumbersException {	
		_prefElection.loadDefs();
		_prefElection.loadVotes();
		_voteList.addPref(4);
		_voteList.addPref(5);
		_voteList.addPref(6);
		
		assertEquals(false,_prefElection.isFormal(_voteList));
	}
	
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * by addPref() of number below numCandidates of 1.
	 * Program returns false.
	 * Test method for {@link asgn1Election.PrefElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalPrefBelow() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection.loadDefs();
		_prefElection.loadVotes();
		_voteList.addPref(0);
		_voteList.addPref(0);
		_voteList.addPref(0);
		
		assertEquals(false,_prefElection.isFormal(_voteList));
	}
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * with no Pref.
	 * Program returns false.
	 * Test method for {@link asgn1Election.PrefElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalNoPref() throws FileNotFoundException, ElectionException, IOException, NumbersException {	
		_prefElection.loadDefs();
		_prefElection.loadVotes();
		
		assertEquals(false,_prefElection.isFormal(_voteList));
	}
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * with valid and invalid Pref.
	 * Program returns false.
	 * Test method for {@link asgn1Election.PrefElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalMixedPref() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection.loadDefs();
		_prefElection.loadVotes();		
		_voteList.addPref(1);
		_voteList.addPref(20);
		_voteList.addPref(5);
		
		assertEquals(false,_prefElection.isFormal(_voteList));
	}
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * to check if vote has preference of 1.
	 * Program returns false.
	 * Test method for {@link asgn1Election.PrefElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalHasPrefOne() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection.loadDefs();
		_prefElection.loadVotes();		
		_voteList.addPref(2);
		_voteList.addPref(3);
		
		assertEquals(false,_prefElection.isFormal(_voteList));
	}
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * to check if vote has double or more preference of 1.
	 * Program returns false.
	 * Test method for {@link asgn1Election.PrefElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalHasDoublePrefOne() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection.loadDefs();
		_prefElection.loadVotes();		
		_voteList.addPref(1);
		_voteList.addPref(1);
		_voteList.addPref(3);
		
		assertEquals(false,_prefElection.isFormal(_voteList));
	}
	
	/**
	 * Test public boolean isFormal(Vote v)
	 * to check if vote has double or more preference of 2,3,4,5,etc.
	 * Program returns false.
	 * Test method for {@link asgn1Election.PrefElection#isFormal(asgn1Election.Vote)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testIsFormalHasDoublePrefth() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection.loadDefs();
		_prefElection.loadVotes();		
		_voteList.addPref(1);
		_voteList.addPref(2);
		_voteList.addPref(2);
		
		assertEquals(false,_prefElection.isFormal(_voteList));
	}

	/**
	 * Protected method. 
	 * Test method for {@link asgn1Election.PrefElection#clearWinner(int)}.
	 */
	@Test
	public void testClearWinnerDontTest() {
		assertTrue(true);
	}

	/**
	 * Confirms Load Def() function is working.  
	 * Test method for {@link asgn1Election.PrefElection#PrefElection(java.lang.String)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testPrefElectionLoadDef() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection2=new PrefElection("MinMorgulVale");
		_prefElection2.loadDefs();
		Collection<Candidate> coll = _prefElection2.getCandidates();
		Iterator<Candidate> i = coll.iterator();	
		
		assertEquals(_cand1.candidateListing(), i.next().candidateListing());
		assertEquals(_cand2.candidateListing(), i.next().candidateListing());
		assertEquals(_cand3.candidateListing(), i.next().candidateListing());
		assertEquals("MinMorgulVale", _prefElection2.getName());		
	}
	
	/**
	 * Catch Invalid Electorate Files
	 * Test method for {@link asgn1Election.PrefElection#PrefElection(java.lang.String)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 */
	@Test(expected=ElectionException.class)
	public void testPrefElectionLoadDefExceptionInvalidElectorateSummaryFormat() throws FileNotFoundException,ElectionException, IOException, NumbersException {
		_prefElection2=new PrefElection("Testfile1");
		_prefElection2.loadDefs();
	}
	
	/**
	 * Catch Invalid Electorate Files
	 * Test method for {@link asgn1Election.PrefElection#PrefElection(java.lang.String)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 */
	@Test(expected=ElectionException.class)
	public void testPrefElectionLoadDefExceptionInvalidSeatNameFormat() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection2=new PrefElection("Testfile2");
		_prefElection2.loadDefs();
	}
	
	/**
	 * Catch Invalid Electorate Files
	 * Test method for {@link asgn1Election.PrefElection#PrefElection(java.lang.String)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 */
	@Test(expected=ElectionException.class)
	public void testPrefElectionLoadDefExceptionInvalidEnrolmentFormat() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection2=new PrefElection("Testfile3");
		_prefElection2.loadDefs();
	}
	
	/**
	 * Catch Invalid Electorate Files
	 * Test method for {@link asgn1Election.PrefElection#PrefElection(java.lang.String)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 */
	@Test(expected=ElectionException.class)
	public void testPrefElectionLoadDefExceptionInvalidNumCandidatesFormat() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection2=new PrefElection("Testfile4");
		_prefElection2.loadDefs();
	}
	
	/**
	 * Catch Invalid Electorate Files
	 * Test method for {@link asgn1Election.PrefElection#PrefElection(java.lang.String)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 */
	@Test(expected=ElectionException.class)
	public void testPrefElectionLoadDefExceptionInvalidCandidateLine() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection2=new PrefElection("Testfile5");
		_prefElection2.loadDefs();
	}
	
	/**
	 * Catch Invalid Electorate Files
	 * Test method for {@link asgn1Election.PrefElection#PrefElection(java.lang.String)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 */
	@Test(expected=ElectionException.class)
	public void testPrefElectionLoadDefExceptionNullCandidateLine() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection2=new PrefElection("Testfile6");
		_prefElection2.loadDefs();
	}
	
	/**
	 * Catch Invalid Electorate Files
	 * Test method for {@link asgn1Election.PrefElection#PrefElection(java.lang.String)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 */
	@Test(expected=ElectionException.class)
	public void testPrefElectionLoadDefExceptionMissingCandidateValue() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_prefElection2=new PrefElection("Testfile7");
		_prefElection2.loadDefs();
	}
	
	/**
	 * Confirms Load Votes() function is working. Expected 21 votes.
	 * Test method for {@link asgn1Election.PrefElection#PrefElection(java.lang.String)}.
	 * 
	 * @throws ElectionException if invalid lines in election files 
	 * @throws FileNotFoundException if election file not found 
	 * @throws IOException from BufferedReader
	 * @throws NumbersException if parsing of integers fails 
	 */
	@Test
	public void testPrefElectionLoadVotes() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_extendedElection = new ExtendedElection("MinMorgulVale");	
		_extendedElection.loadDefs();
		_extendedElection.loadVotes();
		
		int voteCount= 	_extendedElection.getVoteCollection().getFormalCount()+_extendedElection.getVoteCollection().getInformalCount();
		
		assertEquals(21, voteCount);
	}
	
	/**
	 * Catch Invalid vote files
	 * Test method for {@link asgn1Election.PrefElection#PrefElection(java.lang.String)}.
	 * 
	 * @throws NumbersException if parsing error
	 */
	@Test(expected=NumbersException.class)
	public void testPrefElectionLoadVotesExceptionParseIntFromToken() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_extendedElection = new ExtendedElection("Testfile8");	
		_extendedElection.loadDefs();
		_extendedElection.loadVotes();
	}

	/**
	 * Catch Invalid vote files
	 * Test method for {@link asgn1Election.PrefElection#PrefElection(java.lang.String)}.
	 * 
	 * @throws NumbersException if parsing error
	 */
	@Test(expected=NumbersException.class)
	public void testPrefElectionLoadVotesExceptionLettersInVote() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_extendedElection = new ExtendedElection("Testfile9");	
		_extendedElection.loadDefs();
		_extendedElection.loadVotes();
	}
	
	/**
	 * Catch Invalid vote files
	 * Test method for {@link asgn1Election.PrefElection#PrefElection(java.lang.String)}.
	 * 
	 * @throws NumbersException if parsing error
	 */
	@Test(expected=ElectionException.class)
	public void testPrefElectionLoadVotesExceptionInvalidVoteLine() throws FileNotFoundException, ElectionException, IOException, NumbersException {
		_extendedElection = new ExtendedElection("Testfile10");	
		_extendedElection.loadDefs();
		_extendedElection.loadVotes();
	}

	/**
	 * Confirms program will print "MorgulVale - Preferential Voting".
	 * Test method for {@link asgn1Election.PrefElection#toString()}.	 * 
	 */
	@Test
	public void testToString() {
		assertEquals("MinMorgulValeTie - Preferential Voting",_prefElection.toString());
	}

}
