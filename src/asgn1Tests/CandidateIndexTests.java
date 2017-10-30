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

import asgn1Election.CandidateIndex;
import asgn1Election.ElectionException;

/**
 * <p>JUnit Test for CandidateIndex</p>
 * 
 * @author Fong
 *
 */
public class CandidateIndexTests {
	/** To catch exceptions */
	private static final int CAND_INDEX_BELOW = 0;
	private static final int CAND_INDEX_ABOVE = 20;
	private static final int CAND_INDEX_INRANGE = 10;
	
	/** New CandidateIndex object */
	private CandidateIndex _candidateIndex;	
	
	/**
	 * Initializing Constructor with 10 to test  
	 * 
	 * @throws java.lang.Exception
	 * @throws ElectionException if <code>isNullOrEmpty(candName,candParty,candAbbrev) OR voteCount <0</code>
	 */
	@Before	
	public void setUpCandidateIndex() throws ElectionException {
		_candidateIndex = new CandidateIndex(CAND_INDEX_INRANGE);		
	}

	/**
	 * Confirms program return false when input value over MaxCandidates of 15.
	 * Test method for {@link asgn1Election.CandidateIndex#inRange(int)}. 
	 */
	@Test	
	public void testInRangeAbove() {
		assertEquals(false,CandidateIndex.inRange(CAND_INDEX_ABOVE));
	}
	
	/**
	 * Confirms program return false when input value less than MinCandidates of 1.
	 * Test method for {@link asgn1Election.CandidateIndex#inRange(int)}. 
	 */
	@Test	
	public void testInRangeBelow() {
		assertEquals(false,CandidateIndex.inRange(CAND_INDEX_BELOW));
	}
	
	/**
	 * Confirms program return false when input value is negative.
	 * Test method for {@link asgn1Election.CandidateIndex#inRange(int)}. 
	 */
	@Test	
	public void testInRangeNegative() {
		assertEquals(false,CandidateIndex.inRange(-CAND_INDEX_INRANGE));
	}
	
	/**
	 * Confirms program return true when input value is within 1 and 15.
	 * Test method for {@link asgn1Election.CandidateIndex#inRange(int)}. 
	 */
	@Test	
	public void testInRangeTrue() {
		assertEquals(true,CandidateIndex.inRange(CAND_INDEX_INRANGE));
	}

	/**
	 * Confirms new CandidateIndex of returns 50.
	 * Test method for {@link asgn1Election.CandidateIndex#toString()}.
	 */
	@Test
	public void testCandidateIndex() {
		CandidateIndex i = new CandidateIndex(50);			
		assertEquals("50",i.toString());
	}

	/**
	 * Confirms when compare with new CandidateIndex of lesser value returns 1.
	 * Test method for {@link asgn1Election.CandidateIndex#compareTo(asgn1Election.CandidateIndex)}. 
	 */
	@Test
	public void testCompareToLesserValue() {
		CandidateIndex i = new CandidateIndex(9);			
		assertEquals(1,_candidateIndex.compareTo(i));
	}
	
	/**
	 * Confirms when compare with new CandidateIndex of bigger value returns -1.
	 * Test method for {@link asgn1Election.CandidateIndex#compareTo(asgn1Election.CandidateIndex)}. 
	 */
	@Test
	public void testCompareToBiggerValue() {
		CandidateIndex i = new CandidateIndex(11);			
		assertEquals(-1,_candidateIndex.compareTo(i));
	}
	
	/**
	 * Confirms deep copy of CandidateIndex returns 0.
	 * Test method for {@link asgn1Election.CandidateIndex#copy()}. 
	 */
	@Test
	public void testCopyCompareToDeepCopy() {
		CandidateIndex i = _candidateIndex.copy();		
		assertEquals(0,_candidateIndex.compareTo(i));
	}
	
	/**
	 * Confirms copied CandidateIndex has value of "20" while original Candidate Index has original value of 10.
	 * Test method for {@link asgn1Election.CandidateIndex#copy()}. 
	 */
	@Test
	public void testCheckCopy() {
		CandidateIndex i = _candidateIndex.copy();
		i.setValue(CAND_INDEX_ABOVE);
		assertEquals("20",i.toString());
		assertEquals("10", _candidateIndex.toString());
	}	
	
	/**
	 * Confirms program can increment Index.
	 * Test method for {@link asgn1Election.CandidateIndex#incrementIndex()}.	
	 */
	@Test
	public void testIncrementIndex() {
		_candidateIndex.incrementIndex();
		assertEquals("11",_candidateIndex.toString());
	}

	/**
	 * Confirms program can set value correctly.
	 * Test method for {@link asgn1Election.CandidateIndex#setValue(int)}.	
	 */
	@Test
	public void testSetValue() {
		_candidateIndex.setValue(CAND_INDEX_INRANGE);
		assertEquals("10",_candidateIndex.toString());	
	}

	/**
	 * Confirms program toString() function work correctly.
	 * Test method for {@link asgn1Election.CandidateIndex#toString()}.	 
	 */
	@Test
	public void testToString() {
		_candidateIndex.setValue(15);
		assertEquals("15",_candidateIndex.toString());	
	}

}
