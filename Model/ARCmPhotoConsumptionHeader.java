package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_photo_consumption_header")
public class ARCmPhotoConsumptionHeader extends ACFaAppModel {

    public ARCmPhotoConsumptionHeader() throws Exception {
        super();
    }


    @Id
    @Column(name = "job_no")
    public String job_no;
    
    
    @Column(name = "job_description")
    public String job_description;
        
    @Column(name = "input_date")
    public Timestamp input_date;
    
    @Column(name = "section_id")
    public String section_id;
    
    
    @Column(name = "photo_from_date")
    public Timestamp photo_from_date;
        
    @Column(name = "photo_to_date")
    public Timestamp photo_to_date;
    
    @Column(name = "photo_from_time")
    public BigDecimal photo_from_time;
       
    @Column(name = "photo_to_time")
    public BigDecimal photo_to_time;
    
    
    @Column(name = "programme_no")
    public String programme_no;
        
    @Column(name = "from_episode_no")
    public BigDecimal from_episode_no;
    
    
    @Column(name = "to_episode_no")
    public BigDecimal to_episode_no;
        
    @Column(name = "request_department")
    public String request_department;
    
    @Column(name = "remarks")
    public String remarks;
    
    @Column(name = "cut_off_date")
    public Timestamp cut_off_date;
    
    
    @Column(name = "cancel_indicator")
    public String cancel_indicator;
        
    @Column(name = "cancel_by")
    public String cancel_by;
    
    @Column(name = "cancel_date")
    public Timestamp cancel_date;
       
}