package com.youflix.autoingest;

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
 * @FileName : CMSDBConfiguration
 * @Project : CUST
 * @Date : 2020.11.20
 * @Author : 조 준 희
 * @Description : CMSDB Connection Configuration
 * 				  Mapper.xml = classpath:/dao/mapper/CMSMapperDao.xml
 * @History :
 */

@Configuration
@MapperScan(basePackages="com.youflix.autoingest.dao.cms", sqlSessionFactoryRef = "cmsSqlSessionFactory")
@EnableTransactionManagement
public class CMSDBConfiguration {

   @Bean(name = "cmsDataSource")
   @ConfigurationProperties(prefix = "spring.cms.datasource")
   public DataSource cmsDataSource() {
      return DataSourceBuilder.create().type(HikariDataSource.class).build();
   }
   
   @Bean(name = "cmsSqlSessionFactory")
   public SqlSessionFactory cmssqlSessionFactory(@Qualifier("cmsDataSource") DataSource cmsDataSource, ApplicationContext applicationContext) throws Exception {
      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
      sqlSessionFactoryBean.setDataSource(cmsDataSource);
      sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
      sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:dao/mapper/CMSmapperDao.xml"));
      return sqlSessionFactoryBean.getObject();
   }

   @Bean(name = "cmssqlSessionTemplate")
   public SqlSessionTemplate cmssqlSessionTemplate(@Qualifier("cmsSqlSessionFactory") SqlSessionFactory cmssqlSessionFactory) {
      return new SqlSessionTemplate(cmssqlSessionFactory);
   }
}