package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_wp_labour_consumption")
public class ARCmWPLabourConsumption extends ACFaAppModel {

    public ARCmWPLabourConsumption() throws Exception {
        super();
    }


    @Id
    @Column(name = "consumption_form_no")
    public String consumption_form_no;
    
    
    @Column(name = "programme_no")
    public String programme_no;
        
    @Column(name = "input_date")
    public Timestamp input_date;
    
    @Id
    @Column(name = "labour_type")
    public String labour_type;
        
    @Column(name = "no_of_hours")
    public BigDecimal no_of_hours;
    
    @Column(name = "hourly_rate")
    public BigDecimal hourly_rate;
       
}