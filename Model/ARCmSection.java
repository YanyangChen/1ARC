package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_section")
public class ARCmSection extends ACFaAppModel {

    public ARCmSection() throws Exception {
        super();
    }


    @Id
    @Column(name = "section_id")
    public String section_id;
    
    @Id
    @Column(name = "sub_section_id")
    public String sub_section_id;
    
    
    @Column(name = "section_name")
    public String section_name;
        
    @Column(name = "dds_code")
    public String dds_code;

    
    @Column(name = "report_caption")
    public String report_caption;
    

    
}