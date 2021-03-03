package jp.co.seattle.library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口
public class Search {
    final static Logger logger = LoggerFactory.getLogger(Search.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/search", method = RequestMethod.POST) //value＝actionで指定したパラメータ
    //RequestParamでname属性を取得
    public String search(
            Model model,
            @RequestParam("search") String search) {
        logger.info("Welcome to Search.java!");

        String sql = "";
        String[] split = search.split(" ");

        for (int i = 0; i < split.length; i++) {
            if (i >= 1) {
                sql += "AND";
            }
            sql += "(title like '%" + split[i] + "%' OR author like '%" + split[i] + "%' OR publisher like '%"
                    + split[i] + "%')";
        }

        model.addAttribute("search", search);
        model.addAttribute("items", SearchBooks(sql));
        return "search";
    }

    private List<Map<String, String>> SearchBooks(String sql) {

        // JSPに渡すデータを設定する
        List<Map<String, String>> Booklist = jdbcTemplate.query("select * from books WHERE" + sql,
                new RowMapper<Map<String, String>>() {
                    @SuppressWarnings({ "rawtypes", "unchecked" })
                    public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Map<String, String> map = new HashMap();
                        map.put("id", rs.getString("id"));
                        map.put("title", rs.getString("title"));
                        map.put("author", rs.getString("author"));
                        map.put("publisher", rs.getString("publisher"));
                        map.put("description", rs.getString("description"));
                        map.put("image", rs.getString("image"));
                        map.put("status", rs.getString("status"));
                        return map;
                    }
                });
        return Booklist;
    }
}
