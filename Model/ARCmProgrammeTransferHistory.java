package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "arc_programme_transfer_history")
public class ARCmProgrammeTransferHistory extends ACFaAppModel {

    public ARCmProgrammeTransferHistory() throws Exception {
        super();
    }


    @Id
    @Column(name = "transfer_date")
    public BigDecimal transfer_date;
    
    @Id
    @Column(name = "from_programme_no")
    public String from_programme_no;
    
    
    @Column(name = "to_programme_no")
    public String to_programme_no;
    
    @Id
    @Column(name = "remarks")
    public String remarks;
    
    

    
    

    
    
}