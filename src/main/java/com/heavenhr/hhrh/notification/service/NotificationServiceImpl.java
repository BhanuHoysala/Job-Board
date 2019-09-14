package com.heavenhr.hhrh.notification.service;

import com.heavenhr.hhrh.application.model.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public boolean notify(Application application) {

        log.info("Sending Application Status {} to candidate {}",
                application.getStatus(), application.getEmailId());
        return true;
    }
}
