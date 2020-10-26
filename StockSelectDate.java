import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StockSelectDate {
	public static void main(String[] args) throws ClassNotFoundException, SQLException { //ClassNotFoundException, SQLException이 발생하면 예외가 발생한 메소드를 호출 한 곳으로 예외 객체를 넘긴다.(throws)
		/*
		 * JDBC 드라이버 Load - 인터페이스 드라이버(interface driver)를 구현(implements)하는 작업으로, Class
		 * 클래스의 forName() 메소드를 사용해서 드라이버를 로드한다. forName(String className) 메소드는 문자열로 주어진
		 * 클래스나 인터페이스 이름을 객체로 리턴한다.
		 */
		Class.forName("com.mysql.cj.jdbc.Driver");

		/*
		 * Connection 객체 생성 - Connection 객체를 연결하는 것으로 DriverManager에 등록된 각 드라이버들을
		 * getConnection(url) 메소드를 사용해서 식별한다.이때 url식별자와 같은 것을 찾아서 매핑(mapping)한다. 찾지 못하면
		 * no suitable error 발생한다.
		 */
		Connection k19_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.17:3306/koposw19", "root", "root"); //우분투 서버 내의 mysql에 koposw19 데이터베이스를 선택, 유저id root, password root로 접속

		/*
		 * Statement 객체 생성 sql 쿼리를 생성/실행하며, 반환된 결과를 가져오게 할 작업 영역을 제공한다. Statement 객체는
		 * Connection 객체의 createStatement() 메소드를 사용하여 생성된다.
		 */
		Statement k19_stmt = k19_conn.createStatement();

		/*
		 * Query 수행 Statement 객체가 생성되면 Statement 객체의 executeQuery() 메소드나
		 * executeUpdate(), execute() 메소드를 사용해서 쿼리를 처리한다. stmt.executeQuery()는 수행결과를
		 * ResultSet 객체로 전달한다.
		 */
		ResultSet k19_rset = k19_stmt.executeQuery("select * from stocks where bsop_date = 20120126;"); // 특정일자(20120126)를 추출하는 쿼리 문장을 처리하고 ResultSet 객채로 반환.

		/*
		 * ResultSet 처리 executeQuery() 메소드는 결과로 ResultSet을 반환한다. 이 ResultSet으로부터 원하는
		 * 데이터를 추출하는 과정을 말한다. 데이터를 추출하는 방법은 ResultSet에서 next() 메소드를 이용해서 한 행씩 이동하면서
		 * getXxx()를 이용해서 원하는 필드 값을 추출한다. 이때 rs.getString("name") 혹은 rs.getString(1) 을
		 * 사용한다. ResultSet의 첫 번째 필드는 1 부터 시작한다.
		 */
		Calendar k19_calt = Calendar.getInstance(); // Calender 클래스의 객체 k19_calt를 선언하고, getTime()메소드를 이용해서 현재시간을 가져온다.
		SimpleDateFormat k19_sdt = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss"); // SimpleDateFormat 클래스의 객채 k19_sdt를 생성하고 날짜형식을 정한다.

		System.out.printf("시작 시간 : %s\n", k19_sdt.format(k19_calt.getTime()));

		while (k19_rset.next()) { //k19_rset이 더이상 없을때까지 while문을 수행한다.
			for (int k19_i = 1; k19_i <= 99; k19_i++) { //정수형 변수 k19_i를 생성하고 0으로 초기화. k19_i를 1씩 늘려가면서 99까지 for문을 수행
				System.out.printf("%s, ", k19_rset.getString(k19_i)); //  k19_rset을 String으로 받아서 출력한다.
			}
			System.out.println(""); // 한 줄 밑으로 이동한다.
		}
		Calendar k19_calt2 = Calendar.getInstance();
		System.out.printf("종료시간 : %s\n", k19_sdt.format(k19_calt2.getTime()));

		k19_rset.close(); // ResultSet을 닫는다.
		k19_stmt.close(); // Statement를 닫는다.
		k19_conn.close(); // Connection을 닫는다.
	}
}
