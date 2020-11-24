package jp.co.seattle.library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
@Controller  //APIの入り口
public class Rent {
	final static Logger logger = LoggerFactory.getLogger(Rent.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/rent",method = RequestMethod.POST)
	public String status(
			@RequestParam("id") int id,
			@RequestParam("status") int status,
			Model model){
		logger.info("Welcome to status");

		String returnJsp = "rent";
		String update = "";
		switch(status) {
			case 0:
			update="UPDATE books SET `status` = '1',`rent-at` = sysdate() WHERE id = "+id;
			jdbcTemplate.update(update);
			returnJsp = "rent";
			break;

			case 1:
			update="UPDATE books SET status = '2' WHERE id = "+id+" ";
			jdbcTemplate.update(update);
			returnJsp = "rent";
			break;
		}
		/**
		 * 貸出日-貸出可能日
		 */


		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        // Date型の日時をCalendar型に変換
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 日時を加算する
        calendar.add(Calendar.MONTH, 1);

        // Calendar型の日時をDate型に戻す
        Date d1 = calendar.getTime();
        String expiration = sdf.format(d1);

		model.addAttribute("expiration", expiration);

		model.addAttribute("items", setSelectList(id));
		return returnJsp;
	}

	private List<Map<String, String>> setSelectList(int id) {

		List<Map<String, String>> Booklist = jdbcTemplate.query("select * from books where id ="+id,
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
						map.put("rent-at", rs.getString("rent-at"));
						return map;
					}
				});
		return Booklist;
	}

}
