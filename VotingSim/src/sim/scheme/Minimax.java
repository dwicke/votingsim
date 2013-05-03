/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.scheme;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import sim.util.Candidate;
import sim.util.Ranking;

/**
 *
 * @author drew
 */
public class Minimax extends Comparison{

    private Ranking<Candidate> r;
    
    
    @Override
    public Ranking<Candidate> vote() {
        Map<LinkedList<Candidate>, Integer> dg = getDirectedGraph();
        
        
        Candidate winner = null;
        Integer score = -1;
        // argmin(max(score(Y,X))) =-> winner
        //   X     Y
        for (Candidate X : cans)
        {
            if (winner == null)
            {
                winner = X;
            }
            Integer max = -1;
            
            for (Candidate Y : cans)
            {
                if (X != Y)
                {
                    LinkedList<Candidate> pair = new LinkedList<>();
                    pair.push(Y);
                    pair.push(X);
                    
                    if (dg.containsKey(pair) && dg.get(pair) > max)
                    {
                        max = dg.get(pair);
                    }
                    
                    
                }
            }
            
            if (max < score || score == -1)
            {
                score = max;
                winner = X;
            }
            
        }
        r = new Ranking<>();
        r.pushCandidate(winner);
        
        return r;
    }

    @Override
    public Ranking getResults() {
        return r;
    }
    
}
