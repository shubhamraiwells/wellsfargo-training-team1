package Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.Optional;

import com.banking.teamone.model.CRole;
import com.banking.teamone.service.CustomerIbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.banking.teamone.converter.CustomerConverter;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.repository.CustomerIbRepository;
import org.mockito.internal.verification.VerificationModeFactory;

public class CustomerIbServiceTest {

    @Mock
    private CustomerIbRepository customerIbRepository;

    @InjectMocks
    private CustomerIbService customerIbService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCustomerByUsername_CustomerExists() {
        String username = "john_doe";
        CustomerIb customerIb = new CustomerIb();
        customerIb.setUsername(username);

        // Mock the repository to return the customer
        when(customerIbRepository.findById(username)).thenReturn(Optional.of(customerIb));

        // Perform the test
        CustomerIb result = customerIbService.getCustomerByUsername(username);

        // Verify that the correct customer is returned
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    public void testGetCustomerByUsername_CustomerNotFound() {
        String username = "john_doe";

        // Mock the repository to return an empty optional
        when(customerIbRepository.findById(username)).thenReturn(Optional.empty());

        // Perform the test
        CustomerIb result = customerIbService.getCustomerByUsername(username);

        // Verify that null is returned when the customer is not found
        assertNull(result);
    }

    @Test
    public void testCreateCustomerIb() {
        CustomerIb customerIb = new CustomerIb("shubhamrai", "wellsfargo@123", CRole.ROLE_USER, "1234567890123", true,true,0,new Date());;
//        when(customerIbRepository.save(customerIb)).thenReturn(customerIb);

        CustomerIb result = customerIbService.createCustomerIb(customerIb);

        verify(customerIbRepository, VerificationModeFactory.times(1)).save(any(CustomerIb.class));
    }

    @Test
    public void testIncreaseFailedAttempts() {
        CustomerIb customerIb = new CustomerIb();
        customerIb.setUsername("john_doe");
        customerIb.setFailedAttempt(2);
        customerIbService.increaseFailedAttempts(customerIb);

        verify(customerIbRepository, times(1)).updateFailedAttempts(3, "john_doe");
    }

    @Test
    public void testResetFailedAttempts() {
        String username = "john_doe";

        // Perform the test
        customerIbService.resetFailedAttempts(username);

        // Verify that failed attempts are reset to 0 and saved
        verify(customerIbRepository, times(1)).updateFailedAttempts(0, username);
    }

    @Test
    public void testLock() {
        CustomerIb customerIb = new CustomerIb();
        customerIb.setUsername("john_doe");

        // Perform the test
        customerIbService.lock(customerIb);

        // Verify that the account is locked, lock time is set, and saved
        assertFalse(customerIb.isAccountNonLocked());
        assertNotNull(customerIb.getLockTime());
        verify(customerIbRepository, times(1)).save(customerIb);
    }

    @Test
    public void testUnlockWhenTimeExpired_Success() {
        CustomerIb customerIb = new CustomerIb();
        customerIb.setUsername("john_doe");
        customerIb.setFailedAttempt(3);

        long currentTimeInMillis = System.currentTimeMillis();
        long lockTimeInMillis = currentTimeInMillis - (CustomerIbService.LOCK_TIME_DURATION + 1000);
        customerIb.setLockTime(new Date(lockTimeInMillis));

        // Perform the test
        boolean result = customerIbService.unlockWhenTimeExpired(customerIb);

        // Verify that the account is unlocked, lock time is cleared, and failed attempts are reset
        assertTrue(result);
        assertTrue(customerIb.isAccountNonLocked());
        assertNull(customerIb.getLockTime());
        assertEquals(0, customerIb.getFailedAttempt());
        verify(customerIbRepository, times(1)).save(customerIb);
    }

    @Test
    public void testUnlockWhenTimeExpired_Failure() {
        CustomerIb customerIb = new CustomerIb();
        customerIb.setUsername("john_doe");
        customerIb.setFailedAttempt(3);

        // Lock time is not expired
        long currentTimeInMillis = System.currentTimeMillis();
        long lockTimeInMillis = currentTimeInMillis - (CustomerIbService.LOCK_TIME_DURATION - 1000);
        customerIb.setLockTime(new Date(lockTimeInMillis));

        // Perform the test
        boolean result = customerIbService.unlockWhenTimeExpired(customerIb);

        // Verify that the account remains locked
        assertFalse(result);
        assertFalse(customerIb.isAccountNonLocked());
        assertNotNull(customerIb.getLockTime());
        assertEquals(3, customerIb.getFailedAttempt());
        verify(customerIbRepository, never()).save(customerIb);
    }
}
