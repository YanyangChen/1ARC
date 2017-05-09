package arc.apf.Service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acf.acf.Abstract.ACFaSQLAss;
import acf.acf.Interface.ACFiSQLAssInterface;
import arc.apf.Dao.ARCoPPRPgmBasicHist;
import arc.apf.Dao.ARCoPPRPgmCasting;
import arc.apf.Model.ARCmPPRPgmBasicHist;
import arc.apf.Model.ARCmPPRPgmCasting;

@Service
public class ARCsPPRPgmCastingImpl extends ARCsPPRPgmCasting {
	
	@Autowired ARCoPPRPgmCasting pgmCastingDao;
	
	public ARCsPPRPgmCastingImpl() throws Exception {
		super();
	}
	
	public ARCmPPRPgmCasting selectItemByPgmNum(final BigDecimal pgm_num) throws Exception {//for apff011 select item by programme no. AC, 2017/03/15
		//if (pgm_num.isEmpty())
		//	return null;
		
		return pgmCastingDao.selectItem(new ACFiSQLAssInterface() {
			@Override
			public void customize(ACFaSQLAss ass) throws Exception {
				String sql = "SELECT CO_CODE, PGM_NUM, CAST_LEVEL, MUSIC_THEME_NUM, START_EPI_NUM, "
				        + "ITEM_NUM, CAST_ROLE_CODE, NAME AS EP_NAME, END_EPI_NUM, NAME_UPPERCASE, "
				        + "CAST_CLASS_CODE, CRE_USER_ID, CRE_DT, AMD_USER_ID, AMD_DT "
				        + "FROM PPR_PGM_CASTING "
				        + "WHERE PGM_NUM = '%s' "
				        + "AND CAST_ROLE_CODE = 'EP'";
				ass.setConnection(getConnection());
				ass.setCustomSQL(sql, pgm_num);
			}
		});
	}

   
    
}
