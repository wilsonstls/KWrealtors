
package kwrealtors;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;


/**
 * User: wilsonstls
 * Date: 9/10/15
 *
 */



@Entity
@Table(name="property")
public class Property {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "list_id")
    private int listID;
    @Column(name = "addr_street")
    private String addrStreet;
    @Column(name = "addr_city")
    private String addrCity;
    @Column(name = "addr_state")
    private String addrState;
    @Column(name = "addr_zip")
    private int addrZip;
    @Column(name = "sq_ft")
    private int sqFt;
    @Column(name = "beds")
    private int Beds;
    @Column(name = "price")
    private int Price;
    @Column(name = "listing_date")
    private String listingDate;
    @Column(name = "license_no")
    private int licenseNo;



    public int getListID() {return listID;}
    public void setListID( int list_id ) {this.listID = list_id;}
    public String getAddrStreet() {return addrStreet;}
    public void setAddrStreet( String addr_street ) {this.addrStreet = addr_street;}
    public String getAddrCity() {return addrCity;}
    public void setAddrCity( String addr_city ) {this.addrCity = addr_city;}
    public String getAddrState() {return addrState;}
    public void setAddrState( String addr_state ) {this.addrState = addr_state;}
    public int getAddrZip() {return addrZip;}
    public void setAddrZip( int addr_zip ) {this.addrZip = addr_zip;}
    public int getSqFt() {return sqFt;}
    public void setSqFt( int sq_ft ) {this.sqFt = sq_ft;}
    public int getBeds() {
        return Beds;
    }
    public void setBeds( int beds ) {
        this.Beds = beds;
    }
    public int getPrice() {
        return Price;
    }
    public void setPrice( int price ) {
        this.Price = price;
    }
    public String getListingDate() {return listingDate;}
    public void setListingDate( String listing_date ) {this.listingDate = listing_date;}
    public int getLicenseNo() {
        return licenseNo;
    }
    public void setLicenseNo( int license_no ) {
        this.licenseNo = license_no;
    }

}