package com.youflix.cust;

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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;


/**
 * @FileName : CUSTDBConfiguration
 * @Project : ContentAPI MY
 * @Date : 2021.01.29
 * @Author : 조 준 희
 * @Description : CUSTDB Connection Configuration
 * 				  Mapper.xml = classpath:/dao/mapper/CUSTMapperDao.xml
 * @History :
 */

@Configuration
@MapperScan(basePackages = "com.youflix.cust.dao.cust", sqlSessionFactoryRef = "custSqlSessionFactory")
@EnableTransactionManagement
public class CUSTDBConfiguration {
   
   //properties DB Config 
	   @Bean(name = "custDataSource")
	   @ConfigurationProperties(prefix = "spring.cust.datasource")
	   public DataSource custDataSource() {
	      return DataSourceBuilder.create().type(HikariDataSource.class).build();
	   }
	   
	   @Bean(name = "custSqlSessionFactory")
	   public SqlSessionFactory custsqlSessionFactory(@Qualifier("custDataSource") DataSource custDataSource, ApplicationContext applicationContext) throws Exception {
	      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	      sqlSessionFactoryBean.setDataSource(custDataSource);
	      sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
	      sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:dao/mapper/CUSTmapperDao.xml"));
	      return sqlSessionFactoryBean.getObject();
	   }

	   @Bean(name = "custsqlSessionTemplate")
	   public SqlSessionTemplate custsqlSessionTemplate(@Qualifier("custSqlSessionFactory") SqlSessionFactory custsqlSessionFactory) {
	      return new SqlSessionTemplate(custsqlSessionFactory);
	   }
}