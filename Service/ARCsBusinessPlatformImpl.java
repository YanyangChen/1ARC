package arc.apf.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.Static.ACFtDBUtility;


@Service
public class ARCsBusinessPlatformImpl extends ARCsBusinessPlatform {

    public ARCsBusinessPlatformImpl() throws Exception {
        super();
        // TODO Auto-generated constructor stub
    }
   
    public String getDepartmentByBusinessPlatform(String business_platform) throws Exception//For apff011 get content in form, AC, 2017/03/20
    {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
       /* ass.setCustomSQL("SELECT listagg(staff_name,chr(9)) as staff_name from ( " +
                         "  SELECT v.*, case when chi_name <> '' then chi_name else eng_name end as staff_name FROM APM_LOCAL_TEMP_PROG_PROD_MEMBER_VIEW v " +
                         ") " +
                         "WHERE prog_no = '%s' " +
                         "AND member_type = '%s' "
                         ,prog_no, staff_type);*/
        ass.setCustomSQL(
                "SELECT bp.department from arc_business_platform bp "+
                "WHERE bp.business_platform = '%s'"
                ,business_platform);
        
        List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("ARCDB"));
        
        return result.size()>0 ? result.get(0).getString("department") : "";
        
    }
    
    public List<ACFgRawModel> getBBusinessPlatform() throws Exception {//For apff011 get content in form, AC, 2017/03/20
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
        //ass.setCustomSQL("select mod_id as id, mod_id || ' - ' || mod_name as text from acf_module order by mod_seq");
        ass.setCustomSQL("select business_platform as id, business_platform as text from arc_business_platform");
        return ass.executeQuery();
        
    }

    public List<ACFgRawModel> getBDepartment() throws Exception {//For apff011 get content in form, AC, 2017/03/20
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
        //ass.setCustomSQL("select mod_id as id, mod_id || ' - ' || mod_name as text from acf_module order by mod_seq");
        ass.setCustomSQL("select department as id, department as text from arc_programme_master order by department asc");
        return ass.executeQuery();
        
    }


}