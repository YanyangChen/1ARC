package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_business_platform")
public class ARCmBusinessPlatform extends ACFaAppModel {

    public ARCmBusinessPlatform() throws Exception {
        super();
    }


    @Id
    @Column(name = "business_platform")
    public String business_platform;
    
    @Id
    @Column(name = "department")
    public String department;
    
    
    @Column(name = "description")
    public String description;
    
    @Column(name = "report_caption")
    public String report_caption;
    
    
}