package arc.apf.Dao;


import java.sql.Connection;

import org.springframework.stereotype.Repository;

import acf.acf.Abstract.ACFaAppDao;
import acf.acf.Static.ACFtDBUtility;
//import arc.apw.Model.APWmLabour;
//import arc.apf.Model.APFmSection;
//import arc.apw.Model.APWmPONO;
import arc.apf.Model.ARCmAutoGenNo;
import arc.apf.Model.ARCmGroup;
import arc.apf.Model.ARCmItemAdjustmentHistory;

@Repository
public class ARCoItemAdjustmentHistory extends ACFaAppDao<ARCmItemAdjustmentHistory> { //it should extends the object under Module file source

    public ARCoItemAdjustmentHistory() throws Exception {
        super();
    }
    
    @Override
    protected Connection getConnection() throws Exception {
        Connection conn = ACFtDBUtility.getConnection("ARCDB");
        return conn;
    }
}