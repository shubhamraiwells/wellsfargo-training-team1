package Services;
import com.banking.teamone.model.Admin;
import com.banking.teamone.model.CRole;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerIbServiceImplTest {

    @InjectMocks
    private CustomerIbServiceImpl customerIbService;

    @Mock
    private CustomerIbService mockCustomerIbService;

    @Mock
    private AdminService mockAdminService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsernameCustomerFound() {
        CustomerIb customerIb = new CustomerIb();
        customerIb.setUsername("customerUsername");
        customerIb.setRole(CRole.ROLE_USER);
        when(mockCustomerIbService.getCustomerByUsername("customerUsername")).thenReturn(customerIb);

        UserDetails userDetails = customerIbService.loadUserByUsername("customerUsername");

        assertNotNull(userDetails);
        assertTrue(userDetails instanceof CustomerIbDetailsImpl);
    }

    @Test
    public void testLoadUserByUsernameAdminFound() {
        Admin admin = new Admin();
        admin.setUsername("adminUsername");
        admin.setRole(CRole.ROLE_ADMIN);
        when(mockAdminService.getAdminByUsername("adminUsername")).thenReturn(admin);
        UserDetails userDetails = customerIbService.loadUserByUsername("adminUsername");
        assertNotNull(userDetails);
        assertTrue(userDetails instanceof AdminDetailImpl);
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        when(mockCustomerIbService.getCustomerByUsername(anyString())).thenReturn(null);
        when(mockAdminService.getAdminByUsername(anyString())).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> customerIbService.loadUserByUsername("nonExistentUsername"));
    }

    @Test
    public void testLoadUserByUsernameDuplicate() {
        when(mockCustomerIbService.getCustomerByUsername(anyString())).thenReturn(new CustomerIb());
        when(mockAdminService.getAdminByUsername(anyString())).thenReturn(new Admin());

        assertThrows(UsernameNotFoundException.class, () -> customerIbService.loadUserByUsername("duplicateUsername"));
    }
}
