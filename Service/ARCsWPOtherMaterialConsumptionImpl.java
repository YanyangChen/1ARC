package arc.apf.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.Static.ACFtDBUtility;


@Service
public class ARCsWPOtherMaterialConsumptionImpl extends ARCsWPOtherMaterialConsumption {

    public ARCsWPOtherMaterialConsumptionImpl() throws Exception {
        super();
        // TODO Auto-generated constructor stub
    }
   
    public String getSectionNameById(String section_id) throws Exception
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
       
        ass.setCustomSQL(
                "SELECT s.section_name from arc_section s "+
                "WHERE s.section_id = '%s'"
                ,section_id);
        
        List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
        
        return result.size()>0 ? result.get(0).getString("section_name") : "";
        
    }
    
    public BigDecimal GenerateSequenceNo(String consumption_form_no) throws Exception//for apwf005 autogen sequence number in grid record
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
      
        ass.setCustomSQL(
                "SELECT max(sequence_no) as max_seq_no from arc_wp_other_material_consumption m "+
                "WHERE m.consumption_form_no = '%s'"
                ,consumption_form_no);
        
        List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
        BigDecimal max_seq_no = result.get(0).getBigDecimal("max_seq_no");
        if (max_seq_no == null)
            return BigDecimal.ONE;
        else
            return max_seq_no.add(BigDecimal.ONE);
     
    }



}