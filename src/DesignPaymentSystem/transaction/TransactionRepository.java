package DesignPaymentSystem.transaction;

import DesignPaymentSystem.paymentDto.PaymentResponse;

import java.util.HashMap;
import java.util.Map;

public class TransactionRepository {
    Map<Integer, PaymentResponse> transactionResponseMap;
    Map<Integer, Transaction> allTransactions;

    public TransactionRepository() {
        transactionResponseMap = new HashMap<>();
        allTransactions = new HashMap<>();
    }

    public boolean isProcessed(int tranId) {
        return transactionResponseMap.containsKey(tranId);
    }

    public PaymentResponse getPaymentResponse(int tranId) {
        return transactionResponseMap.get(tranId);
    }

    public void save(PaymentResponse paymentResponse) {
        transactionResponseMap.putIfAbsent(paymentResponse.getTransactionId(), paymentResponse);
        allTransactions.get(paymentResponse.getTransactionId()).setStatus(paymentResponse.getPaymentStatus());
    }

    public  void save(Transaction transaction) {
        allTransactions.putIfAbsent(transaction.getTransactionId(), transaction);
    }

    public Transaction getTransaction(int tranId) {
        return allTransactions.get(tranId);
    }
}
