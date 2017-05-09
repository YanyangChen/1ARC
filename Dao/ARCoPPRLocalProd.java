package arc.apf.Dao;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

import acf.acf.Static.ACFtDBUtility;
import arc.apf.Abstract.ARCaAppDao;
import arc.apf.Model.ARCmPPRLocalProd;


@Repository
public class ARCoPPRLocalProd extends ARCaAppDao<ARCmPPRLocalProd> {

	public ARCoPPRLocalProd() throws Exception {
		super();
	}

	@Override
	protected Connection getConnection() throws Exception {
		return ACFtDBUtility.getConnection("PPRDB");
	}
}
