package jdbc;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest2 {
	
	//상수는 final 적어주고 변수명 대문자로 작성
	//원래는 별도로 빼줘야함
	public static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:ex";
	public static final String USER = "kh";
	public static final String PASSWORD = "kh";
	
	public static void main(String[] args) {
		
		try {
			//1. 드라이버 로딩
			Class.forName(DRIVER_NAME);
			System.out.println("Driver Loading!!!!!!!!!!!");
			
			
			//2. 디비연결
				Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
				System.out.println("DB Connection!!!!!!");
				
			//3.Statement 객체 생성 - INSERT
				String query = "INSERT INTO emp(emp_id, emp_name) VALUES(?,?)";
				PreparedStatement st = conn.prepareStatement(query);
				
			//4.쿼리문 실행
				st.setInt(1,1); //인덱스 설정해주기(위치, 값) 
				st.setString(2, "미리"); //28줄 물음표에 들어갈 값을 작성하기
				
				int result = st.executeUpdate(); //해당값은 결과값이 int로 받아옴/ 제대로 실행되면 1 아니면 0
				
				System.out.println(result + "명 추가!");
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}


