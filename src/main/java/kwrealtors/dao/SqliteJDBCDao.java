
package kwrealtors.dao;


import kwrealtors.*;
import kwrealtors.Employee;
import kwrealtors.Property;
import kwrealtors.Key;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;



@Repository
public class SqliteJDBCDao implements KwRealtorsDao {

    public SqliteJDBCDao() {
    }


    @Autowired
    private SessionFactory sessionFactoryBean;


    public SqliteJDBCDao(SessionFactory sessionFactoryBean) {
        this.sessionFactoryBean = sessionFactoryBean;
    }

    JFrame frame = new JFrame();

    public int empKey = 0;
    public int licKey = 0;
    public int listIDKey = 0;

    /**
     * retrieves entry in Key table that contains this instance employee ID & license no
     */
    private List fetchKeys() {
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Key.class);
        List list = cr.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }


    /** retrieve active employees of KW reality  */
    public List getEmployeeAllEmployees() throws HibernateException {
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Employee.class);
        cr.add(Restrictions.eq("Status", "A"));
        cr.addOrder(Order.asc("lastName"));
        List list = cr.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }

    /** retrieves all property listings  */
    public List getPropertyAllProperty() throws HibernateException {
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Property.class);
        List list = cr.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }

    /** retrieves property listings by price - highest to lowest  */
    public List getPropertyByPrice() throws HibernateException {
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Property.class);
        cr.addOrder(Order.desc("Price"));
        List list = cr.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }

    /** retrieves property listings by agent*/
    public List getPropertyByAgent() throws HibernateException {
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Property.class);
        cr.addOrder(Order.asc("licenseNo"));
        List list = cr.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }

    /** retrieves an agent's property listings */
    public List getPropertyAgentProperty() throws HibernateException {
        /** retrieve the agent's real estate license no */
        List<Key> result = fetchKeys();
        for (Key key : result) {
            licKey = key.getLicenseKey();
        }

        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Property.class, "property");
        cr.add(Restrictions.eq("licenseNo", licKey));
        List list = cr.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        sessionFactoryBean.close();
        return list;
    }


    /**
     * Add an employee
     * new employee ID is auto generated and their status is set to 'A' - active - in the Employee table
     * an entry is then established in the User table for the ID and default password  'ABC'
     */
    public void employeeAddEmployee() {
        /** creates an entry in the Employee table for new employee */
        Employee employee = new Employee();
        Session session = sessionFactoryBean.openSession();
        session.beginTransaction();
        employee.setStatus("A");
        session.save(employee);
        session.flush();
        session.getTransaction().commit();

        /** creates an entry in the User table for new employee - default passw is set to 'ABC' */
        User user = new User();
        session.beginTransaction();
        user.setEmpID(employee.getEmplID());
        user.setPassW("ABC");
        session.save(user);
        session.flush();
        session.getTransaction().commit();
        session.close();

        JOptionPane.showMessageDialog(frame, "Added; new employee ID is:  " + employee.getEmplID() +
                "  the default password is: ABC");
        JOptionPane.showMessageDialog(frame, "Use UPDATE to add in data on this new employee.");
    }

    /**
     * Update an active employee
     * only active employees with a status of 'A' can be updated
     */
    public void employeeUpdateEmployee() {
        boolean continueUpdate = false;
        JTextField txtEmpID = new JTextField(5);
        JPanel message = new JPanel();
        message.add(txtEmpID);
        int oResult = JOptionPane.showConfirmDialog(null, message, "Enter Employee ID to update", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (oResult == JOptionPane.OK_OPTION) {
            continueUpdate = true;
        }

        /** the OK button has been hit */
        if (continueUpdate) {

            String sEID = txtEmpID.getText();
            empKey = Integer.parseInt(sEID);

            /** check if valid employee ID and their current employment status is 'A'
             *  only active employees can be updated
             */
            sessionFactoryBean.getCurrentSession().beginTransaction();
            Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Employee.class);
            cr.add(Restrictions.eq("emplID", empKey)).add(Restrictions.eq("Status", "A"));

            /** update only if criteria result is not null */
            if (cr.uniqueResult() != null) {

                Employee employee = new Employee();
                employee.setEmplID(empKey);
                employee.getEmplID();
                employee.setStatus("A");

                /** update first name */
                JTextField txtFName = new JTextField(20);
                message.add(txtFName);
                oResult = JOptionPane.showConfirmDialog(null, message, "Enter employee's first name", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (oResult == JOptionPane.OK_OPTION) {
                    String strFName = String.valueOf(txtFName.getText());
                    employee.setFirstName(strFName);
                }

                /** update last name */
                JTextField txtLName = new JTextField(20);
                message.add(txtLName);
                oResult = JOptionPane.showConfirmDialog(null, message, "Enter employee's last name", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (oResult == JOptionPane.OK_OPTION) {
                    String strLName = String.valueOf(txtLName.getText());
                    employee.setLastName(strLName);
                }

                /** update job type */
                JTextField txtJobType = new JTextField(1);
                message.add(txtJobType);
                oResult = JOptionPane.showConfirmDialog(null, message, "Enter job type (A - agent, C - clerical, M - manager", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (oResult == JOptionPane.OK_OPTION) {
                    String strJobType = String.valueOf(txtJobType.getText());
                    employee.setJobType(strJobType);
                }

                /** update date hired */
                JTextField txtHireDate = new JTextField(10);
                message.add(txtHireDate);
                oResult = JOptionPane.showConfirmDialog(null, message, "date hired - enter as 'mm/dd/yyyy'", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (oResult == JOptionPane.OK_OPTION) {
                    String strHireDate = String.valueOf(txtHireDate.getText());
                    employee.setHireDate(strHireDate);
                }

                /** update real estate license number */
                SpinnerModel spinnerModel = new SpinnerNumberModel();
                JSpinner spinner = new JSpinner(spinnerModel);

                int LNo = 0;
                int option = JOptionPane.showOptionDialog(null, spinner, "Real Estate License Number ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (option == JOptionPane.OK_OPTION) {
                    LNo = (int) spinner.getValue();
                    employee.setLicenseNo(LNo);
                }

                try {
                sessionFactoryBean.getCurrentSession().merge(employee);
                sessionFactoryBean.getCurrentSession().getTransaction().commit();

                JOptionPane.showMessageDialog(frame, "Employee - " + empKey + "  has been updated");
                } catch (HibernateException e){
                    sessionFactoryBean.getCurrentSession().getTransaction().rollback();
                    JOptionPane.showMessageDialog(frame, "Update attempt failed!");
                    throw e;
                }
                finally {
                    sessionFactoryBean.close();
                }

            } /** criteria result came back null - entry was not found  */
            else {
                sessionFactoryBean.getCurrentSession().getTransaction().rollback();
                sessionFactoryBean.close();
                JOptionPane.showMessageDialog(frame, "Update attempt failed!");
            }
        }
    }


    /**
     * Delete an employee
     * Check if a valid employee - if so set the employee's status to 'I' for inactive
     * then delete the employee ID & password from User table
     */
    public void employeeDeleteEmployee() throws HibernateException {
        boolean continueDelete = false;
        JTextField txtEmpID = new JTextField(5);
        JPanel message = new JPanel();
        message.add(txtEmpID);
        int oResult = JOptionPane.showConfirmDialog(null, message, "Enter Employee ID to delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (oResult == JOptionPane.OK_OPTION) {
            continueDelete = true;
        }

        if (continueDelete) {

            String sEID = txtEmpID.getText();
            int empIDKey = Integer.parseInt(sEID);

            /** open a session to check if ID key exists in the User table
             * - only active employees are on this table */
            sessionFactoryBean.getCurrentSession().beginTransaction();
            Object validKey = sessionFactoryBean.getCurrentSession().get(User.class, empIDKey);

            if (validKey != null) {

                /** remove entry from User table  */
                try {
                    Object deleteID = sessionFactoryBean.getCurrentSession().load(User.class, empIDKey);
                    sessionFactoryBean.getCurrentSession().delete(deleteID);

                } catch (HibernateException e) {
                    sessionFactoryBean.getCurrentSession().getTransaction().rollback();
                    throw e;
                }

                /** reset employment status to 'I' for inactive in the Employee table  */
                Employee employee = new Employee();
                employee.setEmplID(empIDKey);
                employee.getEmplID();
                employee.setStatus("I");

                try {
                    sessionFactoryBean.getCurrentSession().merge(employee);

                    JOptionPane.showMessageDialog(frame, "Employee - " + empIDKey + "  is now inactive");
                } catch (HibernateException e) {
                    sessionFactoryBean.getCurrentSession().getTransaction().rollback();
                    throw e;
                }

                sessionFactoryBean.getCurrentSession().getTransaction().commit();
                sessionFactoryBean.close();
            }

            /** ID key does not exist in User table */
            if (validKey == null) {
                    JOptionPane.showMessageDialog(frame, "Delete attempt failed!");
            }
        }
    }


    /** add a new property listing  */
    public void propertyAddProperty() {
        Property property = new Property();
        /** retrieve the agent's real estate license no */
        List<Key> result = fetchKeys();
        for (Key key : result) {
            licKey = key.getLicenseKey();
        }

        Session session = sessionFactoryBean.openSession();
        property.setLicenseNo(licKey);
        session.beginTransaction();
        session.save(property);
        session.flush();
        session.getTransaction().commit();
        session.close();
        JOptionPane.showMessageDialog(frame, "Successful, new Listing ID is:  " + property.getListID());
        JOptionPane.showMessageDialog(frame, "Use UPDATE to add in information for this new listing.");
    }

    /**
     * Update a property Listing
     * Listings can only be updated by the agent assigned to the Listing.
     */
    public void propertyUpdateProperty() {

        Property property = new Property();

        boolean continueUpdate = false;
        JTextField txtListID = new JTextField(7);
        JPanel message = new JPanel();
        message.add(txtListID);
        int oResult = JOptionPane.showConfirmDialog(null, message, "Enter Listing ID to update", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (oResult == JOptionPane.OK_OPTION) {
            continueUpdate = true;
        }

        if (continueUpdate) {
            String sLID = txtListID.getText();
            listIDKey = Integer.parseInt(sLID);

            /** retrieve the agent's real estate license no */
            List<Key> result = fetchKeys();
            for (Key key : result) {
                licKey = key.getLicenseKey();
            }

            sessionFactoryBean.getCurrentSession().beginTransaction();
            Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Property.class);
            cr.add(Restrictions.eq("listID", listIDKey)).add(Restrictions.eq("licenseNo", licKey));

            /** criteria result is valid - begin updating */
            if (cr.uniqueResult() != null) {

                property.setListID(listIDKey);
                property.setLicenseNo(licKey);
                property.setAddrState("MO");



                JTextField txtStreetAddr = new JTextField(10);
                message.add(txtStreetAddr);
                oResult = JOptionPane.showConfirmDialog(null, message, "Enter Street address: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (oResult == JOptionPane.OK_OPTION) {
                    String strStreetAddr = String.valueOf(txtStreetAddr.getText());
                    property.setAddrStreet(strStreetAddr);
                }

                JTextField txtCityAddr = new JTextField(15);
                message.add(txtCityAddr);
                oResult = JOptionPane.showConfirmDialog(null, message, "Enter City: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (oResult == JOptionPane.OK_OPTION) {
                    String strCityAddr = String.valueOf(txtCityAddr.getText());
                    property.setAddrCity(strCityAddr);
                }


                /** Jspinner for integers input   */
                SpinnerModel spinnerModel = new SpinnerNumberModel();
                JSpinner spinner = new JSpinner(spinnerModel);


                int pZ = 0;
                int optionZ = JOptionPane.showOptionDialog(null, spinner, "Zip Code: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (optionZ == JOptionPane.OK_OPTION) {
                    pZ = (int) spinner.getValue();
                    property.setAddrZip(pZ);
                }

                int pSF = 0;
                int optionSF = JOptionPane.showOptionDialog(null, spinner, "Square Footage: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (optionSF == JOptionPane.OK_OPTION) {
                    pSF = (int) spinner.getValue();
                    property.setSqFt(pSF);
                }

                int pB = 0;
                int optionB = JOptionPane.showOptionDialog(null, spinner, "# of Bedrooms: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (optionB == JOptionPane.OK_OPTION) {
                    pB = (int) spinner.getValue();
                    property.setBeds(pB);
                }

                int pP = 0;
                int optionP = JOptionPane.showOptionDialog(null, spinner, "Asking Price: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (optionP == JOptionPane.OK_OPTION) {
                    pP = (int) spinner.getValue();
                    property.setPrice(pP);
                }

                /** listing date */
                JTextField txtListDate = new JTextField(10);
                message.add(txtListDate);
                oResult = JOptionPane.showConfirmDialog(null, message, "Date Listed - enter as 'mm/dd/yyyy'", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (oResult == JOptionPane.OK_OPTION) {
                    String strListDate = String.valueOf(txtListDate.getText());
                    property.setListingDate(strListDate);
                }


                try {
                    sessionFactoryBean.getCurrentSession().merge(property);
                    sessionFactoryBean.getCurrentSession().getTransaction().commit();
                    JOptionPane.showMessageDialog(frame, "Listing - " + listIDKey + "  has been updated");
                } catch (HibernateException e) {
                    sessionFactoryBean.getCurrentSession().getTransaction().rollback();
                    throw e;
                } finally {
                    sessionFactoryBean.close();
                }

            }
            /** else criteria results came back null - entry was not found  */
            else {
                sessionFactoryBean.getCurrentSession().getTransaction().rollback();
                sessionFactoryBean.close();
                JOptionPane.showMessageDialog(frame, "Failed! - you are not allowed to update this listing");
            }
        }
    }


    /** Delete an existing property listing  */
    public void propertyDeleteProperty() throws HibernateException {

        boolean continueDelete = false;
        JTextField txtListID = new JTextField(7);
        JPanel message = new JPanel();
        message.add(txtListID);
        int oResult = JOptionPane.showConfirmDialog(null, message, "Enter Listing ID to delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (oResult == JOptionPane.OK_OPTION) {
            continueDelete = true;
        }

        if (continueDelete) {
            String sLID = txtListID.getText();
            listIDKey = Integer.parseInt(sLID);

            /** retrieve the agent's real estate license no */
            List<Key> result = fetchKeys();
            for (Key key : result) {
                licKey = key.getLicenseKey();
            }

            sessionFactoryBean.getCurrentSession().beginTransaction();
            Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Property.class);
            cr.add(Restrictions.eq("listID", listIDKey)).add(Restrictions.eq("licenseNo", licKey));

            /** only delete if result is not null */
            if (cr.uniqueResult() != null) {
                Object deleteID = sessionFactoryBean.getCurrentSession().load(Property.class, listIDKey);
                sessionFactoryBean.getCurrentSession().delete(deleteID);
                JOptionPane.showMessageDialog(frame, "Property Listing: " +listIDKey+   " has been deleted");
            } else {
                JOptionPane.showMessageDialog(frame, "Delete attempt failed!");
            }

            sessionFactoryBean.getCurrentSession().getTransaction().commit();
        }
    }


    /** change the user's password as listed in the User table */
    public void passwChange() throws HibernateException {

        /** retrieve the user's ID stored in Key table*/
        List<Key> result = fetchKeys();
        for (Key key : result) {
            empKey = key.getEmpKey();
        }

        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(User.class);
        cr.add(Restrictions.eq("empID", empKey));

        /** update only if criteria result is not null */
        if (cr.uniqueResult() != null) {

            User user = new User();
            user.setEmpID(empKey);

            JTextField txtPassW = new JTextField(10);
            JPanel message = new JPanel();
            message.add(txtPassW);
            JOptionPane.showMessageDialog(frame, "Passwords can be any combination of letters, numbers, symbols, less than 10 characters");
            int oResult = JOptionPane.showConfirmDialog(null, message, "Enter your new password: ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (oResult == JOptionPane.OK_OPTION) {
                String strPassW = String.valueOf(txtPassW.getText());
                user.setPassW(strPassW);

                try {
                    sessionFactoryBean.getCurrentSession().merge(user);
                    sessionFactoryBean.getCurrentSession().getTransaction().commit();
                    JOptionPane.showMessageDialog(frame, "Your new password is:  " + strPassW);
                } catch (HibernateException e) {
                    sessionFactoryBean.getCurrentSession().getTransaction().rollback();
                    throw e;
                } finally {
                    sessionFactoryBean.close();
                }
            }
            /** else the cancel button was hit  */
            else {
                sessionFactoryBean.getCurrentSession().getTransaction().rollback();
                sessionFactoryBean.close();
            }

        } else {
            JOptionPane.showMessageDialog(frame, "attempt failed!");
        }
    }


} // closes SqliteJDBCDao


