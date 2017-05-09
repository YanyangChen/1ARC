package arc.apf.Service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import arc.apf.Abstract.ARCaAppService;
import arc.apf.Model.ARCmCPLPgmBasic;



@Service
public abstract class ARCsCPLPgmBasic extends ARCaAppService {
	
	public ARCsCPLPgmBasic() throws Exception {
		super();
	}
	
	public abstract ARCmCPLPgmBasic selectItemByPgmNum(BigDecimal pgm_num) throws Exception;//for apff011 selecting number from cpl, AC, 2017/03/15
	
	public abstract String getCatCodeByPgmNo(int prog_no) throws Exception;//for apff011 get category code bt programme no, AC, 2017/03/20
}
