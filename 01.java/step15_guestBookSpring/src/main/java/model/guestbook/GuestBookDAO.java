package model.guestbook;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GuestBookDAO {

    private final JdbcTemplate jdbcTemplate;

    public GuestBookDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean writeContent(GuestBookBean vo) {
        String sql = "INSERT INTO gbook (num, title, author, email, content, password, writeday, readnum) " +
                     "VALUES (seq_gbook_num.nextval, ?, ?, ?, ?, ?, sysdate, 0)";
        int rows = jdbcTemplate.update(sql, vo.getTitle(), vo.getAuthor(), vo.getEmail(), vo.getContent(), vo.getPassword());
        return rows > 0;
    }

    public GuestBookBean getContent(int num, boolean flag) {
        if (flag) {
            String updateSql = "UPDATE gbook SET readnum = readnum + 1 WHERE num = ?";
            jdbcTemplate.update(updateSql, num);
        }

        String selectSql = "SELECT num, title, author, email, content, password, " +
                           "to_char(writeday, 'yyyy/mm/dd hh24:mi:ss') AS writeday, readnum " +
                           "FROM gbook WHERE num = ?";
        return jdbcTemplate.queryForObject(selectSql, new Object[]{num}, new GuestBookRowMapper());
    }

    public boolean deleteContent(int num, String password) {
        String sql = "DELETE FROM gbook WHERE num = ? AND password = ?";
        int rows = jdbcTemplate.update(sql, num, password);
        return rows > 0;
    }

    public boolean updateContent(GuestBookBean vo) {
        String sql = "UPDATE gbook SET title = ?, author = ?, email = ?, content = ? WHERE num = ? AND password = ?";
        int rows = jdbcTemplate.update(sql, vo.getTitle(), vo.getAuthor(), vo.getEmail(), vo.getContent(), vo.getNum(), vo.getPassword());
        return rows > 0;
    }

    public List<GuestBookBean> getAllContents() {
        String sql = "SELECT num, title, author, email, content, password, " +
                     "to_char(writeday, 'yyyy/mm/dd hh24:mi:ss') AS writeday, readnum " +
                     "FROM gbook ORDER BY num DESC";
        return jdbcTemplate.query(sql, new GuestBookRowMapper());
    }

    private static class GuestBookRowMapper implements RowMapper<GuestBookBean> {
        @Override
        public GuestBookBean mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new GuestBookBean(
                rs.getInt("num"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("email"),
                rs.getString("content").replaceAll("\n", "<br>"),
                rs.getString("password"),
                rs.getString("writeday"),
                rs.getInt("readnum")
            );
        }
    }
}
