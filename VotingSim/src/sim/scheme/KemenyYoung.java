/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.scheme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import sim.util.Candidate;
import sim.util.Ranking;

/**
 *
 * @author drew
 */
public class KemenyYoung extends Comparison{

    
    
    
    List<Candidate> ranked;
    int score;
    
    Ranking<Candidate> r;
    
    @Override
    public Ranking<Candidate> vote() {
        Map<LinkedList<Candidate>, Integer> dg = getDirectedGraph();
        ranked = new ArrayList<>();
        score = 0;
        
        permute(new ArrayList<>(cans), 0,dg);
        r = new Ranking<>();
        
        for (int i = ranked.size() - 1; i >= 0; i--)
        {
            r.pushCandidate(ranked.get(i));
        }
        
        /*
        for (int i = 0; i < ranked.size(); i++)
        {
            System.out.println(r.getRankAt(i) + "  " + ranked.get(i) + " " + score + " " + generateScore(ranked, dg));
        }
        */
        
        
        return r;
        
    }
  private void permute(List<Candidate> arr, int curStart, Map<LinkedList<Candidate>, Integer> dg){
        for(int i = curStart; i < arr.size(); i++){
            Collections.swap(arr, i, curStart);
            permute(arr, curStart+1, dg);
            Collections.swap(arr, curStart, i);
        }
        if (curStart == arr.size() -1){
            int curScore = generateScore(arr, dg);
            if (curScore > score)
            {
                score = curScore;
                ranked = new ArrayList<>(arr);
            }
        }
    }
    
    
    
    @Override
    public Ranking getResults() {
        return r;
    }

    private int generateScore(List<Candidate> arr, Map<LinkedList<Candidate>, Integer> dg) {
        int curScore = 0;
        
        for (int i = 0; i < arr.size(); i++)
        {
            for (int j = i + 1; j < arr.size(); j++)
            {
                
                    LinkedList<Candidate> c2c1 = new LinkedList<>();
                    
                    c2c1.push(arr.get(i));
                    c2c1.push(arr.get(j));
                    curScore += dg.get(c2c1);
                    
                    
                    
                
            }
        }
        return curScore;
        
    }
    
}
