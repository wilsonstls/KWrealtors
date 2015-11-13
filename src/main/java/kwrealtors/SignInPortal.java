package kwrealtors;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;


/**
 *wilsonstls
 *10/25/15
 *portal to determine if employee id and password are valid
 */
@Repository
public class SignInPortal extends Portal
{
    /** constructor */
    public SignInPortal() {
    }

    @Autowired
    private KwRealtorsDao kwrealtorsDao;

    @Autowired
    private SessionFactory sessionFactoryBean;
    public SignInPortal(SessionFactory sessionFactoryBean) {
        this.sessionFactoryBean = sessionFactoryBean;
    }

    JTextField empID;
    JPasswordField pw;

    public void signIn() {
        frame = new JFrame("Secure ID & Password ");
        P.setLayout(new BoxLayout(P, BoxLayout.PAGE_AXIS));

        P.add(bL1);
        P.add(bL2);
        P.add(bL3);
        P.add(L17);
        P.add(bL13);
        P.add(bL14);
        P.add(L18);
        P.add(bL15);
        P.add(bL16);
        P.add(bL17);

        P.add(L19);  // enter employee id
        empID = new JTextField();
        P.add(empID).setMaximumSize(new java.awt.Dimension(100, 20));

        P.add(bL18);

        P.add(L20);  // enter password
        pw = new JPasswordField(10);
        P.add(pw).setMaximumSize(new java.awt.Dimension(100, 20));
        pw.setEchoChar('*');

        P.add(bL19);
        P.add(B20);  // enter button
        B20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws HibernateException {

                String idStr = empID.getText();  // get inputted ID
                int idInt = Integer.parseInt(idStr);
                char[] inputPW = pw.getPassword();  // get inputted password
                String strPW = null;
                boolean continueSignIn = true;

                /** query looks up user table for employee id & associated password */
                //  try {
                Session session = sessionFactoryBean.openSession();
                session.beginTransaction();
                String hql = "FROM User WHERE empid = :num";
                Query query = session.createQuery(hql);
                query.setInteger("num", idInt);

                List<User> result1 = query.list();
                if (result1.isEmpty()) {
                    continueSignIn = false;
                    session.close();
                }

                if (continueSignIn) {

                    for (User user : result1) {
                        strPW = user.getPassW();     // gets the password that is in the db table
                    }
                    session.close();

                    char[] dbPW = strPW.toCharArray();   // place database password into an Array

                        /* compare the inputted password with password in the db table */
                    if (Arrays.equals(inputPW, dbPW)) {

                        JOptionPane.showMessageDialog(frame, "Login Successful.");

                        /** preform these steps when user clicks the 'OK' button in the Message Dialog box */
                        try {
                            sessionFactoryBean.getCurrentSession().beginTransaction();
                            Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Employee.class);
                            cr.add(Restrictions.eq("Status", "A")).add(Restrictions.eq("emplID", idInt));
                            List list = cr.list();
                            sessionFactoryBean.getCurrentSession().getTransaction().commit();
                            List<Employee> result = list;
                            for (Employee employee : result) {
                                JOptionPane.showMessageDialog(null, employee.getFirstName() + "\nclick OK for your portal page", "WELCOME", JOptionPane.PLAIN_MESSAGE);
                                /** store the user's employee ID & license no in Key table */
                                addKey(employee.getEmplID(), employee.getLicenseNo());
                                /** Now go and create the employee portal page based on their job type
                                 * A - agent
                                 * C - clerical
                                 * M - manager
                                 */
                                PortalFactory.makePortalType(employee.getJobType());
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } else
                       /* inputted password does not match what is in the db table */
                        JOptionPane.showMessageDialog(frame, "Login Failed!");
                        Arrays.fill(inputPW, '0');  // zero fill the inputted password
                        pw.selectAll();             //select all characters in password field
                        pw.requestFocusInWindow();  //reset focus on password field

                }
                if (!continueSignIn) {
                    JOptionPane.showMessageDialog(frame, "Failed! - not a valid ID");
                }
            }
        });  //ends B20

        P.add(bL20);
        P.add(bL21);
        P.add(bL22);
        P.add(bL23);
        P.add(L1);
        P.add(B1);   // quit
        B1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("*** Ending this session ");
                System.exit(0);
            }
        });

        //create the window
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.getContentPane().add(P);
        frame.setVisible(true);

    } // closes method signIn


    /** This method stores the user's employee ID & license No in the Key table
     *  as a reference to the current instance */
    private void addKey(int empKey, int licenseKey) {

        Key key = new Key();

        Session session = sessionFactoryBean.openSession();
        key.setEmpKey(empKey);
        key.setLicenseKey(licenseKey);
        session.beginTransaction();
        session.save(key);
        session.flush();
        session.getTransaction().commit();
        session.close();

    }


} // closes class SignInPortal

