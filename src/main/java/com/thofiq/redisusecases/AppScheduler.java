package com.thofiq.redisusecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class AppScheduler {

    @Resource(name = "redisTemplate")
    private ListOperations<String, AppData> listOps;

    @Autowired
    private MessagePublisher messagePublisher;

    public AppScheduler() {

    }

    @Scheduled(fixedDelay = 2000)
    public void saveRecords() {
        System.out.println("producing the records...");
        String uuid = UUID.randomUUID().toString();
        AppData appData = new AppData();
        appData.setDetails(uuid);
        this.listOps.rightPush(uuid, appData);

        //fetch the value from redis
        /*AppData result = this.listOps.rightPop(uuid);
        System.out.println(result);*/

        //publish to news channel
        messagePublisher.publish(appData);
    }
}
