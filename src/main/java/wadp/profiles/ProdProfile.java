package wadp.profiles;


import java.net.URISyntaxException;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Profile("prod")
public class ProdProfile {

    @Bean
    public PlatformTransactionManager transactionManager() throws URISyntaxException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws URISyntaxException {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaDialect(new HibernateJpaDialect());

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPersistenceUnitName("product");
        factory.setPackagesToScan("wadp.domain");
        factory.setDataSource(dataSource());

        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public DataSource dataSource() throws URISyntaxException {

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setUrl("jdbc:postgresql://localhost/postgres");
        basicDataSource.setUsername("postgres");
        basicDataSource.setPassword("testpg");

        return basicDataSource;
    }
}
