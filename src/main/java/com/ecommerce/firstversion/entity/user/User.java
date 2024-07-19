package com.ecommerce.firstversion.entity.user;

import com.ecommerce.firstversion.entity.user.dto.UserDTO;
import com.ecommerce.firstversion.entity.user.dto.UserDataUpdateDTO;
import com.ecommerce.firstversion.entity.address.Address;
import com.ecommerce.firstversion.entity.user_storage.UserStorage;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Entity(name = "User")
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String username;
    @Column(length = 25)
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
        this.username = data.fullName();
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

    public void updateUser (UserDataUpdateDTO data) {
        if (data.fullName() != null ) {
            this.username = data.fullName();
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
}
