package arc.apf.Service;

import org.springframework.stereotype.Service;

import arc.apf.Abstract.ARCaAppService;
import arc.apf.Model.ARCmPPRPgmBasicHist;


@Service
public abstract class ARCsPPRPgmBasicHist extends ARCaAppService {
	
	public ARCsPPRPgmBasicHist() throws Exception {
		super();
	}
	
	public abstract ARCmPPRPgmBasicHist getLatestHistory(final String prog_no) throws Exception;
	
}
