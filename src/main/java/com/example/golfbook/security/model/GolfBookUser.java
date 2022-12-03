package com.example.golfbook.security.model;

import com.example.golfbook.security.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Collections;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GolfBookUser implements UserDetails {

    @Id
    private String userId;
    @Setter
    private String displayName;
    private String email;
    private String password;
    private int handicap;

    public GolfBookUser(UserRequestDto userRequestDto, String password) {
        this.userId = userRequestDto.getUserId();
        this.displayName = userRequestDto.getDisplayName();
        this.email = userRequestDto.getEmail();
        this.password = password;
        this.handicap = userRequestDto.getHandicap();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return userId;
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
