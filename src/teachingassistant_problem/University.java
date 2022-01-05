/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teachingassistant_problem;

import java.util.ArrayList;


/**
 *
 * @author Peter
 */
public class University {
    
    public teachingAssistant[] teachers;
    public chair[] chairs;
    public static ArrayList<String> data=new ArrayList<String>() ;
    public int nstudent;
   
    

    
    private static University instance = null;
    
    public static synchronized University getInstance() {
        if (instance == null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            instance = new University();
        }
        return instance;
    }
    
    public void setnTAs(int nTAs) {
        teachers = new teachingAssistant[nTAs];
        for (int i = 0; i < nTAs; i++) {
            teachers[i] = new teachingAssistant();
            System.out.println("teacher " + i + " is in the university.");
        }
        System.out.println("___________________________");
    }
    
    public void setnChairs(int nChairs) {
        this.chairs = new chair[nChairs];
         for (int i = 0; i < nChairs; i++) {
            chairs[i] = new chair();
        }
         System.out.println("there are "+nChairs+" empty chairs. \n ___________________________");
       
    }
    
    private University() {
    }
    
}
