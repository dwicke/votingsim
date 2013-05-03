/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.scheme;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sim.graph.DAG;
import sim.util.Candidate;
import sim.util.Ranking;

/**
 *
 * @author drew
 */
public class RankedPairs extends Comparison{

    private Ranking<Candidate> r;
    
    @Override
    public Ranking<Candidate> vote() {
        
        
        // tally the votes
        final Map<LinkedList<Candidate>, Integer> pg = getDirectedGraph();
       
        
        
        
        // Sort the votes based on wikipedia article.
        ArrayList<Entry<LinkedList<Candidate>, Integer> > sor = new ArrayList(pg.entrySet());
        Collections.sort(sor, new Comparator<Entry<LinkedList<Candidate>, Integer> >() {

            @Override
            public int compare(Entry<LinkedList<Candidate>, Integer> o1, Entry<LinkedList<Candidate>, Integer> o2) {
                //To change body of generated methods, choose Tools | Templates.
                if(o1.getValue() > o2.getValue())
                {
                    return -1;
                }
                else if (o1.getValue() == o2.getValue())
                {
                    Collections.reverse(o1.getKey());
                    Collections.reverse(o2.getKey());
                    
                    int ro1Val, ro2Val;
                    
                    if (pg.get(o1.getKey()) == null)
                        ro1Val = 0;
                    else
                        ro1Val = pg.get(o1.getKey());
                        
                    if (pg.get(o2.getKey()) == null)
                        ro2Val = 0;
                    else
                        ro2Val = pg.get(o2.getKey());
                        
                    // must reverse back
                    Collections.reverse(o1.getKey());
                    Collections.reverse(o2.getKey());
                    if (ro2Val > ro1Val)
                    {
                        return -1;
                    }
                    
                    
                }
                
                return 1;
                
            }
        });
        
        
        
      
        
        // now lock by creating a DAG
        DAG<Candidate> g = new DAG<>();
        
        
        for (Entry<LinkedList<Candidate>, Integer> ent : sor)
        {
            if (ent.getValue() > (voters.size() / 2))
            {
                //System.out.println(ent.getKey().getLast().getID() +  " --" + ent.getValue() +  "--> " + ent.getKey().getFirst().getID());
                // build the dag 
                g.addEdge(ent.getKey().getLast(), ent.getKey().getFirst());
                
            }
        }
        
        // get the first place candidates
         r = new Ranking<>();
        // the winner is the candidate with no edges entering it.
        for (Candidate n : g.getNoIncoming())
        {
            r.pushCandidate(n);
        }
        
        
        return r;
        
        
    }
    
    
    @Override
    public Ranking getResults() {
        return r;
    }
    
    
}
