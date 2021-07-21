package me.zhengjie.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {


    @Bean(name = "druidDataSource")
    @Primary
    @Qualifier("druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource primaryDatasource() {
        return DataSourceBuilder.create().build();
    }
    //
    @Bean(name = "orclDataSource")
    @Qualifier("orclDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

}
