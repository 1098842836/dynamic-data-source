package com.yl.dynamicdatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.util.JdbcConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 动态数据源config
 * @author: yl
 * @date: 2022-07-20
 **/
@Configuration
public class DynamicDruidConfig {


    @Autowired
    private Environment env;


    private static String DATA_SOURCE_CLASS_NAME="org.postgresql.Driver";


    private static String ORACLE_DATA_SOURCE_CLASS_NAME="oracle.jdbc.driver.OracleDriver";



    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DruidDataSource registerDruidDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * @Description //设置多数据源
     * @Date 2022/7/21
     * @return com.yl.dynamicdatasource.config.DynamicDataSource
     * @Author yl
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource setMultiDruidDataSource() throws Exception{

        Map<Object, Object> targetDataSources = new HashMap<>(16);

        DruidDataSource druidDataSource1=registerDruidDataSource();
        // 遂宁
        druidDataSource1.setDriverClassName(DATA_SOURCE_CLASS_NAME);
        druidDataSource1.setUrl("jdbc:postgresql://10.206.20.201:5432/respg");
        druidDataSource1.setUsername("suining");
        druidDataSource1.setPassword("111");
        druidDataSource1.setValidationQuery("SELECT 1");
        druidDataSource1.setDbType(JdbcConstants.POSTGRESQL);
        targetDataSources.put("suining", druidDataSource1.clone());


        DruidDataSource druidDataSource2=registerDruidDataSource();

        // 资阳
        druidDataSource2.setDriverClassName(DATA_SOURCE_CLASS_NAME);
        druidDataSource2.setUrl("jdbc:postgresql://10.206.20.201:5432/respg");
        druidDataSource2.setUsername("ziyang");
        druidDataSource2.setPassword("2222");
        druidDataSource2.setValidationQuery("SELECT 1");
        druidDataSource2.setDbType(JdbcConstants.POSTGRESQL);
        targetDataSources.put("ziyang", druidDataSource2.clone());


        DruidDataSource druidDataSource3=registerDruidDataSource();

        //资阳
        druidDataSource3.setDriverClassName(ORACLE_DATA_SOURCE_CLASS_NAME);
        druidDataSource3.setUrl("jdbc:oracle:thin:@127.0.0.1:8999:cisser1");
        druidDataSource3.setUsername("TABLEQUERYMANAGER");
        druidDataSource3.setPassword("mypassword123");
        druidDataSource3.setValidationQuery("SELECT 'x' FROM DUAL");
        druidDataSource3.setDbType(JdbcConstants.ORACLE);
        druidDataSource3.setMaxActive(20);
        druidDataSource3.setInitialSize(10);
        druidDataSource3.setMinIdle(10);
        druidDataSource3.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource3.setMinEvictableIdleTimeMillis(300000);
        druidDataSource3.setTestOnBorrow(false);
        druidDataSource3.setTestWhileIdle(true);
        druidDataSource3.setTestOnReturn(false);
        druidDataSource3.setMaxOpenPreparedStatements(20);
        druidDataSource3.setFilters("stat");
        druidDataSource3.setMaxWait(60000);
        druidDataSource3.setRemoveAbandoned(true);
        druidDataSource3.setRemoveAbandonedTimeout(1800);
        druidDataSource3.setLogAbandoned(true);


        targetDataSources.put("249", druidDataSource3.clone());




        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(druidDataSource3);
        return dataSource;
    }


    @Bean
    @ConditionalOnMissingBean
    public ServletRegistrationBean<StatViewServlet> druidServlet() {
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(), "/druid/*");

        //白名单：
        //servletRegistrationBean.addInitParameter("allow","192.168.6.195");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        //servletRegistrationBean.addInitParameter("deny","192.168.6.73");
        //登录查看信息的账号密码, 用于登录Druid监控后台 paas平台还没想好怎么解决登录问题 暂时屏蔽
        /*servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");*/
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "true");
        return servletRegistrationBean;

    }

    /**
     * 注册Filter信息, 监控拦截器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }




}
