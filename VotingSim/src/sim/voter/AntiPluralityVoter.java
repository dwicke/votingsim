/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.voter;

import java.util.ArrayList;
import java.util.Set;
import sim.util.Candidate;
import sim.util.MersenneTwisterFast;

/**
 *
 * @author drew
 */
public class AntiPluralityVoter extends RandOrderVoter{

    
    private String id;
    public AntiPluralityVoter() {}
    public AntiPluralityVoter(int numcan, MersenneTwisterFast rand, String id)
    {
        //indexofvote = rand.nextInt(numcan);
        this.id = id;
        
    }
    
    
    @Override
    public void vote(Set<Candidate> options) {
        orderCand(options);
       // System.out.println(ordering.firstCandidate().toString());
        // vote for the candidate you like the least
        // the person with the least votes wins
        ordering.getLast().addVote(this);
        //ArrayList<Candidate> ops = new ArrayList<>(options);
        //ops.get(indexofvote).addVote(this);
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
