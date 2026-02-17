package com.prtech.game_library_hb.framework.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final DataSource dataSource;
    private final Resource initSql;

    public DatabaseInitializer(DataSource dataSource, @Value("classpath:init.sql") Resource initSql) {
        this.dataSource = dataSource;
        this.initSql = initSql;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(initSql);
        DatabasePopulatorUtils.execute(populator, dataSource);
    }
}
