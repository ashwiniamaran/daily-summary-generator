package com.bank.summarygenerator.dailysummarygenerator.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {
	String clientInformation;
	String productInformation;
	Double transactionAmount;
}
