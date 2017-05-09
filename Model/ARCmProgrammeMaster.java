package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_programme_master")
public class ARCmProgrammeMaster extends ACFaAppModel {

    public ARCmProgrammeMaster() throws Exception {
        super();
    }


    @Id
    @Column(name = "programme_no")
    public String programme_no;
    
    
    @Column(name = "programme_name")
    public String programme_name;
    
    
    @Column(name = "chinese_programme_name")
    public String chinese_programme_name;
    
    @Column(name = "business_platform")
    public String business_platform;
    
    
    @Column(name = "department")
    public String department;
    
    @Column(name = "no_of_episode")
    public BigDecimal no_of_episode;
    
    
    @Column(name = "programme_type")
    public String programme_type;
    
    @Column(name = "vtr_from_date")
    public Timestamp vtr_from_date;
    
    
    @Column(name = "vtr_to_date")
    public Timestamp vtr_to_date;
    
    @Column(name = "efp_from_date")
    public Timestamp efp_from_date;
    
    
    @Column(name = "efp_to_date")
    public Timestamp efp_to_date;
    
    @Column(name = "executive_producer")
    public String executive_producer;
    
    
    @Column(name = "setting_designer")
    public String setting_designer;
    
    @Column(name = "costume_designer")
    public String costume_designer;
    
    
    @Column(name = "direct_budget")
    public BigDecimal direct_budget;
    
    @Column(name = "transfer_status")
    public String transfer_status;
    
    
    @Column(name = "transfer_from_programme")
    public String transfer_from_programme;
    
    @Column(name = "transfer_from_date")
    public Timestamp transfer_from_date;
    
    
    @Column(name = "transfer_to_programme")
    public String transfer_to_programme;
    
    @Column(name = "transfer_to_date")
    public Timestamp transfer_to_date;
    
    
    @Column(name = "transfer_remarks")
    public String transfer_remarks;
    
    
}