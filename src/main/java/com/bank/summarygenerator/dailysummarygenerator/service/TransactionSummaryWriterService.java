package com.bank.summarygenerator.dailysummarygenerator.service;

import com.bank.summarygenerator.dailysummarygenerator.domain.Transaction;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionSummaryWriterService {

    @Value("${output.destination}")
    private String outputDestination;

    public void writeTransactionSummaryToCSV(List<Transaction> transactions) {
        try {
            Path path = Paths.get(this.outputDestination);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            writeCSVRecords(transactions);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCSVRecords(List<Transaction> transactions) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(this.outputDestination))) {
            String[] header = {"Client_Information", "Product_Information", "Total_Transaction_Amount"};
            List<String[]> records = transactions.stream()
                    .map(this::prepareSingleCSVRecord)
                    .collect(Collectors.toList());
            List<String[]> finalRecords = new ArrayList<>();
            finalRecords.add(header);
            finalRecords.addAll(records);
            csvWriter.writeAll(finalRecords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] prepareSingleCSVRecord(Transaction t) {
        return new String[]{t.getClientInformation() == null ? "" : t.getClientInformation(),
                t.getProductInformation() == null ? "" : t.getProductInformation(),
                t.getTransactionAmount() == null ? "" : t.getTransactionAmount().toString()};
    }
}
