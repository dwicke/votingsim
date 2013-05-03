/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.voter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import sim.util.Candidate;
import sim.util.MersenneTwisterFast;
import sim.util.Ranking;

/**
 *
 * @author drew
 */
public class ApprovalVoter extends RandOrderVoter{

    
    private String id;
    
    public ApprovalVoter()
    {}
    public ApprovalVoter(String id)
    {
        this.id = id;
    }

    @Override
    public void vote(Set<Candidate> options) {
        
        if (ordering == null)
        {
            orderCand(options);
        }
        
        for (Candidate can : options)
        {
            // only vote for can that i like the most
           if (ordering.getRank(can) <= ((options.size() - 1) / 2)) // since rank goes 0 (high) to size() - 1 (low)
           {
               can.addVote(this);
           }
        }
        
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void setID(String id) {
        this.id = id;
    }

    
    
    
}
