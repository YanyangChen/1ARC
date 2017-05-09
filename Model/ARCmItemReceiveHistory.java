package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_item_receive_history")
public class ARCmItemReceiveHistory extends ACFaAppModel {

    public ARCmItemReceiveHistory() throws Exception {
        super();
    }


    @Id
    @Column(name = "purchase_order_no")
    public String purchase_order_no;
    
    @Id
    @Column(name = "item_no")
    public String item_no;
    
    @Id
    @Column(name = "received_date")
    public Timestamp received_date;
    
    
    @Column(name = "received_quantity")
    public BigDecimal received_quantity;
    
    
    @Column(name = "back_order_quantity")
    public BigDecimal back_order_quantity;

    
    

    
    
}