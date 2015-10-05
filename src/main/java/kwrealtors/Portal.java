package kwrealtors;


import javax.swing.*;

public abstract class Portal
{

            JFrame frame = new JFrame("Employees Portal ");
            JPanel P = new JPanel();

            JButton B1 = new JButton("press");  // to quit session
            JButton B5 = new JButton("press");  // list of employees
            JButton B6 = new JButton("press");  // add to employee db
            JButton B7 = new JButton("press");  // delete from employee db
            JButton B8 = new JButton("press");  // update employee db
            JButton B10 = new JButton("press"); // property list
            JButton B11 = new JButton("press"); // property list by price
            JButton B12 = new JButton("press"); // property list by agent
            JButton B13 = new JButton("press"); // property listings for an agent
            JButton B14 = new JButton("press");  // add to property db
            JButton B15 = new JButton("press");  // delete from property db
            JButton B16 = new JButton("press");  // update property db

            JLabel bL1 = new JLabel("\n");
            JLabel bL2 = new JLabel("\n");
            JLabel bL3 = new JLabel("\n");
            JLabel bL4 = new JLabel("\n");
            JLabel bL5 = new JLabel("\n");
            JLabel bL6 = new JLabel("\n");
            JLabel bL7 = new JLabel("\n");
            JLabel bL8 = new JLabel("\n");
            JLabel bL9 = new JLabel("\n");
            JLabel bL10 = new JLabel("\n");
            JLabel bL11 = new JLabel("\n");
            JLabel bL12 = new JLabel("\n");
            JLabel bL13 = new JLabel("\n");
            JLabel bL14 = new JLabel("\n");

            JLabel L1 = new JLabel("    QUIT");
            JLabel L2 = new JLabel(" As the Manager you are allow to perform these actions: ");
            JLabel L3 = new JLabel(" As an Agent you are allow to perform these actions:");
            JLabel L4 = new JLabel(" As a Clerical you are allow to perform these actions:");
            JLabel L5 = new JLabel("     LIST all current employees");
            JLabel L6 = new JLabel("     ADD employees to the database");
            JLabel L7 = new JLabel("     DELETE employees from the database");
            JLabel L8 = new JLabel("     UPDATE an Employee's information");
            JLabel L10 = new JLabel("    LIST all available property");
            JLabel L11 = new JLabel("    LIST property by price (highest to lowest)");
            JLabel L12 = new JLabel("    LIST property by agent");
            JLabel L13 = new JLabel("    LIST out your property listings");
            JLabel L14 = new JLabel("    ADD property to the database");
            JLabel L15 = new JLabel("    DELETE property from the database");
            JLabel L16 = new JLabel("    UPDATE a Property listing");


}
