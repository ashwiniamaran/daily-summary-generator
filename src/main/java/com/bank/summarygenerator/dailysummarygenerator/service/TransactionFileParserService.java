package com.bank.summarygenerator.dailysummarygenerator.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bank.summarygenerator.dailysummarygenerator.enums.TransactionRecordFieldsEnum;
import org.springframework.stereotype.Service;

import com.bank.summarygenerator.dailysummarygenerator.domain.Transaction;

@Service
public class TransactionFileParserService {
	
	public List<Transaction> parseFileContents(List<String> transactions) {
		return transactions.stream().map(t -> {
			String clientInformation;
			String productInformation;
			Double totalTransactionAmount;
			clientInformation = this.getClientInformation(t);
			productInformation = this.getProductInformation(t);
			totalTransactionAmount = this.getTotalTransactionAmount(t);
			return Transaction.builder()
					.clientInformation(clientInformation)
					.productInformation(productInformation)
					.transactionAmount(totalTransactionAmount)
					.build();
		}).collect(Collectors.toList());
	}

	private String getClientInformation(String transaction) {
		return transaction.substring(TransactionRecordFieldsEnum.CLIENT_TYPE.getActualStartIndex(), TransactionRecordFieldsEnum.CLIENT_TYPE.getEndIndex()).trim() + "," +
				transaction.substring(TransactionRecordFieldsEnum.CLIENT_NUMBER.getActualStartIndex(), TransactionRecordFieldsEnum.CLIENT_NUMBER.getEndIndex()).trim() + "," +
				transaction.substring(TransactionRecordFieldsEnum.ACCOUNT_NUMBER.getActualStartIndex(), TransactionRecordFieldsEnum.ACCOUNT_NUMBER.getEndIndex()).trim() + "," +
				transaction.substring(TransactionRecordFieldsEnum.SUB_ACCOUNT_NUMBER.getActualStartIndex(), TransactionRecordFieldsEnum.SUB_ACCOUNT_NUMBER.getEndIndex()).trim();
	}

	private String getProductInformation(String transaction) {
		return transaction.substring(TransactionRecordFieldsEnum.EXCHANGE_CODE.getActualStartIndex(), TransactionRecordFieldsEnum.EXCHANGE_CODE.getEndIndex()).trim() + "," +
				transaction.substring(TransactionRecordFieldsEnum.PRODUCT_GROUP_CODE.getActualStartIndex(), TransactionRecordFieldsEnum.PRODUCT_GROUP_CODE.getEndIndex()).trim() + "," +
				transaction.substring(TransactionRecordFieldsEnum.SYMBOL.getActualStartIndex(), TransactionRecordFieldsEnum.SYMBOL.getEndIndex()).trim() + "," +
				transaction.substring(TransactionRecordFieldsEnum.EXPIRATIONDATE.getActualStartIndex(), TransactionRecordFieldsEnum.EXPIRATIONDATE.getEndIndex()).trim();
	}

	private Double getTotalTransactionAmount(String transaction) {
		Double quantityLong = Double.parseDouble(transaction.substring(TransactionRecordFieldsEnum.QUANTITY_LONG.getActualStartIndex(), TransactionRecordFieldsEnum.QUANTITY_LONG.getEndIndex()).trim());
		Double quantityShort = Double.parseDouble(transaction.substring(TransactionRecordFieldsEnum.QUANTITY_SHORT.getActualStartIndex(), TransactionRecordFieldsEnum.QUANTITY_SHORT.getEndIndex()).trim());
		return quantityLong == null && quantityShort == null ? null : quantityLong - quantityShort;
	}
}
