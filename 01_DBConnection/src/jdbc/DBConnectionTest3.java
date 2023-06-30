package jdbc;

import java.sql.*;

import config.ServerInfo;

public class DBConnectionTest3 {
	
	/*
	 * 디비 서버에 대한 정보가 프로그램상에 하드코딩 되어져 있음
	 * --> 해결책 : 별도의 모듈에 디비서버에 대한 정보를 뽑아내서 별도 처리
	 * */
	public static void main(String[] args) {
		
		try {
			//1. 드라이버 로딩
			Class.forName(ServerInfo.DRIVER_NAME);
			System.out.println("Driver Loading!!!!!!!!!!!");
			
			
			//2. 디비연결
				Connection conn = DriverManager.getConnection(ServerInfo.URL,ServerInfo.USER,ServerInfo.PASSWORD);
				System.out.println("DB Connection!!!!!!");
				
			//3.Statement 객체 생성 - UPDATE
				String query = "UPDATE emp SET dept_title = ? WHERE emp_id = ? ";
				//PK로 지정된 값을 WHERE절로 찾아서 dept_title 값 변경해주기
				PreparedStatement st = conn.prepareStatement(query);
				
			//4.쿼리문 실행
				st.setString(1, "디자인팀"); //첫번째는 물음표 자리
				st.setInt(2, 1);
				
				int result = st.executeUpdate();
				System.out.println(result + "명 수정!");
				
			//결과값 확인하기 
			//select문 사용
				query = "SELECT * FROM emp";
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


