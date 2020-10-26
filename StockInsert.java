
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StockInsert {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		/*
		 * main 메소드 시작. ClassNotFoundExceptron은 String을 통해 클래스를 참조하여 클래스를 로드하려고 할 때 발생하는
		 * 예외상황을 throw. SQL에 문법 에러나 DB에서 처리할 수 없을 정도로 데이터 엑세스 로직에 심각한 버그가 있거나, 서버가 죽는 등의
		 * 네트워키가 귾기는 심각한 상황등에 대한 예외 상황에대해 throw.
		 */
		Class.forName("com.mysql.cj.jdbc.Driver");
		/*
		 * 1단계 JVM에서 동작할 클래스들의 정보를 묘사하기 위한 메타 클래스. Class.forName()을 호출하면 Driver가 자기자신을
		 * 초기화하여 DriverManager에 등록을 한다. 즉 개발자가 따로 관리하지 않는 static 객체들이 알아서 DriverManager에
		 * 등록이 되어 Class.forName()을 호출하고 나서 어떠한 인자로도 전달하지 않고 바로 getConnection()을 호출해도
		 * 드라이버가 찾아지는 것. forName()에서 괄호 안은 드라이버 클래스가 들어가는데, cj는 현재의 새로운 드라이버 클래스에서
		 * 추가되었다. 여기서 드라이버 클래스를 찾지 못할경우 forName()메소드는 ClassNotFoundException 예외를 발생시키므로
		 * 예외 처리 해야 한다.
		 */

		Connection k19_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.17:3306/koposw19", "root", "root");
		/*
		 * 2단계 Connection은 객체를 연결하는 것으로 DriverManager에 등록된 각 드라이버들을 getConnection(String
		 * url)메소드를 사용해서 식별한다. 이때 url 식별자와 같은 것을 찾아서 매핑하는데 찾지 못하면 no suitable error가
		 * 발생함. 3306/이후 "테이블을 생성할 database명 선택 후 적기.(use 데이터베이스 명과 같다)",
		 * "우분투에서 MySQL에 접속하는 사용자 ID", "그 사용자ID 비밀번호" 순으로 적는다.
		 */
		Statement k19_stmt = k19_conn.createStatement();
		/*
		 * 3단계 sql 쿼리를 생성/실행하며, 반환된 결과를 가져오게 할 작업 영역 제공 Statement 객체는 Connection 객체의
		 * createStatement() 메소드를 사용하여 생성하므로 conn.createStatement()로 적는다.
		 */

		Calendar k19_calt = Calendar.getInstance(); // Calender 클래스의 객체 k19_calt를 선언하고, getTime()메소드를 이용해서 현재시간을 가져온다.
		SimpleDateFormat k19_sdt = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss"); // SimpleDateFormat 클래스의 객채 k19_sdt를 생성하고 날짜형식을 정한다.

		long k19_start = System.currentTimeMillis(); // 시작하는 시점 계산

		File k19_f = new File("C:\\Users\\kopo19\\Desktop\\data\\StockDailyPrice.csv"); // 해당 경로에 파일에 접근을 가능하게 하는 file클래스 인스턴스 생성
		BufferedReader k19_br = new BufferedReader(new FileReader(k19_f)); // 그 파일에 내용을 버퍼라이터로 쓴다

		String k19_readTxt; // 한줄씩 읽어올 변수

		String k19_query = "INSERT IGNORE INTO stocks values"; // insert 문 

		int k19_cnt = 0; // 레코드 카운트 변수
		int k19_wCnt = 0; // 한 레코드 열 개수 변수

		StringBuffer k19_sb = new StringBuffer(); // insert 문을 만들기 위한 StringBuffer 객체 선언
		k19_sb.append(k19_query); // insert문 앞 부분 StringBuffer에 붙이기
		String[] k19_staticField = new String[99]; // DB 테이블 컬럼 최대 길이 만큼의 배열 선언

		while ((k19_readTxt = k19_br.readLine()) != null) { // 파일 끝까지 읽을 때까지 반복

			String[] k19_field = k19_readTxt.split(","); // ,를 구분자로 컬럼 구분

			k19_sb.append("("); //  StringBuffer에 insert문 괄호 열기
			for (int k19_i = 0; k19_i < k19_staticField.length; k19_i++) { // 테이블 컬럼 길이만큼 반복
				if (k19_i > k19_field.length - 1) { // 원본의 컬럼 길이가 i의 값보다 작으면
					k19_staticField[k19_i] = null; // 원본의 컬럼 길이 다음의 값은 null 로 초기화
				} else { // 원본의 컬럼 길이가 i 값에 속하면 
					k19_staticField[k19_i] = k19_field[k19_i]; // 그대로 값 대입
				}
				if (k19_staticField[k19_i] == null) { // 최대길이의 배열의 값이 null이면 아무것도 하지않음

				} else if (!k19_staticField[k19_i].matches("^[0-9]*$")) { // 숫자가 아니면
					k19_staticField[k19_i] = "'" + k19_staticField[k19_i] + "'"; // DB String 처리
				} else if (k19_staticField[k19_i].equals("")) { // 값이 공백이면
					k19_staticField[k19_i] = null; // 최대 길이의 배열에 null 값 대입 
				}
				if (k19_i == k19_staticField.length - 1) { // 컬럼의 마지막이 되면 StringBuffer에 insert문 괄호를 닫고 ,를 붙인다
					k19_sb.append(k19_staticField[k19_i] + "),");
				} else {
					k19_sb.append(k19_staticField[k19_i] + ","); // 컬럼의 마지막이 아니면 StringBuffer에 ,를 붙인다
				}
				k19_wCnt++; //wCnt를 1씩 늘린다.
			}

			if (k19_cnt == 0) { //k19_cnt가 0이면 
				System.out.printf(" 시작 시간 : %s\n", k19_sdt.format(k19_calt.getTime())); //시작시간을 출력한다.
			}

			if (k19_cnt % 10000 == 0) { // 10000 건씩 데이터 입력
				k19_stmt.addBatch(k19_sb.toString().substring(0, k19_sb.toString().length() - 1)); // StringBuffer 맨 마지막 ,를 빼준다
				k19_stmt.executeBatch(); // 배치 실행
				k19_sb.setLength(0);
				; // StringBuffer null로 초기화
				k19_sb = new StringBuffer(); //  StringBuffer 다시 생성
				k19_sb.append(k19_query); // insert문 앞부분 추가
				System.out.printf("[%d]record \n", k19_cnt); // 넣은 레코드 몇개인지 출력
			}

			k19_cnt++; // 카운트 증가
		}
		k19_stmt.addBatch(k19_sb.toString().substring(0, k19_sb.toString().length() - 1)); // 마지막으로 10000건 안에 안들어온 insert문 배치에 저장
		k19_stmt.executeBatch(); // 배치 실행
		//         k19_stmt.clearBatch(); // 배치 청소
		System.out.printf("Program End[%d][%d]records\n", k19_cnt, k19_wCnt); // 몇건이 입력됐는지 출력
		k19_conn.close();
		long k19_end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
		Calendar k19_calt2 = Calendar.getInstance();
		System.out.printf(" 종료 시간 : %s Program End[%d][%d] records\n", k19_sdt.format(k19_calt2.getTime()), k19_cnt, k19_wCnt);
		System.out.println("실행 시간 : " + (k19_end - k19_start) / 1000.0 + "초"); //실행 시간 계산 및 출력

		k19_stmt.close(); // Statement의 stmt 닫기. 밑에서 stmt을 다시 얻기 전에는 사용 불가.
		k19_conn.close(); // connection의 conn 닫기. 밑에서 conn을 다시 얻기 전에는 사용 불가.
	}
}