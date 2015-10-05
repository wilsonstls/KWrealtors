package kwrealtors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
*wilsonstls
*9/30/15
*subclass for an agent's portal. 
*/

public class ManagerPortal extends Portal
{ 

   {

       P.setLayout(new BoxLayout(P, BoxLayout.PAGE_AXIS));
       P.add(bL1);
       P.add(bL2);
       P.add(bL3);
       P.add(L2);
       P.add(bL4);
       P.add(bL5);
       P.add(L5);
       P.add(B5);

       /** list all current employees */
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


       /* Listing of all available property for sale */
       P.add(bL8);
       P.add(bL9);
       P.add(L10);
       P.add(B10);
       B10.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
               KwRealtorsApp kwrealtorsApp = (KwRealtorsApp) context.getBean("kwrealtorsApp");
               try {
                   kwrealtorsApp.fetchAllProperty();
               } catch (Exception e1) {
                   e1.printStackTrace();
               }
           }
       });

       P.add(bL12);
       P.add(L11);
       P.add(B11);
       B11.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
               KwRealtorsApp kwrealtorsApp = (KwRealtorsApp) context.getBean("kwrealtorsApp");
               try {
                   kwrealtorsApp.fetchPropertyByPrice();
               } catch (Exception e1) {
                   e1.printStackTrace();
               }
           }
       });

       P.add(bL13);
       P.add(L12);
       P.add(B12);
       B12.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
               KwRealtorsApp kwrealtorsApp = (KwRealtorsApp) context.getBean("kwrealtorsApp");
               try {
                   kwrealtorsApp.fetchPropertyByAgent();
               } catch (Exception e1) {
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
