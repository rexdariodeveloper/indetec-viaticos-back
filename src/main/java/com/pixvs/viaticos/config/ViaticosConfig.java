package com.pixvs.viaticos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.Base64;
import java.util.HashMap;

import static java.lang.Character.isUpperCase;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
        basePackages = "com.pixvs.viaticos.dao.viaticos",
        entityManagerFactoryRef = "viaticosEntityManager",
        transactionManagerRef = "viaticosTransactionManager"
)

public class ViaticosConfig {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean viaticosEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(viaticosDataSource());
        em.setPackagesToScan( new String[] { "com.pixvs.viaticos.model.viaticos" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        //properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    public DataSource viaticosDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("viaticos.datasource.url"));
        dataSource.setUsername(env.getProperty("viaticos.datasource.username"));
        dataSource.setPassword(env.getProperty("viaticos.datasource.password"));
        dataSource.setDriverClassName(env.getProperty(("viaticos.datasource.driverClassName")));
        /*
        System.out.println("***********************************AQUI***********************************");
        System.out.println(decrypt(env.getProperty("viaticos.datasource.password")));*/
        return dataSource;
    }
    public String decrypt(String str){
        String encode="",decodedString="contraseña no valida";
        for (int i=0;i<str.chars().count();i++) {
            char l=(char) str.chars().toArray()[i];

            if(!isNumeric(Character.toString((char) l)) && Character.isLowerCase(l)){
                l=Character.toUpperCase(l);

            }else if(!isNumeric(Character.toString((char) l))){
                l=Character.toLowerCase(l);
            }
            encode +=(char) l;
        }

        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encode);
            decodedString = new String(decodedBytes);
        } catch (Exception e) {
            return decodedString;
        }


        return  decodedString;

    }
    public static String getMD5(String input) {
        String encode="";
        for (int i=0;i<Base64.getEncoder().encodeToString(input.getBytes()).chars().count();i++) {
            char l=(char) Base64.getEncoder().encodeToString(input.getBytes()).chars().toArray()[i];

            if(!isNumeric(Character.toString((char) l)) && Character.isUpperCase(l)){
                l=Character.toLowerCase(l);

            }else if(!isNumeric(Character.toString((char) l))){
                l=Character.toUpperCase(l);
            }
           encode +=(char) l;
        }
        return encode;
    }
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    @Primary
    @Bean
    public PlatformTransactionManager viaticosTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(viaticosEntityManager().getObject());
        return transactionManager;
    }
}
