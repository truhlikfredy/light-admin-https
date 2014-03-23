package org.lightadmin.core.config.context;

import org.lightadmin.core.config.LightAdminConfiguration;
import org.lightadmin.core.config.bootstrap.parsing.configuration.DomainConfigurationSourceFactory;
import org.lightadmin.core.config.bootstrap.parsing.validation.DomainConfigurationSourceValidatorFactory;
import org.lightadmin.core.config.domain.DomainTypeAdministrationConfigurationFactory;
import org.lightadmin.core.persistence.metamodel.DomainTypeEntityMetadataResolver;
import org.lightadmin.core.persistence.metamodel.JpaDomainTypeEntityMetadataResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.Repositories;
import org.springframework.web.context.support.ServletContextResourceLoader;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class LightAdminDomainConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ServletContextResourceLoader servletContextResourceLoader;

    @Autowired
    private LightAdminConfiguration lightAdminConfiguration;

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Bean
    public DomainTypeEntityMetadataResolver domainTypeEntityMetadataResolver() {
        return new JpaDomainTypeEntityMetadataResolver(entityManager);
    }

    @Bean
    @Autowired
    public DomainConfigurationSourceFactory domainConfigurationSourceFactory() {
        return new DomainConfigurationSourceFactory(domainTypeEntityMetadataResolver(), this.beanFactory);
    }

    @Bean
    public DomainConfigurationSourceValidatorFactory domainConfigurationSourceValidatorFactory() {
        return new DomainConfigurationSourceValidatorFactory(lightAdminConfiguration, domainTypeEntityMetadataResolver(), servletContextResourceLoader);
    }

    @Bean
    @Autowired
    public DomainTypeAdministrationConfigurationFactory domainTypeAdministrationConfigFactory(Repositories repositories, DomainTypeEntityMetadataResolver entityMetadataResolver) {
        return new DomainTypeAdministrationConfigurationFactory(repositories, entityManager);
    }


//    @Bean
//    public GlobalConfigurationManagementService globalConfigurationManagementService() {
//        return new GlobalConfigurationManagementServiceImpl();
//    }
//
//    @Bean
//    public DataManipulationService dataManipulationService() {
//        return new DataManipulationServiceImpl();
//    }

}
