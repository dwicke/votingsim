/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.voter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import sim.util.Candidate;
import sim.util.Ranking;

/**
 *
 * @author drew
 */
public abstract class RandOrderVoter implements IVoter {

    protected Ranking<Candidate> ordering;

    public RandOrderVoter() {}
    @Override
    public void orderCand(Set<Candidate> options) {
        if (ordering == null) {
            List<Candidate> alts = new ArrayList<>(options);
            Collections.shuffle(alts);
            ordering = new Ranking<>();

            //System.out.print("Ranking: ");
            for (Candidate c : alts) {
                ordering.pushCandidate(c);
                //      System.out.print(c.getID() + ", ");
            }
            // reverse so that I printed it out correctly...
            ordering.reverseRanks();
            //   System.out.println();
        }
    }

    @Override
    public Ranking<Candidate> getCurOrdering() {
        return ordering;
    }

    @Override
    public void setOrdering(Ranking<Candidate> ordering) {
        this.ordering = ordering;
    }
}
