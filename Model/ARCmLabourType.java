package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_labour_type")
public class ARCmLabourType extends ACFaAppModel {

    public ARCmLabourType() throws Exception {
        super();
    }


    @Id
    @Column(name = "labour_type")
    public String labour_type;
    
    @Column(name = "labour_type_description")
    public String labour_type_description;
    
    @Column(name = "section_id")
    public String section_id;
    
    @Column(name = "sub_section_id")
    public String sub_section_id;
    
    @Column(name = "hourly_rate")
    public BigDecimal hourly_rate;
    
    @Id
    @Column(name = "effective_from_date")
    public Timestamp effective_from_date;
    
    @Column(name = "effective_to_date")
    public Timestamp effective_to_date;
    
    
}