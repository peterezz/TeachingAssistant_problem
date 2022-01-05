/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teachingassistant_problem;

import GUI.Home;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Peter
 */
public class Student {

    private final ReentrantLock LOCK;

    private volatile University helwan = University.getInstance();
    private boolean ishelped;
    private int ID;
    private JTable table;
    private DefaultTableModel model1;

    public Student(int studentid) {
        LOCK = new ReentrantLock();
        this.ID = studentid;
        System.out.println("student " + ID + " wants help");
    }

    public Runnable seekhelp = new Runnable() {
        @Override
        public void run() {
            while (ishelped == false) {
                needHelp();
                SleepUtilities.nap();
                leaveUniversity();
            }
            System.out.println("student " + ID + " is bieng helped. \n ___________________________");
            helwan.data.add("student " + ID + " is bieng helped.");
            if (helwan.nstudent - ID == 1) {
                Home.setValuesTOtable();
            }

        }

    };

    public boolean isIshelped() {
        return ishelped;
    }

    private void needHelp() {
        checkmyself();
    }

    private void leaveUniversity() {
        LOCK.lock();
        try {
            for (int i = 0; i < helwan.teachers.length; i++) {
                if (helwan.teachers[i].StudentID == this.ID) {
                    helwan.teachers[i].takeNap();
                    System.out.println("teacher " + i + " is napping .");
                    helwan.data.add("teacher " + i + " is napping .");
                    this.ishelped = true;

                }
            }
            SleepUtilities.nap();
        } finally {
            LOCK.unlock();
        }

    }

    private void checkmyself() {
        LOCK.lock();
        try {
            int emptyChairCount = 0;
            boolean TA_Found = false;
            for (chair chaiir : helwan.chairs) {
                if (chaiir.studentID == this.ID) {
                    if (SeeAvailableTA() == true) {
                        TA_Found = true;

                        break;

                    }
                }
                if (chaiir.isEmpty) {
                    emptyChairCount++;
                }
            }
            if (!TA_Found) {
                if (emptyChairCount == helwan.chairs.length) {
                    if (!SeeAvailableTA()) {
                        setOnA_Chair();

                    }

                } else if (emptyChairCount == 0) {
                    comeLater();

                } else {
                    setOnA_Chair();
                }
            }

        } finally {
            LOCK.unlock();
        }

    }

    private void comeLater() {
        LOCK.lock();
        try {

            SleepUtilities.nap();
        } finally {
            LOCK.unlock();
        }

    }

    private void setOnA_Chair() {
        LOCK.lock();
        try {
            boolean Found_Chair = false;
            for (int i = 0; i < helwan.chairs.length; i++) {
                if (helwan.chairs[i].isEmpty == true) {
                    helwan.chairs[i].studentID = this.ID;
                    helwan.chairs[i].isEmpty = false;
                    System.out.println("Student " + this.ID + " is sitting on chair. " + i + " .");
                    helwan.data.add("Student " + this.ID + " is sitting on chair " + i + " .");

                    Found_Chair = true;
                    break;

                }
            }
            if (!Found_Chair) {
                comeLater();
            }
        } finally {
            LOCK.unlock();
        }
    }

    private boolean SeeAvailableTA() {
        for (int i = 0; i < helwan.teachers.length; i++) {
            if (helwan.teachers[i].busy != true) {
                helwan.teachers[i].StudentID = this.ID;
                helwan.teachers[i].busy = true;
                System.out.println("teacher " + i + " is helping student " + this.ID + " .");
                helwan.data.add("teacher " + i + " is helping student " + this.ID + " .");

                return true;
            }
        }
        return false;
    }

}
