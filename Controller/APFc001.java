package arc.apf.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import acf.acf.Abstract.ACFaAppController;
import acf.acf.Database.ACFdSQLAssDelete;
import acf.acf.Database.ACFdSQLAssInsert;
import acf.acf.Database.ACFdSQLAssUpdate;
import acf.acf.Database.ACFdSQLRule;
import acf.acf.Database.ACFdSQLRule.RuleCase;
import acf.acf.Database.ACFdSQLRule.RuleCondition;
import acf.acf.General.annotation.ACFgAuditKey;
import acf.acf.General.annotation.ACFgFunction;
import acf.acf.General.annotation.ACFgTransaction;
import acf.acf.General.core.ACFgRequestParameters;
import acf.acf.General.core.ACFgResponseParameters;
import acf.acf.General.core.ACFgSearch;
import acf.acf.Interface.ACFiSQLAssWriteInterface;
import arc.apf.Dao.ARCoSection;
import arc.apf.Model.ARCmSection;
import arc.apf.Service.ARCsModel;
import arc.apf.Static.APFtMapping;

@Controller
@Scope("session")
@ACFgFunction(id="APFF001")
@RequestMapping(value=APFtMapping.APFF001)
public class APFc001 extends ACFaAppController {
    
    @Autowired ARCsModel moduleService;
    //@Autowired ACFoFunction functionDao;
    @Autowired ARCoSection sectionDao; //modify according to the table asd
    //@Autowired APFsFuncGp funcGpService; //click the object and click import
    @ACFgAuditKey String section_id;
    @ACFgAuditKey String sub_section_id;
    
  //  Search search = new Search();

    private class Search extends ACFgSearch {
        public Search() {
            super();
            setModel(ARCmSection.class); //define a Search which accept 4 filters from client
            addRule(new ACFdSQLRule("section_id", RuleCondition.EQ, null, RuleCase.Sensitive)); //sec_id
            addRule(new ACFdSQLRule("sub_section_id", RuleCondition.EQ, null, RuleCase.Sensitive));//sub_sec_id
            addRule(new ACFdSQLRule("dds_code", RuleCondition._LIKE_, null, RuleCase.Insensitive));
            addRule(new ACFdSQLRule("section_name", RuleCondition._LIKE_, null, RuleCase.Insensitive));
            addRule(new ACFdSQLRule("report_caption", RuleCondition._LIKE_, null, RuleCase.Insensitive));
        }// ACF will forward the content to client and post to the grid which ID equals to “grid_browse”.
    }
    Search search = new Search();

    
	@RequestMapping(value=APFtMapping.APFF001_MAIN, method=RequestMethod.GET)
	public String main(ModelMap model) throws Exception {
	    model.addAttribute("section_id", section_id);
	    model.addAttribute("sub_section_id", sub_section_id); //set tow keys
	    //initial value in function maintenance form
        model.addAttribute("modules", moduleService.getAllModuleValuePairs()); //acf's function, get data from ACFDB
        //System.out.println(moduleService.getAllModuleValuePairs());
        //search value groups in search form and main form
        //model.addAttribute("moduleGroups", funcGpService.getModuleFuncGpIndex()); // no need to group tables just now

        return view();
		
	}
	@RequestMapping(value=APFtMapping.APFF001_SEARCH_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getGrid(@RequestBody ACFgRequestParameters param) throws Exception {
      //The method getGrid responds to AJAX by obtain the Search JSON result and put in variable “grid_browse”.
        // ACF will forward the content to client and post to the grid which ID equals to “grid_browse”.
        search.setConnection(getConnection("ARCDB")); //get connection to the database
        search.setValues(param);
        search.setFocus(section_id, sub_section_id); //set two keys
        System.out.println(param);
       // System.out.println(search.getGridResult());
        return new ACFgResponseParameters().set("grid_browse", search.getGridResult()); // can only be called once, otherwise reset parameter
    }

    @RequestMapping(value=APFtMapping.APFF001_GET_FORM_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getForm(@RequestBody ACFgRequestParameters param) throws Exception {
        section_id = param.get("section_id", String.class); //pick the value of parameter “func_id” from client
        sub_section_id = param.get("sub_section_id", String.class);  //set two keys!!
        //retrieves the result by DAO, and put in the variable “frm_main”. 
        //ACF will forward the content to client and post to the form which ID equals to “frm_main”
        return new ACFgResponseParameters().set("frm_main", sectionDao.selectItem(section_id, sub_section_id)); //change dao here //set two keys!!
    }

    @ACFgTransaction
    @RequestMapping(value=APFtMapping.APFF001_SAVE_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters save(@RequestBody ACFgRequestParameters param) throws Exception { //function in the upper right "save" button
      //the controller obtains the changes of form data 
        List<ARCmSection> amendments = param.getList("form", ARCmSection.class);
        //and call DAO to save the changes
        ARCmSection lastItem = sectionDao.saveItems(amendments, new ACFiSQLAssWriteInterface<ARCmSection>(){
            
            
            //interface for the related functions
            @Override
            public boolean insert(ARCmSection newItem, ACFdSQLAssInsert ass) throws Exception {
                //ass.columns.put("allow_print", 1); //without the allow_print column, the whole sql won't work
                return false;
            }

            @Override
            public boolean update(ARCmSection oldItem, ARCmSection newItem, ACFdSQLAssUpdate ass) throws Exception {
                return false;
            }

            @Override
            public boolean delete(ARCmSection oldItem, ACFdSQLAssDelete ass) throws Exception {
                return false;
            }
        });
        section_id = lastItem!=null? lastItem.section_id: null;

        return new ACFgResponseParameters();
    }

}