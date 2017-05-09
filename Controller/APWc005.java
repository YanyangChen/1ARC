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
import acf.acf.Database.ACFdSQLAssSelect;
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
import acf.acf.Interface.ACFiCallback;
import acf.acf.Interface.ACFiSQLAssWriteInterface;
import arc.apf.Dao.ARCoOtherMaterials;
import arc.apf.Dao.ARCoWPConsumptionHeader;
import arc.apf.Dao.ARCoWPConsumptionItem;
import arc.apf.Dao.ARCoWPLabourConsumption;
import arc.apf.Dao.ARCoWPOtherMaterialConsumption;
import arc.apf.Model.ARCmItemInventory;
import arc.apf.Model.ARCmWPConsumptionHeader;
import arc.apf.Model.ARCmWPConsumptionItem;
import arc.apf.Model.ARCmWPLabourConsumption;
import arc.apf.Model.ARCmWPOtherMaterialConsumption;
import arc.apf.Service.ARCsAccountAllocation;
import arc.apf.Service.ARCsItemInventory;
import arc.apf.Service.ARCsLabourType;
import arc.apf.Service.ARCsModel;
import arc.apf.Service.ARCsOtherMaterial;
import arc.apw.Static.APWtMapping;

@Controller
@Scope("session")
@ACFgFunction(id="APWF005")
@RequestMapping(value=APWtMapping.APWF005)
public class APWc005 extends ACFaAppController {
    
    @Autowired ARCsModel moduleService;
    @Autowired ARCsLabourType LabourTypeService;
    //@Autowired ACFoFunction functionDao;
    @Autowired ARCoOtherMaterials othermaterialsDao; //modify according to the table
    @Autowired ARCoWPConsumptionHeader WPconsumptionHeaderDao;
    @Autowired ARCoWPConsumptionItem WPconsumptionItemDao;
    @Autowired ARCoWPOtherMaterialConsumption OtherMaterialConsumptionDao;
    @Autowired ARCoWPLabourConsumption WPLabourConsumptionDao;
    @Autowired ARCsItemInventory InventoryService;
    @Autowired ARCsOtherMaterial OtherMaterialService;
    @Autowired ARCsAccountAllocation AccountAllocationService;
    //@Autowired APFsFuncGp funcGpService; //click the object and click import
    @ACFgAuditKey String consumption_form_no;
    @ACFgAuditKey String item_no;
    @ACFgAuditKey String purchase_order_no;
   // @ACFgAuditKey String other_material;
    //@ACFgAuditKey BigDecimal unit_cost;
    
  //  Search search = new Search();

    private class Search extends ACFgSearch {
        public Search() {
            super();
            //setModel(ARCmWPConsumptionHeader.class); //define a Search which accept 4 filters from client
            setCustomSQL("select * from (select H.*, P.programme_name, P.chinese_programme_name, L.location_name " +
                    "from arc_wp_consumption_header H, arc_programme_master P, arc_location L " +
                    "where H.programme_no = P.programme_no "
                    + "and H.location_code = L.location_code)");
            setKey("consumption_form_no");////modify according to arc table's columns apw_ arc_po_header apw_supplier -> dev.arc_supplier
            addRule(new ACFdSQLRule("consumption_form_no", RuleCondition.EQ, null, RuleCase.Insensitive));
            addRule(new ACFdSQLRule("programme_no", RuleCondition.EQ, null, RuleCase.Insensitive));
        }// ACF will forward the content to client and post to the grid which ID equals to “grid_browse”.
        @Override
        public Search setValues(ACFgRequestParameters param) throws Exception { //use the search class to setup an object
            super.setValues(param);// param is a object, "Search" 's mother class passed
                if(!param.isEmptyOrNull("start_date")) {
                wheres.and("completion_date", ACFdSQLRule.RuleCondition.GE, param.get("start_date", Timestamp.class));
                }//// change date to column name
                if(!param.isEmptyOrNull("end_date")) {
                wheres.and("completion_date", ACFdSQLRule.RuleCondition.LT, new Timestamp(param.get("end_date", Long.class) + 24*60*60*1000));
                }
                //wheres.and("po_date", ACFdSQLRule.RuleCondition.LT, param.get("po_date_e", Timestamp.class));
            
            orders.put("completion_date", false);
            return this;
        }
    }
    Search search = new Search();

    
    @RequestMapping(value=APWtMapping.APWF005_MAIN, method=RequestMethod.GET)
    public String main(ModelMap model) throws Exception {
        model.addAttribute("consumption_form_no", consumption_form_no);
        //model.addAttribute("other_material", other_material); //set row keys
        //model.addAttribute("unit_cost", unit_cost);
        //initial value in function maintenance form
        model.addAttribute("itemnoselect", InventoryService.getItemNos()); //acf's function, get data from ACFDB
        model.addAttribute("OtherMaterialselect", OtherMaterialService.getOtherMaterial());
        model.addAttribute("ACselect", AccountAllocationService.getActualAccountAllocation());
        model.addAttribute("LabourTypeselect", LabourTypeService.getLabourType());
        //System.out.println(moduleService.getAllModuleValuePairs());
        //search value groups in search form and main form
        //model.addAttribute("moduleGroups", funcGpService.getModuleFuncGpIndex()); // no need to group tables just now

        return view();
        
    }
    @RequestMapping(value=APWtMapping.APWF005_SEARCH_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getGrid(@RequestBody ACFgRequestParameters param) throws Exception {
      //The method getGrid responds to AJAX by obtain the Search JSON result and put in variable “grid_browse”.
        // ACF will forward the content to client and post to the grid which ID equals to “grid_browse”.
        search.setConnection(getConnection("ARCDB")); //get connection to the database
        search.setValues(param);
        search.setFocus(consumption_form_no); //set two keys
        System.out.println(param);
       // System.out.println(search.getGridResult());
        return new ACFgResponseParameters().set("grid_browse", search.getGridResult()); // can only be called once, otherwise reset parameter
    }

    @RequestMapping(value=APWtMapping.APWF005_GET_FORM_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getForm(@RequestBody ACFgRequestParameters param) throws Exception {
        consumption_form_no = param.get("consumption_form_no", String.class); //pick the value of parameter “func_id” from client
        getConsumptionItem(param.getRequestParameter("item_browse")); 
        getMaterialConsumption(param.getRequestParameter("material_browse")); 
        getLabourConsumption(param.getRequestParameter("labour_browse")); 
        //other_material = param.get("other_material", String.class);  //set two keys!!
        //unit_cost = param.get("unit_cost", BigDecimal.class);
        //retrieves the result by DAO, and put in the variable “frm_main”. 
        //ACF will forward the content to client and post to the form which ID equals to “frm_main”
        return getResponseParameters().set("frm_main", WPconsumptionHeaderDao.selectItem(consumption_form_no)); //change dao here //set two keys!!
    }
    
    @RequestMapping(value=APWtMapping.APWF005_GET_CONSUMPTION_ITEM_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getConsumptionItem(@RequestBody ACFgRequestParameters param) throws Exception {                                
        ACFdSQLAssSelect select = new ACFdSQLAssSelect(); 
      //  select.setCustomSQL("");
      
        select.setCustomSQL("select * from(select I.*, P.purchase_order_date, P.purchase_order_no, I.consumption_quantity * I.unit_cost as amount, IM.item_description_1 "
                + "from arc_wp_consumption_item I, arc_po_header P, arc_item_master IM "
                + "where P.purchase_order_no = I.purchase_order_no "
                + "and I.item_no = IM.item_no)");
        select.setKey("consumption_form_no");
        select.wheres.and("consumption_form_no", consumption_form_no);
        //select.orders.put("seq", true);
        return getResponseParameters().set("item_browse", select.executeGridQuery(getConnection("ARCDB"), param));
        //only getResponseParameters() is right, not 'new ACFgResponseParameters()' otherwise parameters won't pass
      
      }
    
    @RequestMapping(value=APWtMapping.APWF005_GET_MATERIAL_CONSUMPTION_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getMaterialConsumption(@RequestBody ACFgRequestParameters param) throws Exception {                                
        ACFdSQLAssSelect select = new ACFdSQLAssSelect(); 
      //  select.setCustomSQL("");
      
        select.setCustomSQL("select m.*, m.unit_cost as other_material_amount from arc_wp_other_material_consumption m");
        select.setKey("consumption_form_no");
        select.wheres.and("consumption_form_no", consumption_form_no);
        //select.orders.put("seq", true);
        return getResponseParameters().set("material_browse", select.executeGridQuery(getConnection("ARCDB"), param));
      
      }
    
    @RequestMapping(value=APWtMapping.APWF005_GET_LABOUR_CONSUMPTION_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getLabourConsumption(@RequestBody ACFgRequestParameters param) throws Exception {                                
        ACFdSQLAssSelect select = new ACFdSQLAssSelect(); 
      //  select.setCustomSQL("");
      
        select.setCustomSQL("select * from(select l.*, lt.labour_type_description, lt.labour_type, l.no_of_hours * l.hourly_rate as amount "
                + "from arc_wp_labour_consumption l, arc_labour_type lt "
                + "where l.labour_type = lt.labour_type)");
        select.setKey("consumption_form_no");
        select.wheres.and("consumption_form_no", consumption_form_no);
        //select.orders.put("seq", true);
        return getResponseParameters().set("labour_browse", select.executeGridQuery(getConnection("ARCDB"), param));
      
      }

    @ACFgTransaction
    @RequestMapping(value=APWtMapping.APWF005_SAVE_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters save(@RequestBody ACFgRequestParameters param) throws Exception { //function in the upper right "save" button
      //the controller obtains the changes of form data 
        List<ARCmWPConsumptionHeader> amendments = param.getList("form", ARCmWPConsumptionHeader.class);
        final List<ARCmWPConsumptionItem> Itemamendments = param.getList("Item", ARCmWPConsumptionItem.class);
        final List<ARCmWPOtherMaterialConsumption> Materialamendments = param.getList("Material", ARCmWPOtherMaterialConsumption.class);
        final List<ARCmWPLabourConsumption> Labouramendments = param.getList("Labour", ARCmWPLabourConsumption.class);
        //and call DAO to save the changes
        ARCmWPConsumptionHeader lastItem = WPconsumptionHeaderDao.saveItems(amendments, new ACFiSQLAssWriteInterface<ARCmWPConsumptionHeader>(){
            
            
            //interface for the related functions
            @Override
            public boolean insert(ARCmWPConsumptionHeader newItem, ACFdSQLAssInsert ass) throws Exception {
                //ass.columns.put("allow_print", 1); //without the allow_print column, the whole sql won't work
                ass.setAfterExecute(new ACFiCallback() {
                    @Override
                    public void callback() throws Exception {
                        if (Itemamendments != null)
                            WPconsumptionItemDao.saveItems(Itemamendments);
                        if (Materialamendments != null)
                            OtherMaterialConsumptionDao.saveItems(Materialamendments);
                        if (Labouramendments != null)
                            WPLabourConsumptionDao.saveItems(Labouramendments);
                 
                    }
                });
                return false;
            }

            @Override
            public boolean update(ARCmWPConsumptionHeader oldItem, ARCmWPConsumptionHeader newItem, ACFdSQLAssUpdate ass) throws Exception {
                ass.setAfterExecute(new ACFiCallback() {
                    @Override
                    public void callback() throws Exception {
                        if (Itemamendments != null)
                            WPconsumptionItemDao.saveItems(Itemamendments);
                        if (Materialamendments != null)
                            OtherMaterialConsumptionDao.saveItems(Materialamendments);
                        if (Labouramendments != null)
                            WPLabourConsumptionDao.saveItems(Labouramendments);
                 
                    }
                });
                return false;
            }

            @Override
            public boolean delete(ARCmWPConsumptionHeader oldItem, ACFdSQLAssDelete ass) throws Exception {
                return false;
            }
        });
        consumption_form_no = lastItem!=null? lastItem.consumption_form_no: null;// what's the purpose of this?
        //other_material = lastItem!=null? lastItem.other_material: null;
        //unit_cost = lastItem!=null? lastItem.unit_cost: null;

        return new ACFgResponseParameters();
    }

}