package arc.apf.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.Static.ACFtDBUtility;


@Service
public class ARCsLabourTypempl extends ARCsLabourType {

    public ARCsLabourTypempl() throws Exception {
        super();
        // TODO Auto-generated constructor stub
    }
   
    public String getSectionNameById(String section_id) throws Exception
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
       /* ass.setCustomSQL("SELECT listagg(staff_name,chr(9)) as staff_name from ( " +
                         "  SELECT v.*, case when chi_name <> '' then chi_name else eng_name end as staff_name FROM APM_LOCAL_TEMP_PROG_PROD_MEMBER_VIEW v " +
                         ") " +
                         "WHERE prog_no = '%s' " +
                         "AND member_type = '%s' "
                         ,prog_no, staff_type);*/
        ass.setCustomSQL(
                "SELECT s.section_name from arc_section s "+
                "WHERE s.section_id = '%s'"
                ,section_id);
        
        List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
        
        return result.size()>0 ? result.get(0).getString("section_name") : "";
        
    }
    
    public  List<ACFgRawModel> getLabourUnits(String labour_type) throws Exception//for combobox 2017/03/21
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
       
        ass.setCustomSQL(
                "SELECT * from arc_labour_type l "+
                "WHERE l.labour_type = '%s'"
                ,labour_type);
        
        
       /*
        *  ass.setCustomSQL(
                "SELECT * from dev.apw_item i "+
                "WHERE i.item_no= '%1$s'" +                
                "and i.item_no= '%2$s'"
                ,id,id);
        * 
        * */ 
        List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
        return result;
      //  return result.size()>0 ? result.get(0).getString("supplier_desc") : "";
        
    }
    
    public List<ACFgRawModel> getLabourType() throws Exception {//for combobox 2017/03/21
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
        //ass.setCustomSQL("select mod_id as id, mod_id || ' - ' || mod_name as text from acf_module order by mod_seq");
        ass.setCustomSQL("select labour_type as id, labour_type as text from arc_labour_type order by labour_type");
        return ass.executeQuery();
        
    }



}