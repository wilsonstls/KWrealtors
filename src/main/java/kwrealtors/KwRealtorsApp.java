
package kwrealtors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext; 
import org.springframework.context.support.ClassPathXmlApplicationContext; 
import org.springframework.stereotype.Component;

import java.util.*;

/** 
  * User: wilsonstls
  * Date: 9/11/15
  */

@Component 
public class KwRealtorsApp {

    public static void main(String[] varArgs) throws Exception {


        System.out.println("\n     Welcome to KW REALTORS employees portal \n");
        System.out.println("\n");

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        KwRealtorsApp kwrealtorsApp = (KwRealtorsApp) context.getBean("kwrealtorsApp");
        kwrealtorsApp.fetchEmployee();

    }


    @Autowired
    private KwRealtorsDao kwrealtorsDao;
    @Autowired
    private AppOutput appOutput;

    private void fetchEmployee() throws Exception {

        /** retrieve employee portal based on user input */
        @SuppressWarnings("unchecked")
        List<Employee> employeesGetEmployee = kwrealtorsDao.getEmployee();
        /** user's employee id is valid  */
        if (!employeesGetEmployee.isEmpty()) {
            for (Employee employee : employeesGetEmployee) {

                String jobTitle = "null";
                String jobType = employee.getJobType();
                switch (jobType) {
                    case "A":
                        jobTitle = "Agent   ";
                        break;
                    case "C":
                        jobTitle = "Clerical";
                        break;
                    case "M":
                        jobTitle = "Manager ";
                        break;
                }
                appOutput.print(String.format("--- %s  %s   %s", employee.getFirstName(), employee.getLastName(), jobTitle ));
                PortalFactory.makePortalType(employee.getJobType());
            }
        }
        /** user's entry is not valid  */
        if (employeesGetEmployee.isEmpty()) {
            appOutput.print(String.format("  !! Employee ID not valid !!"));
        }
    }  // closes fetchEmployee


    public void fetchAllEmployee() throws Exception {

        /** list all active employees for KW Realtors */
        appOutput.print(String.format("\nList of all active employees\n"));
        appOutput.print(String.format( "                                   Empl_ID  License_No   Date Hired\n" ));
        @SuppressWarnings("unchecked")
        List<Employee> employeeAllEmployees = kwrealtorsDao.getEmployeeAllEmployees();
        for (Employee employee : employeeAllEmployees) {
            String jobTitle = "null";
            String jobType = employee.getJobType();
            switch (jobType) {
                case "A":
                    jobTitle = "Agent   ";
                    break;
                case "C":
                    jobTitle = "Clerical";
                    break;
                case "M":
                    jobTitle = "Manager ";
                    break;
            }
            appOutput.print(String.format(jobTitle + "  - %-9s  %-12s    %s   %6s    %s", employee.getFirstName(), employee.getLastName(),
                    employee.getEmplID(), employee.getLicenseNo(), employee.getHireDate()));
        }
    }  // closes fetchAllEmployee


    public void fetchAllProperty() throws Exception {

        /** list all property listings for KW Realtors */
        appOutput.print(String.format("\nLIST of all available property\n"));
        appOutput.print(String.format("Listing ID                                                             Agent "));
        @SuppressWarnings("unchecked")
        List<Property> propertyAllProperty = kwrealtorsDao.getPropertyAllProperty();
        for (Property property : propertyAllProperty) {

            appOutput.print(String.format("%s----  %-20s %-12s  $%7s  %10s  %6s", property.getListID(), property.getAddrStreet(),
                    property.getAddrCity(), property.getPrice(), property.getListingDate(), property.getLicenseNo()));
        }
    }
  //  }// closes fetchAllProperty



    public void fetchPropertyByPrice() throws Exception {

        /** property listings by listing Price */
        appOutput.print(String.format("\nLIST of property by price\n"));
        appOutput.print(String.format("         Listing_ID"));
        @SuppressWarnings("unchecked")
        List<Property> propertyByPrice = kwrealtorsDao.getPropertyByPrice();
        for (Property property : propertyByPrice) {

            appOutput.print(String.format("$%7s   %s   %-20s  %-13s", property.getPrice(), property.getListID(), property.getAddrStreet(),
                    property.getAddrCity()));
        }
    }  // closes fetchPropertyByPrice


    public void fetchPropertyByAgent() throws Exception {

        /** property listings by Agent */
        appOutput.print(String.format("\nLIST of property by agent\n"));
        appOutput.print(String.format("Agent    Listing_ID"));
        @SuppressWarnings("unchecked")
        List<Property> propertyByAgent = kwrealtorsDao.getPropertyByAgent();
        int tmpID = 0;
        for (Property property : propertyByAgent) {

            if (!(property.getLicenseNo() == tmpID)) {
                appOutput.print(String.format(" "));
                appOutput.print(String.format("%s   %s   %-20s  %-12s   $%7s", property.getLicenseNo(), property.getListID(), property.getAddrStreet(),
                        property.getAddrCity(), property.getPrice()));
            } else
                appOutput.print(String.format("%s   %s   %-20s  %-12s   $%7s", property.getLicenseNo(), property.getListID(), property.getAddrStreet(),
                        property.getAddrCity(), property.getPrice()));
            tmpID = property.getLicenseNo();
        }
    }  // closes fetchPropertyByAgent

    public void fetchAgentProperty() throws Exception {

            /** property listings for an agent */

            @SuppressWarnings("unchecked")
            List<Property> propertyAgentProperty = kwrealtorsDao.getPropertyAgentProperty();

            if (!propertyAgentProperty.isEmpty()) {
                appOutput.print(String.format("\nListing ID                                          Sq ft  Beds  Price    Dated Listed"));
                for (Property property : propertyAgentProperty) {
                appOutput.print(String.format("%s   %-20s  %-12s %s   %s   %s   $%7s   %s", property.getListID(), property.getAddrStreet(),
                        property.getAddrCity(), property.getAddrZip(), property.getSqFt(), property.getBeds(), property.getPrice(), property.getListingDate()));
            }
            }
            if (propertyAgentProperty.isEmpty()) {
                appOutput.print(String.format("  !! License Number invalid !!"));
            }
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


} // closes KwRealtorsApp


