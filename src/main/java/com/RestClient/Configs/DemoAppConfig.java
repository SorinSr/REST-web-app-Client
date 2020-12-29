package com.RestClient.Configs;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.RestClient")
@PropertySource({"classpath:application.properties", "classpath:security-persistence-mysql.properties"})
public class DemoAppConfig implements WebMvcConfigurer {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private Environment environment;

//    @Bean
//    public DataSource securityDataSource() {
//        ComboPooledDataSource comboPooledSecurityDataSource = new ComboPooledDataSource();
//        try {
//            comboPooledSecurityDataSource.setDriverClass(environment.getProperty("security.jdbc.driver"));
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//
//        logger.info(">>SECURITY>> jdbc.url=" + environment.getProperty("security.jdbc.driver"));
//        logger.info(">>SECURITY>> jdbc.user=" + environment.getProperty("security.jdbc.user"));
//
//        comboPooledSecurityDataSource.setJdbcUrl(environment.getProperty("security.jdbc.url"));
//        comboPooledSecurityDataSource.setUser(environment.getProperty("security.jdbc.user"));
//        comboPooledSecurityDataSource.setPassword(environment.getProperty("security.jdbc.password"));
//
//        comboPooledSecurityDataSource.setInitialPoolSize(helperMethodConversionStringToInteger("security.connection.pool.initialPoolSize"));
//        comboPooledSecurityDataSource.setMinPoolSize(helperMethodConversionStringToInteger("security.connection.pool.minPoolSize"));
//        comboPooledSecurityDataSource.setMaxPoolSize(helperMethodConversionStringToInteger("security.connection.pool.maxPoolSize"));
//        comboPooledSecurityDataSource.setMaxIdleTime(helperMethodConversionStringToInteger("security.connection.pool.maxIdleTime"));
//        return comboPooledSecurityDataSource;
//    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/StaticResources/**")
                .addResourceLocations("/StaticResources/");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private Integer helperMethodConversionStringToInteger(String string) {
        String propertyValue = environment.getProperty(string);
        if (propertyValue != null) {
            return Integer.parseInt(propertyValue);
        } else {
            throw new RuntimeException("Value not defined.");
        }
    }
}
