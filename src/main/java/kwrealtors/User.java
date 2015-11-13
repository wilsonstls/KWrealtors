
package kwrealtors;

import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;


/**
 * User: wilsonstls
 * Date: 9/10/15
 *
 */

@Entity
@Table(name="user")
public class User {


    @Id
    @Column(name = "empid")
    private int empID;
    @Column(name = "passw")
    private String passW;

    public int getEmpID() {return empID;}
    public void setEmpID( int empid ) {this.empID = empid;}
    public String getPassW() {return passW;}
    public void setPassW( String passw ) {
        this.passW = passw;
    }

}