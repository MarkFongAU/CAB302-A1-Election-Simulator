/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

import java.util.Iterator;
import asgn1Util.Strings;


/**
 * 
 * Subclass of <code>Election</code>, specialised to simple, first past the post voting
 * 
 * @author hogan
 */
public class SimpleElection extends Election {

	/**
	 * Simple Constructor for <code>SimpleElection</code>, takes name and also sets the 
	 * election type internally. 
	 * 
	 * @param name <code>String</code> containing the Election name
	 */
	public SimpleElection(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Election#findWinner()
	 */
	@Override
	public String findWinner() {	
		//Count Primary Votes
		vc.countPrimaryVotes(cds);
		
		//Print Election result String 
		String str= showResultHeader()+"Counting primary votes; "+ numCandidates+ " alternatives available"+"\n"+reportCountResult() +reportWinner(clearWinner(0));		
		return str;		
	}

	/* 
	 * (non-Javadoc)
	 * @see asgn1Election.Election#isFormal(asgn1Election.Vote)
	 */
	@Override
	public boolean isFormal(Vote v) {				
		boolean check=true;
		int currentPref=0;
		int highestPref=0;
		Iterator<Integer> current = v.iterator();
		
		//Check if votes has anymore Preference
		if(current.hasNext()){
			while(current.hasNext()){	
				currentPref=current.next();	
				if(currentPref<1 || currentPref>numCandidates){
					check=false;
					break;
				}	
				if(currentPref==1){
					if(highestPref==1){
						check=false;
						break;
					}else{
						highestPref=currentPref;	
					}
				}				
			}
		}else{
			check=false;
		}	
		if(highestPref!=1){
			check=false;
		}
		return check;			
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = this.name + " - Simple Voting";
		return str;
	}
	
	// Protected and Private/helper methods below///

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Election#clearWinner(int)
	 */
	@Override
	protected Candidate clearWinner(int wVotes) {
		Candidate winner = null;
		int highestVote=0;
		
		for(CandidateIndex c:cds.keySet()){
			//Get Candidate with most votes
			if(cds.get(c).getVoteCount()> highestVote){
				winner=cds.get(c);
				highestVote=winner.getVoteCount();
			}
		}
		return winner;
	}

	/**
	 * Helper method to create a string reporting the count result
	 * 
	 * @return <code>String</code> containing summary of the count
	 */
	private String reportCountResult() {
		String str = "\nSimple election: " + this.name + "\n\n"
				+ candidateVoteSummary() + "\n";
		String inf = "Informal";
		String voteStr = "" + this.vc.getInformalCount();
		int length = ElectionManager.DisplayFieldWidth - inf.length()
				- voteStr.length();
		str += inf + Strings.createPadding(' ', length) + voteStr + "\n\n";

		String cast = "Votes Cast";
		voteStr = "" + this.numVotes;
		length = ElectionManager.DisplayFieldWidth - cast.length()
				- voteStr.length();
		str += cast + Strings.createPadding(' ', length) + voteStr + "\n\n";
		return str;
	}
	
}