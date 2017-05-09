package arc.apf.Dao;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

import acf.acf.Static.ACFtDBUtility;
import arc.apf.Abstract.ARCaAppDao;
import arc.apf.Model.ARCmPPRPgmBasicHist;


@Repository
public class ARCoPPRPgmBasicHist extends ARCaAppDao<ARCmPPRPgmBasicHist> {

	public ARCoPPRPgmBasicHist() throws Exception {
		super();
	}

	@Override
	protected Connection getConnection() throws Exception {
		return ACFtDBUtility.getConnection("PPRDB");
	}
}
