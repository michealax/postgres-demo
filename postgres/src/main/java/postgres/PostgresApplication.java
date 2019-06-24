package postgres;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("postgres.dao")
public class PostgresApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgresApplication.class, args);
    }

}
