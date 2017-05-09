package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_payment_request")
public class ARCmPaymentRequest extends ACFaAppModel {

    public ARCmPaymentRequest() throws Exception {
        super();
    }


    @Id
    @Column(name = "payment_form_no")
    public String payment_form_no;
    
    
    @Column(name = "request_date")
    public Timestamp request_date;
        
    @Column(name = "section_id")
    public String section_id;
    
    @Column(name = "request_department")
    public String request_department;
        
    @Column(name = "supplier_code")
    public String supplier_code;
    
    @Column(name = "payment_purpose")
    public String payment_purpose;
        
    @Column(name = "despatch_date")
    public Timestamp despatch_date;
    
    @Column(name = "remarks")
    public String remarks;
        
    @Column(name = "cut_off_date")
    public Timestamp cut_off_date;

    @Column(name = "printed_by")
    public String printed_by;
    
    @Column(name = "printed_at")
    public Timestamp printed_at;
        
    @Column(name = "no_of_times_printed")
    public BigDecimal no_of_times_printed;
    
}