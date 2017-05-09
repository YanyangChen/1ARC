package arc.apf.Model;


import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_payment_details")
public class ARCmPaymentDetails extends ACFaAppModel {

    public ARCmPaymentDetails() throws Exception {
        super();
    }


    @Id
    @Column(name = "payment_form_no")
    public String payment_form_no;
    
    @Id
    @Column(name = "sequence_no")
    public BigDecimal sequence_no;
    
    @Column(name = "sub_section_id")
    public String sub_section_id;
    
    
    @Column(name = "purchase_order_no")
    public String purchase_order_no;
    
    @Column(name = "invoice_no")
    public String invoice_no;
    
    
    @Column(name = "programme_no")
    public String programme_no;
    
    @Id
    @Column(name = "request_date")
    public Timestamp request_date;
    
    @Column(name = "from_episode_no")
    public BigDecimal from_episode_no;
    
    @Column(name = "to_episode_no")
    public BigDecimal to_episode_no;
    
    @Column(name = "particulars")
    public String particulars;
    
    @Column(name = "job_description")
    public String job_description;
    
    
    @Column(name = "account_allocation")
    public String account_allocation;
    
    @Column(name = "payment_amount")
    public BigDecimal payment_amount;
    
    @Column(name = "include_in_weekly_reporting")
    public String include_in_weekly_reporting;
    
    @Column(name = "include_in_monthly_reporting")
    public String include_in_monthly_reporting;
    
}