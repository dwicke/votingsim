
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sim.scheme.IRV;
import sim.scheme.KemenyYoung;
import sim.scheme.Majority;
import sim.scheme.Minimax;
import sim.scheme.Nanson;
import sim.scheme.PairwiseElim;
import sim.scheme.Plurality;
import sim.scheme.RankedPairs;
import sim.scheme.Schulze;
import sim.util.Candidate;
import sim.util.MersenneTwisterFast;
import sim.util.Ranking;
import sim.voter.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author drew
 */
public class Main {
    
    
    public static void main(String args[])
    {
        MersenneTwisterFast rand = new MersenneTwisterFast(System.currentTimeMillis());
        //testPlurality(3, 10, rand);
       // testCumulative(3, 10, 5, rand);
       // testApproval(3, 10, rand);
       // testIRV(3, 50, rand);
       // testBorda(3,10,rand);
        // testNanson(3,10,rand);
        //testPair(3,10,rand);
        
        
        // bucklin // need to make a test
        // coombs // need to make a test
        // antiplurality // need to make a test
        //testSchulze(3,10,rand);    
        //testRankedPairs(3,10,rand);
        
         //testMajority(3,10, rand); 
         // testMinimax(3,10,rand);
        // testKemenyYoung(3,10,rand);
        
    }

    private static void testPlurality(int can, int vot, MersenneTwisterFast rand) {
        Plurality p = new Plurality();
        //set up candidates
        Set<Candidate> cans = new HashSet<>();
        Set<IVoter> voters = new HashSet<>();
        
        for (int i = 0; i < can; i++)
        {
            cans.add(new Candidate(String.valueOf(i)));
        }
        
        for (int i = 0; i < vot; i++)
        {
            voters.add(new PluralityVoter(can, rand, String.valueOf(i)));
        }
        
        p.setup(cans, voters);
        System.out.println(p.vote());
        
    }
    
    private static void testCumulative(int can, int vot, int k, MersenneTwisterFast rand) {
        Plurality p = new Plurality();
        //set up candidates
        Set<Candidate> cans = new HashSet<>();
        Set<IVoter> voters = new HashSet<>();
        
        for (int i = 0; i < can; i++)
        {
            cans.add(new Candidate(String.valueOf(i)));
        }
        
        for (int i = 0; i < vot; i++)
        {
            voters.add(new CumulativeVoter(k, rand, String.valueOf(i)));
        }
        
        p.setup(cans, voters);
        System.out.println(p.vote());
        
    }
    
    private static void testApproval(int can, int vot, MersenneTwisterFast rand) {
        Plurality p = new Plurality();
        //set up candidates
        Set<Candidate> cans = new HashSet<>();
        Set<IVoter> voters = new HashSet<>();
        
        for (int i = 0; i < can; i++)
        {
            cans.add(new Candidate(String.valueOf(i)));
        }
        
        for (int i = 0; i < vot; i++)
        {
            voters.add(new ApprovalVoter(String.valueOf(i)));
        }
        
        p.setup(cans, voters);
        System.out.println(p.vote());
        
    }
    
    private static void testIRV(int can, int vot, MersenneTwisterFast rand) {
        IRV p = new IRV();
        //set up candidates
        Set<Candidate> cans = new HashSet<>();
        Set<IVoter> voters = new HashSet<>();
        
        for (int i = 0; i < can; i++)
        {
            cans.add(new Candidate(String.valueOf(i)));
        }
        
        for (int i = 0; i < vot; i++)
        {
            voters.add(new IRVVoter(String.valueOf(i), cans));
        }
        
        p.setup(cans, voters);
        System.out.println(p.vote());
        
    }
    
     private static void testBorda(int can, int vot, MersenneTwisterFast rand) {
        Plurality p = new Plurality();
        //set up candidates
        Set<Candidate> cans = new HashSet<>();
        Set<IVoter> voters = new HashSet<>();
        
        for (int i = 0; i < can; i++)
        {
            cans.add(new Candidate(String.valueOf(i)));
        }
        
        for (int i = 0; i < vot; i++)
        {
            voters.add(new BordaVoter(String.valueOf(i), cans));
        }
        
        p.setup(cans, voters);
        System.out.println(p.vote());
        
    }
     
     private static void testNanson(int can, int vot, MersenneTwisterFast rand) {
        Nanson p = new Nanson();
        //set up candidates
        Set<Candidate> cans = new HashSet<>();
        Set<IVoter> voters = new HashSet<>();
        
        for (int i = 0; i < can; i++)
        {
            cans.add(new Candidate(String.valueOf(i)));
        }
        
        for (int i = 0; i < vot; i++)
        {
            voters.add(new BordaVoter(String.valueOf(i), cans));
        }
        
        p.setup(cans, voters);
        System.out.println(p.vote());
        
    }
     
     private static void testPair(int can, int vot, MersenneTwisterFast rand) {
         PairwiseElim p = new PairwiseElim();
        //set up candidates
        Set<Candidate> cans = new HashSet<>();
        Set<IVoter> voters = new HashSet<>();
        
        p.setComparator(new Comparator<Candidate>() {

             @Override
             public int compare(Candidate o1, Candidate o2) {
                 return o1.getID().compareTo(o2.getID());
             }
         });
        
        
        for (int i = 0; i < can; i++)
        {
            cans.add(new Candidate(String.valueOf(i)));
        }
        
        for (int i = 0; i < vot; i++)
        {
            voters.add(new PairwiseVoter(String.valueOf(i), cans));
        }
        // Print the ordering
        List<Candidate> sortCan = new ArrayList<>(cans);
        Collections.sort(sortCan, new Comparator<Candidate>() {

             @Override
             public int compare(Candidate o1, Candidate o2) {
                 return o1.getID().compareTo(o2.getID());
             }
         });
        
        for(Candidate c : sortCan)
        {
            System.out.print(c.getID() + ", ");
        }
        System.out.println("");
        
        p.setup(cans, voters);
        System.out.println(p.vote());
        
    }
     
     
     private static void testSchulze(int can, int vot, MersenneTwisterFast rand) {
         Schulze p = new Schulze();
        Set<Candidate> cans = new HashSet<>();
        Set<IVoter> voters = new HashSet<>();
        
        for (int i = 0; i < can; i++)
        {
            cans.add(new Candidate(String.valueOf(i)));
        }
        
        for (int i = 0; i < vot; i++)
        {
            voters.add(new IRVVoter(String.valueOf(i), cans));
        }
        
        p.setup(cans, voters);
        System.out.println(p.vote());
        
    }
     
     private static void testRankedPairs(int can, int vot, MersenneTwisterFast rand) {
         RankedPairs p = new RankedPairs();
        Set<Candidate> cans = new HashSet<>();
        Set<IVoter> voters = new HashSet<>();
        
        for (int i = 0; i < can; i++)
        {
            cans.add(new Candidate(String.valueOf(i)));
        }
        
        for (int i = 0; i < vot; i++)
        {
            voters.add(new IRVVoter(String.valueOf(i), cans));
        }
        
        p.setup(cans, voters);
        System.out.println(p.vote());
        
    }
     
     private static void testMajority(int can, int vot, MersenneTwisterFast rand) {
        Majority p = new Majority();
        //set up candidates
        Set<Candidate> cans = new HashSet<>();
        Set<IVoter> voters = new HashSet<>();
        
        for (int i = 0; i < can; i++)
        {
            cans.add(new Candidate(String.valueOf(i)));
        }
        
        for (int i = 0; i < vot; i++)
        {
            voters.add(new PluralityVoter(can, rand, String.valueOf(i)));
        }
        
        p.setup(cans, voters);
        System.out.println(p.vote());
        
    }
    private static void testMinimax(int can, int vot, MersenneTwisterFast rand) {
         Minimax p = new Minimax();
        Set<Candidate> cans = new HashSet<>();
        Set<IVoter> voters = new HashSet<>();
        
        for (int i = 0; i < can; i++)
        {
            cans.add(new Candidate(String.valueOf(i)));
        }
        
        for (int i = 0; i < vot; i++)
        {
            voters.add(new IRVVoter(String.valueOf(i), cans));
        }
        
        p.setup(cans, voters);
        System.out.println(p.vote());
        
    }
    
    private static void testKemenyYoung(int can, int vot, MersenneTwisterFast rand) {
         KemenyYoung p = new KemenyYoung();
        Set<Candidate> cans = new HashSet<>();
        Set<IVoter> voters = new HashSet<>();
        
        for (int i = 0; i < can; i++)
        {
            cans.add(new Candidate(String.valueOf(i)));
        }
        
        for (int i = 0; i < vot; i++)
        {
            voters.add(new IRVVoter(String.valueOf(i), cans));
        }
        
        p.setup(cans, voters);
        System.out.println(p.vote());
        
    }
    
}
