package sim.util;


import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.TreeSet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author drew
 */
public class Ranking<T> implements Cloneable{

    LinkedList<T> ranks;

    public Ranking() {
        ranks = new LinkedList<>();
    }
    
    public static <K> Ranking<K> buildRanking(Collection<K> cans, Comparator<K> ranker)
    {
        TreeSet<K> sorted = new TreeSet<>(ranker);
        sorted.addAll(cans);
        Ranking<K> result = new Ranking<>();
        //result.ranks.addAll(sorted);
        
        for (K c: sorted)
        {
            result.pushCandidate(c);
        }
        
        
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //To change body of generated methods, choose Tools | Templates.
        Ranking<T> r = (Ranking<T>) super.clone();
        
        r.ranks = new LinkedList<>(ranks);//(LinkedList<T>) this.ranks.clone();
        
        
        return r;
    }
    
    
    
    /**
     * Make a new first place.
     * Push candidates in the order from nth place to first
     * @param cand 
     */
    public void pushCandidate(T cand)
    {
        ranks.push(cand);
    }
    
    /**
     * returns the top candidate
     * @return 
     */
    public T popCandidate()
    {
        return ranks.pop();
    }
    
    public T firstCandidate()
    {
        return ranks.peek();
    }
    
    
    public void update(Collection<T> data)
    {
        Iterator<T> it = ranks.iterator();
        while(it.hasNext())
        {
            if (!data.contains(it.next()))
            {
                it.remove();
            }
        }
    }
    
    /**
     * returns the rank of the queried item.
     * If elt is in this returns the ranking [0 - n-1] where n is the number
     * of elements in this.
     * returns -1 if elt is not a part of this
     * @param elt
     * @return 
     */
    public int getRank(T elt)
    {
        return ranks.indexOf(elt);
    }
    
    /**
     * Returns the Value at rank i.
     * @param i
     * @return 
     */
    public T getRankAt(int i)
    {
        if (i < ranks.size())
            return ranks.get(i);
        return null;
    }
    
    public T getLast()
    {
        return ranks.peekLast();
    }
    
    public Ranking<T> reverseRanks()
    {
        Collections.reverse(ranks);
        return this;
    }
    
    @Override
    public String toString() {
        return ranks.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Ranking other = null;
        if (obj instanceof Ranking)
            other = (Ranking)obj;
        else
            return false;
        
        return ranks.equals(other.ranks);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.ranks);
        return hash;
    }

    public boolean isEmpty() {
        return ranks.isEmpty();
    }

    public int numRanked() {
       return ranks.size();
    }

    public void swap(String newRank, String oldRank) {
        
        T oldT = null, newT = null;
        int oldTi = 0, newTi = 0, count = 0;
        for (T ca : ranks)
        {
            if (ca.toString().equals(newRank))
            {
                newT = ca;
                newTi = count;
            }
            else if (ca.toString().equals(oldRank))
            {
                oldT = ca;
                oldTi = count;
            }
            count++;
        }
        
        ranks.set(newTi,oldT );
        ranks.set(oldTi, newT );
        System.out.println(firstCandidate().toString());
        
    }
    
    
    
    
    
}
