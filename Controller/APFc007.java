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
import acf.acf.Database.ACFdSQLRule;
import acf.acf.Database.ACFdSQLRule.RuleCase;
import acf.acf.Database.ACFdSQLRule.RuleCondition;
import acf.acf.General.annotation.ACFgAuditKey;
import acf.acf.General.annotation.ACFgFunction;
import acf.acf.General.annotation.ACFgTransaction;
import acf.acf.General.core.ACFgRequestParameters;
import acf.acf.General.core.ACFgResponseParameters;
import acf.acf.General.core.ACFgSearch;

import arc.apf.Service.ARCsBusiPlatform;
import arc.apf.Dao.APFoBusiPlatform;
import arc.apf.Model.APFmBusiPlatform;
import arc.apf.Static.APFtMapping;

@Controller
@Scope("session")
@ACFgFunction(id="APFF007")
@RequestMapping(value=APFtMapping.APFF007)
public class APFc007 extends ACFaAppController {

	@Autowired APFoBusiPlatform    busiPlatformDao;
	
	@Autowired ARCsBusiPlatform    busiPlatformService;
	
	@ACFgAuditKey String busi_platform;
	
	Search search = new Search();
	
	private class Search extends ACFgSearch {
		public Search() {
			super();
			setModel(APFmBusiPlatform.class);
			addRule(new ACFdSQLRule("busi_platform", RuleCondition.EQ, null, RuleCase.Insensitive));	
			
		}
		
	}

	@RequestMapping(value=APFtMapping.APFF007_MAIN, method=RequestMethod.GET)
	public String main(ModelMap model) throws Exception {

		model.addAttribute("busiPlatform", busiPlatformService.getAllBusiPlatform());
		
		getResponseParameters().set("s_busi_platform", busiPlatformService.getAllBusiPlatform());
		return view();
	}

    @RequestMapping(value=APFtMapping.APFF007_SEARCH_AJAX, method=RequestMethod.POST)
	@ResponseBody
	public ACFgResponseParameters getGrid(@RequestBody ACFgRequestParameters param) throws Exception {

		search.setConnection(getConnection("ARCDB"));
		search.setValues(param);
		search.setFocus(busi_platform);

        return new ACFgResponseParameters().set("grid_browse", search.getGridResult());

	}

	@RequestMapping(value=APFtMapping.APFF007_GET_FORM_AJAX, method=RequestMethod.POST)
	@ResponseBody
	public ACFgResponseParameters getForm(@RequestBody ACFgRequestParameters param) throws Exception {
		
		busi_platform = param.get("busi_platform", String.class);
		return new ACFgResponseParameters().set("frm_main", busiPlatformDao.selectItem(busi_platform));
		
	}
	
	@ACFgTransaction
	@RequestMapping(value=APFtMapping.APFF007_SAVE_AJAX, method=RequestMethod.POST)
	@ResponseBody
	public ACFgResponseParameters save(@RequestBody ACFgRequestParameters param) throws Exception {
		
		List<APFmBusiPlatform> amendments = param.getList("form", APFmBusiPlatform.class);
		
		int action = amendments.get(0).getAction();
	
		if (action == 1){
			String v_busi_platform = amendments.get(0).busi_platform;
			
//			if (busiPlatformDao.selectItem(v_busi_platform)!=null){
//				throw exceptionService.error("APF001E");
//			}
						
			busi_platform = v_busi_platform;
		}


		APFmBusiPlatform busiPlatform = busiPlatformDao.saveItems(amendments);
		busi_platform = busiPlatform!=null? busiPlatform.busi_platform: null;
		
		return new ACFgResponseParameters();
	}

}
