package arc.apf.Model;


import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_direct_budget")
public class ARCmDirectBudget extends ACFaAppModel {

    public ARCmDirectBudget() throws Exception {
        super();
    }


    @Id
    @Column(name = "programme_no")
    public String programme_no;
    
    @Id
    @Column(name = "sequence_no")
    public BigDecimal sequence_no;
    
    
    @Column(name = "direct_budget_description")
    public String direct_budget_description;
    
    @Column(name = "direct_budget_amount")
    public BigDecimal direct_budget_amount;
    
    

    
}