package sim.util;


import sim.voter.IVoter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author drew
 */
public class Candidate {
    
    private String id;
    private Map<IVoter, Integer> votes;
    
    public Candidate(String id)
    {
        this.id = id;
        votes = new HashMap<>();
    }
    
    public int getTotalVotesCast()
    {
        int numVotesCast = 0;
        for (Integer v : votes.values())
        {
            numVotesCast += v;
        }
        return numVotesCast;
    }
    
    public int getNumVoters()
    {
        return votes.size();
    }
    
    public Set<IVoter> getVoters()
    {
        return votes.keySet();
    }
    
    public void addVote(IVoter i)
    {
        if (votes.containsKey(i))
        {
            votes.put(i, new Integer(votes.get(i) + 1));
        }
        else
        {
            votes.put(i,1);
        }
    }
    
    public void addVote(IVoter i, int times)
    {
        if (votes.containsKey(i))
        {
            votes.put(i, new Integer(votes.get(i) + times));
        }
        else
        {
            votes.put(i,times);
        }
    }
    
    /**
     * Returns the number of voters that voted
     * the specified number of times
     * @param nvotes
     * @return 
     */
    public int getNumVotersVotes(int nvotes)
    {
        int count = 0;
        for(Integer i : votes.values())
        {
            if (i == nvotes)
                count++;
        }
            
            return count;
    }
    
    public int getNumVotes(IVoter voter)
    {
        return votes.get(voter);
    }
    
    public String getID()
    {
        return id;
    }
    
    public void clearVotes()
    {
        votes.clear();
    }

    @Override
    public String toString() {
        /*
        StringBuilder state = new StringBuilder();
        
        state.append("Candidate: ").append(id).append("\n");
        
        state.append("Total votes: ").append(getTotalVotesCast()).append("\n");
        state.append("Voters:\n");
        for(IVoter voter : getVoters())
        {
            state.append(voter.getID()).append(" voted ").append(getNumVotes(voter)).append(" times\n");
        }
        
        return state.toString();
        */
        
        return getID();
        
    }
    
    
}
