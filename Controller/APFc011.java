package arc.apf.Controller;

import java.math.BigDecimal;
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
import acf.acf.Service.ACFsList;
import arc.apf.Dao.APFoLabour;
import arc.apf.Dao.ARCoDirectBudget;
import arc.apf.Dao.ARCoDirectBudgetDetails;
import arc.apf.Dao.ARCoIndirectBudget;
import arc.apf.Dao.ARCoProgrammeMaster;
import arc.apf.Dao.ARCoSection;
import arc.apf.Model.ARCmCPLPgmBasic;
import arc.apf.Model.ARCmDirectBudget;
import arc.apf.Model.ARCmDirectBudgetDetails;
import arc.apf.Model.ARCmIndirectBudget;
import arc.apf.Model.ARCmPPRLocalProd;
//import arc.apf.Model.ARCmPPRPgmBasic;
import arc.apf.Model.ARCmPPRPgmBasicHist;
import arc.apf.Model.ARCmPPRPgmCasting;
import arc.apf.Model.ARCmProgrammeMaster;
import arc.apf.Service.ARCsAccountAllocation;
import arc.apf.Service.ARCsBusinessPlatform;
import arc.apf.Service.ARCsCPLPgmBasic;
import arc.apf.Service.ARCsModel;
import arc.apf.Service.ARCsPPRLocalProd;
//import arc.apf.Service.ARCsPPRPgmBasic;
import arc.apf.Service.ARCsPPRPgmBasicHist;
import arc.apf.Service.ARCsPPRPgmCasting;
import arc.apf.Service.ARCsProgrammeMaster;
import arc.apf.Service.ARCsSection;
import arc.apf.Static.APFtMapping;


@Controller
@Scope("session")
@ACFgFunction(id="APFF011")
@RequestMapping(value=APFtMapping.APFF011)
public class APFc011 extends ACFaAppController {

    //protected static final ACFaBaseDao<APFmLocation> SectionDao = null;
    @Autowired ARCsModel moduleService;
    @Autowired ACFsList ListService;
    @Autowired ARCsProgrammeMaster ProgrammeMasterService;
    @Autowired ARCsBusinessPlatform BusinessPlatformService;
    @Autowired ARCsSection SectionService;
    @Autowired ARCsAccountAllocation AccountAllocationService;
    @Autowired ARCsCPLPgmBasic pprPgmBasicService;
    @Autowired ARCsPPRLocalProd pprLocalProdService;
    @Autowired ARCsPPRPgmBasicHist pprPgmBasicHistService;
    @Autowired ARCsPPRPgmCasting pprPgmCastingService;
    
    
   // @Autowired ACFoFunction functionDao;
    @Autowired APFoLabour LabourDao;
    @Autowired ARCoProgrammeMaster ProgrammeMasterDao;
    @Autowired ARCoIndirectBudget IndirectBudgetDao;
    @Autowired ARCoSection SectionDao;
    @Autowired ARCoDirectBudgetDetails DirectBudgetDetailsDao;
    @Autowired ARCoDirectBudget DirectBudgetDao;
  //  @Autowired APFoSection sectionDao;
   // @Autowired APFsFuncGp funcGpService; //click the object and click import
    @ACFgAuditKey String labour_type;
    @ACFgAuditKey String programme_no;
   // @ACFgAuditKey String APF_PROGRAMME_MASTER;
    

        private class Search extends ACFgSearch {
        public Search() {
            super();
            setModel(ARCmProgrammeMaster.class);
            addRule(new ACFdSQLRule("programme_no", RuleCondition.EQ, null, RuleCase.Insensitive));
            addRule(new ACFdSQLRule("chinese_programme_name", RuleCondition._LIKE_, null, RuleCase.Insensitive));
            addRule(new ACFdSQLRule("business_platform", RuleCondition.EQ, null, RuleCase.Insensitive));
            addRule(new ACFdSQLRule("department", RuleCondition.EQ, null, RuleCase.Insensitive));
            //addRule(new ACFdSQLRule("location_name", RuleCondition.EQ, null, RuleCase.Insensitive));
        }
        
       
        
    }
        
        private class SearchProgramme extends ACFgSearch {
            public SearchProgramme() {
                super();
                setModel(ARCmCPLPgmBasic.class);
                addRule(new ACFdSQLRule("pgm_num", RuleCondition.EQ, null, RuleCase.Insensitive));
                addRule(new ACFdSQLRule("clean_eng_title", RuleCondition._LIKE_, null, RuleCase.Insensitive));
                addRule(new ACFdSQLRule("clean_chi_title", RuleCondition._LIKE_, null, RuleCase.Insensitive));
            }
    }

    Search search = new Search();
    @RequestMapping(value=APFtMapping.APFF011_MAIN, method=RequestMethod.GET)
    public String main(ModelMap model) throws Exception {
        
        model.addAttribute("ProgrammeNo", ProgrammeMasterService.getProgrammeNo()); //SQL code implemented in getLabout() function here
        model.addAttribute("BusinessPlatform", ProgrammeMasterService.getBusinessPlatform());
        model.addAttribute("Department", ProgrammeMasterService.getDepartment());
        model.addAttribute("BDepartment", BusinessPlatformService.getBDepartment());
        model.addAttribute("BBusinessPlatform", BusinessPlatformService.getBBusinessPlatform());
        model.addAttribute("ProgrammeType", ListService.getListValuePairs("APF_PROGRAMME_MASTER")); //key must in quote
        model.addAttribute("GetSectionId", SectionService.getSectionId()); //key must in quote
        model.addAttribute("GetAccountAllocation", AccountAllocationService.getBudgetAccountAllocation()); //key must in quote
        
        return view();
    }
    
    ////form maintenance here, how to modify? imoprt apff001.java and remove grid in jsp file
    @RequestMapping(value=APFtMapping.APFF011_SEARCH_AJAX, method=RequestMethod.POST)
    @ResponseBody
     public ACFgResponseParameters getGrid(@RequestBody ACFgRequestParameters param) throws Exception {
          //The method getGrid responds to AJAX by obtain the Search JSON result and put in variable “grid_browse”.
            // ACF will forward the content to client and post to the grid which ID equals to “grid_browse”.
         search = new Search();    
            search.setConnection(getConnection("ARCDB")); //get connection to the database
            search.setValues(param);
            search.setFocus(programme_no);
           // System.out.println("param:"+param);
           // System.out.println(search.getGridResult());
            return new ACFgResponseParameters().set("grid_browse", search.getGridResult()); // can only be called once, otherwise reset parameter
        }
    
    @RequestMapping(value=APFtMapping.APFF011_DIRECT_BUDGET, method=RequestMethod.POST)
    @ResponseBody
     public ACFgResponseParameters getDirectBudget(@RequestBody ACFgRequestParameters param) throws Exception {
        ACFdSQLAssSelect select = new ACFdSQLAssSelect();

      
      select.setCustomSQL("select * from (select PM.programme_no, DB.direct_budget_description, DB.direct_budget_amount, DB.sequence_no, DB.modified_at, DB.modified_by, DB.created_at, DB.created_by "
              + "from arc_programme_master PM, arc_direct_budget DB "
              + "where PM.programme_no = DB.programme_no)");
      select.setKey("programme_no");
      select.wheres.and("programme_no", programme_no);
      //select.orders.put("seq", true);
      return getResponseParameters().set("direct_browse", select.executeGridQuery(getConnection("ARCDB"), param));
        }
    
    @RequestMapping(value=APFtMapping.APFF011_DETAIL_DIRECT_BUDGET, method=RequestMethod.POST)
    @ResponseBody
     public ACFgResponseParameters getDetailDirectBudget(@RequestBody ACFgRequestParameters param) throws Exception {
        ACFdSQLAssSelect select = new ACFdSQLAssSelect();

      
      select.setCustomSQL("select * from (select PM.programme_no, AA.budget_account_description, AA.budget_account_allocation, DBD.detail_direct_budget_amount, DBD.modified_at, "
              + "DBD.created_at, DBD.created_by, DBD.modified_by, DBD.account_allocation "
              + "from dev.arc_programme_master PM, dev.arc_direct_budget_details DBD, dev.arc_account_allocation AA "
              + "where PM.programme_no = DBD.programme_no "
              + "and AA.budget_account_allocation = DBD.account_allocation)");
      select.setKey("programme_no","budget_account_allocation");
      select.wheres.and("programme_no", programme_no);
      //select.orders.put("seq", true);
      return getResponseParameters().set("detail_budget_browse", select.executeGridQuery(getConnection("ARCDB"), param));
        }
    
    
    @RequestMapping(value=APFtMapping.APFF011_SECTION_AND_INDIRECT_BUDGET, method=RequestMethod.POST)
    @ResponseBody
     public ACFgResponseParameters getSectionAndIndirectBudget(@RequestBody ACFgRequestParameters param) throws Exception {
        ACFdSQLAssSelect select = new ACFdSQLAssSelect();

      
      select.setCustomSQL("select * from (select PM.programme_no, SEC.section_id, IB.modified_at, SEC.sub_section_id, SEC.section_name, IB.indirect_budget_amount, IB.indirect_budget_hour "
              + "from dev.arc_programme_master PM, dev.arc_section SEC, dev.arc_indirect_budget IB "
              + "where PM.programme_no = IB.programme_no "
              + "and IB.section_id = SEC.section_id)");
      select.setKey("programme_no","section_id");
      select.wheres.and("programme_no", programme_no);
      //select.orders.put("seq", true);
      return getResponseParameters().set("indirect_browse", select.executeGridQuery(getConnection("ARCDB"), param));
        }
    
    
    
    
     @RequestMapping(value=APFtMapping.APFF011_GET_FORM_AJAX, method=RequestMethod.POST)
        @ResponseBody
        public ACFgResponseParameters getForm(@RequestBody ACFgRequestParameters param) throws Exception {
         programme_no = param.get("programme_no", String.class); //pick the value of parameter “func_id” from client
            
            //retrieves the result by DAO, and put in the variable “frm_main”. 
            //ACF will forward the content to client and post to the form which ID equals to “frm_main”
         
         //must have this statement if more than one grid present.
         
         
         getSectionAndIndirectBudget(param.getRequestParameter("indirect_browse"));
         getDirectBudget(param.getRequestParameter("direct_browse"));
         getDetailDirectBudget(param.getRequestParameter("detail_budget_browse"));
            return getResponseParameters().set("frm_main", ProgrammeMasterDao.selectItem(programme_no)); //change dao here
        }
     
     @RequestMapping(value=APFtMapping.APFF011_CPL_PROG_SEARCH_AJAX, method=RequestMethod.POST)
     @ResponseBody
     public ACFgResponseParameters getCPLProgGrid(@RequestBody ACFgRequestParameters param) throws Exception {
         SearchProgramme searchPgm = new SearchProgramme();
         searchPgm.setConnection(getConnection("CPLDB"));
         searchPgm.setValues(param);//set parameters of the table to fit for searching rules
         searchPgm.setFocus(programme_no);
         
         return new ACFgResponseParameters().set("grid_cpl_programme_browse", searchPgm.getGridResult());
     }     
     
     @RequestMapping(value=APFtMapping.APFF011_GET_CPL_PROGRAMME_AJAX, method=RequestMethod.POST)
     @ResponseBody
     public ACFgResponseParameters getCPLProgramme(@RequestBody ACFgRequestParameters param) throws Exception {
         ACFgResponseParameters resParam = new ACFgResponseParameters();
         
         programme_no = param.get("programme_no", String.class);
 
         BigDecimal pgm_num = programme_no.equals("") ? null : new BigDecimal(programme_no);
         
         ARCmCPLPgmBasic pgm = pprPgmBasicService.selectItemByPgmNum(pgm_num);
         
         ARCmPPRLocalProd prod = pprLocalProdService.selectItemByPgmNum(pgm_num);
         
         ARCmPPRPgmCasting cast = pprPgmCastingService.selectItemByPgmNum(pgm_num);
         
         ARCmPPRPgmBasicHist pgmHist = pprPgmBasicHistService.getLatestHistory(programme_no);
         
         resParam.set("pgm", pgm);
         resParam.set("prod", prod);
         resParam.set("cast", cast);
         resParam.set("pgmHist", pgmHist);
         
         return resParam;
     }
    
     
     @ACFgTransaction
        @RequestMapping(value=APFtMapping.APFF011_SAVE_AJAX, method=RequestMethod.POST)
        @ResponseBody
        public ACFgResponseParameters save(@RequestBody ACFgRequestParameters param) throws Exception { //function in the upper right "save" button
          //the controller obtains the changes of form data 
            List<ARCmProgrammeMaster> amendments = param.getList("form", ARCmProgrammeMaster.class);
            final List<ARCmIndirectBudget> IndirectBudgetamendments = param.getList("IndirectBudget", ARCmIndirectBudget.class);
            final List<ARCmDirectBudget> DirectBudgetamendments = param.getList("DirectBudget", ARCmDirectBudget.class);
            final List<ARCmDirectBudgetDetails> DirectBudgetDetailsamendments = param.getList("AccountAllocation", ARCmDirectBudgetDetails.class);
            //and call DAO to save the changes
            ARCmProgrammeMaster lastItem = ProgrammeMasterDao.saveItems(amendments, new ACFiSQLAssWriteInterface<ARCmProgrammeMaster>(){
                
               
                //interface for the related functions
                @Override
                public boolean insert(ARCmProgrammeMaster newItem, ACFdSQLAssInsert ass) throws Exception {
                    //ass.columns.put("allow_print", 1); //without the allow_print column, the whole sql won't work
                    ass.setAfterExecute(new ACFiCallback() {
                        @Override
                        public void callback() throws Exception {
                            if (IndirectBudgetamendments != null)
                                IndirectBudgetDao.saveItems(IndirectBudgetamendments);
                            if (DirectBudgetamendments != null)
                                DirectBudgetDao.saveItems(DirectBudgetamendments);
                            if (DirectBudgetDetailsamendments != null)
                                DirectBudgetDetailsDao.saveItems(DirectBudgetDetailsamendments);
                          
                        }
                    });
                    return false;
                }

                @Override
                public boolean update(ARCmProgrammeMaster oldItem, ARCmProgrammeMaster newItem, ACFdSQLAssUpdate ass) throws Exception {
                    ass.setAfterExecute(new ACFiCallback() {
                        @Override
                        public void callback() throws Exception {
                            if (IndirectBudgetamendments != null)
                                IndirectBudgetDao.saveItems(IndirectBudgetamendments);
                            if (DirectBudgetamendments != null)
                                DirectBudgetDao.saveItems(DirectBudgetamendments);
                            
                            if (DirectBudgetDetailsamendments != null)
                                System.out.println(DirectBudgetDetailsamendments);
                            DirectBudgetDetailsDao.saveItems(DirectBudgetDetailsamendments);
                          
                        }
                    });
                    return false;
                }

                @Override
                public boolean delete(ARCmProgrammeMaster oldItem, ACFdSQLAssDelete ass) throws Exception {
                   // validate(newItem);
                    return false;
                }
            });
            programme_no = lastItem!=null? lastItem.programme_no: null;

            return new ACFgResponseParameters();
        }
}

