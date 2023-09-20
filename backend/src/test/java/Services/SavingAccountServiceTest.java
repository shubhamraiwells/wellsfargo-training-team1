package Services;


import com.banking.teamone.converter.CustomerConverter;
import com.banking.teamone.dto.CustomerInfoRequestModel;
import com.banking.teamone.model.Account;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.repository.CustomerInfoRepository;
import com.banking.teamone.service.AccountService;
import com.banking.teamone.service.SavingsAccountService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SavingAccountServiceTest {

    @Mock
    private CustomerInfoRepository customerInfoRepository;

    @Mock
    private CustomerConverter customerConverter;


    @Mock
    private AccountService accountService;

    @InjectMocks
    private SavingsAccountService savingsAccountService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        savingsAccountService = new SavingsAccountService();

    }


    private Account account;


    @DisplayName("test for updating balance")
    @Test
    public void testUpdateBalance(){

        String accountNo="1234567898";
        BigDecimal item=new BigDecimal("1234");
        account=new Account("1234567898","savings",1,true,new Date(),item);
        when(accountService.getAccountById("1234567898")).thenReturn(account);

        savingsAccountService.updateBalance(accountNo,item);

        verify(accountService, VerificationModeFactory.times(1)).getAccountById("1234567898");


    }
    @Test
    @DisplayName("test update balance account exists")
    public void testUpdateBalanceAccountExists() {
        // Arrange
        String accountNo = "account123";
        BigDecimal toAdd = new BigDecimal("100.00");
        Account account = new Account(accountNo,"savings",1,true,new Date(),new BigDecimal("12345"));

        when(accountService.getAccountById(accountNo)).thenReturn(account);

        String result = savingsAccountService.updateBalance(accountNo, toAdd);

        assertEquals("Account updated successfully", result);
        verify(accountService, times(1)).createAccount(account);
    }

    @Test

    public void testUpdateBalanceAccountNotExists() {
        // Arrange
        String accountNo = "nonExistentAccount";
        BigDecimal toAdd = new BigDecimal("100.00");

        when(accountService.getAccountById(accountNo)).thenReturn(null);
        String result = savingsAccountService.updateBalance(accountNo, toAdd);
        assertEquals("Account not exist", result);
        verify(accountService, never()).createAccount(any());
    }

    @Test
    @DisplayName("test create saving account")
    public void testCreateSavingsAccount() {
        CustomerInfoRequestModel customerInfoRequestModel = new CustomerInfoRequestModel(
                "Savings",
                "1234 5678 9012",
                "Mr.",
                "John",
                "M",
                "Doe",
                "Michael Doe",
                "1234567890",
                "john.doe@example.com",
                new Date(), // Set a valid date here
                "123 Main Street",
                "Apt 4B",
                "Near Park",
                "New York",
                "10001",
                "456 Elm Street",
                "Suite 101",
                "Downtown",
                "Los Angeles",
                "90001",
                "Engineer",
                "Employment",
                new BigDecimal("75000.00")
        );

        when(customerConverter.customerInfoRequestModelToCustomerInfo(customerInfoRequestModel)).thenReturn(new CustomerInfo(123, "Savings",
                "123456789012",
                "Mr.",
                "John",
                "M",
                "Doe",
                "Michael Doe",
                "1234567890",
                "john.doe@example.com",
                new Date(), // Set a valid date here
                "123 Main Street",
                "Apt 4B",
                "Near Park",
                "New York",
                "10001",
                "456 Elm Street",
                "Suite 101",
                "Downtown",
                "Los Angeles",
                "90001",
                "Engineer",
                "Employment",
                new BigDecimal("75000.00")));
        when(customerInfoRepository.save(any(CustomerInfo.class))).thenReturn(new CustomerInfo(123, "Savings",
                "123456789012",
                "Mr.",
                "John",
                "M",
                "Doe",
                "Michael Doe",
                "1234567890",
                "john.doe@example.com",
                new Date(), // Set a valid date here
                "123 Main Street",
                "Apt 4B",
                "Near Park",
                "New York",
                "10001",
                "456 Elm Street",
                "Suite 101",
                "Downtown",
                "Los Angeles",
                "90001",
                "Engineer",
                "Employment",
                new BigDecimal("75000.00")));

        String result = savingsAccountService.createSavingsAccount(customerInfoRequestModel);

        assertEquals("Account generated successfully", result);
        verify(accountService, times(1)).createAccount(any(Account.class));
    }




}
