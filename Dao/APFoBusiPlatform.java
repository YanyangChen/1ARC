package arc.apf.Dao;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

import acf.acf.Abstract.ACFaAppDao;
import acf.acf.Static.ACFtDBUtility;
import arc.apf.Model.APFmBusiPlatform;

@Repository
public class APFoBusiPlatform extends ACFaAppDao<APFmBusiPlatform> {

	public APFoBusiPlatform() throws Exception {
		super();
	}
	
	@Override
	protected Connection getConnection() throws Exception {
		Connection conn = ACFtDBUtility.getConnection("ARCDB");
		return conn;
	}
	
}

