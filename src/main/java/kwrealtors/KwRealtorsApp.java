
package kwrealtors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext; 
import org.springframework.context.support.ClassPathXmlApplicationContext; 
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

import java.io.*;
import java.net.URL;
import java.util.List;

/** 
  * Author: wilsonstls
  * Date: 11/11/15
  * This app instigates a in-house portal to be used by a real estate company for its employees.
  * Employees will sign in with a valid ID and password. After signing in the user will then be directed
  * to their portal page based on their job type as set up in the Employee db table.
  */

@Component 
public class KwRealtorsApp {


    public static void main(String[] varArgs) throws Exception {

        /** sign in using your employee ID & password */
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        SignInPortal signInPortal = (SignInPortal) context.getBean("signInPortal");
        signInPortal.signIn();

    }


    @Autowired
    private KwRealtorsDao kwrealtorsDao;



    /** fetches all active employees from db */
    public void fetchAllEmployee() throws Exception {

         @SuppressWarnings("unchecked")
         List<Employee> employeeList = kwrealtorsDao.getEmployeeAllEmployees();

        /** creates a table model for the implementation of the RowTableModel */
        final BeanTableModel btModel = new BeanTableModel(Employee.class, employeeList);

        /** sort by column name in order to set the same index order for every run */
        btModel.sortColumnNames();

        /** create the table */
        JTable table = new JTable(btModel);

        /** move & remove columns, after they have been sorted, to appear as you want in the Jtable */
        table.moveColumn(0,5);  //empl_id moves to 5th index col in Jtable
        table.moveColumn(3,0);  //last name moves to 0 "  "  will appear 1st - First name doesn't move
        table.moveColumn(3,2);  // move job type to appear after first name
        table.moveColumn(4,3);  // move license no to appear after job type
        table.removeColumn( table.getColumn( "Status" ) );  // no need to show this column

        JScrollPane scrollPane = new JScrollPane(table);

        final JFrame frame = new JFrame("KW realtors");
        JLabel tHeading = new JLabel("Active Employees");
        tHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT, 18));
        JLabel bHeading = new JLabel("JobType = A: Agent  C: Clerical  M: Manager");
        bHeading.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);   // maximizes to full screen size
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(tHeading, BorderLayout.PAGE_START);
        frame.getContentPane().add(bHeading, BorderLayout.PAGE_END);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);

    }  // closes fetchAllEmployee


    /** list all property listings for KW Realtors */
    public void fetchAllProperty() throws Exception {

        @SuppressWarnings("unchecked")
        List<Property> propertyList = kwrealtorsDao.getPropertyAllProperty();

        /** creates a table model for the implementation of the RowTableModel */
        final BeanTableModel btModel = new BeanTableModel(Property.class, propertyList);
        /** sort by column name in order to set the same index order for every run */
        btModel.sortColumnNames();
        /** create the table */
        JTable table = new JTable(btModel);

        /** move & remove columns to appear as you want in the Jtable */
        table.moveColumn(6,0);  //listing ID moves to 0 - City is now 1; Street is 2
        table.removeColumn( table.getColumn( "Addr State" ) );  // no need to show
        table.removeColumn( table.getColumn( "Addr Zip" ) );  // no need to show this column
        table.removeColumn( table.getColumn( "Beds" ) );  // no need to show this column
        table.moveColumn(5,3);  // move price to appear after street
        table.moveColumn(5,4);  // move listing date to appear after price - License No is now last
        table.removeColumn( table.getColumn( "Sq Ft" ) );  // no need to show this column

        JScrollPane scrollPane = new JScrollPane(table);

        final JFrame frame = new JFrame("KW realtors");
        JLabel tHeading = new JLabel("Available Properties");
        tHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT, 18));

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);   // maximizes to full screen size
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(tHeading, BorderLayout.PAGE_START);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);

    }


    public void fetchPropertyByPrice() throws Exception {

        @SuppressWarnings("unchecked")
        List<Property> propertyList = kwrealtorsDao.getPropertyByPrice();

        /** creates a table model for the implementation of the RowTableModel */
        final BeanTableModel btModel = new BeanTableModel(Property.class, propertyList);
        /** sort by column name in order to set the same index order for every run */
        btModel.sortColumnNames();
        /** create the table */
        JTable table = new JTable(btModel);

        /** move & remove columns to appear as you want in the Jtable */
        table.moveColumn(8,0);  //Price moves to 0 - City is now 1; Street is 2
        table.removeColumn( table.getColumn( "Addr State" ) );  // no need to show
        table.removeColumn( table.getColumn( "Addr Zip" ) );  // no need to show this column
        table.removeColumn( table.getColumn( "Beds" ) );  // no need to show this column
        table.moveColumn(5,3);  // move price to appear after street
        table.moveColumn(5,4);  // move listing date to appear after price - License No is now last
        table.removeColumn( table.getColumn( "Sq Ft" ) );  // no need to show this column

        JScrollPane scrollPane = new JScrollPane(table);

        final JFrame frame = new JFrame("KW realtors");
        JLabel tHeading = new JLabel("Available Properties by Price");
        tHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT, 18));

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);   // maximizes to full screen size
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(tHeading, BorderLayout.PAGE_START);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);

    }  // closes fetchPropertyByPrice


    /** property listings by Agent */
    public void fetchPropertyByAgent() throws Exception {

        @SuppressWarnings("unchecked")
        List<Property> propertyList = kwrealtorsDao.getPropertyByAgent();

        /** creates a table model for the implementation of the RowTableModel */
        final BeanTableModel btModel = new BeanTableModel(Property.class, propertyList);
        /** sort by column name in order to set the same index order for every run */
        btModel.sortColumnNames();
        /** create the table */
        JTable table = new JTable(btModel);

        /** move & remove columns to appear as you want in the Jtable */
        table.moveColumn(5,0);  //License No moves to 0 - City is now 1; Street is 2
        table.removeColumn( table.getColumn( "Addr State" ) );  // no need to show
        table.removeColumn( table.getColumn( "Addr Zip" ) );  // no need to show this column
        table.removeColumn( table.getColumn( "Beds" ) );  // no need to show this column
        table.moveColumn(5,3);  // move price to appear after street
        table.moveColumn(5,4);  // move listing date to appear after price - License No is now last
        table.removeColumn( table.getColumn( "Sq Ft" ) );  // no need to show this column

        JScrollPane scrollPane = new JScrollPane(table);

        final JFrame frame = new JFrame("KW realtors");
        JLabel tHeading = new JLabel("Available Properties by Agent");
        tHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT, 18));

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);   // maximizes to full screen size
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(tHeading, BorderLayout.PAGE_START);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);

    }  // closes fetchPropertyByAgent

    /** property listings for an agent */
    public void fetchAgentProperty() throws Exception {

            @SuppressWarnings("unchecked")
            List<Property> propertyList = kwrealtorsDao.getPropertyAgentProperty();

        /** creates a table model for the implementation of the RowTableModel */
        final BeanTableModel btModel = new BeanTableModel(Property.class, propertyList);
        /** sort by column name in order to set the same index order for every run */
        btModel.sortColumnNames();
        /** create the table */
        JTable table = new JTable(btModel);

        /** move & remove columns to appear as you want in the Jtable  */
        table.moveColumn(5,0);  //license No moves to 0 - City is now 1; Street is 2
        table.moveColumn(6,1);  // move listing ID to appear after license no
        table.moveColumn(7,8);  //
        table.moveColumn(9,7);  // move sq ft

        JScrollPane scrollPane = new JScrollPane(table);

        final JFrame frame = new JFrame("KW realtors");
        JLabel bHeading = new JLabel("Click on a Listing to map its location");
        bHeading.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);   // maximizes to full screen size
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.add(bHeading, BorderLayout.PAGE_END);
        frame.setVisible(true);

        /** Listener will check if a row in the property listing has been clicked on
         *  if so, generate a map of that location
         */
                 table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                     @Override
                     public void valueChanged(ListSelectionEvent event) {
                         if (table.getSelectedRow() > -1) {
                             System.out.println("Reminder: an internet connection is necessary for a map lookup.");

                             /** remove any existing map image files from the root directory */
                             String fileName = "map.jpg";
                             File f = new File(fileName);
                             boolean success = f.delete();
                             if (!success)
                                 System.out.println("No map files were found");


                             JFrame mapFrame = new JFrame("Map of selected Listing");

                             Object streetAdd, cityAdd, stAdd;
                             streetAdd = (table.getValueAt(+table.getSelectedRow(), 4));
                             cityAdd = (table.getValueAt(+table.getSelectedRow(), 2));
                             stAdd = (table.getValueAt(+table.getSelectedRow(), 3));

                             try {
                                 String strUrl = "https://maps.googleapis.com/maps/api/staticmap?center="+streetAdd+","+cityAdd+","+stAdd+"&zoom=15&markers="+streetAdd+","+cityAdd+","+stAdd+"&size=600x500&scale=2&maptype=roadmap";
                                 //remove all white spaces from the string url
                                 String mapUrl = strUrl.replaceAll(" ", "%20");

                                 String destinationFile = "map.jpg";
                                 URL url = new URL(mapUrl);
                                 InputStream is = url.openStream();
                                 OutputStream os = new FileOutputStream(destinationFile);

                                 byte[] b = new byte[2048];
                                 int length;

                                 while ((length = is.read(b)) != -1) {
                                     os.write(b, 0, length);
                                 }
                                 is.close();
                                 os.close();
                             } catch (IOException e) {
                                 e.printStackTrace();
                                 System.exit(1);
                             }

                             mapFrame.add(new JLabel(new ImageIcon((new ImageIcon("map.jpg")).getImage().getScaledInstance(630, 600,
                                     java.awt.Image.SCALE_SMOOTH))));
                             mapFrame.pack();
                             mapFrame.setVisible(true);


                             //clear the selection in the Listener so that each invocation of the listener has a single selection
                             table.getSelectionModel().clearSelection();
                         }

                     }

                 });  // close property table Listener

             }  // closes fetchAgentProperty


             public void employeeAddEmployee() throws Exception {

                 kwrealtorsDao.employeeAddEmployee();

             }

             public void employeeUpdateEmployee() throws Exception {

                 kwrealtorsDao.employeeUpdateEmployee();

             }


             public void employeeDeleteEmployee() throws Exception {

                 kwrealtorsDao.employeeDeleteEmployee();

             }


             public void propertyAddProperty() throws Exception {

                 kwrealtorsDao.propertyAddProperty();

             }

             public void propertyUpdateProperty() throws Exception {

                 kwrealtorsDao.propertyUpdateProperty();

             }


             public void propertyDeleteProperty() throws Exception {

                 kwrealtorsDao.propertyDeleteProperty();

             }


             public void changePassW() throws Exception {

                 kwrealtorsDao.passwChange();

             }

         } // closes KwRealtorsApp


