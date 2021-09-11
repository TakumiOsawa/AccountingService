package com.ftgo.AccountingService.saga.command;

import com.ftgo.AccountingService.domain.Money;
import io.eventuate.tram.commands.common.Command;
import lombok.Data;

@Data
public class AuthorizeCommandInternal implements AccountingCommand, Command {
    private long consumerId;
    private Long orderId;
    private Money orderTotal;

    public AuthorizeCommandInternal(long consumerId, Long orderId, Money orderTotal) {
        this.consumerId = consumerId;
        this.orderId = orderId;
        this.orderTotal = orderTotal;
    }
}