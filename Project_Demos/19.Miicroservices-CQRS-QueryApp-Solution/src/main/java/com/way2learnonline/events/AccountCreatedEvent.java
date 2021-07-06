package com.way2learnonline.events;

public class AccountCreatedEvent extends BaseEvent<String> {

    @Override
	public String toString() {
		return "AccountCreatedEvent [accountBalance=" + accountBalance + ", currency=" + currency + ", id=" + id + "]";
	}

	public final double accountBalance;

    public final String currency;

    public AccountCreatedEvent(String id, double accountBalance, String currency) {
        super(id);
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}
