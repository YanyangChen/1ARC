package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_wp_consumption_item")
public class ARCmWPConsumptionItem extends ACFaAppModel {

    public ARCmWPConsumptionItem() throws Exception {
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
    @Column(name = "item_no")
    public String item_no;
        
    @Id
    @Column(name = "purchase_order_no")
    public String purchase_order_no;
    
    @Column(name = "re_used_indicator")
    public String re_used_indicator;
    
    @Column(name = "consumption_quantity")
    public BigDecimal consumption_quantity;
    
    @Column(name = "unit_cost")
    public BigDecimal unit_cost;
    
    @Column(name = "account_allocation")
    public String account_allocation;
    
    
}