package arc.apf.Dao;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

import acf.acf.Abstract.ACFaAppDao;
import acf.acf.Static.ACFtDBUtility;
import arc.apf.Model.APFmItem;

@Repository
public class APFoItemCat extends ACFaAppDao<APFmItem> {

	public APFoItemCat() throws Exception {
		super();
	}
	
	@Override
	protected Connection getConnection() throws Exception {
		Connection conn = ACFtDBUtility.getConnection("ARCDB");
		return conn;
	}
	
}
