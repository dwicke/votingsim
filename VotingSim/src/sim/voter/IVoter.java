package sim.voter;



import java.util.Set;
import sim.util.Candidate;
import sim.util.Ranking;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author drew
 */
public interface IVoter {
    
    public void vote(Set<Candidate> options);
    public void orderCand(Set<Candidate> options);
    public Ranking<Candidate> getCurOrdering();
    public void setOrdering(Ranking<Candidate> ordering);
    public void setID(String id);
    public String getID();
    
}
