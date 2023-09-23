package Services;


import com.banking.teamone.model.Admin;
import com.banking.teamone.model.CRole;
import com.banking.teamone.model.CustomerInfo;
import com.banking.teamone.repository.AdminRepository;
import com.banking.teamone.service.AdminService;
import com.banking.teamone.service.CustomerIbService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AdminServiceTest {

    @Mock
    AdminRepository adminRepository;

    @Mock
    CustomerIbService customerIbService;


    @InjectMocks
    private AdminService adminService;


    private Admin admin;


    @Captor
    ArgumentCaptor<Admin> acAdmin;


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
       verify(adminRepository).save(acAdmin.capture());
//        verify(adminRepository,VerificationModeFactory.times(1)).save(eq(admin));
        verify(customerIbService,VerificationModeFactory.times(1)).getCustomerByUsername(admin.getUsername());

    }




}
