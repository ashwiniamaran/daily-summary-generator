package com.bank.summarygenerator.dailysummarygenerator.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionFileReaderService {
	
	@Value("${input.source}")
	String inputFileSource;

	public List<String> initialiseFileRead() {
		try(Stream<String> transactions = Files.lines(Paths.get(this.inputFileSource))) {
			return transactions.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
