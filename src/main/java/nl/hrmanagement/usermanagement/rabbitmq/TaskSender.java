package nl.hrmanagement.usermanagement.rabbitmq;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Queue;

@Service
public class TaskSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    @Qualifier("deleteUserAbsences")
    private Queue queueDeleteUserAbsences;

    @Autowired
    @Qualifier("deleteUserAttendances")
    private Queue queueDeleteUserAttendances;

    private Gson gson = new Gson();

    public TaskSender(){

    }

    public void sendDeleteQueue(int userId) {
        template.convertAndSend(queueDeleteUserAbsences.getName(), gson.toJson(userId));
        template.convertAndSend(queueDeleteUserAttendances.getName(), gson.toJson(userId));
    }


}