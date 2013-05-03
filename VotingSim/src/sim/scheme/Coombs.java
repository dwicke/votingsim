/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.scheme;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import sim.util.Candidate;
import sim.util.CandidateRanker;
import sim.util.Ranking;
import sim.util.VotesRanker;
import sim.voter.IVoter;

/**
 * Uses BordaVoter to vote.
 * @author drew
 */
public class Coombs implements IScheme{
private Set<Candidate> cans;
    private Set<IVoter> voters;

    @Override
    public void setup(Set<Candidate> cans, Set<IVoter> voters) {
        this.cans = cans;
        this.voters = voters;
    }

    @Override
    public Ranking<Candidate> vote() {

        Set<Candidate> remainingCans = new HashSet<>(cans);
        Set<IVoter> remainingVoters = voters;
        
        while (remainingCans.size() > 1) {
            for (IVoter voter : remainingVoters) {
                voter.vote(remainingCans);
            }
            // remaining.size() - 1 since that is the top ranking.
            Ranking<Candidate> r = Ranking.buildRanking(remainingCans,new VotesRanker(remainingCans.size() - 1));
            
            // check if the top candidate has the majority of the top votes
            if (r.firstCandidate().getNumVotersVotes(remainingCans.size()) > (voters.size() / 2))
            {
                // we have a winner!!
                return r;
            }
            r = Ranking.buildRanking(remainingCans,new VotesRanker(0));
            // remove the candidate that has the most votes for last place
            remainingCans.remove(r.firstCandidate());
            // reset the votes.
            for (Candidate c : remainingCans)
            {
                c.clearVotes();
            }
            
        }
        // if one candidate
        return getResults();
    }

    @Override
    public Ranking<Candidate> getResults() {
        return Ranking.buildRanking(cans, new CandidateRanker());
    }
    
}
