package Services;

import com.banking.teamone.converter.AccountConverter;
import com.banking.teamone.dto.AccountDto;
import com.banking.teamone.model.Account;
import com.banking.teamone.repository.AccountRepository;
import com.banking.teamone.service.AccountService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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
