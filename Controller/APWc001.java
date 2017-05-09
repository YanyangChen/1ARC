package arc.apw.Controller;

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
import arc.apf.Dao.ARCoItemInventory;
import arc.apf.Dao.ARCoItemMaster;
import arc.apf.Model.ARCmItemInventory;
import arc.apf.Model.ARCmItemMaster;
import arc.apf.Service.ARCsItemCategory;
//import arc.apf.Model.ARCmItemMaster;
//import arc.apf.Service.APFsFuncGp;
import arc.apf.Service.ARCsModel;
import arc.apw.Static.APWtMapping;

@Controller
@Scope("session")
@ACFgFunction(id="APWF001")
@RequestMapping(value=APWtMapping.APWF001)
public class APWc001 extends ACFaAppController {
    
    @Autowired ARCsModel moduleService;
    @Autowired ARCsItemCategory ItemCategoryService;
    @Autowired ARCoItemMaster ItemMasterDao; //modify according to the table
    @Autowired ARCoItemInventory ItemInventoryDao;
    //@Autowired APFsFuncGp funcGpService; //click the object and click import
    @ACFgAuditKey String item_no;
    @ACFgAuditKey String purchase_order_no;
    //@ACFgAuditKey String sub_section_id;
    
  //  Search search = new Search();

    private class Search extends ACFgSearch {
        public Search() {
            super();
            setModel(ARCmItemMaster.class); //define a Search which accept 4 filters from client
            addRule(new ACFdSQLRule("item_no", RuleCondition.LIKE_, null, RuleCase.Insensitive)); //sec_id
            //addRule(new ACFdSQLRule("item_no", RuleCondition.EQ, null, RuleCase.Insensitive));//sub_sec_id
            addRule(new ACFdSQLRule("item_desc", RuleCondition._LIKE_, null, RuleCase.Insensitive));
            //addRule(new ACFdSQLRule("section_name", RuleCondition._LIKE_, null, RuleCase.Insensitive));
            //addRule(new ACFdSQLRule("report_caption", RuleCondition._LIKE_, null, RuleCase.Insensitive));
        }// ACF will forward the content to client and post to the grid which ID equals to “grid_browse”.
    }
    Search search = new Search();

    
    @RequestMapping(value=APWtMapping.APWF001_MAIN, method=RequestMethod.GET)
    public String main(ModelMap model) throws Exception {
        model.addAttribute("item_no", item_no);
       // model.addAttribute("sub_section_id", sub_section_id); //set tow keys
        //initial value in function maintenance form
        model.addAttribute("modules", ItemCategoryService.getCat_No()); //acf's function, get data from ACFDB
        //System.out.println(moduleService.getAllModuleValuePairs());
        //search value groups in search form and main form
        //model.addAttribute("moduleGroups", funcGpService.getModuleFuncGpIndex()); // no need to group tables just now

        return view();
        
    }
    @RequestMapping(value=APWtMapping.APWF001_SEARCH_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getGrid(@RequestBody ACFgRequestParameters param) throws Exception {
      //The method getGrid responds to AJAX by obtain the Search JSON result and put in variable “grid_browse”.
        // ACF will forward the content to client and post to the grid which ID equals to “grid_browse”.
        search.setConnection(getConnection("ARCDB")); //get connection to the database
        search.setValues(param);
        search.setFocus(item_no); //set two keys
        System.out.println(param);
       // System.out.println(search.getGridResult());
        return new ACFgResponseParameters().set("grid_browse", search.getGridResult()); // can only be called once, otherwise reset parameter
    }
    
    
    
    @RequestMapping(value=APWtMapping.APWF001_GET_FORM_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters getForm(@RequestBody ACFgRequestParameters param) throws Exception {
        item_no = param.get("item_no", String.class); 
        getRemainingInventory(param.getRequestParameter("remaining_browse"));
        getInventory(param.getRequestParameter("inventory_browse"));
       // sub_section_id = param.get("sub_section_id", String.class);  
        //return new ACFgResponseParameters().set("frm_main", ItemMasterDao.selectItem(item_no)); 
        return getResponseParameters().set("frm_main", ItemMasterDao.selectItem(item_no));
    }
    
    @RequestMapping(value=APWtMapping.APWF001_GET_REMAINING_INVENTORY_AJAX, method=RequestMethod.POST)
    @ResponseBody
     public ACFgResponseParameters getRemainingInventory(@RequestBody ACFgRequestParameters param) throws Exception {
        ACFdSQLAssSelect select = new ACFdSQLAssSelect();
     
      select.setCustomSQL("select * from (select I.item_no, I.purchase_order_no, I.purchase_order_date, I.unit_cost, I.received_quantity + I.adjusted_quantity - I.consumed_quantity "
              + "as remaining_quantity, I.order_quantity, I.received_quantity, "
              + "I.back_order_quantity, I.modified_at, "
              + "I.receive_date, I.consumed_quantity, I.adjusted_quantity, I.order_quantity - I.received_quantity - I.back_order_quantity as out_standing_quantity "
              
              + "from arc_item_inventory I)");
      select.setKey("item_no","purchase_order_no");
      select.wheres.and("item_no", item_no);
      //select.orders.put("seq", true);
      return getResponseParameters().set("remaining_browse", select.executeGridQuery(getConnection("ARCDB"), param));
        }

    @RequestMapping(value=APWtMapping.APWF001_GET_INVENTORY_AJAX, method=RequestMethod.POST)
    @ResponseBody
     public ACFgResponseParameters getInventory(@RequestBody ACFgRequestParameters param) throws Exception {
        ACFdSQLAssSelect select = new ACFdSQLAssSelect();
     
      select.setCustomSQL("select * from (select I.item_no, I.purchase_order_no, I.purchase_order_date, I.unit_cost, I.received_quantity + I.adjusted_quantity - I.consumed_quantity "
              + "as remaining_quantity, I.order_quantity, I.received_quantity, I.receive_date, I.consumed_quantity, I.adjusted_quantity, "
              + "I.back_order_quantity, I.modified_at, "
              + "I.order_quantity - I.received_quantity - I.back_order_quantity as out_standing_quantity "
              + "from arc_item_inventory I)");
      select.setKey("item_no","purchase_order_no");
      select.wheres.and("item_no", item_no);
      //select.orders.put("seq", true);
      return getResponseParameters().set("inventory_browse", select.executeGridQuery(getConnection("ARCDB"), param));
        }
    
    @ACFgTransaction
    @RequestMapping(value=APWtMapping.APWF001_SAVE_AJAX, method=RequestMethod.POST)
    @ResponseBody
    public ACFgResponseParameters save(@RequestBody ACFgRequestParameters param) throws Exception { //function in the upper right "save" button
      //the controller obtains the changes of form data 
        List<ARCmItemMaster> amendments = param.getList("form", ARCmItemMaster.class);
       // final List<ARCmItemInventory> ItemInvRemamendments = param.getList("remain", ARCmItemInventory.class);
        final List<ARCmItemInventory> ItemInvamendments = param.getList("inv", ARCmItemInventory.class);
        //and call DAO to save the changes
        ARCmItemMaster lastItem = ItemMasterDao.saveItems(amendments, new ACFiSQLAssWriteInterface<ARCmItemMaster>(){
            
            
            //interface for the related functions
            @Override
            public boolean insert(ARCmItemMaster newItem, ACFdSQLAssInsert ass) throws Exception {
                //ass.columns.put("allow_print", 1); //without the allow_print column, the whole sql won't work
                ass.setAfterExecute(new ACFiCallback() {
                    @Override
                    public void callback() throws Exception {
                      //  if (ItemInvRemamendments != null)
                      //      ItemInventoryDao.saveItems(ItemInvRemamendments);
                        System.out.println("debug01" + ItemInvamendments);
                        if (ItemInvamendments != null)
                            ItemInventoryDao.saveItems(ItemInvamendments);
                      
                    }
                });
                return false;
            }

            @Override
            public boolean update(ARCmItemMaster oldItem, ARCmItemMaster newItem, ACFdSQLAssUpdate ass) throws Exception {
                ass.setAfterExecute(new ACFiCallback() {
                    @Override
                    public void callback() throws Exception {
                       // if (ItemInvRemamendments != null)
                        //    ItemInventoryDao.saveItems(ItemInvRemamendments);
                        System.out.println("debug02" + ItemInvamendments);
                        if (ItemInvamendments != null)
                            ItemInventoryDao.saveItems(ItemInvamendments);
                      
                    }
                });
                return false;
                
            }

            @Override
            public boolean delete(ARCmItemMaster oldItem, ACFdSQLAssDelete ass) throws Exception {
                return false;
            }
        });
        item_no = lastItem!=null? lastItem.item_no: null;

        return new ACFgResponseParameters();
    }

}