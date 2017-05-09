package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_photo_labour_consumption")
public class ARCmPhotoLabourConsumption extends ACFaAppModel {

    public ARCmPhotoLabourConsumption() throws Exception {
        super();
    }


    @Id
    @Column(name = "job_no")
    public String job_no;
    
    
    @Column(name = "labour_type")
    public String labour_type;
    
    
    @Column(name = "programme_no")
    public String programme_no;
    
    @Id
    @Column(name = "photo_to_date")
    public Timestamp photo_to_date;
    
    
    @Column(name = "no_of_hours")
    public BigDecimal no_of_hours;
    
    
    @Column(name = "hourly_rate")
    public BigDecimal hourly_rate;
    
    

    
    
}