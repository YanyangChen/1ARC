package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_monthly_manhour")
public class ARCmMonthlyManhour extends ACFaAppModel {

    public ARCmMonthlyManhour() throws Exception {
        super();
    }


    @Id
    @Column(name = "labour_type")
    public String labour_type;
    
    @Id
    @Column(name = "ye_ar")
    public BigDecimal ye_ar;
    
    
    @Column(name = "section_id")
    public String section_id;
    
    @Column(name = "sub_section_id")
    public String sub_section_id;
    
    @Column(name = "jan_man_hour")
    public BigDecimal jan_man_hour;
    
    
    @Column(name = "feb_man_hour")
    public BigDecimal feb_man_hour;
    
    @Column(name = "mar_man_hour")
    public BigDecimal mar_man_hour;
    
    @Column(name = "apr_man_hour")
    public BigDecimal apr_man_hour;
    
    
    @Column(name = "may_man_hour")
    public BigDecimal may_man_hour;
    
    @Column(name = "jun_man_hour")
    public BigDecimal jun_man_hour;
    
    @Column(name = "jul_man_hour")
    public BigDecimal jul_man_hour;
    
    
    @Column(name = "aug_man_hour")
    public BigDecimal aug_man_hour;
    
    @Column(name = "sep_man_hour")
    public BigDecimal sep_man_hour;
    
    @Column(name = "oct_man_hour")
    public BigDecimal oct_man_hour;
    
    
    @Column(name = "nov_man_hour")
    public BigDecimal nov_man_hour;
    
    @Column(name = "dec_man_hour")
    public BigDecimal dec_man_hour;
    
    
}