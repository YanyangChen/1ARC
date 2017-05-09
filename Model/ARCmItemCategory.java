package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_item_category")
public class ARCmItemCategory extends ACFaAppModel {

    public ARCmItemCategory() throws Exception {
        super();
    }


    @Id
    @Column(name = "section_id")
    public String section_id;
    
    @Id
    @Column(name = "item_category_no")
    public String item_category_no;
    
    
    @Column(name = "item_category_description")
    public String item_category_description;
    

    
    
}