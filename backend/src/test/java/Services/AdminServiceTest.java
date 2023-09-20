package Services;


import com.banking.teamone.model.Admin;
import com.banking.teamone.model.CRole;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.repository.AdminRepository;
import com.banking.teamone.service.AdminService;
import com.banking.teamone.service.CustomerIbService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    @Mock
    AdminRepository adminRepository;

    @Mock
    CustomerIbService customerIbService;


    @InjectMocks
    private AdminService adminService;


    private Admin admin;



    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Test for getting admin by username")
    @Test
    public void testGetAdminByUsername(){
        admin=new Admin("shubhamrai","wellsfargo", CRole.ROLE_ADMIN);
        when(adminRepository.findById(admin.getUsername())).thenReturn(Optional.ofNullable(admin));
        adminService.getAdminByUsername(admin.getUsername());
        verify(adminRepository, VerificationModeFactory.atLeastOnce()).findById("shubhamrai");



    }

    @DisplayName("Test for create admin")
    @Test
    public void testCreateAdmin(){
        admin=new Admin("shubhamrai","wellsfargo", CRole.ROLE_ADMIN);
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);
        adminService.createAdmin(admin.getUsername(), admin.getPassword());

//        verify(adminRepository,VerificationModeFactory.times(1)).save(admin);
        verify(customerIbService,VerificationModeFactory.times(1)).getCustomerByUsername(admin.getUsername());

    }




}
