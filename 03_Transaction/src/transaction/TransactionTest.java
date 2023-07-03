package transaction;

import java.sql.*;

import javax.sql.rowset.serial.SQLOutputImpl;

import config.ServerInfo;

public class TransactionTest {

	public static void main(String[] args) {
			
		
		//1.드라이버 로딩
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
			
		//2.데이터베이스 연결
			Connection conn = DriverManager.getConnection(ServerInfo.URL,ServerInfo.USER,ServerInfo.PASSWORD);
			System.out.println("DB Connection....");
			//원래 sql에 자료 넘길때 커밋을 해줘야하는데 자동기능 설정되어있어서 안해도되는데
			//아래 코드는 커밋 안하게끔 설정한거야
			//transaction 시작
			conn.setAutoCommit(false); 
			
		//3.PreparedStatement
			String query1 = "INSERT INTO customer(name,age,address) VALUES (?,?,?)";
			PreparedStatement st = conn.prepareStatement(query1);
			
	
		//4.쿼리문 실행
					
			st.setString(1,"금미리");
			st.setInt(2, 17);
			st.setString(3, "서울 강남구");
			
			int result = st.executeUpdate();
			
			Savepoint savepoint = conn.setSavepoint("A"); //A가 있는 곳으로 갔기 때문에 저장이됨
			//롤백이나 커밋의 범위를 제어하기 위해 사용
			
			String query2 = "SELECT * FROM customer WHERE name=?";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setString(1, "강원구");
			
			ResultSet rs = st2.executeQuery();
			
			if(rs.next()) {
				conn.rollback(savepoint);
				System.out.println("회원 정보가 있으므로 rollback!!");
			} else {
				conn.commit();
				System.out.println("회원정보가 없으므로 commit");
			}
			
			//transaction 처리 워낼대로 돌려놓음~~
			conn.setAutoCommit(true); 
//			System.out.println(result + "명 추가!!");
			
			
			
			if(result == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			st.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
