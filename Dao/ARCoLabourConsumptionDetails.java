package arc.apf.Dao;


import java.sql.Connection;

import org.springframework.stereotype.Repository;

import acf.acf.Abstract.ACFaAppDao;
import acf.acf.Static.ACFtDBUtility;
import arc.apf.Model.ARCmItemReceiveHistory;
import arc.apf.Model.ARCmLabourConsumption;
import arc.apf.Model.ARCmLabourConsumptionDetails;

@Repository
public class ARCoLabourConsumptionDetails extends ACFaAppDao<ARCmLabourConsumptionDetails> { //it should extends the object under Module file source

    public ARCoLabourConsumptionDetails() throws Exception {
        super();
    }
    
    @Override
    protected Connection getConnection() throws Exception {
        Connection conn = ACFtDBUtility.getConnection("ARCDB");
        return conn;
    }
}