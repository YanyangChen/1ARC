package arc.apf.Dao;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

import acf.acf.Static.ACFtDBUtility;
import arc.apf.Abstract.ARCaAppDao;
import arc.apf.Model.ARCmPPRPgmBasicHist;
import arc.apf.Model.ARCmPPRPgmCasting;


@Repository
public class ARCoPPRPgmCasting extends ARCaAppDao<ARCmPPRPgmCasting> {

    public ARCoPPRPgmCasting() throws Exception {
        super();
    }

    @Override
    protected Connection getConnection() throws Exception {
        return ACFtDBUtility.getConnection("PPRDB");
    }
}
