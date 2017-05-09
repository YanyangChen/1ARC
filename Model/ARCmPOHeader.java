package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_po_header")
public class ARCmPOHeader extends ACFaAppModel {

    public ARCmPOHeader() throws Exception {
        super();
    }


    @Id
    @Column(name = "purchase_order_no")
    public String purchase_order_no;
    
    @Column(name = "purchase_order_date")
    public Timestamp purchase_order_date;
    
    @Column(name = "latest_receive_date")
    public Timestamp latest_receive_date;
    
    @Column(name = "section_id")
    public String section_id;
    
    @Column(name = "receive_location")
    public String receive_location ;
    
    @Column(name = "in_stock_location_code")
    public String in_stock_location_code;
    
    @Column(name = "department_reference_no")
    public String department_reference_no;
    
    @Column(name = "supplier_code")
    public String supplier_code;
    
    @Column(name = "remarks")
    public String remarks;
    
    @Column(name = "cancel_indicator")
    public String cancel_indicator;
    
    @Column(name = "cancel_by")
    public String cancel_by;
    
    @Column(name = "cancel_date")
    public Timestamp cancel_date;
    
    @Column(name = "printed_by")
    public String printed_by;
    
    @Column(name = "printed_at")
    public Timestamp printed_at;
    
    @Column(name = "no_of_times_printed")
    public BigDecimal no_of_times_printed;
    
}