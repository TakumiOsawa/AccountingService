package com.ftgo.AccountingService.config;

import com.ftgo.AccountingService.AccountingService;
import com.ftgo.AccountingService.domain.account.repository.AccountRepository;
import com.ftgo.AccountingService.saga.AccountingCommandHandlers;
import io.eventuate.tram.consumer.common.DuplicateMessageDetector;
import io.eventuate.tram.consumer.common.NoopDuplicateMessageDetector;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.eventuate.tram.sagas.spring.participant.SagaParticipantConfiguration;
import io.eventuate.tram.spring.consumer.kafka.EventuateTramKafkaMessageConsumerConfiguration;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.spring.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TramEventsPublisherConfiguration.class,
        TramMessageProducerJdbcConfiguration.class,
        EventuateTramKafkaMessageConsumerConfiguration.class,
        SagaParticipantConfiguration.class })
public class AccountingServiceConfiguration {
    @Bean
    public AccountingService orderService(AccountRepository accountRepository) {
        return new AccountingService(accountRepository);
    }

    @Bean
    public DuplicateMessageDetector duplicateMessageDetector() {
        return new NoopDuplicateMessageDetector();
    }

    @Bean
    public AccountingCommandHandlers accountingCommandHandlers(AccountingService accountingService) {
        return new AccountingCommandHandlers(accountingService);
    }

    @Bean
    public SagaCommandDispatcher accountingCommandHandlersDispatcher(AccountingCommandHandlers accountingCommandHandlers,
                                                                   SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
        return sagaCommandDispatcherFactory.make("accountingService",
                accountingCommandHandlers.commandHandlers());
    }
}
