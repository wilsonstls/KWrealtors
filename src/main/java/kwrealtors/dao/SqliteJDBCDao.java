
package kwrealtors.dao;


import kwrealtors.*;
import kwrealtors.Employee;
import kwrealtors.Property;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Scanner;



@Repository
public class SqliteJDBCDao implements KwRealtorsDao {

    public SqliteJDBCDao() {
    }

    @Autowired
    private AppOutput appOutput;
    @Autowired
    private SessionFactory sessionFactoryBean;

    public SqliteJDBCDao(SessionFactory sessionFactoryBean) {
        this.sessionFactoryBean = sessionFactoryBean;
    }


    public List getEmployee() throws HibernateException {
        System.out.println("\nEnter your employee ID:  ");
        Scanner empInput = new Scanner(System.in);
        String empKey = empInput.nextLine();
        int empID = Integer.parseInt(empKey);

        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Employee.class);
        cr.add(Restrictions.eq("Status", "A")).add(Restrictions.eq("emplID", empID));
        List list = cr.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }

    public List getEmployeeAllEmployees() throws HibernateException {
        /** retrieve employees of KW reality - company id 1222  */
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Employee.class);
        cr.add(Restrictions.eq("Status", "A"));
        cr.addOrder(Order.asc("jobType"));
        List list = cr.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }

    public List getPropertyAllProperty() throws HibernateException {
        /** retrieves all property listings  */
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Property.class);
        List list = cr.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }

    public List getPropertyByPrice() throws HibernateException {
        /** retrieves property listings by price - highest to lowest  */
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Property.class);
        cr.addOrder(Order.desc("Price"));
        List list = cr.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }

    public List getPropertyByAgent() throws HibernateException {
        /** retrieves property listings by agent*/
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Property.class);
        cr.addOrder(Order.asc("licenseNo"));
        List list = cr.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }

    public List getPropertyAgentProperty() throws HibernateException {
        System.out.println("\nEnter your Real Estate License No:  ");
        Scanner empInput = new Scanner(System.in);
        String licStr = empInput.nextLine();
        int licNo = Integer.parseInt(licStr);

        /** query to retrieve agent name */
        Session session = sessionFactoryBean.openSession();
        session.beginTransaction();
        String hql = "FROM Employee WHERE license_no = :num";
        Query query = session.createQuery(hql);
        query.setInteger("num", licNo);
        @SuppressWarnings("unchecked")
        List<Employee> result = query.list();

        for ( Employee employee : result ) {
            appOutput.print(String.format("Listings for  %s  %s", employee.getFirstName(), employee.getLastName()));
        }
        session.close();

        /** retrieves an agent's property listings*/
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Property.class);
        cr.add(Restrictions.eq("licenseNo", licNo));
        List list = cr.list();
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        return list;
    }


    public void employeeAddEmployee() {
        Employee employee = new Employee();
        Session session = sessionFactoryBean.openSession();
        session.beginTransaction();
        session.save(employee);
        session.flush();
        session.getTransaction().commit();
        session.close();
        String newempID = "ID for new employee is:  ";
        appOutput.print(String.format(newempID + "%s", employee.getEmplID()));
        System.out.println("\nUse the UPDATE action to enter data for this employee");
    }



    public void employeeUpdateEmployee() {
        Employee employee = new Employee();
        System.out.println("To update, all data has to be entered");
        System.out.println("\nEnter the Employee ID to update :  ");
        Scanner empInput = new Scanner(System.in);
        String idStr = empInput.nextLine();
        int idInt = Integer.parseInt(idStr);
        employee.setEmplID(idInt);

        System.out.println("\nemployee status (A - active, I - inactive) :  ");
        Scanner strInput = new Scanner(System.in);
        String staStr = strInput.nextLine();
        employee.setStatus(staStr);

        System.out.println("\nfirst name :  ");
        Scanner fnInput = new Scanner(System.in);
        String fnStr = fnInput.nextLine();
        employee.setFirstName(fnStr);

        System.out.println("\nlast name :  ");
        Scanner lnInput = new Scanner(System.in);
        String lnStr = lnInput.nextLine();
        employee.setLastName(lnStr);

        System.out.println("\njob type (A - agent, C - clerical, M - manager :  ");
        Scanner jInput = new Scanner(System.in);
        String jStr = jInput.nextLine();
        employee.setJobType(jStr);

        System.out.println("\ndate hired - enter as 'mm/dd/yyyy' :  ");
        Scanner hdInput = new Scanner(System.in);
        String hdStr = hdInput.nextLine();
        employee.setHireDate(hdStr);

        System.out.println("\nreal estate license number (enter 0 if N/A) :  ");
        Scanner lInput = new Scanner(System.in);
        String lStr = lInput.nextLine();
        int lInt = Integer.parseInt(lStr);
        employee.setLicenseNo(lInt);

        Session session = sessionFactoryBean.openSession();
        session.beginTransaction();
        session.merge(employee);
        session.flush();
        session.getTransaction().commit();
        session.close();

        System.out.println("\nUpdate action is completed ");
    }


    public void employeeDeleteEmployee() throws HibernateException {
        System.out.println("\nEnter the Employee ID to delete :  ");
        Scanner empInput = new Scanner(System.in);
        String idStr = empInput.nextLine();
        int idInt = Integer.parseInt(idStr);

        sessionFactoryBean.getCurrentSession().beginTransaction();
        Object deleteID = sessionFactoryBean.getCurrentSession().load(Employee.class, idInt);
        if(deleteID != null){
           sessionFactoryBean.getCurrentSession().delete(deleteID);
        }
        sessionFactoryBean.getCurrentSession().getTransaction().commit();
        System.out.println("Delete successful");
    }


    public void propertyAddProperty() {
        System.out.println("\nTo add a listing please enter your Real Estate License No:  ");
        Scanner empInput = new Scanner(System.in);
        String licStr = empInput.nextLine();
        int licNo = Integer.parseInt(licStr);
        Property property = new Property();

        Session session = sessionFactoryBean.openSession();
        property.setLicenseNo(licNo);
        session.beginTransaction();
        session.save(property);
        session.flush();
        session.getTransaction().commit();
        session.close();
        String newempID = "ID for new listing is:  ";
        appOutput.print(String.format(newempID + "%s", property.getListID()));
        System.out.println("\nUse the UPDATE option to enter data for this listing");
    }

    public void propertyUpdateProperty() {
        Property property = new Property();
        System.out.println("To update a listing, all data has to be entered");
        System.out.println("First, enter your real estate License No:  ");
        Scanner empInput = new Scanner(System.in);
        String licStr = empInput.nextLine();
        int licNo = Integer.parseInt(licStr);
        System.out.println("\nEnter the Listing ID to update :  ");
        System.out.println("You are allowed to update only your listings");
        Scanner lstInput = new Scanner(System.in);
        String lidStr = lstInput.nextLine();
        int lidInt = Integer.parseInt(lidStr);
        /** check if license number matches whats in the listing */
        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Property.class);
        cr.add(Restrictions.eq("listID", lidInt)).add(Restrictions.eq("licenseNo", licNo));


        /** update only if result is not null */
        if (cr.uniqueResult() != null) {

            property.setListID(lidInt);
            property.setLicenseNo(licNo);

            System.out.println("\nEnter street address:  ");
            Scanner strInput = new Scanner(System.in);
            String strStr = strInput.nextLine();
            property.setAddrStreet(strStr);

            System.out.println("\nEnter city:  ");
            Scanner cInput = new Scanner(System.in);
            String cStr = cInput.nextLine();
            property.setAddrCity(cStr);

            System.out.println("\nEnter state:  ");
            Scanner staInput = new Scanner(System.in);
            String staStr = staInput.nextLine();
            property.setAddrState(staStr);

            System.out.println("\nEnter zip code:  ");
            Scanner zipInput = new Scanner(System.in);
            String zipStr = zipInput.nextLine();
            int zipInt = Integer.parseInt(zipStr);
            property.setAddrZip(zipInt);

            System.out.println("\nEnter square footage:  ");
            System.out.println("whole number w/o comma");
            Scanner sqftInput = new Scanner(System.in);
            String sqftStr = sqftInput.nextLine();
            int sqftInt = Integer.parseInt(sqftStr);
            property.setSqFt(sqftInt);

            System.out.println("\nEnter # of bedrooms:  ");
            Scanner bedInput = new Scanner(System.in);
            String bedStr = bedInput.nextLine();
            int bedInt = Integer.parseInt(bedStr);
            property.setBeds(bedInt);

            System.out.println("\nEnter asking price:  ");
            System.out.println("whole number w/o comma");
            Scanner pInput = new Scanner(System.in);
            String pStr = pInput.nextLine();
            int pInt = Integer.parseInt(pStr);
            property.setPrice(pInt);

            System.out.println("\ndate listed - enter as 'mm/dd/yyyy' :  ");
            Scanner hdInput = new Scanner(System.in);
            String hdStr = hdInput.nextLine();
            property.setListingDate(hdStr);

            sessionFactoryBean.getCurrentSession().merge(property);
            sessionFactoryBean.getCurrentSession().getTransaction().commit();
            System.out.println("\nUpdate action is completed ");

        } else {
            System.out.println("\n You are not allowed to update this listing!");
            sessionFactoryBean.getCurrentSession().getTransaction().rollback();
            sessionFactoryBean.close();
        }

    }


    public void propertyDeleteProperty() throws HibernateException {
        System.out.println("\nTo delete a listing please enter your Real Estate License No:  ");
        Scanner empInput = new Scanner(System.in);
        String licStr = empInput.nextLine();
        int licNo = Integer.parseInt(licStr);
        System.out.println("\n   Enter the Listing ID to delete :  ");
        System.out.println("(you are allowed to delete only your listings)");
        Scanner lstInput = new Scanner(System.in);
        String idStr = lstInput.nextLine();
        int idInt = Integer.parseInt(idStr);

        sessionFactoryBean.getCurrentSession().beginTransaction();
        Criteria cr = sessionFactoryBean.getCurrentSession().createCriteria(Property.class);
        cr.add(Restrictions.eq("listID", idInt)).add(Restrictions.eq("licenseNo", licNo));

        /** only delete if result is not null */
        if (cr.uniqueResult() != null) {
               Object deleteID = sessionFactoryBean.getCurrentSession().load(Property.class, idInt);
               sessionFactoryBean.getCurrentSession().delete(deleteID);
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete unsuccessful - check the listing ID entered");
        }
        sessionFactoryBean.getCurrentSession().getTransaction().commit();

    }

}// closes class


