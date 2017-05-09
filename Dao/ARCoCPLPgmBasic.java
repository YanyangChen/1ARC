package arc.apf.Dao;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

import acf.acf.Static.ACFtDBUtility;
import arc.apf.Abstract.ARCaAppDao;
import arc.apf.Model.ARCmCPLPgmBasic;



@Repository
public class ARCoCPLPgmBasic extends ARCaAppDao<ARCmCPLPgmBasic> {

	public ARCoCPLPgmBasic() throws Exception {
		super();
	}

	@Override
	protected Connection getConnection() throws Exception {
		return ACFtDBUtility.getConnection("CPLDB");
	}
}
