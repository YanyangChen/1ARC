package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_group")
public class ARCmGroup extends ACFaAppModel {

    public ARCmGroup() throws Exception {
        super();
    }


    @Id
    @Column(name = "section_id")
    public String section_id;
    
    @Id
    @Column(name = "sub_section_id")
    public String sub_section_id;
    
    @Id
    @Column(name = "group_no")
    public String group_no;
    
    @Column(name = "group_name")
    public String group_name;
    
    @Column(name = "hourly_rate")
    public BigDecimal hourly_rate;
    
    @Id
    @Column(name = "effective_from_date")
    public String effective_from_date;
    
    @Column(name = "effective_to_date")
    public String effective_to_date;
    
    
}