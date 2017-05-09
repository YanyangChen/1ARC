package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_photo_other_material_consumption")
public class ARCmPhotoOtherMaterialConsumption extends ACFaAppModel {

    public ARCmPhotoOtherMaterialConsumption() throws Exception {
        super();
    }


    @Id
    @Column(name = "job_no")
    public String job_no;
    
    
    @Column(name = "programme_no")
    public String programme_no;
    
    
    @Column(name = "input_date")
    public Timestamp input_date;
    
    @Id
    @Column(name = "sequence_no")
    public String sequence_no;
    
    
    @Column(name = "other_material_description")
    public String other_material_description;
    
    
    @Column(name = "unit_cost")
    public BigDecimal unit_cost;
    
    
    @Column(name = "account_allocation")
    public String account_allocation;
    
    
    @Column(name = "other_material_amount")
    public BigDecimal other_material_amount;
    
    
}