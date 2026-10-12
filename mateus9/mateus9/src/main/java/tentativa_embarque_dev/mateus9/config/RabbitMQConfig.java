package tentativa_embarque_dev.mateus9.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    @Value("${app.rabbitmq.queue}")
    private String queuePagamento;

    @Value("${app.rabbitmq.exchange}")
    private String exchangePagamento;

    @Value("${app.rabbitmq.dlx.exchange}")
    private String exchangeDLX;

    @Value("${app.rabbitmq.dlq.queue}")
    private String queueDLQ;

    @Value("${app.rabbitmq.dlq.routing-key}")
    private String routingKeyDLQ;

    // ainda possuo algumas duvidas sobre isso aqui, eu posso configurar essas queues, binding, exchange diretamente no .yaml sem ter que escrever código aqui? (estudar isso depois)
    @Bean
    public Queue queue() {
        return QueueBuilder
                .durable(queuePagamento)
                .deadLetterExchange(exchangeDLX)
                .deadLetterRoutingKey(routingKeyDLQ)
                .build();
    }

    @Bean
    public Queue queueDLQ() {
        return QueueBuilder
                .durable(queueDLQ)
                .build();
    }

    @Bean
    public DirectExchange directExchange () {
        return new DirectExchange (exchangePagamento);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(exchangeDLX);
    }

    @Bean
    public Binding binding () {
        return BindingBuilder
                .bind(queue())
                .to(directExchange())
                .with(routingKey);
    }

    @Bean
    public Binding bindingDLQ() {
        return BindingBuilder
                .bind(queueDLQ())
                .to(deadLetterExchange())
                .with(routingKeyDLQ);
    }

    @Bean
    public MessageConverter messageConverter () {
        return new JacksonJsonMessageConverter();
    }

    // entendi que connection factory é o responsável por fornecer as conexões com o broker, ele utiliza informações como host, porta, usuário e senha
    // messageconverter basicamente tem um fluxo de objeto - json - objeto novamente
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }

}
