import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StockDrop {
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
		Connection k19_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.17:3306/koposw19", "root", "root"); //우분투 서버 내의 mysql에 koposw19 데이터베이스를 선택, 유저id root, password root로 접속

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
		k19_stmt.execute("drop table stocks;"); // delete from examtable; 쿼리 문장을 처리하고 boolean 결과를 리턴한다.
		k19_stmt.close(); // Statement를 닫는다.
		k19_conn.close(); // Connection을 닫는다.
	}

}
