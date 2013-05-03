/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.view;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import sim.util.Candidate;
import sim.util.Ranking;

/**
 *
 * @author drew
 */
public class VoterModel extends AbstractTableModel {

    private List<Candidate> cans;
    private List<Ranking<Candidate> > rankings;
    
    public void updateData(List<Candidate> cans, List<Ranking<Candidate> > rankings)
    {
        this.cans = cans;
        this.rankings = rankings;
    }
    
    public List<Ranking<Candidate> > getRankings()
    {
        return rankings;
    }
    
    public List<Candidate> getCans()
    {
        return cans;
    }
    
    @Override
    public int getRowCount() {
        return rankings.size();// number of voters
    }

    @Override
    public int getColumnCount() {
        return cans.size() + 1; // number of candidates plus one that says the index of the voter
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0)
        {
            return "Voter ID / Candidate Rank";
        }
        return "" + columnIndex;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //To change body of generated methods, choose Tools | Templates.
        if (columnIndex > 0 && columnIndex < getColumnCount())
            return true;
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
        {
            return rowIndex;
        }
        // the cells contain the id of th candidate that is ranked there
        return rankings.get(rowIndex).getRankAt(columnIndex - 1).getID();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (rankings == null)
            super.setValueAt(aValue, rowIndex, columnIndex);
        else if (columnIndex != 0) {
            // id of the candidate who is taking over
            String candID = (String) aValue;
            // get who is ranked their now
            String oldCand = (String) getValueAt(rowIndex, columnIndex);
            
            rankings.get(rowIndex).swap(candID, oldCand);
        }
    }

    
    
    
}
