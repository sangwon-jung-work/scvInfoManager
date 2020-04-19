package net.ddns.scvstorage.scvInfoManager.runner;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class DataSourceRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try(Connection connection = dataSource.getConnection()) {
            System.out.println("URL: " + connection.getMetaData().getURL());
            System.out.println("ID: " + connection.getMetaData().getUserName());
        }
    }
}