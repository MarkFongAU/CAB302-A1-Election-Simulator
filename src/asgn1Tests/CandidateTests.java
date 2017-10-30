/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Election.Candidate;
import asgn1Election.ElectionException;

/**
 * <p>JUnit Test for Candidate</p>
 * 
 * @author Fong
 */
public class CandidateTests {
	
	
	/** Candidate Information */
	private static final int INITIAL_VOTECOUNT = 0;
	private static final String CAND_NAME = "Shelob";
	private static final String CAND_ABBREV = "MSP";
	private static final String CAND_PARTY = "Monster Spider Party";
		
	/** To catch exceptions */
	private static final String EMPTY_STRING = "";	
	private static final int VOTE_COUNTNEGATIVE = -10;
	
	/** New Candidate object */
	private Candidate _candidate;	
		
	/**
	 * Initialize Constructor _candidate with "Shelob", "Monster Spider Party" ,"MSP" and 0.
	 * 
	 * @throws ElectionException if <code>isNullOrEmpty(candName,candParty,candAbbrev) OR voteCount <0</code>
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpCandidate() throws ElectionException {
		_candidate = new Candidate(CAND_NAME, CAND_PARTY ,CAND_ABBREV , INITIAL_VOTECOUNT);
	}	

	/**
	 * Confirms program throw exception when initialize Constructor _candidate with null input.	
	 * Test method for {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * 
	 * @throws ElectionException if <code>isNullOrEmpty(candName,candParty,candAbbrev) OR voteCount <0</code>
	 */
	@Test(expected=ElectionException.class)
	public void testCandidateExceptionAllNullInput() throws ElectionException{
		_candidate = new Candidate(null, null ,null , INITIAL_VOTECOUNT);		
	}
	
	/**
	 * Confirms program throws exception when initialize Constructor _candidate with empty input.
	 * Test method for {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * 
	 * @throws ElectionException if <code>isNullOrEmpty(candName,candParty,candAbbrev) OR voteCount <0</code>
	 */
	@Test(expected=ElectionException.class)
	public void testCandidateExceptionAllEmptyInput() throws ElectionException{
		_candidate = new Candidate(EMPTY_STRING, EMPTY_STRING ,EMPTY_STRING,INITIAL_VOTECOUNT);			
	}
	
	/**
	 * Confirms program throw exception when initialize Constructor _candidate with null Candidate Name.
	 * Test method for {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * 
	 * @throws ElectionException if <code>isNullOrEmpty(candName,candParty,candAbbrev) OR voteCount <0</code>
	 */
	@Test(expected=ElectionException.class)
	public void testCandidateExceptionNullCandName() throws ElectionException{
		_candidate = new Candidate(null, CAND_PARTY ,CAND_ABBREV , INITIAL_VOTECOUNT);			
	}
	
	/**
	 * Confirms program throw exception when initialize Constructor _candidate with empty Candidate Name. 
	 * Test method for {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * 
	 * @throws ElectionException if <code>isNullOrEmpty(candName,candParty,candAbbrev) OR voteCount <0</code>
	 */
	@Test(expected=ElectionException.class)
	public void testCandidateExceptionEmptyCandName() throws ElectionException{
		_candidate = new Candidate(EMPTY_STRING, CAND_PARTY ,CAND_ABBREV , INITIAL_VOTECOUNT);				
	}
	
	/**
	 * Confirms program throw exception when initialize Constructor _candidate with null Candidate Party.
	 * Test method for {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * 
	 * @throws ElectionException if <code>isNullOrEmpty(candName,candParty,candAbbrev) OR voteCount <0</code>
	 */
	@Test(expected=ElectionException.class)
	public void testCandidateExceptionNullCandParty() throws ElectionException{
		_candidate = new Candidate(CAND_NAME, null ,CAND_ABBREV , INITIAL_VOTECOUNT);					
	}
	
	/**
	 * Confirms program throw exception when initialize Constructor _candidate with empty Candidate Name.
	 * Test method for {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * 
	 * @throws ElectionException if <code>isNullOrEmpty(candName,candParty,candAbbrev) OR voteCount <0</code>
	 */
	@Test(expected=ElectionException.class)
	public void testCandidateExceptionEmptyCandParty() throws ElectionException{
		_candidate = new Candidate(CAND_NAME, EMPTY_STRING ,CAND_ABBREV , INITIAL_VOTECOUNT);						
	}
	
	/**
	 * Confirms program throw exception when initialize Constructor _candidate with null Candidate Abbrev. 
	 * Test method for {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * 
	 * @throws ElectionException if <code>isNullOrEmpty(candName,candParty,candAbbrev) OR voteCount <0</code>
	 */
	@Test(expected=ElectionException.class)
	public void testCandidateExceptionNullCandAbbrev() throws ElectionException{
		_candidate = new Candidate(CAND_NAME, CAND_PARTY ,null , INITIAL_VOTECOUNT);						
	}
	
	/**
	 * Confirms program throw exception when initialize Constructor _candidate with empty Candidate Abbrev. 
	 * Test method for {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * 
	 * @throws ElectionException if <code>isNullOrEmpty(candName,candParty,candAbbrev) OR voteCount <0</code>
	 */
	@Test(expected=ElectionException.class)
	public void testCandidateExceptionEmptyCandAbbrev() throws ElectionException{
		_candidate = new Candidate(CAND_NAME, CAND_PARTY ,EMPTY_STRING , INITIAL_VOTECOUNT);							
	}
	
	/**
	 * Confirms program throws exception when voteCount is less than 0.
	 * Test method for {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * 
	 * @throws ElectionException if <code>isNullOrEmpty(candName,candParty,candAbbrev) OR voteCount <0</code>
	 */
	@Test(expected = ElectionException.class)
	public void testCandidateExceptionVoteCountLessThanZero() throws ElectionException{
		_candidate = new Candidate(CAND_NAME, CAND_PARTY ,CAND_ABBREV , VOTE_COUNTNEGATIVE);								
	}
	
	/**
	 * Check constructor for correct input.
	 * Test method for {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testCandidateCorrectCandNameParty()  {		
		assertEquals(CAND_NAME,_candidate.getName());
		assertEquals(CAND_PARTY, _candidate.getParty());		
	}
	
	/**
	 * Check constructor for incorrect input.
	 * Test method for {@link asgn1Election.Candidate#Candidate(java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testCandidateIncorrectCandNameParty()  {		
		assertNotEquals("Shelob123",_candidate.getName());
		assertNotEquals("Monster Spider Party123", _candidate.getParty());		
	}
	
	/**
	 * No test.
	 * Test method for {@link asgn1Election.Candidate#candidateListing()}.
	 */
	@Test
	public void testCandidateListingDontTest() {
		assertTrue(true);
	}

	/**
	 * Confirms copied voteCount = 2 when initial voteCount is 0 after incrementVoteCount() twice. 
	 * Original voteCount still at 0.
	 * Test method for {@link asgn1Election.Candidate#copy()}.
	 * 
	 * @throws ElectionException if <code>isNullOrEmpty(candName,candParty,candAbbrev) OR voteCount <0</code>
	 */
	@Test
	public void testCandCopyVoteCount() throws ElectionException {
		Candidate i = _candidate.copy();
		i.incrementVoteCount();
		i.incrementVoteCount();
		assertEquals(2,i.getVoteCount());
		assertEquals(INITIAL_VOTECOUNT,_candidate.getVoteCount());
	}

	/**
	 * Confirms _candidate Candidate name equals "Shelob". 
	 * Test method for {@link asgn1Election.Candidate#getName()}.
	 */
	@Test
	public void testGetCandName() {
		assertEquals(CAND_NAME,_candidate.getName());		
	}

	/**
	 * Confirms _candidate Party name equals "Monster Spider Party".
	 * Test method for {@link asgn1Election.Candidate#getParty()}.
	 */
	@Test
	public void testGetCandParty() {
		assertEquals(CAND_PARTY,_candidate.getParty());	
	}

	/**
	 * Confirms _candidate Vote Count equals 0.
	 * Test method for {@link asgn1Election.Candidate#getVoteCount()}.
	 */
	@Test
	public void testGetCandVoteCount() {
		assertEquals(INITIAL_VOTECOUNT,_candidate.getVoteCount());
	}

	/**
	 * Confirms _candidate Vote Count String equals "0".
	 * Test method for {@link asgn1Election.Candidate#getVoteCountString()}.
	 */
	@Test
	public void testGetVoteCandCountString() {
		assertEquals("0",_candidate.getVoteCountString());
	}

	/**
	 * Confirms <code> incrementVoteCount() </code> can increment.
	 * Test method for {@link asgn1Election.Candidate#incrementVoteCount()}.
	 */
	@Test
	public void testIncrementVoteCount() {
		_candidate.incrementVoteCount();
		assertEquals(1,_candidate.getVoteCount());	
	}

	/**
	 * No test.
	 * Test method for {@link asgn1Election.Candidate#toString()}.
	 */
	@Test
	public void testToStringDontTest() {
		assertTrue(true);
	}

}
