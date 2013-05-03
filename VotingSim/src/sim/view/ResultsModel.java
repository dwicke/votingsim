/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.view;

import java.util.List;
import java.util.Map;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import sim.util.Candidate;
import sim.util.Ranking;

/**
 *
 * @author drew
 */
public class ResultsModel extends AbstractTableModel{

    List<String> schemes;
    List< Ranking<Candidate> > results;
    int numCans;
    
    public void updateData(List<String> schemes, List< Ranking<Candidate> > results, int numCans)
    {
        this.schemes = schemes;
        this.results = results;
        this.numCans = numCans;
    }
    
    @Override
    public int getRowCount() {
        return numCans;
    }

    @Override
    public int getColumnCount() {
        return schemes.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return schemes.get(columnIndex);
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (results.get(columnIndex).numRanked() > rowIndex)
        {
            //String s = results.get(columnIndex).getRankAt(Math.abs( (results.get(columnIndex).numRanked() - 1) - rowIndex)).getID();
            
            
            Candidate s = results.get(columnIndex).getRankAt(rowIndex);
            
            if (s == null)
            {
                return "";
            }
            return s.getID();
        }
        return "";
    }

    
}
