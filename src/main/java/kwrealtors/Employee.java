
package kwrealtors;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;



/**
 * User: wilsonstls
 * Date: 9/10/15
 *
 */



@Entity
@Table(name="employee")
public class Employee {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "empl_id")
    private int emplID;
    @Column(name = "status")
    private String Status;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "job_type")
    private String jobType;
    @Column(name = "hire_date")
    private String hireDate;
    @Column(name = "license_no")
    private int licenseNo;


    public int getEmplID() {return emplID;}
    public void setEmplID( int empl_id ) {this.emplID = empl_id;}
    public String getStatus() {
        return Status;
    }
    public void setStatus( String status ) {
        this.Status = status;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName( String first_name ) {
        this.firstName = first_name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName( String last_name ) {
        this.lastName = last_name;
    }
    public String getJobType() {
        return jobType;
    }
    public void setJobType( String job_type ) { this.jobType = job_type; }
    public String getHireDate() {
        return hireDate;
    }
    public void setHireDate( String hire_date ) {
        this.hireDate = hire_date;
    }
    public int getLicenseNo() {
        return licenseNo;
    }
    public void setLicenseNo( int license_no ) {
        this.licenseNo = license_no;
    }
}