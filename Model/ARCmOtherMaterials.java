package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_other_materials")
public class ARCmOtherMaterials extends ACFaAppModel {

    public ARCmOtherMaterials() throws Exception {
        super();
    }


    @Id
    @Column(name = "section_id")
    public String section_id;
    
    @Id
    @Column(name = "other_material")
    public String other_material;
    
    
    @Column(name = "unit_cost")
    public BigDecimal unit_cost;
    

    
    
}