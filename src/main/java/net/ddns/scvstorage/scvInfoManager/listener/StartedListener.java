package net.ddns.scvstorage.scvInfoManager.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {

    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent arg0) {
        System.out.println("====================================");
        System.out.println("Application is Started to " + active + "!");
        System.out.println("====================================");
    }

}