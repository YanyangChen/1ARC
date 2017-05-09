package arc.apf.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;













//import cal.amm.Static.AMMtMapping;
//import cal.exe.Model.EXEmFunction;
import acf.acf.Abstract.ACFaAppController;
import acf.acf.General.annotation.ACFgFunction;
import acf.acf.General.core.ACFgRequestParameters;
import acf.acf.General.core.ACFgResponseParameters;
import arc.apf.Service.ARCsAccountAllocation;
import arc.apf.Service.ARCsBusinessPlatform;
import arc.apf.Service.ARCsSection;
import arc.apf.Static.APFtMapping;

@Controller
@Scope("session")
@ACFgFunction(id="")
@RequestMapping(value=APFtMapping.APF) 
public class APFc extends ACFaAppController {
    
    @Autowired ARCsSection SectionService;
    @Autowired ARCsAccountAllocation AccountAllocationService;
    @Autowired ARCsBusinessPlatform BusinessPlatformService;
   
    
    public APFc() throws Exception {
        super();
    }
    @RequestMapping(value=APFtMapping.APF_GET_SECTION_NAME_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getProgramme(@RequestBody ACFgRequestParameters param) throws Exception {
        setAuditKey("section_id", param.get("section_id", String.class));
              getResponseParameters().put("section_name",         SectionService.getSectionNameByID((param.get("section_id", String.class))));
       return getResponseParameters();
    } 
    
    @RequestMapping(value=APFtMapping.APF_ALLOCATION_ACCOUNT_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getBudgetAccountAllocation(@RequestBody ACFgRequestParameters param) throws Exception {
        setAuditKey("budget_account_allocation", param.get("budget_account_allocation", String.class));
              getResponseParameters().put("budget_account_description",         AccountAllocationService.getBudgetAccountDescription((param.get("budget_account_allocation", String.class))));
       return getResponseParameters();
    } 
    
    
    @RequestMapping(value=APFtMapping.APF_GET_SECTION_NAME_BY_ID_AND_SUBID_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getSectionname(@RequestBody ACFgRequestParameters param) throws Exception {
        setAuditKey("section_id", param.get("section_id", String.class));
        setAuditKey("sub_section_id", param.get("sub_section_id", String.class));
              getResponseParameters().put("section_name",         SectionService.getSectionNameByIDAndSubID((param.get("section_id", String.class)),(param.get("sub_section_id", String.class))));
       return getResponseParameters();
    } 
    
      
    @RequestMapping(value=APFtMapping.APF_GET_DEPARTMENT_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getDepartment(@RequestBody ACFgRequestParameters param) throws Exception {
        setAuditKey("business_platform", param.get("business_platform", String.class));
           getResponseParameters().put("department",         BusinessPlatformService.getDepartmentByBusinessPlatform((param.get("business_platform", String.class))));
         return getResponseParameters();
    } 
    

}
