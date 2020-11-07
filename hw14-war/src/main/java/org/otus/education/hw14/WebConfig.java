package org.otus.education.hw14;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.SessionFactory;
import org.otus.education.hw14.data.core.model.User;
import org.otus.education.hw14.data.hibernate.HibernateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@EnableWebMvc
@PropertySource(value = {"classpath:/application.properties"})
public class WebConfig implements WebMvcConfigurer {

    @Value("${database.url}")
    private String dbUrl;

    @Value("${database.user}")
    private String dbUser;

    @Value("${database.password}")
    private String dbPassword;

    @Value("${flyway.scripts}")
    private String flywayScripts;

    @Value("${hibernate.config}")
    private String hibernateConfig;


    @Value("${template.prefix}")
    private String prefix;

    @Value("${template.suffix}")
    private String suffix;

    @Value("${template.charset}")
    private String charset;

    @Bean
    public DataSource dataSource() {
        var dataSource = new JdbcDataSource();
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setURL(dbUrl);
        return dataSource;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations(flywayScripts)
                .load();
    }

    @Bean
    @DependsOn({"flyway"})
    public SessionFactory sessionFactory(DataSource dataSource) {
        return HibernateUtils.buildSessionFactory(hibernateConfig, dataSource, User.class);
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(ApplicationContext applicationContext) {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix(prefix);
        templateResolver.setSuffix(suffix);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);
        templateResolver.setCharacterEncoding(charset);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(ISpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setOrder(1);
        viewResolver.setCharacterEncoding(charset);
        return viewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/index.html").addResourceLocations("/WEB-INF/static/index.html");
        registry.addResourceHandler("/res/**").addResourceLocations("/WEB-INF/static/res/");
    }
}
