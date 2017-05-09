package arc.apf.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.Static.ACFtDBUtility;


@Service
public class ARCsIndirectBudgetImpl extends ARCsIndirectBudget {

    public ARCsIndirectBudgetImpl() throws Exception {
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



}