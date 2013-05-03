/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.voter;

import java.util.Set;
import sim.util.Candidate;
import sim.util.MersenneTwisterFast;
import sim.util.Ranking;

/**
 *
 * @author drew
 */
public class CumulativeVoter extends RandOrderVoter{
    
    private int k = 1;
    private String id;
    private MersenneTwisterFast rand;
    public CumulativeVoter(){ rand = new MersenneTwisterFast(System.currentTimeMillis());}
    public CumulativeVoter(int k, MersenneTwisterFast rand, String id)
    {
        this.k = k;
        this.id = id;
        this.rand = rand;
    }

    @Override
    public void vote(Set<Candidate> options) {
        int votesLeft = k;
        for (Candidate can : options)
        {
            if(votesLeft > 0)
            {
                int votes = rand.nextInt(votesLeft + 1);// add 1 since nextInt is [0,n-1]
                if(votes > 0)
                {
                    can.addVote(this,votes);
                    votesLeft -= votes;
                }
            }
            else
                break;
        }
        // distribute the rest of the votes uniformly
        while (votesLeft > 0)
        {
            for (Candidate can : options)
            {
                can.addVote(this);
                votesLeft--;
                if (votesLeft == 0)
                    break;
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
    
    /**
     * Set the number of votes it is allowed to distribute
     * @param k 
     */
    public void setK(int k)
    {
        this.k = k;
    }

   
    
}
