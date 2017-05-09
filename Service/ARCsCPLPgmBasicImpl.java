package arc.apf.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.Database.ACFdSQLRule;
import acf.acf.Database.ACFdSQLWhere;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.Static.ACFtDBUtility;
import arc.apf.Dao.ARCoCPLPgmBasic;
//import arc.apf.Dao.ARCoPPRPgmBasic;
import arc.apf.Model.ARCmCPLPgmBasic;
//import arc.apf.Model.ARCmPPRPgmBasic;

@Service
public class ARCsCPLPgmBasicImpl extends ARCsCPLPgmBasic {
	
	@Autowired ARCoCPLPgmBasic pgmBasicDao;
	
	public ARCsCPLPgmBasicImpl() throws Exception {
		super();
	}
	
	public ARCmCPLPgmBasic selectItemByPgmNum(BigDecimal pgm_num) throws Exception {//for apff011 selecting number from cpl, AC, 2017/03/15
	    
		return pgmBasicDao.selectItem(new ACFdSQLWhere().and(new ACFdSQLRule("pgm_num", ACFdSQLRule.RuleCondition.EQ, pgm_num)));
	}
	
	public String getCatCodeByPgmNo(int prog_no) throws Exception {//for apff011 get category code bt programme no, AC, 2017/03/20
		ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
		ass.setCustomSQL("select cat_code from ppr_pgm_basic where pgm_num = '" + prog_no + "'");
		List<ACFgRawModel> result = ass.executeQuery(ACFtDBUtility.getConnection("CPLDB"));
		return result.size() > 0 ? result.get(0).get("cat_code").toString() : "";
	}
	
}
