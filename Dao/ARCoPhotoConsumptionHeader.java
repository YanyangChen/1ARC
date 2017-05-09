package arc.apf.Dao;


import java.sql.Connection;

import org.springframework.stereotype.Repository;

import acf.acf.Abstract.ACFaAppDao;
import acf.acf.Static.ACFtDBUtility;
import arc.apf.Model.ARCmPhotoConsumptionHeader;

@Repository
public class ARCoPhotoConsumptionHeader extends ACFaAppDao<ARCmPhotoConsumptionHeader> { //it should extends the object under Module file source

    public ARCoPhotoConsumptionHeader() throws Exception {
        super();
    }
    
    @Override
    protected Connection getConnection() throws Exception {
        Connection conn = ACFtDBUtility.getConnection("ARCDB");
        return conn;
    }
}