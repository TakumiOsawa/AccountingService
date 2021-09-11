package com.ftgo.AccountingService.saga;

import com.ftgo.AccountingService.AccountingService;
import com.ftgo.AccountingService.exception.AccountDisabledException;
import com.ftgo.AccountingService.saga.command.AuthorizeCommand;
import com.ftgo.AccountingService.saga.command.AuthorizeCommandInternal;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class AccountingCommandHandlers {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final AccountingService accountingService;

    public AccountingCommandHandlers(@Autowired AccountingService accountingService) {
        this.accountingService = accountingService;
    }

    public CommandHandlers commandHandlers() {
        return SagaCommandHandlersBuilder
                .fromChannel("accountingService")
                .onMessage(AuthorizeCommand.class, this::authorize)
                .build();
    }

    public Message authorize(CommandMessage<AuthorizeCommand> cm) {
        AuthorizeCommand command = cm.getCommand();

        try {
            accountingService.updateAccount(new AuthorizeCommandInternal(command.getConsumerId(),
                    command.getOrderId(), command.getOrderTotal()));
            return withSuccess();
        }
        catch (AccountDisabledException e) {
            return withFailure(new AccountDisabledReply());
        }
    }
}
