package arc.apf.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.Static.ACFtDBUtility;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.General.core.ACFgRequestParameters;
import acf.acf.General.core.ACFgResponseParameters;
import acf.acf.General.core.ACFgSearch;


@Service
public class ARCsItemMasterImpl extends ARCsItemMaster {

    public ARCsItemMasterImpl() throws Exception {
        super();
        // TODO Auto-generated constructor stub
    }
   
    public String getSectionNameById(String section_id) throws Exception//for ajax retrieve, AC, 2017/04/11
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
    
    public List<ACFgRawModel> getItem_No() throws Exception {//for combobox, AC, 2017/04/11
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
        //ass.setCustomSQL("select mod_id as id, mod_id || ' - ' || mod_name as text from acf_module order by mod_seq");
        ass.setCustomSQL("select distinct item_no as id, item_no as text from dev.apw_item");
        return ass.executeQuery();
        
    }
    
    public String getLocationcodeByItemNo(String item_no) throws Exception//for apwf005 grid column ajax retrieve, AC, 2017/04/11
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
       /* ass.setCustomSQL("SELECT listagg(staff_name,chr(9)) as staff_name from ( " +
                         "  SELECT v.*, case when chi_name <> '' then chi_name else eng_name end as staff_name FROM APM_LOCAL_TEMP_PROG_PROD_MEMBER_VIEW v " +
                         ") " +
                         "WHERE prog_no = '%s' " +
                         "AND member_type = '%s' "
                         ,prog_no, staff_type);*/
        ass.setCustomSQL(
                "SELECT im.location_code from arc_item_master im "+
                "WHERE im.item_no = '%s'"
                ,item_no);
        
        List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
        
        return result.size()>0 ? result.get(0).getString("location_code") : "";
        
    }
    
    public String getItemDescByItemNo(String item_no) throws Exception//for apwf005 grid column ajax retrieve, AC, 2017/04/11
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
       /* ass.setCustomSQL("SELECT listagg(staff_name,chr(9)) as staff_name from ( " +
                         "  SELECT v.*, case when chi_name <> '' then chi_name else eng_name end as staff_name FROM APM_LOCAL_TEMP_PROG_PROD_MEMBER_VIEW v " +
                         ") " +
                         "WHERE prog_no = '%s' " +
                         "AND member_type = '%s' "
                         ,prog_no, staff_type);*/
        ass.setCustomSQL(
                "SELECT im.item_description_1 from arc_item_master im "+
                "WHERE im.item_no = '%s'"
                ,item_no);
        
        List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
        
        return result.size()>0 ? result.get(0).getString("item_description_1") : "";
        
    }
    
    public BigDecimal getUniteCostByItemNo(String item_no) throws Exception//for apwf005 grid column ajax retrieve, AC, 2017/04/11
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
       /* ass.setCustomSQL("SELECT listagg(staff_name,chr(9)) as staff_name from ( " +
                         "  SELECT v.*, case when chi_name <> '' then chi_name else eng_name end as staff_name FROM APM_LOCAL_TEMP_PROG_PROD_MEMBER_VIEW v " +
                         ") " +
                         "WHERE prog_no = '%s' " +
                         "AND member_type = '%s' "
                         ,prog_no, staff_type);*/
        ass.setCustomSQL(
                "SELECT im.reference_unit_cost from arc_item_master im "+
                "WHERE im.item_no = '%s'"
                ,item_no);
        
        List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
        
        return  result.size()>0 ? result.get(0).getBigDecimal("reference_unit_cost") : new BigDecimal(0);
        
    }
    
    public  List<ACFgRawModel> getItemUnits(String item_no) throws Exception//for apwf005 grid column ajax retrieve, AC, 2017/04/11
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
       
        ass.setCustomSQL(
                "SELECT * from dev.arc_item_master i "+
                "WHERE i.item_no= '%s'"
                ,item_no);
     
        List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
        return result;
      //  return result.size()>0 ? result.get(0).getString("supplier_desc") : "";
        
    }



}