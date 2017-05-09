package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_photo_consumption_item")
public class ARCmPhotoConsumptionItem extends ACFaAppModel {

    public ARCmPhotoConsumptionItem() throws Exception {
        super();
    }


    @Id
    @Column(name = "job_no")
    public String job_no;
    
    @Id
    @Column(name = "item_no")
    public String item_no;
        
    @Column(name = "prorgramme_no")
    public String prorgramme_no;
    
    
    @Column(name = "input_date")
    public Timestamp input_date;
        
    @Column(name = "consumption_quantity")
    public BigDecimal consumption_quantity;
    
    @Column(name = "unit_cost")
    public BigDecimal unit_cost;
       
    @Column(name = "account_allocation")
    public String account_allocation;
    
    
    
       
}