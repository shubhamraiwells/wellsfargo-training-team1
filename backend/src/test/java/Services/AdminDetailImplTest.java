package Services;

import com.banking.teamone.model.Admin;
import com.banking.teamone.model.CRole;
import com.banking.teamone.service.AdminDetailImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class AdminDetailImplTest {

    @InjectMocks
     AdminDetailImpl adminDetail;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuild() {
        Admin admin = new Admin();
        admin.setUsername("adminUsername");
        admin.setPassword("adminPassword");
        admin.setRole(CRole.ROLE_ADMIN);

        AdminDetailImpl adminDetails = AdminDetailImpl.build(admin);

        assertEquals("adminUsername", adminDetails.getUsername());
        assertEquals("adminPassword", adminDetails.getPassword());

        Collection<? extends GrantedAuthority> authorities = adminDetails.getAuthorities();
        List<GrantedAuthority> expectedAuthorities = new ArrayList<>();
        expectedAuthorities.add(new SimpleGrantedAuthority(CRole.ROLE_ADMIN.name()));

        assertEquals(expectedAuthorities.size(), authorities.size());
        assertTrue(authorities.containsAll(expectedAuthorities));
    }
}
