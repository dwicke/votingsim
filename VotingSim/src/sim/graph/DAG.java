/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 * unweighted DAG data structure
 * @author drew
 */
public class DAG <T> {
    
    // out -----> in
    // so out has a list of in nodes
    // and in maps the in node 
    // to the list of out nodes coming in to it
    HashMap<T, List<T> > out, in;
    
    List<T> oldOutList, oldInList;
    
    public DAG()
    {
        out = new HashMap<>();
        in = new HashMap<>();
    }
    
    
    /**
     * Will only add the connection
     * if by drawing and edge from 
     * start to end will maintain the 
     * DAG structure
     * @param e 
     */
    public void addEdge(T start, T end)
    {
        // copy the out and in list
        HashMap<T, List<T> > newOut, newIn;
        
        newOut = new HashMap<>();
        newIn = new HashMap<>();
        
        for (Entry<T, List<T> > e : out.entrySet())
        {
            newOut.put(e.getKey(), new ArrayList<>(e.getValue()));
        }
        
        for (Entry<T, List<T> > e : in.entrySet())
        {
            newIn.put(e.getKey(), new ArrayList<>(e.getValue()));
        }
        
        // add the start and end node to the new structures
        insertEnd(end, start, newIn);
        insertStart(start, end, newOut);
        
        List<T> noIn = noIncoming(newIn, newOut);
        // so loop over nodes that do not have
        // any incoming edges (this means that
        // the 
        while(!noIn.isEmpty())
        {
            // this node is not a key in newIn
            // curOut ----> {n,m,l}
            T curOut = noIn.remove(noIn.size() - 1);
            if (newOut.get(curOut) != null)
            {
                Iterator<T> it = newOut.get(curOut).iterator();
                // loop over all of the nodes that curOut connect to
                while(it.hasNext())
                {
                    // curOut ---> curIn
                    // must remove the connection
                    // so first remove from curOut list of in nodes
                    T curIn = it.next();
                    it.remove();
                    // then remove curIn's in connection
                    // from curOut
                    newIn.get(curIn).remove(curOut);
                    // if curIn has no incoming edges
                    // then it is empty 

                    if (newIn.get(curIn).isEmpty())
                    {
                        // I will remove
                        // curIn from newIn since no longer
                        // any connections into it
                        newIn.remove(curIn);

                        // and then add curIn to the noIn list
                        // since it has no incoming edges
                        noIn.add(curIn);
                    }

                }
            }
            
        }
        
        if (newIn.isEmpty())
        {
            // we have a dag with the new nodes still so add them
            insertEnd(end, start, in);
            insertStart(start, end, out);
        }
        
        // do nothing.
        
    }
    
    public List<T> getNoIncoming()
    {
        return noIncoming(in, out);
    }
    
    private List<T> noIncoming(HashMap<T, List<T> > inNodes, HashMap<T, List<T> > outNodes)
    {
        ArrayList<T> noneInc = new ArrayList<>();
        // so loop over the nodes that have edges coming out of them
        for(T n : outNodes.keySet())
        {
            // make sure that no edges enter it
            if (!inNodes.containsKey(n))
            {
                noneInc.add(n);
            }
        }
        
        return noneInc;
    }
    

    private void insertStart(T start, T end, HashMap<T, List<T> > outNode) {
        if (!outNode.containsKey(start))
        {
            oldOutList = null;
            ArrayList<T> newL = new ArrayList<>();
            newL.add(end);
            outNode.put(start, newL);
        }
        else
        {
            oldOutList = new ArrayList<>(outNode.get(start));
            outNode.get(start).add(end);
        }
    }

    private void insertEnd(T end, T start, HashMap<T, List<T> > inNode) {
        if (!inNode.containsKey(end))
        {
            oldInList = null;
            ArrayList<T> newL = new ArrayList<>();
            newL.add(start);
            inNode.put(end, newL);
        }
        else
        {
            oldInList = new ArrayList<>(inNode.get(end));
            inNode.get(end).add(start);
        }
    }

    public void printGraph() {
        System.out.println("DAG Printing:");
        for (Entry<T, List<T> > en : out.entrySet())
        {
            for (T tIn : en.getValue())
            {
                System.out.println(en.getKey() + " ------> " + tIn);
            }
            
        }
        
        System.out.println("End DAG Pringing");
    }
    
    
    
    
    
}
