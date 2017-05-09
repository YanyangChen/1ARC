package arc.apf.Controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;





//import cal.exe.Model.EXEmFunction;
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
import arc.apf.Dao.ARCoLabourType;
import arc.apf.Model.ARCmLabourType;
import arc.apf.Service.ARCsModel;
import arc.apf.Static.APFtMapping;


@Controller
@Scope("session")
@ACFgFunction(id="APFF004")
@RequestMapping(value=APFtMapping.APFF004)
public class APFc004 extends ACFaAppController {

    //protected static final ACFaBaseDao<APFmLocation> SectionDao = null;
    @Autowired ARCsModel moduleService;
   // @Autowired ACFoFunction functionDao;
    @Autowired ARCoLabourType LabourDao;
   // @Autowired APFoSection sectionDao;
   // @Autowired APFsFuncGp funcGpService; //click the object and click import
    @ACFgAuditKey String labour_type;
    @ACFgAuditKey String effective_to_date;

	private class Search extends ACFgSearch {
		public Search() {
			super();
			setModel(ARCmLabourType.class);
			addRule(new ACFdSQLRule("section_id", RuleCondition.EQ, null, RuleCase.Insensitive));
			//addRule(new ACFdSQLRule("location_name", RuleCondition.EQ, null, RuleCase.Insensitive));
		}
		
		 @Override
	        public Search setValues(ACFgRequestParameters param) throws Exception {
	            super.setValues(param);// param is a object, "Search" 's mother class passed
	                if(!param.isEmptyOrNull("last_date_from")) {
	                wheres.and("effective_to_date", ACFdSQLRule.RuleCondition.GE, param.get("last_date_from", Timestamp.class));
	                }
	                if(!param.isEmptyOrNull("last_date_to")) {
	                wheres.and("effective_to_date", ACFdSQLRule.RuleCondition.LT, new Timestamp(param.get("last_date_to", Long.class) + 24*60*60*1000));
	                }
	                //wheres.and("po_date", ACFdSQLRule.RuleCondition.LT, param.get("po_date_e", Timestamp.class));
	            
	            orders.put("effective_to_date", false);
	            return this;
	        }
	}
	Search search = new Search();
	@RequestMapping(value=APFtMapping.APFF004_MAIN, method=RequestMethod.GET)
	public String main(ModelMap model) throws Exception {
		
		model.addAttribute("modules", moduleService.getSection()); //SQL code implemented in getLabout() function here
		return view();
	}
	
	////form maintenance here, how to modify? imoprt apff001.java and remove grid in jsp file
	@RequestMapping(value=APFtMapping.APFF004_SEARCH_AJAX, method=RequestMethod.POST)
	@ResponseBody
	 public ACFgResponseParameters getGrid(@RequestBody ACFgRequestParameters param) throws Exception {
	      //The method getGrid responds to AJAX by obtain the Search JSON result and put in variable “grid_browse”.
	        // ACF will forward the content to client and post to the grid which ID equals to “grid_browse”.
	     search = new Search();    
	        search.setConnection(getConnection("ARCDB")); //get connection to the database
	        search.setValues(param);
	        search.setFocus(labour_type);
	       // System.out.println("param:"+param);
	       // System.out.println(search.getGridResult());
	        return new ACFgResponseParameters().set("grid_browse", search.getGridResult()); // can only be called once, otherwise reset parameter
	    }
	 @RequestMapping(value=APFtMapping.APFF004_GET_FORM_AJAX, method=RequestMethod.POST)
	    @ResponseBody
	    public ACFgResponseParameters getForm(@RequestBody ACFgRequestParameters param) throws Exception {
	     labour_type = param.get("labour_type", String.class); //pick the value of parameter “func_id” from client
	        
	        //retrieves the result by DAO, and put in the variable “frm_main”. 
	        //ACF will forward the content to client and post to the form which ID equals to “frm_main”
	        return new ACFgResponseParameters().set("frm_main", LabourDao.selectItem(labour_type)); //change dao here
	    }
	 @ACFgTransaction
	    @RequestMapping(value=APFtMapping.APFF004_SAVE_AJAX, method=RequestMethod.POST)
	    @ResponseBody
	    public ACFgResponseParameters save(@RequestBody ACFgRequestParameters param) throws Exception { //function in the upper right "save" button
	      //the controller obtains the changes of form data 
	        List<ARCmLabourType> amendments = param.getList("form", ARCmLabourType.class);
	        //and call DAO to save the changes
	        ARCmLabourType lastItem = LabourDao.saveItems(amendments, new ACFiSQLAssWriteInterface<ARCmLabourType>(){
	            
	            private void validate(ARCmLabourType item) throws Exception {
	               // if(!item.func_id.matches("^[A-Z]{4}[0-9]{3}"))
	               //     throw exceptionService.error("EXE006E");
	                
	              //  if(!item.func_id.substring(0,3).equals(item.mod_id))
	               //     throw exceptionService.error("EXE007E");

	             //   if(!sectionDao.isExists(item.section_no))
	              //      throw exceptionService.error("APFF004E");
	            }
	            //interface for the related functions
	            @Override
	            public boolean insert(ARCmLabourType newItem, ACFdSQLAssInsert ass) throws Exception {
	                //ass.columns.put("allow_print", 1); //without the allow_print column, the whole sql won't work
	                validate(newItem);
	                return false;
	            }

	            @Override
	            public boolean update(ARCmLabourType oldItem, ARCmLabourType newItem, ACFdSQLAssUpdate ass) throws Exception {
	                validate(newItem);
	                return false;
	            }

	            @Override
	            public boolean delete(ARCmLabourType oldItem, ACFdSQLAssDelete ass) throws Exception {
	               // validate(newItem);
	                return false;
	            }
	        });
	        labour_type = lastItem!=null? lastItem.labour_type: null;

	        return new ACFgResponseParameters();
	    }
}

