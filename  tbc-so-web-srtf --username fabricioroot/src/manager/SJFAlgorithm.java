package manager;

import java.util.Vector;
import bean.Process;

/**
 *
 * @author Fabrício Reis
 */
public class SJFAlgorithm {
    
    public SJFAlgorithm() {
    }
    
    /* This method implements the algorithm SJF (Shortest Job First) of processes management.
     * It returns the position where the process that has the shortest execution time is.
     */
    public int toExecute(Vector<Process> processes, int maximum) {
        int out = 0;
        float aux = maximum + 1;
        
        for(int i = 0; i <= (processes.size() - 1); i++) {
            if(aux > processes.elementAt(i).getLifeTime()) {
                aux = processes.elementAt(i).getLifeTime();
                out = i;
                if(aux == 1) {
                    i = processes.size();
                }
            }
        }
        return out;
    }
    
    /*
     * This method finds and stores in a Vector<Integer> the positions of the possible processes being dispatched.
     * The last position of the returned vector stores the position of the shortest process at the Vector 'processes'
     */
    public Vector<Integer> findPositionsPossibleProcesses(Vector<Process> processes, int maximum) {
        Vector<Integer> positions = new Vector<Integer>();
        float aux = maximum + 1;
        
        for(int i = 0; i <= (processes.size() - 1); i++) {
            if(aux > processes.elementAt(i).getLifeTime()) {
                aux = processes.elementAt(i).getLifeTime();
                positions.add(i);
                if(aux == 1) {
                    i = processes.size();
                }
            }
        }
        return positions;
    }
}
