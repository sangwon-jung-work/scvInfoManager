package net.ddns.scvstorage.scvInfoManager.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {

    @Autowired
    String testConfigurations;

    public void run(ApplicationArguments args) throws Exception {
        System.out.println("autowired value: " + testConfigurations);
    }
}