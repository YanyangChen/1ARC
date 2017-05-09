package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_labour_consumption")
public class ARCmLabourConsumption extends ACFaAppModel {

    public ARCmLabourConsumption() throws Exception {
        super();
    }


    
    @Column(name = "process_date")
    public Timestamp process_date;
    
    @Id
    @Column(name = "input_date")
    public Timestamp input_date;
    
    @Id
    @Column(name = "section_id")
    public String section_id;
    
    @Id
    @Column(name = "programme_no")
    public String programme_no;
    
    @Id
    @Column(name = "from_episode_no")
    public BigDecimal from_episode_no;
    
    @Id
    @Column(name = "to_episode_no")
    public BigDecimal to_episode_no;
    
    @Column(name = "from_job_date")
    public Timestamp from_job_date;
    
    @Column(name = "to_job_date")
    public Timestamp to_job_date;
    
    @Column(name = "job_description")
    public String job_description;
    
    @Column(name = "cancel_indicator")
    public String cancel_indicator;
    
    @Column(name = "cancel_by")
    public String cancel_by;
    
    @Column(name = "cancel_date")
    public Timestamp cancel_date;
    
    @Column(name = "remarks")
    public String remarks;
    
    @Column(name = "cut_off_date")
    public Timestamp cut_off_date;
    
    
}