package com.phegondev.Phegon.Eccormerce.security;

import com.phegondev.Phegon.Eccormerce.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class AuthUser implements UserDetails {
    private User user;

    //사용자 권한을 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    //사용자 비밀번호 반환
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //사용자 ID로 사용할 값 반환 email
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    //계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    //자격 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    //계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}
