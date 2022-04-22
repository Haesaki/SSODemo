package com.example.ssodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScans;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.ssodemo.mapper"})
@ConfigurationProperties(prefix = "spring.datasource.hikari")
public class SsoDemoApplication {

    public static void main(String[] args) {
        try {
        SpringApplication.run(SsoDemoApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource' defined in class
//    path resource [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]: Bean instantiation via
//    factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate
//    [com.zaxxer.hikari.HikariDataSource]: Factory method 'dataSource' threw exception; nested exception is
//    org.springframework.boot.autoconfigure.jdbc.DataSourceProperties$DataSourceBeanCreationException:
//    Failed to determine a suitable driver class
}
