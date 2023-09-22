package Services;


import com.banking.teamone.converter.CustomerConverter;
import com.banking.teamone.model.CRole;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.repository.CustomerIbRepository;
import com.banking.teamone.service.CustomerIbService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.Optional;


import static org.mockito.Mockito.verify;


//@RunWith(MockitoJUnitRunner.class)
public class CustomerIbServiceTest {

    @Mock
    CustomerIbRepository customerIbRepository;

    @Mock
    CustomerConverter customerConverter;

    @InjectMocks
    private CustomerIbService customerIbService;


    private CustomerIb customerIb;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @DisplayName("test case for getting customer by username")
    @Test
    public void testGetCustomerByUsername() {
        customerIb = new CustomerIb("shubhamrai", "wellsfargo@123", CRole.ROLE_USER, "1234567890123", true);

        CustomerIb customer = customerIbService.getCustomerByUsername(customerIb.getUsername());

       verify(customerIbRepository, VerificationModeFactory.times(1)).
               findById("shubhamrai");
    }

    @DisplayName("test for getting customer by account no")
    @Test
    public void testGetCustomerByAccountNo(){
        customerIb = new CustomerIb("shubhamrai", "wellsfargo@123", CRole.ROLE_USER, "1234567890123", true);
        customerIbService.getCustomerByAccountNo(customerIb.getAccountNo()) ;

        verify(customerIbRepository,VerificationModeFactory.times(1)).findByAccountNo(customerIb.getAccountNo());

    }


    @DisplayName("test for creating customer internet banking")
    @Test
    public void testCreateInternetBanking(){
        customerIb = new CustomerIb("shubhamrai", "wellsfargo@123", CRole.ROLE_USER, "1234567890123", true);
        customerIbService.createCustomerIb(customerIb);
        verify(customerIbRepository,VerificationModeFactory.times(1)).save(customerIb);
    }

}
