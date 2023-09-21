package Services;

import com.banking.teamone.model.CRole;
import com.banking.teamone.model.CustomerIb;
import com.banking.teamone.service.CustomerIbDetailsImpl;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class CustomerIbDetailsImplTest {

    @Test
    public void testBuild() {
        CustomerIb customerIb = new CustomerIb();
        customerIb.setUsername("customerUsername");
        customerIb.setPassword("customerPassword");
        customerIb.setRole(CRole.ROLE_USER); // Assuming you have a Role enum with USER role

        CustomerIbDetailsImpl customerIbDetails = CustomerIbDetailsImpl.build(customerIb);

        assertEquals("customerUsername", customerIbDetails.getUsername());
        assertEquals("customerPassword", customerIbDetails.getPassword());

        Collection<? extends GrantedAuthority> authorities = customerIbDetails.getAuthorities();
        List<GrantedAuthority> expectedAuthorities = new ArrayList<>();
        expectedAuthorities.add(new SimpleGrantedAuthority(CRole.ROLE_USER.name()));

        assertEquals(expectedAuthorities.size(), authorities.size());
        assertTrue(authorities.containsAll(expectedAuthorities));
    }
}
