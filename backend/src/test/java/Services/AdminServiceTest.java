package Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import com.banking.teamone.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.banking.teamone.dto.ApproveBankAccountModel;
import com.banking.teamone.model.Account;
import com.banking.teamone.model.AccountRequest;
import com.banking.teamone.model.Admin;
import com.banking.teamone.model.CRole;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.repository.AccountRepository;
import com.banking.teamone.repository.AccountRequestRepository;
import com.banking.teamone.repository.AdminRepository;
import com.banking.teamone.repository.CustomerInfoRepository;
import com.banking.teamone.service.CustomerIbService;

public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private CustomerIbService customerIbService;

    @Mock
    private AccountRequestRepository accountRequestRepository;

    @Mock
    private CustomerInfoRepository customerInfoRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAdmin_Success() {
        String username = "admin";
        String password = "admin123";

        // Mock the customerIbService to return null (username is unique)
        when(customerIbService.getCustomerByUsername(username)).thenReturn(null);

        // Perform the test
        String result = adminService.createAdmin(username, password);

        // Verify that admin is saved
        verify(adminRepository, times(1)).save(any(Admin.class));
        assertEquals("Admin registered successfully", result);
    }

    @Test
    public void testCreateAdmin_UsernameNotUnique() {
        String username = "admin";
        String password = "admin123";

        // Mock the customerIbService to return an existing customerIb
        when(customerIbService.getCustomerByUsername(username)).thenReturn(new CustomerIb());

        // Perform the test
        String result = adminService.createAdmin(username, password);

        // Verify that admin is not saved
        verify(adminRepository, never()).save(any(Admin.class));
        assertEquals("Username should be unique", result);
    }


    @Test
    public void testGetAdminByUsername_AdminExists() {
        String username = "admin";

        Admin admin = new Admin();
        admin.setUsername(username);

        when(adminRepository.findById(username)).thenReturn(java.util.Optional.of(admin));

        Admin result = adminService.getAdminByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    public void testGetAdminByUsername_AdminNotFound() {
        String username = "admin";

        when(adminRepository.findById(username)).thenReturn(java.util.Optional.empty());

        Admin result = adminService.getAdminByUsername(username);

        assertNull(result);
    }








@Test
    public void testApproveBankAccount_Success() {
        String accountNo = "12345";
        ApproveBankAccountModel approveBankAccountModel = new ApproveBankAccountModel(accountNo, true);

        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setOwnerId(1);

        CustomerInfo customer = new CustomerInfo();
        customer.setId(1);
        customer.setAccountType("Savings");

        // Mock the repository to return the account request
        when(accountRequestRepository.findById(accountNo)).thenReturn(Optional.of(accountRequest));

        // Mock the customerInfoRepository to return the customer
        when(customerInfoRepository.findById(1)).thenReturn(Optional.of(customer));

        // Perform the test
        String result = adminService.approveBankAccount(approveBankAccountModel);

        // Verify that the account is approved and saved
        verify(accountRequestRepository, times(1)).delete(accountRequest);
        verify(accountRepository, times(1)).save(any(Account.class));
        assertEquals("Bank account approved successfully", result);
    }

    @Test
    public void testApproveBankAccount_Reject() {
        String accountNo = "12345";
        ApproveBankAccountModel approveBankAccountModel = new ApproveBankAccountModel(accountNo, false);

        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setOwnerId(1);

        CustomerInfo customer = new CustomerInfo();
        customer.setId(1);
        customer.setAccountType("Savings");

        // Mock the repository to return the account request
        when(accountRequestRepository.findById(accountNo)).thenReturn(Optional.of(accountRequest));

        // Mock the customerInfoRepository to return the customer
        when(customerInfoRepository.findById(1)).thenReturn(Optional.of(customer));

        // Perform the test
        String result = adminService.approveBankAccount(approveBankAccountModel);

        // Verify that the account request and customer info are deleted
        verify(accountRequestRepository, times(1)).delete(accountRequest);
        verify(customerInfoRepository, times(1)).delete(customer);
        assertEquals("Bank account rejected successfully", result);
    }

    @Test
    public void testApproveBankAccount_Exception() {
        String accountNo = "12345";
        ApproveBankAccountModel approveBankAccountModel = new ApproveBankAccountModel(accountNo, true);

        // Mock the repository to throw an exception
        when(accountRequestRepository.findById(accountNo)).thenThrow(new RuntimeException("Failed to fetch account request"));

        // Perform the test
        String result = adminService.approveBankAccount(approveBankAccountModel);

        // Verify that an error message is returned
        assertEquals("Some error occurred while approving bank account", result);
    }


}
