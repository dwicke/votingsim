/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.scheme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import sim.util.Candidate;
import sim.util.CandidateRanker;
import sim.util.Ranking;
import sim.voter.IVoter;

/**
 *
 * @author drew
 */
public class Majority implements IScheme{
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
       
        
        List<Candidate> wins = new ArrayList<>();
        for (Candidate c : cans)
        {
            if (c.getTotalVotesCast() > (voters.size() / 2))
            {
                wins.add(c);
            }
        }
        return Ranking.buildRanking(wins, new CandidateRanker());
        
    }

    
}
