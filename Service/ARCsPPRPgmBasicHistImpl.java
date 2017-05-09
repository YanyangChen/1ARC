package arc.apf.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acf.acf.Abstract.ACFaSQLAss;
import acf.acf.Interface.ACFiSQLAssInterface;
import arc.apf.Dao.ARCoPPRPgmBasicHist;
import arc.apf.Model.ARCmPPRPgmBasicHist;

@Service
public class ARCsPPRPgmBasicHistImpl extends ARCsPPRPgmBasicHist {
	
	@Autowired ARCoPPRPgmBasicHist pgmHistoryDao;
	
	public ARCsPPRPgmBasicHistImpl() throws Exception {
		super();
	}
	
	public ARCmPPRPgmBasicHist getLatestHistory(final String prog_no) throws Exception {
		if (prog_no.isEmpty())
			return null;
		
		return pgmHistoryDao.selectItem(new ACFiSQLAssInterface() {
			@Override
			public void customize(ACFaSQLAss ass) throws Exception {
				String sql = "select * from ppr_pgm_basic_hist where pgm_num = '%s' order by ver_num desc";
				ass.setConnection(getConnection());
				ass.setCustomSQL(sql, prog_no);
			}
		});
	}
}
