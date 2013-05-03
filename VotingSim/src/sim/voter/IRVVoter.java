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
public class IRVVoter extends RandOrderVoter {

    private String id;
public IRVVoter(){}
    public IRVVoter(String id, Set<Candidate> cand) {
        this.id = id;
        orderCand(cand);
    }

    @Override
    public void vote(Set<Candidate> options) {

        while (!options.contains(ordering.firstCandidate())) {
            ordering.popCandidate();
        }
        ordering.firstCandidate().addVote(this);

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
