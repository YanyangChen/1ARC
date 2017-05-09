package arc.apf.Model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import acf.acf.Abstract.ACFaAppModel;
import acf.acf.General.annotation.ACFgTable;

@ACFgTable(name = "PPR_LOCAL_PROD", enableTableStatistics = false)
public class ARCmPPRLocalProd extends ACFaAppModel {

	public ARCmPPRLocalProd() throws Exception {
		super();
	}

	@Id
	@Column(name = "co_code")
	public String co_code;
	
	@Id
	@Column(name = "pgm_num")
	public BigDecimal pgm_num;
	
	@Column(name = "nw_code")
	public String nw_code;
	
	@Column(name = "studio_start_dt")
	public Date studio_start_dt;
	
	@Column(name = "studio_end_dt")
	public Date studio_end_dt;
	
	@Column(name = "studio_num")
	public String studio_num;
	
	@Column(name = "ob_start_dt")
	public Date ob_start_dt;
	
	@Column(name = "ob_end_dt")
	public Date ob_end_dt;
	
	@Column(name = "location")
	public String location;
	
	@Column(name = "efp_start_dt")
	public Date efp_start_dt;
	
	@Column(name = "efp_end_dt")
	public Date efp_end_dt;
	
	@Column(name = "cre_user_id")
	public String cre_user_id;
	
	@Column(name = "cre_dt")
	public Timestamp cre_dt;
	
	@Column(name = "amd_user_id")
	public String amd_user_id;
	
	@Column(name = "amd_dt")
	public Timestamp amd_dt;
	
	@Column(name = "first_epi_complete_dt")
	public Date first_epi_complete_dt;
	
	@Column(name = "last_epi_complete_dt")
	public Date last_epi_complete_dt;
	
	@Column(name = "video_def")
	public String video_def;
	
	@Column(name = "video_ratio_mode")
	public String video_ratio_mode;
	
	@Column(name = "protect_mode")
	public String protect_mode;
	
	@Column(name = "sound_mode")
	public String sound_mode;
	
	@Column(name = "subtitle")
	public String subtitle;
	
	@Column(name = "mld_product_placement")
	public String mld_product_placement;
	
	@Column(name = "roller_feature")
	public String roller_feature;
	
	@Column(name = "close_caption_1")
	public String close_caption_1;
	
	@Column(name = "close_caption_2")
	public String close_caption_2;
	
	@Column(name = "close_caption_3")
	public String close_caption_3;
	
	@Column(name = "close_caption_4")
	public String close_caption_4;
	
	@Column(name = "close_caption_5")
	public String close_caption_5;
	
	@Column(name = "close_caption_6")
	public String close_caption_6;
	
	@Column(name = "close_caption_7")
	public String close_caption_7;
	
	@Column(name = "close_caption_8")
	public String close_caption_8;
	
	@Column(name = "close_caption_9")
	public String close_caption_9;
	
	@Column(name = "tx_mode")
	public String tx_mode;
	
	@Column(name = "lang1")
	public String lang1;
	
	@Column(name = "lang2")
	public String lang2;
	
}
