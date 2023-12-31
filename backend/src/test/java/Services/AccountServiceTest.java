package Services;

import com.banking.teamone.converter.AccountConverter;
import com.banking.teamone.dto.AccountDto;
import com.banking.teamone.model.Account;
import com.banking.teamone.repository.AccountRepository;
import com.banking.teamone.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoJUnitRunner.class)
public class AccountServiceTest {


    @Mock
    AccountRepository accountRepository;

    @Mock
    AccountConverter accountConverter;

    @InjectMocks
    AccountService accountService;

    @Mock
    Account account;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Captor
    ArgumentCaptor<Account>argumentCaptor;

    @Test
    public void testGetAccountById_AccountExists() {
        String accountId = "12345";
        Account account = new Account();
        account.setId(accountId);

        // Mock the repository to return the account
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Perform the test
        Account result = accountService.getAccountById(accountId);

        // Verify that the correct account is returned
        assertNotNull(result);
        assertEquals(accountId, result.getId());
    }

    @Test
    public void testGetAccountById_AccountNotFound() {
        String accountId = "12345";

        // Mock the repository to return an empty optional
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        // Perform the test
        Account result = accountService.getAccountById(accountId);

        // Verify that null is returned when the account is not found
        assertNull(result);
    }


    @Test
    @DisplayName("test to create account")
    public void testCreateAccount(){
        accountService.createAccount(account);
        verify(accountRepository).save(argumentCaptor.capture());
        Account newAccount = new Account("shubhamrai","saving",12,true,new Date(),new BigDecimal("1234"));
        when(accountRepository.save(newAccount)).thenReturn(newAccount);

        Account createdAccount = accountService.createAccount(newAccount);
        assertNotNull(createdAccount);

    }

    @Test
    @DisplayName("test get all account")
    public void testGetAllAccount() {
        // Arrange
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account("shubhamrai","saving",12,true,new Date(),new BigDecimal("1234")));
        accountList.add(new Account("shubhamrai","saving",13,true,new Date(),new BigDecimal("1234")));

        when(accountRepository.findAll()).thenReturn(accountList);

        List<Account> result = accountService.getAllAccount();

        assertEquals(accountList.size(), result.size());
    }


    @Test
    public void testFetchAccountByOwnerId() {
        // Arrange
        Integer ownerId = 1;
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account("shubhamrai","saving",12,true,new Date(),new BigDecimal("1234")));
        accountList.add(new Account("shubhamrai","saving",13,true,new Date(),new BigDecimal("1234")));

        when(accountRepository.findByOwnerId(ownerId)).thenReturn(accountList);

        List<AccountDto> result = accountService.fetchAccountByOwnerId(ownerId);


        assertEquals(accountList.size(), result.size());
    }



}
