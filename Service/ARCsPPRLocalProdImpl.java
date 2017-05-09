package arc.apf.Service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import acf.acf.Database.ACFdSQLRule;
import acf.acf.Database.ACFdSQLWhere;
import arc.apf.Dao.ARCoPPRLocalProd;
import arc.apf.Model.ARCmPPRLocalProd;

@Service
public class ARCsPPRLocalProdImpl extends ARCsPPRLocalProd {
	
	@Autowired ARCoPPRLocalProd localProdDao;
	
	public ARCsPPRLocalProdImpl() throws Exception {
		super();
	}
	
	public ARCmPPRLocalProd selectItemByPgmNum(BigDecimal pgm_num) throws Exception {
		return localProdDao.selectItem(new ACFdSQLWhere().and(new ACFdSQLRule("pgm_num", ACFdSQLRule.RuleCondition.EQ, pgm_num)));
	}
	
}
