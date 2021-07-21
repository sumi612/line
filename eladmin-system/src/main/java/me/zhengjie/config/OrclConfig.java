package me.zhengjie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryorcl",//配置连接工厂 entityManagerFactory
        transactionManagerRef = "transactionManagerorcl",//配置 事物管理器  transactionManager
        basePackages = {"me.zhengjie.orcl.repository"}//todo 设置持久层所在位置,这里需要配置
)
public class OrclConfig {

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;
    @Autowired
    @Qualifier("orclDataSource")
    private DataSource orclDataSource;// 自动注入配置好的数据源
    @Value("${spring.jpa.hibernate.orcl-dialect}")
    private String orclDialect;// 获取对应的数据库方言

    /**
     *
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerFactoryorcl")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryorcl(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        properties.put("hibernate.dialect",orclDialect);
        return builder
                //设置数据源
                .dataSource(orclDataSource)
                //设置数据源属性
                .properties(properties)
                //设置实体类所在位置.扫描所有带有 @Entity 注解的类
                //todo 这里需要改成实际项目中包的位置
                .packages("me.zhengjie.orcl.domain")
                // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
                // Repository就能用它来创建 EntityManager 了,然后 EntityManager 就可以针对数据库执行操作
                .persistenceUnit("orclPersistenceUnit")
                .build();

    }


    /**
     * 配置事物管理器
     *
     * @param builder
     * @return
     */
    @Bean(name = "transactionManagerorcl")
    PlatformTransactionManager transactionManagerorcl(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryorcl(builder).getObject());
    }

}
