package arc.apf.Dao;


import java.sql.Connection;

import org.springframework.stereotype.Repository;

import acf.acf.Abstract.ACFaAppDao;
import acf.acf.Static.ACFtDBUtility;
import arc.apf.Model.ARCmItemReceiveHistory;
import arc.apf.Model.ARCmLabourConsumption;

@Repository
public class ARCoLabourConsumption extends ACFaAppDao<ARCmLabourConsumption> { //it should extends the object under Module file source

    public ARCoLabourConsumption() throws Exception {
        super();
    }
    
    @Override
    protected Connection getConnection() throws Exception {
        Connection conn = ACFtDBUtility.getConnection("ARCDB");
        return conn;
    }
}