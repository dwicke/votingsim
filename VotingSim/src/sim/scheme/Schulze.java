/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.scheme;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import sim.util.Candidate;
import sim.util.Ranking;

/**
 *
 * @author drew
 */
public class Schulze extends Comparison {

    private Ranking<Candidate> r;
    @Override
    public Ranking<Candidate> vote() {
        Map<LinkedList<Candidate>, Integer> dg = getDirectedGraph();
        Map<LinkedList<Candidate>, Integer> pg = new HashMap<>();

        // Implementation from pseudo code on wikipedia page
        // to create the graph with the strengths of the 
        // strongest paths:
        
        for (Candidate c1 : cans) {
            for (Candidate c2 : cans) {
                if (c1 != c2) {
                    LinkedList<Candidate> c1c2 = new LinkedList<>();
                    LinkedList<Candidate> c2c1 = new LinkedList<>();

                    c1c2.push(c2);
                    c1c2.push(c1);

                    c2c1.push(c1);
                    c2c1.push(c2);
                    // so if more voters prefered
                    // candidate 2 vs candidate 1 than
                    // candidate 1 vs candidate 2
                    // then set the weight for the c1c2 edge 
                    // to the value of the directed graph
                    if (dg.get(c1c2) > dg.get(c2c1)) {
                        pg.put(c1c2, dg.get(c1c2));
                    } else {
                        pg.put(c1c2, 0);
                    }


                }
            }
        }


        for (Candidate c1 : cans) {
            for (Candidate c2 : cans) {
                if (c1 != c2) {
                    for (Candidate k : cans) {
                        if (c1 != k && c2 != k) {

                            LinkedList<Candidate> c2k = new LinkedList<>();
                            LinkedList<Candidate> c2c1 = new LinkedList<>();
                            LinkedList<Candidate> c1k = new LinkedList<>();

                            c2k.push(k);
                            c2k.push(c2);

                            c2c1.push(c1);
                            c2c1.push(c2);

                            c1k.push(k);
                            c1k.push(c1);


                            pg.put(c2k, Math.max(pg.get(c2k), Math.min(pg.get(c2c1), pg.get(c1k))));
                        }
                    }
                }
            }
        }

        // so now get the ranking
        r = new Ranking<>();

        Set<Candidate> remCans = new HashSet<>(cans);

        //System.out.println("While loop: ");
        
        while (remCans.size() > 0) {
            Candidate top = getTop(remCans, pg);
           // System.out.print(top.getID() + " ");
            remCans.remove(top);
            r.pushCandidate(top);
        }
        // must reverse since i find the best candidate first
        // in the above loop.
        //r.reverseRanks();
        /*
        System.out.println();
        System.out.println("For loop: ");
        for (int i = 0; i < r.numRanked(); i++)
        {
            System.out.print(r.getRankAt(i).getID() + " ");
        }
        */
        return r;
    }

    private Candidate getTop(Collection<Candidate> candidates, Map<LinkedList<Candidate>, Integer> pg) {
        for (Candidate c1 : candidates) {
            boolean best = true;
            for (Candidate c2 : candidates) {
                if (c1 != c2) {
                    LinkedList<Candidate> c1c2 = new LinkedList<>();
                    LinkedList<Candidate> c2c1 = new LinkedList<>();

                    c1c2.push(c2);
                    c1c2.push(c1);

                    c2c1.push(c1);
                    c2c1.push(c2);
                    
                    
                    if (pg.get(c1c2) < pg.get(c2c1)) {
                        best = false;
                    }

                }
            }
            if (best == true) {
                return c1;
            }

        }

        return candidates.iterator().next();

    }

    @Override
    public Ranking getResults() {
        return r;
    }
}
