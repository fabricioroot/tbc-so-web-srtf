package manager;

import java.util.Vector;
import bean.Process;

/**
 *
 * @author Fabricio Reis
 */
public class Calculator {
    
    public Calculator() {
    }    

    /* This method calculates the waiting time and the turn around of the process that is going to be executed according
     * the algorithm SRTF (shortest remaining time first).
     */
    public Process waitingTimeAndTurnAround (Vector<Process> processes, int time, int maximum) {
        Process out;
        float aux = maximum + 1;
        int position = 0;
        
        // It finds the shortest burst time (life time)
        for(int i = 0; i <= (processes.size() - 1); i++) {
            if(aux > processes.elementAt(i).getLifeTime()) {
                aux = processes.elementAt(i).getLifeTime();
                position = i;
                if(aux == 1) {
                    i = processes.size();
                }
            }
        }        
        out = new Process(processes.elementAt(position));
        // Waiting time = time (clock) - creation time
        out.setWaitingTime(time - out.getCreationTime());
        // Turn Around = waiting time + burst time
        out.setTurnAround(out.getWaitingTime() + out.getLifeTime());
        return out;
    }
    
    /* This method calculates the waiting time and the turn around of the process that is going to be executed according
     * the algorithm SRTF (shortest remaining time first), but only for those which are in the wainting processes queue.
     */
    public Vector<Process> waitingTimeAndTurnAround_2 (Vector<Process> waitingProcesses, Vector<Process> reportBase, int time) {
        Process aux; 
        int position = 0;  // This is used to save the position where the 'process' (equals to 'aux') in 'reportBase' is
        float floatAux = 0;
        Vector<Process> reportBaseAux = new Vector<Process>(reportBase);
        
        // It catchs the last element of 'waintingProcesses', that according to the algorithm has the highest priority
        aux = new Process(waitingProcesses.lastElement());
        floatAux = (float)time - aux.getTimeWhenIncludeInWaitingState();
        
        // It finds the element in the reportBase vector
        for(int i = 0; i <= (reportBaseAux.size() - 1); i++) {
            if(aux.getId() == reportBaseAux.elementAt(i).getId()) {
                position = i;
                i = reportBaseAux.size();
            }
        }

        aux.setWaitingTime(floatAux + reportBaseAux.elementAt(position).getWaitingTime());
        // Turn Around = waiting time + burst time
        aux.setTurnAround(aux.getWaitingTime() + aux.getSize());

        reportBaseAux.elementAt(position).setWaitingTime(aux.getWaitingTime());
        reportBaseAux.elementAt(position).setTurnAround(aux.getTurnAround());
        return reportBaseAux;
    }
    
    
    /* This method calculates the average of the waiting times.
     */
    public float averageWaitingTime (Vector<Process> processes) {
        float aux = 0;
        for (int i = 0; i <= (processes.size() - 1); i++) {
            aux += processes.elementAt(i).getWaitingTime();
        }
        aux = aux / processes.size();
        return aux;
    }
    
    /* This method calculates the average of the turns around.
     */
    public float averageTurnAround (Vector<Process> processes) {
        float aux = 0;
        for (int i = 0; i <= (processes.size() - 1); i++) {
            aux += processes.elementAt(i).getTurnAround();
        }
        aux = aux / processes.size();
        return aux;
    }
}