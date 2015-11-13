package kwrealtors;

import javax.persistence.*;

/**
*K. Wilson
* Getter and Setter methods for storing the employee IDs & License No when signing in.   
*/

@Entity
@Table(name="key")
public class Key {
    @Id
    @Column(name = "empKey")
    private int empKey;
    @Column(name = "licenseKey")
    private int licenseKey;


    public void setEmpKey(int empKey) {this.empKey = empKey;}
    public void setLicenseKey(int licenseKey) {this.licenseKey = licenseKey;}

    public int getEmpKey() {
        return empKey;
    }
    public int getLicenseKey() {return licenseKey;}


}
