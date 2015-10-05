package kwrealtors;

import java.util.List; 
 
 
/** 
  * User: wilsonstls
  * Date: 6/3/15 
  * Time: 1:48 AM 
  */ 
public interface KwRealtorsDao {

     public List getEmployee();
     public List getEmployeeAllEmployees();
     public List getPropertyAgentProperty();
     public List getPropertyAllProperty();
     public List getPropertyByAgent();
     public List getPropertyByPrice();
     public void employeeAddEmployee();
     public void employeeDeleteEmployee();
     public void employeeUpdateEmployee();
     public void propertyAddProperty();
     public void propertyDeleteProperty();
     public void propertyUpdateProperty();

} 
