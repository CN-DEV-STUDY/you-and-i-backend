package com.i.and.you.user.entity;

import com.i.and.you.period.entity.Period;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "user")
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String nickname;

    @Column(length = 100, nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "you_id")
    private User you;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Period period;

    //==연관관계 메서드==//
    public void addYou(User you) {
        this.you = you;
        you.pairUpWith(this);
    }

    private void pairUpWith(User me) {
        this.you = me;
    }


    //==UserDetails 메서드==//
    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    // 사용자의 id를 반환(고유한 값)
    @Override
    public String getUsername() {
        return this.email;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // IF NEEDED 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않음
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // IF NEEDED 잠금되었는지 확인하는 로직
        return true; // true -> 잠금되지 않음
    }

    // 패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // IF NEEDED 패스워드가 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않음
    }

    // 계정 활성화 여부 반환
    @Override
    public boolean isEnabled() {
        // IF NEEDED 계정이 활성화되었는지 확인하는 로직
        return true; // true -> 활성화됨
    }

    /**
     * 인코딩된 패스워드로 변경
     */
    public void changePasswordTo(String encodedPassword) {
        this.password = encodedPassword;
    }
}
