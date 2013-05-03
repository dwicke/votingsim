/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.voter;

import java.util.Set;
import sim.util.Candidate;

/**
 *
 * @author drew
 */
public class BucklinVoter extends RandOrderVoter {

    private String id;

    public BucklinVoter() {
    }

    public BucklinVoter(String id, Set<Candidate> options) {
        this.id = id;
        orderCand(options);
    }

    @Override
    public void vote(Set<Candidate> options) {
        // vote for current top candidate
        // I get called again when no majority candidate was
        // elected in previous round

        Candidate c = ordering.popCandidate();
        c.addVote(this);

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
