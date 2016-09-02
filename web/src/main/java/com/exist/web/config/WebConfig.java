package com.exist.web.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@EnableWebMvc
@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = "com.exist")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    //Fremmarker Configuration
    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        result.setTemplateLoaderPath("/WEB-INF/ftl/");
        return result;
    }

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver fmvr = new FreeMarkerViewResolver();
        fmvr.setCache(true);
        fmvr.setPrefix("");
        fmvr.setSuffix(".ftl");
        fmvr.setRequestContextAttribute("ctx");
        return fmvr;
    }

    //Data Source Configuration
    @Bean
    public DataSource dataSource() throws Exception {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("root");
        dataSource.setPoolName("springHikariCP");
        dataSource.setConnectionTestQuery("SELECT 1");
        dataSource.setMaximumPoolSize(100);
        dataSource.setIdleTimeout(30000);
        return dataSource;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.cache.provider_class", "com.zaxxer.hikari.hibernate.HikariConnectionProvider");
        properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        properties.put("hibernate.cache.use_second_level_cache", "true");
        properties.put("hibernate.cache.use_query_cache", "true");
        properties.put("hibernate.enable_lazy_load_no_trans", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws Exception {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setAnnotatedClasses(com.exist.model.Person.class, com.exist.model.Role.class,
                com.exist.model.Contact.class, com.exist.model.User.class);
        sfb.setDataSource(dataSource());
        sfb.setHibernateProperties(hibernateProperties());
        return sfb;
    }
}