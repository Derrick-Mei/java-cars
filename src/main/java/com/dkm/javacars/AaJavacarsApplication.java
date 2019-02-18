package com.dkm.javacars;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AaJavacarsApplication
{
    private static final String EXCHANGE_NAME = "ServerCars";
    public static final String QUEUE_NAME = "QueueCars";

    public static void main(String[] args)
    {
        SpringApplication.run(AaJavacarsApplication.class, args);
    }

    @Bean
    public TopicExchange newServer()
    {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue newQueue()
    {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Binding bindQueueToServer()
    {
        return BindingBuilder.bind(newQueue()).to(newServer()).with(QUEUE_NAME);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2JsonMessageConverter()
    {
       return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rt(final ConnectionFactory cf)
    {
        final RabbitTemplate rt = new RabbitTemplate(cf);
        rt.setMessageConverter(producerJackson2JsonMessageConverter());
        return rt;
    }


}
