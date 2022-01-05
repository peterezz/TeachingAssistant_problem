/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teachingassistant_problem;

/**
 *
 * @author Peter
 */
public class teachingAssistant {

    public boolean busy;
    public int StudentID;
    boolean alreadyNapping;
   

    public teachingAssistant() {
        this.busy = false;
        this.StudentID = -1;
        alreadyNapping=false;
    }

    public void helpingStudent() {
        this.busy = true;
    }

    public void takeNap() {
        this.busy = false;
        this.StudentID = -1;
    }

}
