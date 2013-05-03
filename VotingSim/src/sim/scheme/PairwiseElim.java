/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.scheme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
public class PairwiseElim implements IScheme {

    private Set<Candidate> cans;
    private Set<IVoter> voters;
    private Comparator<Candidate> comp;

    /**
     * Assumes that the cans are ordered
     *
     * @param cans
     * @param voters
     */
    @Override
    public void setup(Set<Candidate> cans, Set<IVoter> voters) {
        this.cans = cans;
        this.voters = voters;
        // set the default comparator
        setComparator(new Comparator<Candidate>() {

             @Override
             public int compare(Candidate o1, Candidate o2) {
                 return o1.getID().compareTo(o2.getID());
             }
         });
    }

    /**
     * The comparator to use to sort the candidates.
     *
     * @param comp
     */
    public void setComparator(Comparator<Candidate> comp) {
        this.comp = comp;
    }

    /**
     * returns ranking where the top is the winner
     * and the bottom is the first to loose, second to
     * bottom is the second to loose etc.
     * @return 
     */
    @Override
    public Ranking<Candidate> vote() {
        List<Candidate> sortCan = new ArrayList<>(cans);
        if (comp != null)
            Collections.sort(sortCan, comp);

        ListIterator<Candidate> iCan = sortCan.listIterator();
        Ranking<Candidate> ranking = new Ranking<>();
        Candidate winner = null;
        if (iCan.hasNext()) {
            winner = iCan.next();
            while (iCan.hasNext()) {
                Candidate other = iCan.next();
                Set<Candidate> pair = new HashSet<>();
                pair.add(other);
                pair.add(winner);
                for(IVoter vt : voters)
                {
                    // collect votes
                    vt.vote(pair);
                }
                if (winner.getTotalVotesCast() < other.getTotalVotesCast())
                {
                    // other beat winner
                    ranking.pushCandidate(winner);
                    winner = other;
                }
                else
                {
                    // winner beat other
                    ranking.pushCandidate(other);
                }
                winner.clearVotes();
            }
        }
        
        ranking.pushCandidate(winner);

        return ranking;
    }

    @Override
    public Ranking getResults() {
        return Ranking.buildRanking(cans, new CandidateRanker());
    }
}
