/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.util.Properties;
import javax.sql.DataSource;
import model.Alamat;
import model.Kecamatan;
import model.Person;
import model.Student;
import model.Transaksi;
import model.TransaksiDetail;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author victo
 */
@Configuration
@ComponentScan(basePackages = {"dao"})
@EnableTransactionManagement
public class Konfigurasi {

    @Bean
    public LocalSessionFactoryBean getSessionFactoryBean(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        
        Properties props = new Properties();
        props.put("hibernate.show_sql", true);
        props.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        
        factoryBean.setHibernateProperties(props);
        //Ini untuk 1 kelas
        factoryBean.setAnnotatedClasses(Transaksi.class, Person.class, Alamat.class, Student.class, Kecamatan.class);
        //ini utnuk lebih dari 1 kelas
//        factoryBean.setAnnotatedClasses(Transaksi.class, TransaksiDetail.class);
        //ini untuk 1 package atau folder class
        //factoryBean.setAnnotatedPackages("model");
        
        return factoryBean;
    }
    
    @Bean
    public HibernateTransactionManager getTransactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactoryBean().getObject());
        return transactionManager;
    }
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://localhost;databaseName=DB_Transaksi;instanceName=SQLEXPRESS_2017");
        dataSource.setUsername("sa");
        dataSource.setPassword("localhost");

        return dataSource;

    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    @Bean
    public NamedParameterJdbcTemplate nameJdbcTemplate() {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
        return jdbcTemplate;
    }
}
