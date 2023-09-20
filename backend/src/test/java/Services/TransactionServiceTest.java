package Services;

import com.banking.teamone.converter.TransactionConverter;
import com.banking.teamone.dto.TransactionDto;
import com.banking.teamone.dto.TransactionRequestDto;
import com.banking.teamone.model.Account;
import com.banking.teamone.model.Transaction;
import com.banking.teamone.repository.TransactionRepository;
import com.banking.teamone.service.AccountService;
import com.banking.teamone.service.TransactionService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class TransactionServiceTest {
    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private TransactionConverter converter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTransactionById() {
        Integer transactionId = 1;
        Transaction mockTransaction = new Transaction();
        mockTransaction.setId(transactionId);
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(mockTransaction));

        TransactionDto result = transactionService.getTransactionById(transactionId);

        assertNotNull(result);
        assertEquals(transactionId, result.getId());
    }

    @Test
    public void testGetTransactionByAccountNo() {
        // Arrange
        String accountNo = "123456";
        Transaction mockTransaction = new Transaction();
        mockTransaction.setFromAccountNo(accountNo);
        when(transactionRepository.findByFromAccountNo(accountNo)).thenReturn(mockTransaction);

        // Act
        TransactionDto result = transactionService.getTransactionByAccountNo(accountNo);

        // Assert
        assertNotNull(result);
        assertEquals(accountNo, result.getFromAccountNo());
    }

    @Test
    public void testGetAllTransactionByAccountNo() {
        // Arrange
        String accountNo = "123456";
        List<Transaction> mockTransactions = new ArrayList<>();
        when(transactionRepository.findAllByFromAccountNo(accountNo)).thenReturn(mockTransactions);

        List<TransactionDto> result = transactionService.getAllTransactionByAccountNo(accountNo);

        assertNotNull(result);
        assertEquals(mockTransactions.size(), result.size());
    }



    @Test
    public void testCreateTransactionSufficientBalance() {
        TransactionRequestDto transactionRequest = new TransactionRequestDto();
        transactionRequest.setFromAccountNo("123456");
        transactionRequest.setToAccountNo("789123");
        transactionRequest.setTransactionAmount(new BigDecimal("100"));

        Account mockFromAccount = new Account();
        mockFromAccount.setTotalBalance(new BigDecimal("200"));
        Account mockToAccount = new Account();
        mockToAccount.setTotalBalance(new BigDecimal("300"));

        when(accountService.getAccountById(transactionRequest.getFromAccountNo())).thenReturn(mockFromAccount);
        when(accountService.getAccountById(transactionRequest.getToAccountNo())).thenReturn(mockToAccount);

        String result = transactionService.createTransaction(transactionRequest);

        assertEquals("Transactions performed successfully", result);
    }


    @Test
    public void testCreateTransactionInsufficientBalance() {
        // Arrange
        TransactionRequestDto transactionRequest = new TransactionRequestDto();
        transactionRequest.setFromAccountNo("123456");
        transactionRequest.setToAccountNo("789123");
        transactionRequest.setTransactionAmount(new BigDecimal("1000"));

        Account mockFromAccount = new Account();
        mockFromAccount.setTotalBalance(new BigDecimal("200"));
        Account mockToAccount = new Account();
        mockToAccount.setTotalBalance(new BigDecimal("300"));

        when(accountService.getAccountById(transactionRequest.getFromAccountNo())).thenReturn(mockFromAccount);
        when(accountService.getAccountById(transactionRequest.getToAccountNo())).thenReturn(mockToAccount);

        // Act
        String result = transactionService.createTransaction(transactionRequest);

        // Assert
        assertEquals("Transaction failed unsufficient balance", result);
    }

}
