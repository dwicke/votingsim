package sim.scheme;


import sim.util.Ranking;
import sim.util.Candidate;
import sim.voter.IVoter;
import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author drew
 */
public interface IScheme {
    /**
     * Sets up the voting scheme with the provided candidates and voters
     * @param cans
     * @param voters 
     */
    public void setup(Set<Candidate> cans, Set<IVoter> voters);
    /**
     * Runs the scheme based on the setup and returns the results.
     * @return 
     */
    public Ranking<Candidate> vote();
    /**
     * Get the results of the voting simulation.
     * @return 
     */
    public Ranking getResults();
    
}
