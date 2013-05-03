/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.util;

import java.util.Comparator;

/**
 *
 * @author drew
 */
public class CandidateRanker implements Comparator<Candidate> {

    @Override
    public int compare(Candidate o1, Candidate o2) {
        if(((Integer) o1.getTotalVotesCast()).compareTo(o2.getTotalVotesCast()) == 0)
        {
            return o1.getID().compareTo(o2.getID());
        }
        return ((Integer) o1.getTotalVotesCast()).compareTo(o2.getTotalVotesCast());
    }
    
}
