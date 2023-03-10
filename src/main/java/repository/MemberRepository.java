package repository;

import domain.Member;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class MemberRepository {

    private final BasicDataSource basicDataSource;

    public MemberRepository(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    public Member findByIdAndPw(String id, String pw) throws Exception {
        String sql = "SELECT * FROM MEMBERS WHERE ID = ? AND PW = ?";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, pw);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getMember(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new Exception("아이디 또는 비밀번호가 틀렸습니다.");
    }

    private Member getMember(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("ID");
        String pw = resultSet.getString("PW");
        String name = resultSet.getString("NAME");
        String contact = resultSet.getString("CONTACT");
        Timestamp regTimestamp = resultSet.getTimestamp("REG_TIMESTAMP");
        return new Member(id, pw, name, contact, regTimestamp);
    }

    public void insert(Member member) throws Exception {
        String sql = "INSERT INTO MEMBERS VALUES (?, ?, ?, ?, DEFAULT)";
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, member.getId());
            preparedStatement.setString(2, member.getPw());
            preparedStatement.setString(3, member.getName());
            preparedStatement.setString(4, member.getContact());
            if (preparedStatement.executeUpdate() == 1) {
                connection.commit();
            } else {
                throw new Exception("회원가입에 실패하였습니다.");
            }
        }
        
    }
}