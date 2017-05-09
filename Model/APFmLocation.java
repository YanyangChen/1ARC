package arc.apf.Model;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_location")
public class APFmLocation extends ACFaAppModel {

    public APFmLocation() throws Exception {
        super();
    }

    @Id
    @Column(name = "location_code")
    public String location_code;

    @Column(name = "location_name")
    public String location_name;
    
}
