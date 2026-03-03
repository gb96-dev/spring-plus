package org.example.expert.domain.common.dto;

import lombok.Getter;
import org.example.expert.domain.user.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthUser {

    private final Long id;
    private final String email;
    private final UserRole userRole;
    private final String nickname;

    public AuthUser(Long id, String email, UserRole userRole, String nickname) {
        this.id = id;
        this.email = email;
        this.userRole = userRole;
        this.nickname = nickname;
    }

    // Spring Security 인증 객체 생성 시 권한 정보를 넘겨주기 위해 추가합니다.
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + userRole.name()));
    }
}