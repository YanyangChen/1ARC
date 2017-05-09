package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_account_allocation")
public class ARCmAccountAllocation extends ACFaAppModel {

    public ARCmAccountAllocation() throws Exception {
        super();
    }


    @Id
    @Column(name = "actual_account_allocation")
    public String actual_account_allocation;
    
    @Id
    @Column(name = "actual_account_description")
    public String actual_account_description;
    
    @Id
    @Column(name = "actual_report_caption_1")
    public String actual_report_caption_1;
    
    @Column(name = "actual_report_caption_2")
    public String actual_report_caption_2;
    
    @Column(name = "budget_account_allocation")
    public String budget_account_allocation;
    
    @Id
    @Column(name = "budget_account_description")
    public String budget_account_description;
    
    @Column(name = "budget_report_caption_1")
    public String budget_report_caption_1;
    
    @Column(name = "budget_report_caption_2")
    public String budget_report_caption_2;
}