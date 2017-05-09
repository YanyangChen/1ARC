package arc.apf.Model;


import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_item_master")
public class ARCmItemMaster extends ACFaAppModel {

    public ARCmItemMaster() throws Exception {
        super();
    }


    @Id
    @Column(name = "item_no")
    public String item_no;
    
    
    @Column(name = "section_id")
    public String section_id;
    
    @Column(name = "item_description_1")
    public String item_description_1;
    
    
    @Column(name = "item_description_2")
    public String item_description_2;
    
    @Column(name = "material_type")
    public String material_type;
    
    
    @Column(name = "location_code")
    public String location_code;
    
    @Column(name = "un_it")
    public String un_it;
    
    @Column(name = "reference_unit_cost")
    public BigDecimal reference_unit_cost;
    
    @Column(name = "safety_quantity")
    public BigDecimal safety_quantity;
    
  
    
}