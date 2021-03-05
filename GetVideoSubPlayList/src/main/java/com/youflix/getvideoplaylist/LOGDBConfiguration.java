package com.youflix.getvideoplaylist;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;



/**
 * @FileName : LOGDBConfiguration
 * @Project : CUST
 * @Date : 2020.11.20
 * @Author : 조 준 희
 * @Description : LOGDB Connection Configuration
 * 				  Mapper.xml = classpath:/dao/mapper/LOGMapperDao.xml
 * @History :
 */

@Configuration
@MapperScan(basePackages="com.youflix.getvideoplaylist.dao.log", sqlSessionFactoryRef = "logSqlSessionFactory")
@EnableTransactionManagement
public class LOGDBConfiguration {

   @Bean(name = "logDataSource")
   @ConfigurationProperties(prefix = "spring.log.datasource")
   public DataSource logDataSource() {
      return DataSourceBuilder.create().type(HikariDataSource.class).build();
   }
   
   @Bean(name = "logSqlSessionFactory")
   public SqlSessionFactory logsqlSessionFactory(@Qualifier("logDataSource") DataSource logDataSource, ApplicationContext applicationContext) throws Exception {
      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
      sqlSessionFactoryBean.setDataSource(logDataSource);
      sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
      sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:dao/mapper/LOGmapperDao.xml"));
      return sqlSessionFactoryBean.getObject();
   }

   @Bean(name = "logsqlSessionTemplate")
   public SqlSessionTemplate logsqlSessionTemplate(@Qualifier("logSqlSessionFactory") SqlSessionFactory logsqlSessionFactory) {
      return new SqlSessionTemplate(logsqlSessionFactory);
   }
}