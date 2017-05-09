package arc.apf.Dao;


import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import acf.acf.Abstract.ACFaAppDao;
import acf.acf.Database.ACFdSQLAssDelete;
import acf.acf.Database.ACFdSQLAssInsert;
import acf.acf.Database.ACFdSQLAssUpdate;
import acf.acf.Interface.ACFiSQLAssWriteInterface;
import acf.acf.Model.ACFmJournal;
import acf.acf.Static.ACFtDBUtility;
//import arc.apw.Model.APWmLabour;
//import arc.apf.Model.APFmSection;
//import arc.apw.Model.APWmPONO;
//import arc.apf.Model.ARCmAutoGenNo;
import arc.apf.Model.ARCmBusinessPlatform;
import arc.apf.Model.ARCmDirectBudget;
import arc.apf.Service.ARCsDirectBudget;
import arc.apf.Service.ARCsModel;

@Repository
public class ARCoDirectBudget extends ACFaAppDao<ARCmDirectBudget> { //it should extends the object under Module file source
    @Autowired ARCsDirectBudget DirectBudgetService;
    
    public ARCoDirectBudget() throws Exception {
        super();
    }
    
    @Override
    protected Connection getConnection() throws Exception {
        Connection conn = ACFtDBUtility.getConnection("ARCDB");
        return conn;
    }
    
    @Override
    public ARCmDirectBudget saveItems(List<ARCmDirectBudget> amendments) throws Exception {

        return super.saveItems(amendments, new ACFiSQLAssWriteInterface<ARCmDirectBudget>() {

            @Override
            public boolean insert(ARCmDirectBudget newItem, ACFdSQLAssInsert ass)
                    throws Exception {
                ass.columns.put("sequence_no", DirectBudgetService.GenerateSequenceNo(newItem.programme_no));
                return false;
            }

            @Override
            public boolean update(ARCmDirectBudget oldItem, ARCmDirectBudget newItem,
                    ACFdSQLAssUpdate ass) throws Exception {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean delete(ARCmDirectBudget oldItem, ACFdSQLAssDelete ass)
                    throws Exception {
                // TODO Auto-generated method stub
                return false;
            }

        });
    }
    
}