package com.fsl.gabriel.config;

import com.zaxxer.hikari.HikariDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class BeetlSQL {

  @Bean(name = "dataSource")
  public DataSource datasource(Environment env) {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
    dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
    dataSource.setUsername(env.getProperty("spring.datasource.username"));
    dataSource.setPassword(env.getProperty("spring.datasource.password"));
    return dataSource;
  }

  @Bean
  public BeetlSqlDataSource beetlSqlDataSource(@Qualifier("dataSource") DataSource dataSource) {
    BeetlSqlDataSource source = new BeetlSqlDataSource();
    source.setMasterSource(dataSource);
    return source;
  }
}
