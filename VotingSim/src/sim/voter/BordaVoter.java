/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.voter;

import java.util.Set;
import sim.util.Candidate;
import sim.util.MersenneTwisterFast;

/**
 *
 * @author drew
 */
public class BordaVoter extends RandOrderVoter{

    private String id;
    
    public BordaVoter() {}
    public BordaVoter(String id, Set<Candidate> options)
    {
        this.id = id;
        orderCand(options);
    }
    @Override
    public void vote(Set<Candidate> options) {
        // works with nanson, borda and Coombs voting schemes
        // so update my orderings like the removed option was never there.
        ordering.update(options);
        
        // do a borda vote
        for (Candidate c : options)
        {
            // so do a borda vote higher ranked candidates get more votes
            // the least ranked gets zero
            if (options.size() != 1)
                c.addVote(this, Math.abs(ordering.getRank(c) - (options.size() - 1)));
            else
                c.addVote(this,1);
        }
        
        
        
    }

    @Override
    public String getID() {
        return id;
    }
    
    // to vote rank ordering is achieved by
    // voting for each candidate where
    // more votes is a higher ranking.
    
    // then the bordascheme can figure out the ranking from that!

    @Override
    public void setID(String id) {
        this.id = id;
    }
}
