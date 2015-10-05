package kwrealtors;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 *wilsonstls
 *9/30/15
 *subclass for an agent's portal.
 */

public class ClericalPortal extends Portal {

        {

            P.setLayout(new BoxLayout(P, BoxLayout.PAGE_AXIS));
            P.add(bL1);
            P.add(bL2);
            P.add(bL3);
            P.add(L4);
            P.add(bL4);
            P.add(bL5);
            P.add(L5);
            P.add(B5);

            /** LIST all current employees */
            B5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)  {

                    ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
                    KwRealtorsApp kwrealtorsApp = (KwRealtorsApp) context.getBean("kwrealtorsApp");
                    try {
                        kwrealtorsApp.fetchAllEmployee();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });

            P.add(bL6);
            P.add(L6);
            P.add(B6);

            /* ADD to the Employee table */
            B6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("\nAdd an employee to the database\n");
                    ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
                    KwRealtorsApp kwrealtorsApp = (KwRealtorsApp) context.getBean("kwrealtorsApp");
                    try {
                        kwrealtorsApp.employeeAddEmployee();
                    } catch (Exception e1) {
                        System.out.println("\nThere was a problem with your entry!");
                        e1.printStackTrace();
                    }
                }
            });

            P.add(bL7);
            P.add(bL8);
            P.add(L8);
            P.add(B8);
            /* UPDATE the Employee table */
            B8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        System.out.println("\nUpdate an employee in the database\n");
                        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
                        KwRealtorsApp kwrealtorsApp = (KwRealtorsApp) context.getBean("kwrealtorsApp");
                        try {
                            kwrealtorsApp.employeeUpdateEmployee();
                        } catch (Exception e1) {
                            System.out.println("\nThere was a problem with your entry!");
                            e1.printStackTrace();
                        }
                    }
                });

            P.add(bL7);
            P.add(L7);
            P.add(B7);

            /* DELETE from the Employee table */
            B7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("\nDelete an employee from the database\n");
                    ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
                    KwRealtorsApp kwrealtorsApp = (KwRealtorsApp) context.getBean("kwrealtorsApp");
                    try {
                        kwrealtorsApp.employeeDeleteEmployee();
                    } catch (Exception e1) {
                        System.out.println("\nThere was a problem with your entry!");
                        e1.printStackTrace();
                    }
                }
            });

            P.add(bL10);
            P.add(bL11);
            P.add(L1);

            P.add(B1);
            B1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("*** Ending session ");
                    System.exit(0);
                }
            });


            //create the new window
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().add(P);
            frame.setVisible(true);

        }
}


