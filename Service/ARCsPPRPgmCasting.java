package arc.apf.Service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import arc.apf.Abstract.ARCaAppService;
import arc.apf.Model.ARCmPPRLocalProd;
import arc.apf.Model.ARCmPPRPgmCasting;


@Service
public abstract class ARCsPPRPgmCasting extends ARCaAppService {
    
    public ARCsPPRPgmCasting() throws Exception {
        super();
    }
    
    public abstract ARCmPPRPgmCasting selectItemByPgmNum(final BigDecimal pgm_num) throws Exception;//for apff011 select item by programme no. AC, 2017/03/15
    
}