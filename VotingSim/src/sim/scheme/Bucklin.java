/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.scheme;

import java.util.Set;
import sim.util.Candidate;
import sim.util.CandidateRanker;
import sim.util.Ranking;
import sim.voter.IVoter;

/**
 *http://en.wikipedia.org/wiki/Bucklin_voting
 * @author drew
 */
public class Bucklin implements IScheme {

    private Set<Candidate> cans;
    private Set<IVoter> voters;

    private Ranking<Candidate> r;
    @Override
    public void setup(Set<Candidate> cans, Set<IVoter> voters) {
        this.cans = cans;
        this.voters = voters;
    }

    @Override
    public Ranking<Candidate> vote() {
        boolean winner = false;
        Candidate top = null;
                
        for (int i = 0; i < cans.size(); i++)
        {
            // collect the votes
            for (IVoter voter : voters) {
                voter.vote(cans);
            }
            
            
            for (Candidate c : cans)
            {
                
                if (c.getTotalVotesCast() > (voters.size() / 2))
                {
                    top = c;
                    winner = true;
                    break;
                }
                else
                {
                    winner = false;
                }
            }
            if (winner)
                break;
            
        }
        r =  new Ranking<>();
        if (winner == true)
            r.pushCandidate(top);
        return r;
    }

    @Override
    public Ranking getResults() {
         return r;
    }
}
