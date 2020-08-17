package nl.hrmanagement.usermanagement.config;

import nl.hrmanagement.usermanagement.rabbitmq.TaskReceiver;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean(name="deleteUserAbsences")
    public Queue deleteUserAbsences() {
        return new Queue("deleteUserAbsences");
    }

    @Bean(name="deleteUserAttendances")
    public Queue deleteUserAttendances() {
        return new Queue("deleteUserAttendances");
    }

    @Bean(name="archiveCompanyUsers")
    public Queue archiveCompanyUsers() {
        return new Queue("archiveCompanyUsers");
    }

    private static class ReceiverConfig {

        @Bean
        public TaskReceiver receiver() {
            return new TaskReceiver();
        }

    }

}