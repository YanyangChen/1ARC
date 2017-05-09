package arc.apf.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.Static.ACFtDBUtility;


@Service
public class ARCsProgrammeMasterImpl extends ARCsProgrammeMaster {

    public ARCsProgrammeMasterImpl() throws Exception {
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
   
    
    public List<ACFgRawModel> getProgrammeNo() throws Exception {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
        //ass.setCustomSQL("select mod_id as id, mod_id || ' - ' || mod_name as text from acf_module order by mod_seq");
        ass.setCustomSQL("select programme_no as id, programme_name as text from arc_programme_master order by programme_no asc");
        return ass.executeQuery();
        
    }
    
    public List<ACFgRawModel> getBusinessPlatform() throws Exception {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
        //ass.setCustomSQL("select mod_id as id, mod_id || ' - ' || mod_name as text from acf_module order by mod_seq");
        ass.setCustomSQL("select business_platform as id, business_platform as text from arc_programme_master");
        return ass.executeQuery();
        
    }
    
    public List<ACFgRawModel> getDepartment() throws Exception {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
        //ass.setCustomSQL("select mod_id as id, mod_id || ' - ' || mod_name as text from acf_module order by mod_seq");
        ass.setCustomSQL("select department as id, department as text from arc_programme_master order by department asc");
        return ass.executeQuery();
        
    }
    
    public String getProgrammeName(String programme_no) throws Exception
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
       
        ass.setCustomSQL(
                "SELECT p.programme_name from arc_programme_master p "+
                "WHERE p.programme_no= '%s'"
                ,programme_no);
       List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
       return result.size()>0 ? result.get(0).getString("programme_name") : "";
      //  return result.size()>0 ? result.get(0).getString("supplier_desc") : "";
        
    }
    
    public  String getProgrammePlatform(String programme_no) throws Exception
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
       
        ass.setCustomSQL(
                "SELECT p.business_platform from arc_programme_master p "+
                "WHERE p.programme_no= '%s'"
                ,programme_no);
       List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
       return result.size()>0 ? result.get(0).getString("business_platform") : "";
      //  return result.size()>0 ? result.get(0).getString("supplier_desc") : "";
        
    }
    
    public  String  getProgrammeDepartment(String programme_no) throws Exception
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
       
        ass.setCustomSQL(
                "SELECT p.department from arc_programme_master p "+
                "WHERE p.programme_no= '%s'"
                ,programme_no);
       List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
       return result.size()>0 ? result.get(0).getString("department") : "";
      //  return result.size()>0 ? result.get(0).getString("supplier_desc") : "";
        
    }



   
}