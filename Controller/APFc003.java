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
import arc.apf.Dao.APFoLocation;
import arc.apf.Model.APFmLocation;
import arc.apf.Service.ARCsModel;
import arc.apf.Static.APFtMapping;

@Controller
@Scope("session")
@ACFgFunction(id="APFF003")
@RequestMapping(value=APFtMapping.APFF003)
public class APFc003 extends ACFaAppController {

    @Autowired ARCsModel moduleService;
   // @Autowired ACFoFunction functionDao;
    @Autowired APFoLocation locationDao;
   // @Autowired APFsFuncGp funcGpService; //click the object and click import
    @ACFgAuditKey String location_code;
	

	private class Search extends ACFgSearch {
		public Search() {
			super();
			setModel(APFmLocation.class);
			addRule(new ACFdSQLRule("location_code", RuleCondition.EQ, null, RuleCase.Insensitive));
			addRule(new ACFdSQLRule("location_name", RuleCondition.EQ, null, RuleCase.Insensitive));
		}
	}
	Search search = new Search();
	@RequestMapping(value=APFtMapping.APFF003_MAIN, method=RequestMethod.GET)
	public String main(ModelMap model) throws Exception {
		
		model.addAttribute("modules", moduleService.getlocationPairs());
		return view();
	}
	
	////form maintenance here, how to modify? imoprt apff001.java and remove grid in jsp file
	@RequestMapping(value=APFtMapping.APFF003_SEARCH_AJAX, method=RequestMethod.POST)
	@ResponseBody
	 public ACFgResponseParameters getGrid(@RequestBody ACFgRequestParameters param) throws Exception {
	      //The method getGrid responds to AJAX by obtain the Search JSON result and put in variable “grid_browse”.
	        // ACF will forward the content to client and post to the grid which ID equals to “grid_browse”.
	        search.setConnection(getConnection("ARCDB")); //get connection to the database
	        search.setValues(param);
	        search.setFocus(location_code);
	       // System.out.println("param:"+param);
	       // System.out.println(search.getGridResult());
	        return new ACFgResponseParameters().set("grid_browse", search.getGridResult()); // can only be called once, otherwise reset parameter
	    }
	 @RequestMapping(value=APFtMapping.APFF003_GET_FORM_AJAX, method=RequestMethod.POST)
	    @ResponseBody
	    public ACFgResponseParameters getForm(@RequestBody ACFgRequestParameters param) throws Exception {
	        location_code = param.get("location_code", String.class); //pick the value of parameter “func_id” from client
	        
	        //retrieves the result by DAO, and put in the variable “frm_main”. 
	        //ACF will forward the content to client and post to the form which ID equals to “frm_main”
	        return new ACFgResponseParameters().set("frm_main", locationDao.selectItem(location_code)); //change dao here
	    }
	 @ACFgTransaction
	    @RequestMapping(value=APFtMapping.APFF003_SAVE_AJAX, method=RequestMethod.POST)
	    @ResponseBody
	    public ACFgResponseParameters save(@RequestBody ACFgRequestParameters param) throws Exception { //function in the upper right "save" button
	      //the controller obtains the changes of form data 
	        List<APFmLocation> amendments = param.getList("form", APFmLocation.class);
	        //and call DAO to save the changes
	        APFmLocation lastItem = locationDao.saveItems(amendments, new ACFiSQLAssWriteInterface<APFmLocation>(){
	            
	            
	            //interface for the related functions
	            @Override
	            public boolean insert(APFmLocation newItem, ACFdSQLAssInsert ass) throws Exception {
	                //ass.columns.put("allow_print", 1); //without the allow_print column, the whole sql won't work
	                return false;
	            }

	            @Override
	            public boolean update(APFmLocation oldItem, APFmLocation newItem, ACFdSQLAssUpdate ass) throws Exception {
	                return false;
	            }

	            @Override
	            public boolean delete(APFmLocation oldItem, ACFdSQLAssDelete ass) throws Exception {
	                return false;
	            }
	        });
	        location_code = lastItem!=null? lastItem.location_code: null;

	        return new ACFgResponseParameters();
	    }
}

