package arc.apf.Model;


import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_item_inventory")
public class ARCmItemInventory extends ACFaAppModel {

    public ARCmItemInventory() throws Exception {
        super();
    }


    @Id
    @Column(name = "item_no")
    public String item_no;
    
    @Id
    @Column(name = "purchase_order_no")
    public String purchase_order_no;
    
    @Column(name = "purchase_order_date")
    public Timestamp purchase_order_date;
    
    
    @Column(name = "order_quantity")
    public BigDecimal order_quantity;
    
    @Column(name = "received_quantity")
    public BigDecimal received_quantity;
    
    
    @Column(name = "receive_date")
    public Timestamp receive_date;
    
    @Column(name = "consumed_quantity")
    public BigDecimal consumed_quantity;
    
    @Column(name = "adjusted_quantity")
    public BigDecimal adjusted_quantity;
    
    @Column(name = "back_order_quantity")
    public BigDecimal back_order_quantity;
    

    
    @Column(name = "unit_cost")
    public BigDecimal unit_cost;
    
}