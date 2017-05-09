package arc.apf.Model;


import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_direct_budget_details")
public class ARCmDirectBudgetDetails extends ACFaAppModel {

    public ARCmDirectBudgetDetails() throws Exception {
        super();
    }


    @Id
    @Column(name = "programme_no")
    public String programme_no;
    
    @Id
    @Column(name = "account_allocation")
    public String account_allocation;
    
    
    @Column(name = "detail_direct_budget_amount")
    public BigDecimal detail_direct_budget_amount;
    
  
    
}