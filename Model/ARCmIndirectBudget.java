package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_indirect_budget")
public class ARCmIndirectBudget extends ACFaAppModel {

    public ARCmIndirectBudget() throws Exception {
        super();
    }


    @Id
    @Column(name = "programme_no")
    public String programme_no;
    
    @Id
    @Column(name = "section_id")
    public String section_id;
    
    @Id
    @Column(name = "sub_section_id")
    public String sub_section_id;
    
    @Column(name = "indirect_budget_amount")
    public BigDecimal indirect_budget_amount;
    
    
    @Column(name = "indirect_budget_hour")
    public BigDecimal indirect_budget_hour;
    
}