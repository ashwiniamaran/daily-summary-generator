package com.bank.summarygenerator.dailysummarygenerator.enums;

import lombok.Getter;

@Getter
public enum TransactionRecordFieldsEnum {
	// Client information fields
	CLIENT_TYPE(4, 7),
	CLIENT_NUMBER(8, 11),
	ACCOUNT_NUMBER(12, 15),
	SUB_ACCOUNT_NUMBER(16, 19),
	// Product Information Fields
	EXCHANGE_CODE(28, 31),
	PRODUCT_GROUP_CODE(26,27),
	SYMBOL(32,37),
	EXPIRATIONDATE(38, 45),
	// Total transaction amount fields
	QUANTITY_LONG(53, 62),
	QUANTITY_SHORT(64, 73);


	int startIndex;
	int endIndex;
	
	TransactionRecordFieldsEnum(int startIndex, int endIndex) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	public int getActualStartIndex() {
		// Java index starts from 0, hence subtract 1 from the file index to get the equivalent string index.
		return this.startIndex - 1;
	}
}
