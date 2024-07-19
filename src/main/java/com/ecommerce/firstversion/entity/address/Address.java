package com.ecommerce.firstversion.entity.address;

import com.ecommerce.firstversion.entity.address.dto.AdressDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;


            public void atualizarInformarcoes(AdressDTO dados) {
            if(dados.logradouro() != null){
                this.logradouro = dados.logradouro();
            }
            if(dados.bairro() != null){
                this.bairro = dados.bairro();
            }
            if(dados.cep() != null){
                this.cep = dados.cep();
            }
            if(dados.uf() != null){
                this.uf = dados.uf();
            }
            if(dados.cidade() != null){
                this.cidade = dados.cidade();
            }
            if(dados.numero() != null){
                this.numero = dados.numero();
            }
            if(dados.complemento() != null){
                this.complemento = dados.complemento();
            }

        }

}
