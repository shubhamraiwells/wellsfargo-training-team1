package com.banking.teamone.service;


import com.banking.teamone.model.Admin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


//this class implements spring security userdetails and provide admindetails for authentication
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDetailImpl implements UserDetails {

    private String username;

    @JsonIgnore
    private String password;

    private Collection<?extends GrantedAuthority> authorities;


    //function to build admindetails object from admin data
    public static AdminDetailImpl build(Admin admin){
        List<GrantedAuthority> authorities= new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(admin.getRole().name()));
        return new AdminDetailImpl(admin.getUsername(),admin.getPassword(),authorities);
    }


    //getting all granted authority of user

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
        return true;
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
