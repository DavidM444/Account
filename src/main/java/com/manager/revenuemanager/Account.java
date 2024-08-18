package com.manager.revenuemanager;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Account {
    private UUID account_id;
    private User userId;
    private Double amount_account;


}
