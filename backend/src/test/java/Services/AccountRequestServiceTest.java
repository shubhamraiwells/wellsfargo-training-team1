package Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.banking.teamone.service.AccountRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.banking.teamone.converter.AccountConverter;
import com.banking.teamone.dto.PendingRequestModel;
import com.banking.teamone.model.AccountRequest;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.repository.AccountRequestRepository;
import com.banking.teamone.repository.CustomerInfoRepository;

public class AccountRequestServiceTest {

    @Mock
    private AccountRequestRepository accountRequestRepository;

    @Mock
    private CustomerInfoRepository customerInfoRepository;

    @Mock
    private AccountConverter accountConverter;

    @InjectMocks
    private AccountRequestService accountRequestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAccount_Success() {
        AccountRequest accountRequest = new AccountRequest();
        when(accountRequestRepository.save(accountRequest)).thenReturn(accountRequest);
        AccountRequest createdAccount = accountRequestService.createAccount(accountRequest);
        assertNotNull(createdAccount);
        assertEquals(accountRequest, createdAccount);
    }

    @Test
    public void testGetAllPendingRequests_Success() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setId("1");
        accountRequest.setOwnerId(1);

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setId(1);

        List<AccountRequest> accountRequestList = new ArrayList<>();
        accountRequestList.add(accountRequest);
        when(accountRequestRepository.findAll()).thenReturn(accountRequestList);

        when(customerInfoRepository.findById(1)).thenReturn(Optional.of(customerInfo));

        PendingRequestModel pendingRequestModel = new PendingRequestModel();
        when(accountConverter.createPendingRequestModel(customerInfo, "1")).thenReturn(pendingRequestModel);
        List<PendingRequestModel> pendingRequests = accountRequestService.getAllPendingRequests();
        assertNotNull(pendingRequests);
        assertEquals(1, pendingRequests.size());
        assertEquals(pendingRequestModel, pendingRequests.get(0));
    }

    @Test
    public void testCreateAccount_Failure() {
        AccountRequest accountRequest = new AccountRequest();
        when(accountRequestRepository.save(accountRequest)).thenThrow(new RuntimeException("Failed to save"));

        assertThrows(RuntimeException.class, () -> accountRequestService.createAccount(accountRequest));
    }

    @Test
    public void testGetAllPendingRequests_CustomerInfoNotFound() {
        // Mock data
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setId("1");
        accountRequest.setOwnerId(1);

        // Mock the repository to return a list of account requests
        List<AccountRequest> accountRequestList = new ArrayList<>();
        accountRequestList.add(accountRequest);
        when(accountRequestRepository.findAll()).thenReturn(accountRequestList);

        // Mock the customerInfoRepository to return an empty optional, simulating CustomerInfo not found
        when(customerInfoRepository.findById(1)).thenReturn(Optional.empty());

        // Perform the test
        List<PendingRequestModel> pendingRequests = accountRequestService.getAllPendingRequests();

        // Verify that the result is an empty list
        assertNotNull(pendingRequests);
        assertTrue(pendingRequests.isEmpty());
    }
}
