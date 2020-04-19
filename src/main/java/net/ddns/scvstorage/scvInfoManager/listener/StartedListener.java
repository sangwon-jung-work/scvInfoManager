package net.ddns.scvstorage.scvInfoManager.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent arg0) {
        System.out.println("====================================");
        System.out.println("Application is Started!");
        System.out.println("====================================");
    }

}