package config;

/*
 * 디비 서버 정보의 상수값으로 구성된 인터페이스
 * 
 * */

public interface ServerInfo {
	
	public static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:ex";
	public static final String USER = "kh";
	public static final String PASSWORD = "kh";
}
