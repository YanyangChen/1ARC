package arc.apf.Service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import arc.apf.Abstract.ARCaAppService;
import arc.apf.Model.ARCmPPRLocalProd;


@Service
public abstract class ARCsPPRLocalProd extends ARCaAppService {
	
	public ARCsPPRLocalProd() throws Exception {
		super();
	}
	
	public abstract ARCmPPRLocalProd selectItemByPgmNum(BigDecimal pgm_num) throws Exception;
	
}
