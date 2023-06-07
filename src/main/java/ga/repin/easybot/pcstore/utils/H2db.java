package ga.repin.easybot.pcstore.utils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.invoke.MethodHandles;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@RequiredArgsConstructor
@Configuration
public class H2db implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;
    
    @Value("${db.port}")
    private String dbPort;
    
    @Value("${db.init.script}")
    private String dbInitScript;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    @Bean(initMethod = "start", destroyMethod = "stop")
    public org.h2.tools.Server H2Server() {
        try {
            Server h2Server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", dbPort);
            LOGGER.info("H2 server is running now.");
            return h2Server;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to start H2 server: ", e);
        }
    }
    
    @PreDestroy
    public void onDestroy() {
        LOGGER.info("Spring Container is destroyed!");
    }
    
    @Override
    public void run(String... args) {
        LOGGER.info("CommandLineRunner");
        query();
    }
    
    @PostConstruct
    private void postConstruct() {
        LOGGER.info("postConstruct");
        
    }
    
    private void query() {
        jdbcTemplate.query("select * from \"pcstore_product\"",
                (rs, r) -> {
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();
                    StringBuilder outline = new StringBuilder(Strings.EMPTY);
                    StringBuilder colnames = new StringBuilder(Strings.EMPTY);
                    for (int i = 1; i <= columnCount; i++) {
                        if (r < 1) colnames.append(" | ")
                                .append(rs.getMetaData().getColumnName(i)).append(": ")
                                .append(rs.getMetaData().getColumnTypeName(i));
                        outline.append(" | ").append(rs.getString(i));
                    }
                    if (r < 1) LOGGER.info("\n" + colnames);
                    LOGGER.info("\n" + outline);
                    return null;
                });
    }
}
