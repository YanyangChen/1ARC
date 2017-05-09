package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_po_details")
public class ARCmPODetails extends ACFaAppModel {

    public ARCmPODetails() throws Exception {
        super();
    }


    @Id
    @Column(name = "purchase_order_no")
    public String purchase_order_no;
    
    @Id
    @Column(name = "item_no")
    public String item_no;
        
    @Column(name = "order_quantity")
    public BigDecimal order_quantity;
    
    @Column(name = "unit_cost")
    public BigDecimal unit_cost;
        
    
    
}