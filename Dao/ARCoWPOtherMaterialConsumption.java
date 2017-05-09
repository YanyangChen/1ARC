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
import acf.acf.Static.ACFtDBUtility;
import arc.apf.Model.ARCmDirectBudget;
import arc.apf.Model.ARCmWPOtherMaterialConsumption;
import arc.apf.Service.ARCsDirectBudget;
import arc.apf.Service.ARCsWPOtherMaterialConsumption;

@Repository
public class ARCoWPOtherMaterialConsumption extends ACFaAppDao<ARCmWPOtherMaterialConsumption> { //it should extends the object under Module file source
    @Autowired ARCsWPOtherMaterialConsumption WPOtherMaterialConsumptionService;
    
    public ARCoWPOtherMaterialConsumption() throws Exception {
        super();
    }
    
    @Override
    protected Connection getConnection() throws Exception {
        Connection conn = ACFtDBUtility.getConnection("ARCDB");
        return conn;
    }
    
    @Override
    public ARCmWPOtherMaterialConsumption saveItems(List<ARCmWPOtherMaterialConsumption> amendments) throws Exception {

        return super.saveItems(amendments, new ACFiSQLAssWriteInterface<ARCmWPOtherMaterialConsumption>() {

            @Override
            public boolean insert(ARCmWPOtherMaterialConsumption newItem, ACFdSQLAssInsert ass)
                    throws Exception {
                ass.columns.put("sequence_no", WPOtherMaterialConsumptionService.GenerateSequenceNo(newItem.consumption_form_no));
                return false;
            }

            @Override
            public boolean update(ARCmWPOtherMaterialConsumption oldItem, ARCmWPOtherMaterialConsumption newItem,
                    ACFdSQLAssUpdate ass) throws Exception {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean delete(ARCmWPOtherMaterialConsumption oldItem, ACFdSQLAssDelete ass)
                    throws Exception {
                // TODO Auto-generated method stub
                return false;
            }

        });
    }
}