package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_wp_consumption_header")
public class ARCmWPConsumptionHeader extends ACFaAppModel {

    public ARCmWPConsumptionHeader() throws Exception {
        super();
    }


    @Id
    @Column(name = "consumption_form_no")
    public String consumption_form_no;
    
    @Id
    @Column(name = "completion_date")
    public Timestamp completion_date;
        
    @Column(name = "cut_off_date")
    public Timestamp cut_off_date;
    
    @Column(name = "input_date")
    public Timestamp input_date;
    
    @Column(name = "section_id")
    public String section_id;
        
    @Column(name = "location_code")
    public String location_code;
    
    @Column(name = "programme_no")
    public String programme_no;
    
    @Column(name = "from_episode_no")
    public BigDecimal from_episode_no;
    
    @Column(name = "to_episode_no")
    public BigDecimal to_episode_no;
    
    @Column(name = "job_description")
    public String job_description;
    
    @Column(name = "construction_no")
    public String construction_no;
    
    @Column(name = "group_no")
    public String group_no;
    
    @Column(name = "remarks")
    public String remarks;
    
    @Column(name = "cancel_indicator")
    public String cancel_indicator;
    
    @Column(name = "cancel_by")
    public String cancel_by;
    
    @Column(name = "cancel_date")
    public Timestamp cancel_date;
}