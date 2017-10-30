/**
 * 
 * This file is part of the VotingWizard Project, written as 
 * part of the assessment for CAB302, Semester 1, 2016. 
 * 
 */
package asgn1Election;

//import java.util.BitSet;
import java.util.Iterator;
import asgn1Util.Strings;


/**
 * 
 * Subclass of <code>Election</code>, specialised to preferential, but not optional
 * preferential voting.
 * 
 * @author hogan
 * 
 */
public class PrefElection extends Election {
	/**
	 * Simple Constructor for <code>PrefElection</code>, takes name and also sets the 
	 * election type internally. 
	 * 
	 * @param name <code>String</code> containing the Election name
	 */
	public PrefElection(String name) {
		super(name);			
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Election#findWinner()
	 */
	@Override
	public String findWinner() {			
		Candidate winner=null;
		
		//Count Primary Votes
		vc.countPrimaryVotes(cds);	
		
		//Print Election result String 
		String str= showResultHeader()+"Counting primary votes; "+ numCandidates+ " alternatives available"+"\n"+
		reportCountStatus();
				
		//Check if clearWinner has been found 
		while(clearWinner(vc.getFormalCount())==null){
			//Select candidate with lowest votes after a round of counting 
			CandidateIndex lowest=selectLowestCandidate();
			str += prefDistMessage(cds.get(lowest)) + "\n";
			
			//Remove Candidate Index of the loser candidate
			cds.remove(lowest);		
			
			//Count Preth Votes
			vc.countPrefVotes(cds, lowest);
			str += reportCountStatus();		
		}
		
		//Determine clear Winner
		winner=clearWinner(vc.getFormalCount());
		str += reportWinner(winner);
		return str;
	}

	/* 
	 * (non-Javadoc)
	 * 
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
				int similarPref=0;
				currentPref=current.next();	
				
				//Check for similar preference in the vote
				for(int i:v){
					if(i==currentPref){
						similarPref++;
					}
				}
				if(similarPref>1){
					check=false;
					break;
				}
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
        String str = this.name + " - Preferential Voting";
		return str;
	}
	
	// Protected and Private/helper methods below///


	/*
	 * (non-Javadoc)
	 * 
	 * @see asgn1Election.Election#clearWinner(int)
	 */
	@Override
	protected Candidate clearWinner(int winVotes) {
		Candidate winner = null;	
		
		for(CandidateIndex c:cds.keySet()){
			//Get Candidate which have more than half of the total votes (51% majority)
			if(cds.get(c).getVoteCount()> (winVotes/2)){				
				winner=cds.get(c);				
			}			
		}
		return winner;
	}
	

	/**
	 * Helper method to create a preference distribution message for display 
	 * 
	 * @param c <code>Candidate</code> to be eliminated
	 * @return <code>String</code> containing preference distribution message 
	 */
	private String prefDistMessage(Candidate c) {
		String str = "\nPreferences required: distributing " + c.getName()
				+ ": " + c.getVoteCount() + " votes";
		return str;
	}

	/**
	 * Helper method to create a string reporting the count progress
	 * 
	 * @return <code>String</code> containing count status  
	 */
	private String reportCountStatus() {
		String str = "\nPreferential election: " + this.name + "\n\n"
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

	/**
	 * Helper method to select candidate with fewest votes
	 * 
	 * @return <code>CandidateIndex</code> of candidate with fewest votes
	 */
	private CandidateIndex selectLowestCandidate() {		
		CandidateIndex index = null;
		int minVotes=0;
		boolean haveMin=false;
		
		for (CandidateIndex c : cds.keySet()) {
			if (haveMin) {
				//Select Candidate with lowest votes
				if (cds.get(c).getVoteCount() < minVotes) {					
					minVotes = cds.get(c).getVoteCount();
					index = c;					
				}
			} else {				
				minVotes = cds.get(c).getVoteCount();
				index = c;
				haveMin = true;

			}
		}	
		return index;		
	}
}