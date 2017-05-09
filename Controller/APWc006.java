package arc.apw.Controller;

import java.math.BigDecimal;
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
import arc.apf.Dao.ARCoItemAdjustmentHistory;
import arc.apf.Dao.ARCoOtherMaterials;
import arc.apf.Model.ARCmItemAdjustmentHistory;
import arc.apf.Model.ARCmItemAdjustmentHistory;
import arc.apf.Service.ARCsModel;
import arc.apw.Static.APWtMapping;

@Controller
@Scope("session")
@ACFgFunction(id="APWF006")
@RequestMapping(value=APWtMapping.APWF006)
public class APWc006 extends ACFaAppController {
    
    @Autowired ARCsModel moduleService;
    //@Autowired ACFoFunction functionDao;
    @Autowired ARCoItemAdjustmentHistory ItemAdjustmentHistoryDao; //modify according to the table
    //@Autowired APFsFuncGp funcGpService; //click the object and click import
    @ACFgAuditKey String section_id;
    @ACFgAuditKey String other_material;
    @ACFgAuditKey BigDecimal unit_cost;
    
    @ACFgAuditKey String item_no;
    @ACFgAuditKey Timestamp adjustment_date;
    
  //  Search search = new Search();

    private class Search extends ACFgSearch {
        public Search() {
            super();
            setCustomSQL("select * from (select A.*, A.adjustment_date as adjustment_datee, im.item_description_1 as item_desc " +
                    "from arc_item_adjustment_history A, arc_item_master im "
                    + "where A.item_no = im.item_no)");
            setKey("adjustment_date,item_no");
            
            addRule(new ACFdSQLRule("item_no", RuleCondition._LIKE_, null, RuleCase.Insensitive));
        }// ACF will forward the content to client and post to the grid which ID equals to “grid_browse”.
        @Override
        public Search setValues(ACFgRequestParameters param) throws Exception { //use the search class to setup an object
            super.setValues(param);// param is a object, "Search" 's mother class passed
                if(!param.isEmptyOrNull("start_date")) {
                wheres.and("adjustment_date", ACFdSQLRule.RuleCondition.GE, param.get("start_date", Timestamp.class));
                }//// change date to column name
                if(!param.isEmptyOrNull("end_date")) {
                wheres.and("adjustment_date", ACFdSQLRule.RuleCondition.LT, new Timestamp(param.get("end_date", Long.class) + 24*60*60*1000));
                }
                //wheres.and("po_date", ACFdSQLRule.RuleCondition.LT, param.get("po_date_e", Timestamp.class));
            
            orders.put("adjustment_date", false);
            return this;
        }
    }
    Search search = new Search();

    
    @RequestMapping(value=APWtMapping.APWF006_MAIN, method=RequestMethod.GET)
    public String main(ModelMap model) throws Exception {
        model.addAttribute("section_id", section_id);
        model.addAttribute("other_material", other_material); //set row keys
        //model.addAttribute("unit_cost", unit_cost);
        //initial value in function maintenance form
        //model.addAttribute("modules", moduleService.getAllModuleValuePairs()); //acf's function, get data from ACFDB
        //System.out.println(moduleService.getAllModuleValuePairs());
        //search value groups in search form and main form
        //model.addAttribute("moduleGroups", funcGpService.getModuleFuncGpIndex()); // no need to group tables just now

        return view();
        
    }
    @RequestMapping(value=APWtMapping.APWF006_SEARCH_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getGrid(@RequestBody ACFgRequestParameters param) throws Exception {
      //The method getGrid responds to AJAX by obtain the Search JSON result and put in variable “grid_browse”.
        // ACF will forward the content to client and post to the grid which ID equals to “grid_browse”.
        search.setConnection(getConnection("ARCDB")); //get connection to the database
        search.setValues(param);
        search.setFocus(item_no, adjustment_date); //set two keys
        System.out.println(param);
       // System.out.println(search.getGridResult());
        return new ACFgResponseParameters().set("grid_browse", search.getGridResult()); // can only be called once, otherwise reset parameter
    }

    @RequestMapping(value=APWtMapping.APWF006_GET_FORM_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getForm(@RequestBody ACFgRequestParameters param) throws Exception {
        adjustment_date = param.get("adjustment_date", Timestamp.class); //pick the value of parameter “func_id” from client
        item_no = param.get("item_no", String.class);  //set two keys!!
        //unit_cost = param.get("unit_cost", BigDecimal.class);
        //retrieves the result by DAO, and put in the variable “frm_main”. 
        //ACF will forward the content to client and post to the form which ID equals to “frm_main”
        return new ACFgResponseParameters().set("frm_main", ItemAdjustmentHistoryDao.selectItem(item_no, adjustment_date)); //change dao here //set two keys!!
    }

    @ACFgTransaction
    @RequestMapping(value=APWtMapping.APWF006_SAVE_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters save(@RequestBody ACFgRequestParameters param) throws Exception { //function in the upper right "save" button
      //the controller obtains the changes of form data 
        List<ARCmItemAdjustmentHistory> amendments = param.getList("form", ARCmItemAdjustmentHistory.class);
        //and call DAO to save the changes
        ARCmItemAdjustmentHistory lastItem = ItemAdjustmentHistoryDao.saveItems(amendments, new ACFiSQLAssWriteInterface<ARCmItemAdjustmentHistory>(){
            
            
            //interface for the related functions
            @Override
            public boolean insert(ARCmItemAdjustmentHistory newItem, ACFdSQLAssInsert ass) throws Exception {
                //ass.columns.put("allow_print", 1); //without the allow_print column, the whole sql won't work
                return false;
            }

            @Override
            public boolean update(ARCmItemAdjustmentHistory oldItem, ARCmItemAdjustmentHistory newItem, ACFdSQLAssUpdate ass) throws Exception {
                return false;
            }

            @Override
            public boolean delete(ARCmItemAdjustmentHistory oldItem, ACFdSQLAssDelete ass) throws Exception {
                return false;
            }
        });
        item_no = lastItem!=null? lastItem.item_no: null;// what's the purpose of this?
        adjustment_date = lastItem!=null? lastItem.adjustment_date: null;
        //unit_cost = lastItem!=null? lastItem.unit_cost: null;

        return new ACFgResponseParameters();
    }

}