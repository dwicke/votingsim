/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.scheme;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import sim.util.Candidate;
import sim.util.CandidateRanker;
import sim.util.Ranking;
import sim.voter.IVoter;

/**
 *
 * @author drew
 */
public class AntiPlurality implements IScheme{

    private Set<Candidate> cans;
    private Set<IVoter> voters;
    
    @Override
    public void setup(Set<Candidate> cans, Set<IVoter> voters) {
        this.cans = cans;
        this.voters = voters;
    }
    
    @Override
    public Ranking vote() {
        for (IVoter voter : voters)
        {
            voter.vote(cans);
        }
        return getResults();
    }
    

    @Override
    public Ranking<Candidate> getResults() {
        // person with the least votes wins! you vote for the person
        // you don't want.
        // must reverse so that the least votes is first
        return Ranking.buildRanking(cans, new CandidateRanker()).reverseRanks();
    }

    
}
