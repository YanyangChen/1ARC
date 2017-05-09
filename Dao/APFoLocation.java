package arc.apf.Dao;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

import acf.acf.Abstract.ACFaAppDao;
import acf.acf.Static.ACFtDBUtility;
import arc.apf.Model.APFmLocation;
import arc.apf.Model.APFmSection;

@Repository
public class APFoLocation extends ACFaAppDao<APFmLocation> { //it should extends the object under Module file source

    public APFoLocation() throws Exception {
        super();
    }
    
    @Override
    protected Connection getConnection() throws Exception {
        Connection conn = ACFtDBUtility.getConnection("ARCDB");
        return conn;
    }
    
}
