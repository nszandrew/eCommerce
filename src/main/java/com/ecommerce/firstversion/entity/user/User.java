package com.ecommerce.firstversion.entity.user;

import com.ecommerce.firstversion.entity.user.dto.UserDTO;
import com.ecommerce.firstversion.entity.user.dto.UserDataUpdateDTO;
import com.ecommerce.firstversion.entity.address.Address;
import com.ecommerce.firstversion.entity.user_storage.UserStorage;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Entity(name = "User")
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class User implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String login;
    private String password;
    @Column(unique = true, length = 11)
    private String cpf;
    @Column(unique = true)
    private String email;
    @Column(length = 12)
    private String phone;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "id.userId")
    private List<UserStorage> userStorages;


    public User(UserDTO data){
        this.login = data.login();
        this.password = data.password();
        this.cpf = data.cpf();
        this.email = data.email();
        this.phone = data.phone();
        this.userType = UserType.BUYER;
//        this.address = new Address(data.adress().logradouro(),
//                data.adress().cep(),
//                data.adress().bairro(),
//                data.adress().numero(), data.adress().complemento(), data.adress().cidade(), data.adress().uf());

    }

    public User(String login, String password, String cpf, String email, String phone) {
        this.login = login;
        this.password = password;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.userType = UserType.BUYER;
    }

    public void updateUser (UserDataUpdateDTO data) {
        if (data.fullName() != null ) {
            this.login = data.fullName();
        }
        if (data.email() != null) {
            this.email = data.email();
        }
        if(data.phone() != null) {
            this.phone = data.phone();
        }
        if (data.cpf() != null) {
            this.cpf = data.cpf();
        }
        if (data.password() != null){
            this.password = data.password();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.userType == UserType.ADMIN){
            List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername(){
        return login;
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
