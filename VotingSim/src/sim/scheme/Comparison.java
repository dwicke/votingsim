/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.scheme;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import sim.util.Candidate;
import sim.util.Ranking;
import sim.voter.IVoter;

/**
 *
 * @author drew
 */
public abstract class Comparison implements IScheme{
    
    protected Set<Candidate> cans;
    protected Set<IVoter> voters;

    @Override
    public void setup(Set<Candidate> cans, Set<IVoter> voters) {
        this.cans = cans;
        this.voters = voters;
    }

    
    /**
     * Returns a weighted directed graph
     * c2 --5--> c1
     * 
     * means that 5 voters prefer candidate c2 over c1
     * c1 will be first in the LinkedList
     * c2 will be last in the LinkedList
     * and the value of the Map is 5 (the num voters)
     * @return 
     */
    public Map<LinkedList<Candidate>, Integer> getDirectedGraph()
    {
        HashMap<Ranking, Integer> ranks = new HashMap<>();
        
        for (IVoter voter : voters)
        {
            // make sure the voter has ordered the candidates
            if (voter.getCurOrdering() == null)
                voter.orderCand(cans);
            // get the ordering 
            Ranking r = voter.getCurOrdering();
            // check if another voter also ranked the 
            // candidates in the same way
            if (ranks.containsKey(r))
            {
                ranks.put(r, ranks.get(r) + 1);
            }
            else
            {
                ranks.put(r, 1);
            }
            
        }
        
        
        
        
        HashMap<LinkedList<Candidate>, Integer> directedGraph = new HashMap<>();
        // create a directed graph of the pairwise preferences of the voters
        // each edge says how many voters prefer c1 to c2
        for (Candidate c1: cans)
        {
            for (Candidate c2: cans )
            {
                if (!(c1.getID().equals(c2.getID())))
                {
                    int numVoters = 0;
                    for (Entry<Ranking, Integer> ranked : ranks.entrySet())
                    {
                        if (ranked.getKey().getRank(c1) > ranked.getKey().getRank(c2))
                        {
                            numVoters += ranked.getValue();
                        }
                    }

                    LinkedList<Candidate> edge = new LinkedList<>();
                    edge.push(c2);
                    edge.push(c1);
                    
                    //System.out.println(c2.getID() +  " --" + numVoters +  "--> " + c1.getID());
                    // c2 --numVoters--> c1
                    directedGraph.put(edge, numVoters);
                }
            }
        }
        
        return directedGraph;
    }
    
}
