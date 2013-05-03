/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.scheme;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import sim.util.Candidate;
import sim.util.CandidateRanker;
import sim.util.Ranking;
import sim.voter.IVoter;

/**
 *
 * @author drew
 */
public class Nanson implements IScheme {

    private Set<Candidate> cans;
    private Set<IVoter> voters;
    private Ranking<Candidate> curRanks;

    @Override
    public void setup(Set<Candidate> cans, Set<IVoter> voters) {
        this.cans = cans;
        this.voters = voters;
    }

    @Override
    public Ranking<Candidate> vote() {

        Set<Candidate> remainingCans = new HashSet<>(cans);
        curRanks = new Ranking<>();


        while (!remainingCans.isEmpty()) {
            for (IVoter voter : voters) {
                voter.vote(remainingCans);
            }
            Ranking<Candidate> rank;
            rank = Ranking.buildRanking(remainingCans, new CandidateRanker());
            
            // so get the Candidate that ranked last

            Candidate rmC = rank.getLast();
            remainingCans.remove(rmC);// remove it from the the candidates to be chosen
            for (Candidate c : remainingCans)
            {
                c.clearVotes();
            }
            curRanks.pushCandidate(rmC);

        }
        return curRanks;
    }

    @Override
    public Ranking<Candidate> getResults() {
        return curRanks;
    }
}
