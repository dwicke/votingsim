/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.util;

import java.util.Comparator;

/**
 *  Used to sort candidate based on the number of voters
 *  that ranked the candidate as their nVotes choice.
 * @author drew
 */
public class VotesRanker implements Comparator<Candidate>{

    int nVotes = 0;
    
    public VotesRanker(int nVotes)
    {
        this.nVotes = nVotes;
    }
    
    
    @Override
    public int compare(Candidate o1, Candidate o2) {
        // if 5 voters voted o1 to have 5 votes and 4 voters voted o2 with 5 votes
        // then o1 is greater than o2.
        return ((Integer)o1.getNumVotersVotes(nVotes)).compareTo(o2.getNumVotersVotes(nVotes));
    }
    
}
