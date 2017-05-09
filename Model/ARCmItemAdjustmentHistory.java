package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_item_adjustment_history")
public class ARCmItemAdjustmentHistory extends ACFaAppModel {

    public ARCmItemAdjustmentHistory() throws Exception {
        super();
    }


    @Id
    @Column(name = "item_no")
    public String item_no;
    
    @Id
    @Column(name = "adjustment_date")
    public Timestamp adjustment_date;
    
    
    @Column(name = "adjust_quantity")
    public BigDecimal adjust_quantity;
    
    @Id
    @Column(name = "remarks")
    public String remarks;
    
    

    
    

    
    
}