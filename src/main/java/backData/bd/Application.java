package backData.bd;

/**
 * Created by konstantin on 25.07.2017.
 */
import backData.bd.transactions.Transaction;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@SpringBootApplication
public class Application /*implements CommandLineRunner*/ {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

//    public static void main(String args[]) {
//        SpringApplication.run(Application.class, args);
//    }

    @Autowired
    private JdbcTemplate jdbcTemplate;
//    static {
//        main(ArrayUtils.EMPTY_STRING_ARRAY);
////        init();
//    }



    public void init(String... strings) {

        log.info("Creating tables");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        jdbcTemplate = new JdbcTemplate(dataSource);

        String commands = null;
        try {
            commands = FileUtils.readFileToString(FileUtils.getFile("./CreateTempDB.sql"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] commandsArr = commands.split(";");
        Arrays.stream(commandsArr)
                .forEach(command -> {
                    if (StringUtils.startsWithIgnoreCase(command.trim(),"INSERT"))
                        jdbcTemplate.batchUpdate(command);
                    else
                        jdbcTemplate.execute(command);
                });

//        log.info("Querying for customer records where first_name = 'Josh':");
//        jdbcTemplate.query(
//                "SELECT * " +
//                        "FROM Tran",
//                (rs, rowNum) -> new Transaction(rs.getString("tranDate"),
//                        rs.getString("tranDescription"),
//                        rs.getString("tranAmount"),
//                        rs.getString("tranType"))
//        ).forEach(transaction -> log.info(transaction.toString()));
////        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM Tran");
//        System.out.println();
    }

     public <T>List<T>guery(String sql, RowMapper<T> rowMapper){
        return jdbcTemplate.query(sql, rowMapper);
    }
}
