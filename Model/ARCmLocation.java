package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_location")
public class ARCmLocation extends ACFaAppModel {

    public ARCmLocation() throws Exception {
        super();
    }


    @Id
    @Column(name = "location_code ")
    public String location_code ;
    
    @Column(name = "location_name")
    public String location_name;
    
    
    
}