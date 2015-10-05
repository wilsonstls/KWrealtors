package kwrealtors;

import org.hibernate.annotations.Where;
import org.hibernate.sql.Select;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.criteria.From;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

/**
 *wilsonstls
 *9/30/15
 *subclass for an agent's portal.
 */

public class AgentPortal extends Portal
{

    {

        P.setLayout(new BoxLayout(P, BoxLayout.PAGE_AXIS));
        P.add(bL1);
        P.add(bL2);
        P.add(bL3);
        P.add(L3);

        P.add(bL14);
        P.add(L13);
        P.add(B13);
          /* fetch an agent's property listings */
        B13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
                KwRealtorsApp kwrealtorsApp = (KwRealtorsApp) context.getBean("kwrealtorsApp");
                try {
                    kwrealtorsApp.fetchAgentProperty();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        P.add(bL6);
        P.add(L14);
        P.add(B14);

            /* ADD to the Property table */
        B14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("\nAdd a property listing to the database\n");
                ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
                KwRealtorsApp kwrealtorsApp = (KwRealtorsApp) context.getBean("kwrealtorsApp");
                try {
                    kwrealtorsApp.propertyAddProperty();
                } catch (Exception e1) {
                    System.out.println("\nThere was a problem with your entry!");
                    e1.printStackTrace();
                }
            }
        });

        P.add(bL7);
        P.add(bL8);
        P.add(L16);
        P.add(B16);
            /* UPDATE the Property table */
        B16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("\nUpdate a property listing \n");
                ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
                KwRealtorsApp kwrealtorsApp = (KwRealtorsApp) context.getBean("kwrealtorsApp");
                try {
                    kwrealtorsApp.propertyUpdateProperty();
                } catch (Exception e1) {
                    System.out.println("\nThere was a problem with your entry!");
                    e1.printStackTrace();
                }
            }
        });

        P.add(bL7);
        P.add(L15);
        P.add(B15);

            /* DELETE from the Property table */
        B15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("\nDelete a property listing \n");
                ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
                KwRealtorsApp kwrealtorsApp = (KwRealtorsApp) context.getBean("kwrealtorsApp");
                try {
                    kwrealtorsApp.propertyDeleteProperty();
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
                System.out.println("*** Ending this session ");
                System.exit(0);
            }
        });


        //create the new window
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(P);
        frame.setVisible(true);

    }
}

