package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import config.ServerInfo;

//jdbc 만들때는 파일(쿼리 넣고), 인터페이스(디비 경로 넣기) 2개 만들고 시작하기
public class DBConnectionTest4 {

	public static void main(String[] args) {

		
		try {
			
			Properties p = new Properties(); 
			try {
				p.load(new FileInputStream("src/config/jdbc.properties"));
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//1. 드라이버 로딩
			Class.forName(ServerInfo.DRIVER_NAME);
			System.out.println("Driver Loading!!!!!!!!!!!");
			
			
			//2. 디비연결
				Connection conn = DriverManager.getConnection(ServerInfo.URL,ServerInfo.USER,ServerInfo.PASSWORD);
				System.out.println("DB Connection!!!!!!");
				
			//3.Statement 객체 생성 - UPDATE
				String query = p.getProperty("jdbc.sql.delete"); 
				//25번줄에 만들어놓은 p를 가지고
				PreparedStatement st = conn.prepareStatement(query);
				
			//4.쿼리문 실행
				st.setInt(1, 1); //첫번째 컬럼에 첫번째 데이터 
				
				int result = st.executeUpdate(); //setInt를 int로 받아줌
				
				System.out.println(result + "명 삭제!");
				
			//결과값 확인하기 
			//select문 사용
				query =p.getProperty("jdbc.sql.select");
				st = conn.prepareStatement(query);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					int empId = rs.getInt("emp_id");
					String empName = rs.getString("emp_name");
					String deptTitle = rs.getString("dept_title");
					Date hireDate = rs.getDate("hire_date");
					
					System.out.println(empId + " / " + empName + " / " + deptTitle + " / " + hireDate);
				}
			}
		
		
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}



