package cm.pak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static cm.pak.PakTrainigCons.CM_PAK;

@Configuration
@PropertySource({ "classpath:application.properties" })
@ComponentScan(basePackages = {"cm.pak.repositories"})
public class TrainingDataConfig {


    @Autowired
    private Environment env ;

    public DataSourceProperties dataSourceProperties()
    {
        return  new DataSourceProperties() ;
    }

    @Bean
    @Primary
    public DataSource dataSource()
    {
        final DataSourceProperties dataSourceProperties = new DataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("pak.datasource.driver-class-name"))
                .url(env.getProperty("pak.datasource.url"))
                .username(env.getProperty("pak.datasource.username"))
                .password(env.getProperty("pak.datasource.password"))
                .build();
    }

    private Properties properties()
    {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("pak.jpa.datasource-platform"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("pak.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show_sql", env.getProperty("pak.jpa.show-sql"));

        return properties ;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
    {
        final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("cm.pak");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPersistenceUnitName(PakTrainigCons.PAK_PU);
        factoryBean.setJpaProperties(properties());
        return factoryBean ;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager()
    {
        final EntityManagerFactory factory = entityManagerFactoryBean().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    @Primary
    public EntityManager entityManager()
    {
        return entityManagerFactoryBean()
                .getObject()
                .createEntityManager();
    }
}
