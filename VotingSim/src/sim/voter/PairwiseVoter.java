/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.voter;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import sim.util.Candidate;

/**
 *
 * @author drew
 */
public class PairwiseVoter extends RandOrderVoter{
    
    private String id;
public PairwiseVoter(){}
    public PairwiseVoter(String id, Set<Candidate> cand) {
        this.id = id;
        orderCand(cand);
    }

    @Override
    public void vote(Set<Candidate> options) {
        
        ArrayList<Candidate> can = new ArrayList<>(options);
        if (ordering.getRank(can.get(0)) > ordering.getRank(can.get(1)))
        {
            can.get(0).addVote(this);
        }
        else
        {
            can.get(1).addVote(this);
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
