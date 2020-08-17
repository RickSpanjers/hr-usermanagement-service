package nl.hrmanagement.usermanagement.rabbitmq;

import com.google.gson.Gson;
import nl.hrmanagement.usermanagement.service.impl.UserManagementServiceProvider;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.util.UUID;

@RabbitListener(queues = {"archiveCompanyUsers"})
public class TaskReceiver {

    @Autowired
    UserManagementServiceProvider userManagementServiceProvider;

    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println(" [x] Received message to archive users from company '" + in + "'");
        doWork(in);
        watch.stop();
        System.out.println(" [x] Done in " + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) {
        Gson gson = new Gson();
        userManagementServiceProvider.archiveAllCompanyUsers(gson.fromJson(in, UUID.class));
    }
}