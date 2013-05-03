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
public class Plurality implements IScheme{

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
        Ranking<Candidate> r =  getResults();
        
        /*
        for (int i = 0; i < r.numRanked(); i++)
        {
            System.out.println(r.getRankAt(i) + " votes: " + r.getRankAt(i).getTotalVotesCast());
        }
        */
        return r;
    }
    

    @Override
    public Ranking<Candidate> getResults() {
        return Ranking.buildRanking(cans, new CandidateRanker());
    }

    
}
