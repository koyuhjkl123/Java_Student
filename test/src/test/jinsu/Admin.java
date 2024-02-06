package test.jinsu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Admin implements UserAdmin {
	// 관리자 메뉴얼
	Scanner sc = new Scanner(System.in);
	Connection con;

	String[] sql_insert = { "종목명", "날짜", "종가", "대비", "등락률", "시가", "고가", "저가", "거래량", "시가총액" };
	private String itmsNms; // 종목명
	private String mrktTotAmt; // 시가 총액
	private String basDt; // 기준 일자
	private String fltRt; // 등락률
	private String trqu; // 거래량
	private String vs; // 대비
	private String clpr; // 종가
	private String mkp; // 시가
	private String hipr; // 고가
	private String lopr; // 저가

	private String userid; // 유저 아이디
	private String pwd; // 유저 비밀번호

	public String getVs() {
		return vs;
	}

	public void setVs(String vs) {
		this.vs = vs;
	}

	public String getClpr() {
		return clpr;
	}

	public void setClpr(String clpr) {
		this.clpr = clpr;
	}

	public String getMkp() {
		return mkp;
	}

	public void setMkp(String mkp) {
		this.mkp = mkp;
	}

	public String getHipr() {
		return hipr;
	}

	public void setHipr(String hipr) {
		this.hipr = hipr;
	}

	public String getLopr() {
		return lopr;
	}

	public void setLopr(String lopr) {
		this.lopr = lopr;
	}

	public String getFltRt() {
		return fltRt;
	}

	public String getTrqu() {
		return trqu;
	}

	public String getEndFltRt() {
		return fltRt;
	}

	public void setFltRt(String fltRt) {
		this.fltRt = fltRt;
	}

	public String getEndTrqu() {
		return trqu;
	}

	public void setTrqu(String trqu) {
		this.trqu = trqu;
	}

	public String getItmsNms() {
		return itmsNms;
	}

	public void setItmsNms(String itmsNms) {
		this.itmsNms = itmsNms;
	}

	public String getMrktTotAmt() {
		return mrktTotAmt;
	}

	public void setMrktTotAmt(String mrktTotAmt) {
		this.mrktTotAmt = mrktTotAmt;
	}

	public String getBasDt() {
		return basDt;
	}

	public void setBasDt(String basDt) {
		this.basDt = basDt;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public void Database() {

		Thread data_thread = new Thread();

//		API의 주소들의 저장소
		String[] api_data = {
//				코스닥 12월 1주차
				"https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?serviceKey=B0n71QWQYKWw2A85EXRc5IQbV1P29e6lKpPlaefJZR4ls84%2BKV8HhzYR7pC6oK0wh0CUhKHZMR4z79CrhJhGUQ%3D%3D&numOfRows=150000&resultType=json&beginBasDt=20231204&endBasDt=20231211&mrktCls=KOSDAQ",
//				코스닥 12월 2주차
				"https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?serviceKey=B0n71QWQYKWw2A85EXRc5IQbV1P29e6lKpPlaefJZR4ls84%2BKV8HhzYR7pC6oK0wh0CUhKHZMR4z79CrhJhGUQ%3D%3D&numOfRows=150000&resultType=json&beginBasDt=20231211&endBasDt=20231218&mrktCls=KOSDAQ",
//				코스닥 12월 3주차
				"https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?serviceKey=B0n71QWQYKWw2A85EXRc5IQbV1P29e6lKpPlaefJZR4ls84%2BKV8HhzYR7pC6oK0wh0CUhKHZMR4z79CrhJhGUQ%3D%3D&numOfRows=150000&resultType=json&beginBasDt=20231218&endBasDt=20231225&mrktCls=KOSDAQ",
//				코스닥 12월 4주차
				"https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?serviceKey=B0n71QWQYKWw2A85EXRc5IQbV1P29e6lKpPlaefJZR4ls84%2BKV8HhzYR7pC6oK0wh0CUhKHZMR4z79CrhJhGUQ%3D%3D&numOfRows=150000&resultType=json&beginBasDt=20231225&endBasDt=20240101&mrktCls=KOSDAQ" };

//		멀티스레드를 활용하기 위한 인터페이스 ExecutorService : 지정한 수만큼의 고정된 쓰레드풀을 생성
		ExecutorService mutil_thread = Executors.newFixedThreadPool(api_data.length);

//		데이터의 결과값을 저장한다.
		List<Future<String>> futures = new ArrayList<>();

		// 각 API URL에 대해 Callable을 생성하고 executorService.submit()으로 제출합니다.
		for (String api_datas : api_data) {
			Callable<String> apiCallTask = () -> AdminDatabase(api_datas);

			// Callable을 mutil_thread에 제출하고 Futures를 리스트에 추가합니다.
			futures.add(mutil_thread.submit(apiCallTask));
		}

		for (Future<String> future : futures) {

			try {
				String result = future.get();
				Thread.sleep(200);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		mutil_thread.shutdown(); // 종료하기
	}

	public synchronized boolean isTableExists(String table_name) {
//		중복 테이블 검사

//		테이블이 있으면 true, 없다면 false
		DatabaseMetaData metadata;
		try {
			metadata = con.getMetaData();
			ResultSet resultset = metadata.getColumns(null, null, table_name, null);

			return resultset.next();
		} catch (SQLException e) {
			return false;
		}

	}

//	스레드 전용 메서드
	public synchronized String AdminDatabase(String api_data) {
		String test = ""; // 가져온 데이터가 null이 아닌지 검사하기 위한 변수
		StringBuilder result = new StringBuilder();
		try {
			URL url = new URL(api_data);
			// 응답 형식을 JSON으로 설정
			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

			while ((test = bf.readLine()) != null) {
				result.append(test);
			}

			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(result.toString());
			JSONObject response = (JSONObject) jsonObj.get("response");
			JSONObject body = (JSONObject) response.get("body");
			JSONObject items = (JSONObject) body.get("items");
			JSONArray item = (JSONArray) items.get("item");

//			Stock1이라는 테이블 생성
			String sql_create = "create table Stock1(" + "num int auto_increment not null primary key,"
					+ "itmsNm VARCHAR(40) not null," + "basDt VARCHAR(40) not null," + "clpr VARCHAR(40) not null,"
					+ "vs VARCHAR(40) not null," + "fltRt VARCHAR(40) not null," + "mkp VARCHAR(40) not null,"
					+ "hipr VARCHAR(40) not null," + "lopr VARCHAR(40) not null," + "trqu VARCHAR(40) not null,"
					+ "mrktTotAmt VARCHAR(40) not null)";

			if (!isTableExists("Stock1")) { // 메서드 호출
//					true : 테이블이 없을 때
//		        	false : 테이블이 있을 때

				try {
					Statement stmt = con.createStatement();
					stmt.executeUpdate(sql_create);
					System.out.println("테이블 생성 완료");
				} catch (Exception e) {
					System.out.println("테이블이 중복되었습니다.");
				}
			}

			for (int i = 0; i < item.size(); i++) {
				JSONObject itmsNm = (JSONObject) item.get(i);
				String itmsNms = (String) itmsNm.get("itmsNm"); // 종목명
				String mrktTotAmt = (String) itmsNm.get("mrktTotAmt"); // 시가총액
				String mrktCtg = (String) itmsNm.get("mrktCtg"); // 시장 구분
				String clpr = (String) itmsNm.get("clpr"); // 종가
				String basDt = (String) itmsNm.get("basDt"); // 기준일자
				String vs = (String) itmsNm.get("vs"); // 대비
				String fltRt = (String) itmsNm.get("fltRt"); // 등락률
				String trqu = (String) itmsNm.get("trqu"); // 거래량
				String mkp = (String) itmsNm.get("mkp"); // 시가
				String hipr = (String) itmsNm.get("hipr"); // 고가
				String lopr = (String) itmsNm.get("lopr"); // 저가

//				api 데이터 가져온 값을 통해 set로 값을 저장
				setItmsNms(itmsNms);
				setBasDt(basDt);
				setClpr(clpr);
				setVs(vs);
				setFltRt(fltRt);
				setMkp(mkp);
				setHipr(hipr);
				setLopr(lopr);
				setTrqu(trqu);
				setMrktTotAmt(mrktTotAmt);

				System.out.print(itmsNms + "  " + basDt + "  " + clpr + "  " + vs + "  " + fltRt + "  " + mkp + "  "
						+ hipr + "  " + lopr + " " + trqu + " " + mrktTotAmt + "\n"); // 날짜, 종가

//				SQL INSERT문
				String sql_insert = "insert into Stock1(itmsNm, basDt, clpr, vs, fltRt, mkp, hipr, lopr, trqu, mrktTotAmt) "
						+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

//				set으로 저장한 값을 insert하여 해당 값을 반환
				PreparedStatement pstmt = con.prepareStatement(sql_insert);
				pstmt = con.prepareStatement(sql_insert);
				pstmt.setString(1, getItmsNms()); // 종목명
				pstmt.setString(2, getBasDt()); // 기준일자
				pstmt.setString(3, getClpr()); // 종가
				pstmt.setString(4, getVs()); // 대비
				pstmt.setString(5, getFltRt()); // 등락률
				pstmt.setString(6, getMkp()); // 시가
				pstmt.setString(7, getHipr()); // 고가
				pstmt.setString(8, getLopr()); // 저가
				pstmt.setString(9, getTrqu()); // 거래량
				pstmt.setString(10, getMrktTotAmt()); // 시가총액
				int result_sum = pstmt.executeUpdate();

				if (result_sum == 1) {
					System.out.println("정보 업데이트 완료되었습니다.");
				} else {
					System.out.println("정보 업데이트 실패되었습니다.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "데이터베이스";
	}

	@Override
	public void AdminInsert() {
		String sql = "insert into stock1(num, itmsNm, basDt, clpr, vs, fltRt, mkp, hipr, lopr, trqu, mrktTotAmt) "
				+ "values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			for (int i = 0; i < sql_insert.length; i++) {

				System.out.println(sql_insert[i] + " : ");
				switch (i) {
				case 0:
					setItmsNms(sc.next());
					pstmt.setString(1, getItmsNms());
					break;
				case 1:
					setBasDt(sc.next());
					pstmt.setString(2, getBasDt());
					break;
				case 2:
					setClpr(sc.next());
					pstmt.setString(3, getClpr());
					break;
				case 3:
					setVs(sc.next());
					pstmt.setString(4, getVs());
					break;
				case 4:
					setFltRt(sc.next());
					pstmt.setString(5, getFltRt());
					break;
				case 5:
					setMkp(sc.next());
					pstmt.setString(6, getMkp());
					break;
				case 6:
					setHipr(sc.next());
					pstmt.setString(7, getHipr());
					break;
				case 7:
					setLopr(sc.next());
					pstmt.setString(8, getLopr());
					break;
				case 8:
					setTrqu(sc.next());
					pstmt.setString(9, getTrqu());
					break;
				case 9:
					setMrktTotAmt(sc.next());
					pstmt.setString(10, getMrktTotAmt());
					break;
				}
			}

			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("정보 업데이트가 완료 되었습니다.");
			} else {
				System.out.println("정보 업데이트가 실패 하였습니다.");
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("해당 정보가 잘못되어 insert에 실패하였습니다.");
			e.printStackTrace();
		}

	}

	@Override
	public void AdminUdate() {
		String sql = "update stock1 set trqu = ?" + " where itmsNm = ? and basDt = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			System.out.println("변경할 거래량을 입력하세요 : ");
			setTrqu(sc.next());

//			종목명, 날짜를 입력해서 변경할 거래량을 입력한다.
			System.out.println("거래량을 변경할 종목명을 입력하세요 : ");
			setItmsNms(sc.next());

			System.out.println("날짜를 입력하세요 : 예) 20231212 ");
			setBasDt(sc.next());

			pstmt.setString(1, getTrqu());
			pstmt.setString(2, getItmsNms());
			pstmt.setString(3, getBasDt());

			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("관리자님 요청하신 데이터 변경 완료 하였습니다.");
			} else {
				System.out.println("관리자님 요청하신 데이터 변경 실패 되었습니다.");
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void AdminDelete() {
		String sql = "delete from stock1 where basDt = ? and itmsNm = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			String date = ""; // 날짜
			String itmsNm = ""; // 종목명

			System.out.println("삭제 조건 : 날짜, 종목명을 입력하셔야합니다.");
			System.out.println("날짜를 입력하세요 : ");
			date = sc.next();
			System.out.println("종목명을 입력하세요 :");
			itmsNm = sc.next();

			pstmt.setString(1, date);
			pstmt.setString(2, itmsNm);

			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("해당 데이터가 삭제 완료 되었습니다");
			} else {
				System.out.println("해당 데이터가 삭제 실패 하였습니다.");
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void AdminSelect() {

		System.out.println("원하시는 검색하실 컬럼을 선택하세요");
		String sql = "select * from stock1 limit 100";

		int[] admin_column = new int[11]; // SQL 컬럼의 수
		boolean[] admin_column_is = new boolean[11]; // 선택된 값은 true

		for (int i = 0; i < sql_insert.length; i++) {

			System.out.print("1. 종목명 2. 날짜 3. 종가 4. 대비 5. 등락률\n");
			System.out.print("6. 시가  7. 고가  8. 저가  9. 거래량  10. 시가총액\n");
			System.out.println(" '0' 을 입력하시면 선택 종료됩니다. ");

			int input = sc.nextInt(); // 관리자 원하는 컬럼을 검색을 하기 위한

			if (input == 0) { // 0이면 종료
				break;
			}

//			선택지에 없는 번호를 입력 시 
			if (input < 1 || input > 10) {
				System.out.println("잘못 입력한 번호이며, 다시 입력하시길 바랍니다.");
				i--;
				continue;
			}

//			선택지 중복 체크
			if (admin_column_is[input]) {
				System.out.println("선택하신 해당 메뉴는 중복된 정보입니다.");
				System.out.println("다시 입력하시길 바랍니다.");
				i--;
				continue;
			}

			admin_column[i] = input; // 선택한 번호는 배열에 넣는다
			if (input != 0) {
				admin_column_is[input] = true; // 해당 번호에 불리언 배열에 true
			}
		}
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.print("종목명 \t  날짜 \t  거래량 \t  시가총액 \n");
			while (rs.next()) {

				for (int i = 0; i < sql_insert.length; i++) {

//					admin_column_is boolean타입 배열이 i번째가 true라면 
//					해당 컬럼의 위치의 수가 출력
					if (admin_column_is[i]) {
						System.out.print(rs.getString(i + 1) + "\t");
					}
				}
				System.out.println();
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void AdminEmail(String email) {

		
	
		System.out.println("입력하신 해당 메일에 인증번호를 전송합니다.");
		String user_email = email; // 사용자 이메일

		String email_title = "이메일 인증"; // 사용자가 받을 이메일 제목
//			        6자리 숫자가 랜덤으로 발송됨
		String user_email_body = "인증 코드: " + GenerateVerificationCode();

		String is_key = user_email_body.substring(7); // 해당 발송된 인증 메일
		System.out.println("생성된 인증 코드: " + is_key);
		// 발신 이메일 계정 설정
		String admin_email = "koyu12315@gmail.com"; // 관리자 이메일 정보
		String admin_email_pwd = "qoli ivvo sasc ofae"; // 관리자 이메일 비밀번호

		// SMTP 서버 설정
		String email_server_host = "smtp.gmail.com";
		String email_server_port = "587";

		// 이메일 전송
		SendEmail(user_email, email_title, user_email_body, admin_email, admin_email_pwd, email_server_host, email_server_port);

		System.out.println("해당 메일로 받으신 인증번호를 입력하세요 : ");
		String user_is_key = sc.next();

		if (user_is_key.equals(is_key)) {
			System.out.println("고객님의 인증메일이 성공하였습니다.");
		} else {
			System.out.println("고객님의 인증메일이 실패하였습니다.");
		}
	}

	@Override
//	관리자 메일로 사용자에게 인증메일 발송
	public void SendEmail(String user_email, String email_title, String email_body, String admin_email,
			String admin_email_pwd, String email_server_host, String email_server_port) {

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", email_server_host);
		properties.put("mail.smtp.port", email_server_port);
//	        qoli ivvo sasc ofae // 2차 앱 비밀번호

//	       session 객체 생성: 이메일 전송에 필요한 세션을 설정
//	        Authenticator를 이용한 인증: Authenticator 클래스를 상속받아 발신자 계정의 아이디와 비밀번호를 제공하여 이메일 서버에 인증
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
//	           해당 클래스의 메소드는 아이디와 비밀번호를 저장하는데 사용되고 이메일 발송 시 이 정보를 사용하여 
//	            이메일 서버에 로그인하고 발신자 이메일 주소와 관련된 계정으로 인증을 수행
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(admin_email, admin_email_pwd);
			}
		});

		try {
//	        	MimeMessage : 메세지의 구조와 내용을 설정
//	        	수신자, 제목, 본문 설정: 수신자 주소, 이메일 제목, 본문 내용을 설정
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(admin_email)); // 관리자 이메일 정보
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user_email)); // 사용자 이메일
			message.setSubject(email_title); // 이메일 제목
			message.setText(email_body); // 이메일 본문

			Transport.send(message); // 설정된 메세지를 전송함
			System.out.println("이메일이 성공적으로 전송되었습니다.");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
//	사용자에게 이메일 발송 시 인증코드 6자리 생성하는 메서드
	public String GenerateVerificationCode() {

		SecureRandom random = new SecureRandom();
//		10자리의 인증번호를 생성하기 위해 10개의 스트링빌더를 생성한다.
		StringBuilder randomKey = new StringBuilder(6);

		for (int i = 0; i < 6; i++) {
//			0 ~ 9까지 랜덤으로 6번 반복해서 randomKeyBuilder에 넣는다.
			randomKey.append(random.nextInt(10));
		}

		return randomKey.toString();
	}

}