package arc.apf.Service;
//package acf.acf.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acf.acf.Abstract.ACFaAppService;
import acf.acf.Abstract.ACFaSQLAss;
import acf.acf.Dao.ACFoModule;
import acf.acf.Dao.ACFoModuleOwner;
import acf.acf.Database.ACFdSQLAssDelete;
import acf.acf.Database.ACFdSQLAssInsert;
import acf.acf.Database.ACFdSQLAssSelect;
import acf.acf.Database.ACFdSQLAssUpdate;
import acf.acf.General.core.ACFgRawModel;
import acf.acf.Interface.ACFiCallback;
import acf.acf.Interface.ACFiSQLAssInterface;
import acf.acf.Interface.ACFiSQLAssWriteInterface;
import acf.acf.Model.ACFmModule;
import acf.acf.Static.ACFtDBUtility;

@Service
public class APFsModule extends ACFaAppService {

    @Autowired ACFoModule moduleDao;
    @Autowired ACFoModuleOwner moduleOwnerDao;

    public APFsModule() throws Exception {
        super();
    }

    public List<ACFgRawModel> getAllModuleValuePairs() throws Exception {
        /*
        List<ACFmModule> modules = moduleDao.selectCachedItems();
        Collections.sort(modules);

        List<ACFgRawModel> results = new LinkedList<ACFgRawModel>();
        for(ACFmModule item: modules) {
            ACFgRawModel m = new ACFgRawModel();
            m.set("id", item.mod_id).set("text", item.mod_id + "-" + item.mod_name);
            results.add(m);
        }
        return results;
        */

        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
        //ass.setCustomSQL("select mod_id as id, mod_id || ' - ' || mod_name as text from acf_module order by mod_seq");
        ass.setCustomSQL("select section_id as id, section_id || ' - ' || dds_code as text from arc_section order by section_id");
        return ass.executeQuery();

    }
    public List<ACFgRawModel> getlocationPairs() throws Exception {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
        //ass.setCustomSQL("select mod_id as id, mod_id || ' - ' || mod_name as text from acf_module order by mod_seq");
        ass.setCustomSQL("select location_code as id, location_code as text from dev.apf_location order by location_code");
        return ass.executeQuery();
        
    }
    
    public List<ACFgRawModel> getLabour() throws Exception {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
        //ass.setCustomSQL("select mod_id as id, mod_id || ' - ' || mod_name as text from acf_module order by mod_seq");
        ass.setCustomSQL("select labour_type as id, labour_type as text from apf_section_lab order by labour_type");
        return ass.executeQuery();
        
    }
    
    public List<ACFgRawModel> getSection() throws Exception {
        ACFdSQLAssSelect ass = new ACFdSQLAssSelect();
        ass.setConnection(ACFtDBUtility.getConnection("ARCDB"));
        //ass.setCustomSQL("select mod_id as id, mod_id || ' - ' || mod_name as text from acf_module order by mod_seq");
        ass.setCustomSQL("select distinct section_id as id, section_id || ' - ' || section_name as text from arc_section");
        return ass.executeQuery();
        
    }
    
    
    public ACFmModule getItemByModule(final String mod_id) throws Exception {
        return moduleDao.selectItem(new ACFiSQLAssInterface() {
            @Override
            public void customize(ACFaSQLAss ass) throws Exception {
                ass.setCustomSQL("select m.*, o.mod_owner"
                        + " from acf_module m left outer join (select mod_id, LISTAGG(user_id,chr(9)) as mod_owner from acf_module_owner group by mod_id) o"
                        + "   on m.mod_id = o.mod_id"
                        + " where m.mod_id = '%s'"
                        , mod_id);
            }
        });
    }

    public ACFmModule saveItems(List<ACFmModule> amendments) throws Exception {
        return moduleDao.saveItems(amendments, new ACFiSQLAssWriteInterface<ACFmModule>() {

            @Override
            public boolean insert(final ACFmModule newItem, ACFdSQLAssInsert ass) throws Exception {
                ass.setAfterExecute(new ACFiCallback() {
                    @Override
                    public void callback() throws Exception {
                        moduleOwnerDao.saveItems(null, newItem);
                    }

                });
                return false;
            }

            @Override
            public boolean update(final ACFmModule oldItem, final ACFmModule newItem, ACFdSQLAssUpdate ass) throws Exception {
                moduleOwnerDao.saveItems(oldItem, newItem);
                return false;
            }

            @Override
            public boolean delete(final ACFmModule oldItem, ACFdSQLAssDelete ass) throws Exception {
                moduleOwnerDao.saveItems(oldItem, null);
                return false;
            }
        });
    }

}
