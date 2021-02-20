package com.bank.summarygenerator.dailysummarygenerator.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import com.bank.summarygenerator.dailysummarygenerator.domain.Transaction;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionFileReaderService {
	
	@Value("${input.source}")
	String inputFileSource;

	@NonNull
	private final TransactionFileParserService parserService;
	@NonNull
	private final TransactionSummaryWriterService writerService;
	
	@PostConstruct
	public void initialiseFileRead() {
		try(Stream<String> transactions = Files.lines(Paths.get(this.inputFileSource))) {
			List<String> transactionList = transactions.collect(Collectors.toList());
			List<Transaction> transactionObjectList = parserService.parseFileContents(transactionList);
			writerService.writeTransactionSummaryToCSV(transactionObjectList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
