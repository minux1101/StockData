import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StockCreate {

	public static void main(String[] args) throws ClassNotFoundException, SQLException { //ClassNotFoundException, SQLException이 발생하면 예외가 발생한 메소드를 호출 한 곳으로 예외 객체를 넘긴다.(throws)
		/*
		 * JDBC 드라이버 Load - 인터페이스 드라이버(interface driver)를 구현(implements)하는 작업으로, Class
		 * 클래스의 forName() 메소드를 사용해서 드라이버를 로드한다. forName(String className) 메소드는 문자열로 주어진
		 * 클래스나 인터페이스 이름을 객체로 리턴한다.
		 */
		Class.forName("com.mysql.cj.jdbc.Driver"); // mysql 드라이버 로딩, 드라이버들이 읽히기만 하면 자동 객체가 생성되고 DriverManager에 등록된다.

		/*
		 * Connection 객체 생성 - Connection 객체를 연결하는 것으로 DriverManager에 등록된 각 드라이버들을
		 * getConnection(url) 메소드를 사용해서 식별한다.이때 url식별자와 같은 것을 찾아서 매핑(mapping)한다. 찾지 못하면
		 * no suitable error 발생한다.
		 */
		Connection k19_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.17:3306/koposw19", "root", "root");
		//나의 우분투 서버 접속주소 192.168.23.17/ mysql의 포트번호 3306을 포워딩한 3306/ koposw19 데이터베이스를 선택, 유저id root, password root

		/*
		 * Statement 객체 생성 sql 쿼리를 생성/실행하며, 반환된 결과를 가져오게 할 작업 영역을 제공한다. Statement 객체는
		 * Connection 객체의 createStatement() 메소드를 사용하여 생성된다.
		 */
		Statement k19_stmt = k19_conn.createStatement();

		/*
		 * Query 수행 Statement 객체가 생성되면 Statement 객체의 executeQuery() 메소드나
		 * executeUpdate(), execute() 메소드를 사용해서 쿼리를 처리한다. excute()는 쿼리 종류 상관 없이 수행, 결과는
		 * boolean 데이터를 리턴 (데이터 있을 경우 true, 없을 경우 false)
		 */

		//stock 테이블을 생성한다. 기본키는 bsop_date (일자)와  shrn_iscd(종목) 복합키로 잡는다.
		k19_stmt.execute("create table stocks(" + "stnd_iscd varchar(50), " + "bsop_date int, " + "shrn_iscd char(10), " + "stck_prpr int, " + "stck_oprc int, " + "stck_hgpr int, "
				+ "stck_lwpr int, " + "prdy_vrss_sign char(10), " + "prdy_vrss int, " + "prdy_ctrt float, " + "prdy_vol long, " + "acml_vol long, " + "acml_tr_pbmn long, " + "askp1 int, "
				+ "bidp1 int, " + "total_askp_rsqn long, " + "total_bidp_rsqn long, " + "seln_cntg_smtn long, " + "shnu_cntg_smtn long, " + "seln_tr_pbmn long, " + "shnu_tr_pbmn long, "
				+ "seln_cntg_csnu int, " + "shnu_cntg_csnu int, " + "w52_hgpr int, " + "w52_lwpr int, " + "w52_hgpr_date int, " + "w52_lwpr_date int, " + "ovtm_untp_bsop_hour int, "
				+ "ovtm_untp_prpr int, " + "ovtm_untp_prdy_vrss int, " + "ovtm_untp_prdy_vrss_sign char(10), " + "ovtm_untp_askp1 int, " + "ovtm_untp_bidp1 int, " + "ovtm_untp_vol long, "
				+ "ovtm_untp_tr_pbmn long, " + "ovtm_untp_oprc int, " + "ovtm_untp_hgpr int, " + "ovtm_untp_lwpr int, " + "mkob_otcp_vol long, " + "mkob_otcp_tr_pbmn long, "
				+ "mkfa_otcp_vol long, " + "mkfa_otcp_tr_pbmn long, " + "mrkt_div_cls_code char(10), " + "pstc_dvdn_amt long, " + "lstn_stcn long, " + "stck_sdpr int, " + "stck_fcam float, "
				+ "wghn_avrg_stck_prc double, " + "issu_limt_rate float, " + "frgn_limt_qty long, " + "oder_able_qty long, " + "frgn_limt_exhs_cls_code char(10), " + "frgn_hldn_qty long, "
				+ "frgn_hldn_rate float, " + "hts_frgn_ehrt float, " + "itmt_last_nav float, " + "prdy_last_nav float, " + "trc_errt float, " + "dprt float, " + "ssts_cntg_qty long, "
				+ "ssts_tr_pbmn long, " + "frgn_ntby_qty long, " + "flng_cls_code char(10), " + "prtt_rate float, " + "acml_prtt_rate float, " + "stdv float, " + "beta_cfcn float, "
				+ "crlt_cfcn float, " + "bull_beta float, " + "bear_beta float, " + "bull_dvtn float, " + "bear_dvtn float, " + "bull_crlt float, " + "bear_crlt float, " + "stck_mxpr int, "
				+ "stck_llam int, " + "icic_cls_code char(10), " + "itmt_vol long, " + "itmt_tr_pbmn long, " + "fcam_mod_cls_code char(10), " + "revl_issu_reas_code char(10), "
				+ "orgn_ntby_qty long, " + "adj_prpr int, " + "fn_oprc int, " + "fn_hgpr int, " + "fn_lwpr int, " + "fn_prpr int, " + "fn_acml_vol long, " + "fn_acml_tr_pbmn long, "
				+ "fn_prtt_rate long, " + "fn_flng_cls_code char(10), " + "buyin_nor_prpr int, " + "buyin_nor_prdy_vrss int, " + "buyin_nor_vol int, " + "buyin_nor_tr_pbmn int, "
				+ "buyin_tod_prpr int, " + "buyin_tod_prdy_vrss int, " + "buyin_tod_vol long, " + "buyin_tod_tr_pbmn long, primary key(bsop_date, shrn_iscd)) " + "DEFAUlT CHARSET=utf8;");

		k19_stmt.close(); // Statement를 닫는다.
		k19_conn.close(); // Connection을 닫는다.
	}
}
