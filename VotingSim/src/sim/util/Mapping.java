/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import sim.scheme.*;
import sim.voter.*;

/**
 *
 * @author drew
 */
public enum Mapping {
    
    // Plurality satisfies majority and not majority loser 
    // plurality is opposite
    Plurality(new Plurality(), PluralityVoter.class),
    AntiPlurality(new AntiPlurality(), AntiPluralityVoter.class),
    // only difference between Borda and AntiPlurality is that it does satisfy reversal symetry and condorcet loser
    Borda(new Plurality(), BordaVoter.class),
    
    // the following do not share as much in common
    Approval(new Plurality(), ApprovalVoter.class),
    Bucklin(new Bucklin(), BucklinVoter.class),
    Cumulative(new Plurality(), CumulativeVoter.class),
    Majority(new Majority(), PluralityVoter.class),
    PairwiseElim(new PairwiseElim(), PairwiseVoter.class),
    
    // Coombs and IRV are the same except Coombs does not satisfy http://en.wikipedia.org/wiki/Independence_of_clones_criterion
    Coombs(new Coombs(), BordaVoter.class),
    IRV(new IRV(), IRVVoter.class),
    
    // the following are Condorcet Methods so they should all produce the same winner
    Minimax(new Minimax(), IRVVoter.class),
    Nanson(new Nanson(), BordaVoter.class),
    KemenyYoung(new KemenyYoung(), IRVVoter.class),
    RankedPairs(new RankedPairs(), IRVVoter.class),
    Schulze(new Schulze(), IRVVoter.class);
    
    private final IScheme scheme;
    private final Class voter;
    
    Mapping(IScheme s, Class voter)
    {
        this.scheme = s;
        this.voter = voter;
    }
    
    public Map<IScheme, List<IVoter> > buildVoting(List<Ranking<Candidate> > voterRankings)
    {
       // System.out.println("=============================================");
        List<IVoter> voters = new ArrayList<>();
        int i = 0;
        for (Ranking<Candidate> r : voterRankings)
        {
            try {
                
                IVoter v = (IVoter) voter.newInstance();
                
                v.setID("" + i);
                // clone the ranking so that if the voter modifies it
                // then we are still good.
                v.setOrdering((Ranking<Candidate>) r.clone());
                voters.add(v);
                
             //   System.out.print(v.getID() + " " );
                for (Candidate c : v.getCurOrdering().ranks)
                {
               //     System.out.print(c.getID() + " ");
                }
            //    System.out.println();
                
            } catch (    InstantiationException | IllegalAccessException | CloneNotSupportedException ex) {
                Logger.getLogger(Mapping.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            i++;
        }
        HashMap<IScheme, List<IVoter> > m = new HashMap<>();
        m.put(scheme, voters);
        
        return m;
    }
    


}
