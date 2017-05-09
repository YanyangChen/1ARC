package arc.apf.Model;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_supplier")
public class APFmSupplier extends ACFaAppModel {

    public APFmSupplier() throws Exception {
        super();
    }

    @Id
    @Column(name = "supplier_code")
    public String supplier_code;

    @Column(name = "supplier_name")
    public String supplier_name;
    
    @Column(name = "telephone_no")
    public String telephone_no;

    @Column(name = "fax_no")
    public String fax_no;
    
    @Column(name = "contact_person")
    public String contact_person;
    
    @Column(name = "email_address")
    public String email_address;
    
}
