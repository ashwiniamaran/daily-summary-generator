package com.bank.summarygenerator.dailysummarygenerator.service;

import com.bank.summarygenerator.dailysummarygenerator.domain.Transaction;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryGeneratorService {

    @NonNull
    private final TransactionFileReaderService readerService;

    @NonNull
    private final TransactionFileParserService parserService;

    @NonNull
    private final TransactionSummaryWriterService writerService;

    @PostConstruct
    public void initDailySummaryGenerator() {
        List<String> transactionList = readerService.initialiseFileRead();
        if (transactionList == null || transactionList.isEmpty()) {
            return;
        }
        List<Transaction> transactionObjectList = parserService.parseFileContents(transactionList);
        writerService.writeTransactionSummaryToCSV(transactionObjectList);
    }
}
