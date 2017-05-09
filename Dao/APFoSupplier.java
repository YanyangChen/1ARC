package arc.apf.Dao;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

import acf.acf.Abstract.ACFaAppDao;
import acf.acf.Static.ACFtDBUtility;
import arc.apf.Model.APFmLocation;
import arc.apf.Model.APFmSection;
import arc.apf.Model.APFmSupplier;

@Repository
public class APFoSupplier extends ACFaAppDao<APFmSupplier> { //it should extends the object under Module file source

    public APFoSupplier() throws Exception {
        super();
    }
    
    @Override
    protected Connection getConnection() throws Exception {
        Connection conn = ACFtDBUtility.getConnection("ARCDB");
        return conn;
    }
    
}
