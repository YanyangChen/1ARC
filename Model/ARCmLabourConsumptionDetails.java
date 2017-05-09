package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_labour_consumption_details")
public class ARCmLabourConsumptionDetails extends ACFaAppModel {

    public ARCmLabourConsumptionDetails() throws Exception {
        super();
    }


    @Id
    @Column(name = "input_date")
    public Timestamp input_date;
    
    @Id
    @Column(name = "section_id")
    public String section_id;
    
    @Id
    @Column(name = "sub_section_id")
    public String sub_section_id;
    
    @Id
    @Column(name = "programme_no")
    public String programme_no;
    
    @Id
    @Column(name = "from_episode_no")
    public BigDecimal from_episode_no;
    
    @Column(name = "to_episode_no")
    public BigDecimal to_episode_no;
    
    @Column(name = "labour_type")
    public Timestamp labour_type;
    
    @Column(name = "no_of_hours")
    public BigDecimal no_of_hours;
    
    @Column(name = "hourly_rate")
    public BigDecimal hourly_rate;
    

    
}