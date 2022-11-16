package com.bitacademy.mysite02.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bitacademy.mysite02.vo.BoardVo;
import com.bitacademy.mysite02.vo.UserVo;

public class BoardDao {
	// findAll
	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			// 1, 2
			conn = getConnection();

			// 3. statement
			stmt = conn.createStatement(); // row값

			// 4. SQL 실행
			String sql = "select a.no, a.title, b.name, a.hit, date_format(a.date, '%Y/%m/%d %H:%i:%s') as date, a.user_no "
					+ " from board a, user b" + " where a.user_no = b.no" + " order by group_no desc, order_no asc"; // 쿼리

			rs = stmt.executeQuery(sql); // row값에 쿼리를 대입시킨것 (한줄만)

			// 5. 결과처리
			while (rs.next()) { // 한줄이 아닌 전체를 뽑음
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				Long hit = rs.getLong(4);
				String date = rs.getString(5);
				Long userno = rs.getLong(6);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserName(name);
				vo.setHit(hit);
				vo.setDate(date);
				vo.setUserNo(userno);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (stmt != null) {
					stmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// write
	public Boolean write(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1, 2
			conn = getConnection();

			// 3. statement 준비
			String sql = "insert into board values(null, ?, ?, 0, now(), (select max(group_no)) + 1, 1, 0, ?)"; // 쿼리

			pstmt = conn.prepareStatement(sql); // row값

			// 4. binding
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUserNo());

			// 5. 실행
			int count = pstmt.executeUpdate(); // executeUpdate()는 insert등은 반영된 건수를 반환, create&drop은 -1을 반환

			// 5. 결과처리
			result = count == 1; // count == 1 << true

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// deleteByNo
	public Boolean deleteByNo(Long no) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1, 2
			conn = getConnection();

			// 3. statement 준비
			String sql = "delete" + " from board" + " where no = ?";

			pstmt = conn.prepareStatement(sql); // row값

			// 4. Binding
			pstmt.setLong(1, no);

			// 5. SQL 실행
			int count = pstmt.executeUpdate(); //

			// 6. 결과처리
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	// update할 정보 불러오기
	public BoardVo findByNo(Long no) {
		BoardVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1, 2
			conn = getConnection();

			// 3. statement 준비
			String sql = "select title, contents, hit , user_no from board where no = ?"; // 쿼리

			pstmt = conn.prepareStatement(sql); // row값

			// 4. binding
			pstmt.setLong(1, no);

			// 5. 실행
			rs = pstmt.executeQuery();

			// 6. 결과처리
			if (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long hit = rs.getLong(3);
				Long userno = rs.getLong(4);

				result = new BoardVo();
				result.setTitle(title);
				result.setContents(contents);
				result.setHit(hit);
				result.setUserNo(userno);
			}

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// update
	public boolean update(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = getConnection();

			// 3. statement 준비
			String sql = "update board set title = ?, contents = ?, date = now() where no = ?";

			pstmt = conn.prepareStatement(sql); // row값

			// 4. Binding
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());

			// 4. SQL 실행
			int count = pstmt.executeUpdate(); //

			// 5. 결과처리
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	// 조회수
	public boolean hit(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = getConnection();

			// 3. statement 준비
			String sql = "update board set hit = (SELECT(max(hit)+1)) where no = ?";

			pstmt = conn.prepareStatement(sql); // row값

			// 4. Binding
			pstmt.setLong(1, vo.getNo());

			// 4. SQL 실행
			int count = pstmt.executeUpdate(); //

			// 5. 결과처리
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}
	
	// 예외처리
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 1. JDBC Driver Class Loading
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}
}
