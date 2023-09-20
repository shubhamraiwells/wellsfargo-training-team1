package Services;

import com.banking.teamone.converter.AccountConverter;
import com.banking.teamone.model.Account;
import com.banking.teamone.repository.AccountRepository;
import com.banking.teamone.service.AccountService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.assertNull;
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

    @Test
    @DisplayName("test to get account by id")
    public void testGetAccountById(){
        accountService.getAccountById("shubhamrai");
        verify(accountRepository, VerificationModeFactory.times(1)).findById("shubhamrai");
         when(accountRepository.findById("shubhamrai")).thenReturn(null);
    }




}
