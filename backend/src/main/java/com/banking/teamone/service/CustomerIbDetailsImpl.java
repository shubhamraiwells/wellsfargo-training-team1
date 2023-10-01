package com.banking.teamone.service;

import com.banking.teamone.model.CustomerIb;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


//customer ib details class to provide custome ib details to spring security
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class CustomerIbDetailsImpl implements UserDetails {

    private CustomerIb user;

    public CustomerIbDetailsImpl(CustomerIb customerIb,String username,String password,Collection<?extends GrantedAuthority> authorities) {
        this.username=username;
        this.password=password;
        this.authorities=authorities;
        this.user=customerIb;
    }



    private String username;

    @JsonIgnore
    private String password;

    private Collection<?extends GrantedAuthority> authorities;

    // Static factory method to create a CustomerIbDetailsImpl object from a CustomerIb object

    public static CustomerIbDetailsImpl build(CustomerIb customerIb){

        List<GrantedAuthority>authorities= new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(customerIb.getRole().name()));
        return new CustomerIbDetailsImpl(customerIb,customerIb.getUsername(),customerIb.getPassword(),authorities);
    }

    // Implementation of UserDetails interface methods


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
