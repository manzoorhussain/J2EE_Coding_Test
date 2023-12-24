package com.smallworld.Repositories.Transaction;

import com.smallworld.Common.IMapper;
import com.smallworld.Common.ObjectMapperWrapper;
import com.smallworld.DataStore.DataStore;
import com.smallworld.DataStore.IDataStore;
import com.smallworld.Model.Transaction;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TransactionRespositoryTest {
    Dotenv _Dotenv = Dotenv.load();
    IMapper Mapper = new ObjectMapperWrapper();
    IDataStore Ds = new DataStore(_Dotenv.get("TRANSACTION_TEST_DATA_FILE_PATH"), Mapper);
    TransactionRespository TransactionRespository = new TransactionRespository(Ds);
    @Test
    void SumOfAllTransactionsAmount() {
        assertEquals(TransactionRespository.GetTotalTransactionAmount(), 4860.17);
    }

    @Test
    void MaxTransactionAmountShouldBeReturned() {
        assertEquals(TransactionRespository.GetMaxTransactionAmount(), 986.0);
        assertNotEquals(TransactionRespository.GetMaxTransactionAmount(), 100);
    }

    @Test
    void SumOfAmountSendBySender() {
        assertEquals(TransactionRespository.GetTotalTransactionAmountSentBy("Tom Shelby"), 678.06);
        assertEquals(TransactionRespository.GetTotalTransactionAmountSentBy("Aunt Polly"), 101.02);
        assertEquals(TransactionRespository.GetTotalTransactionAmountSentBy("Grace Burgess"), 666.0);
    }

    @Test
    void TopThreeTransactionByAmountShouldBeReturned() {
        Transaction trx1 = new Transaction();
        Transaction trx2 = new Transaction();
        Transaction trx3 = new Transaction();
        trx1.setMtn((long) 5465465);
        trx2.setMtn((long) 5465466);
        trx3.setMtn((long) 100);
        List<Transaction> transactionList = Stream.of(trx3, trx1, trx2).collect(Collectors.toList());

        assertEquals(TransactionRespository.GetTop3TransactionsByAmount().stream().map(Transaction::getMtn).collect(Collectors.toList()),
                transactionList.stream().map(Transaction::getMtn).collect(Collectors.toList()) );
    }

    @Test
    void ReturnsUnResolvedIssuesId() {
        assertEquals(TransactionRespository.GetUnResolvedIssuesId(), Stream.of(1,3,15,54,99).collect(Collectors.toSet()));
    }

    @Test
    void ReturnsUnResolvedIssuesMessage() {
        List<String> issues = new ArrayList<>();
        issues.add("Never gonna give you up");
        issues.add("Never gonna let you down");
        issues.add("Never gonna run around and desert you");
        assertEquals(TransactionRespository.GetResolvedIssueMessages(), issues);
    }

    @Test
    void TransactionIdOfUniqueTransactionsMustMatch() {
        var expected = new ArrayList<>(TransactionRespository.GetUniqueTransactions().stream().map(Transaction::getMtn).toList());
        long[] testData = {100, 5465466, 663458, 1284564, 96132456, 5465465, 1651665, 6516461, 32612651, 36448252, 645645111, 45431585};
        List<Long> actual = new ArrayList<>(Arrays.stream(testData).boxed().toList());
        Collections.sort(expected);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    void TransactionCountForBeneficiariesMustBeEqual() {
        var trx = TransactionRespository.GetTransactionByBeneficiaryName();
        for (var t: trx.entrySet()) {
            if(t.getKey().equals("Ben Younger")) {
                assertEquals(t.getValue().size(), 3);
            } else {
                assertEquals(t.getValue().size(), 1);
            }
        }
    }

    @Test
    void ClientWithAnyComplianceIssue() {
        assertEquals(TransactionRespository.GetTransactionWithComplianceIssue("Billy Kimber").size(), 0 );
        assertEquals(TransactionRespository.GetTransactionWithComplianceIssue( "Tom Shelby").size(), 2);
        assertEquals(TransactionRespository.GetTransactionWithComplianceIssue( "Ben Younger").size(), 3);
    }
}