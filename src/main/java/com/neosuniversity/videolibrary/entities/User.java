package com.neosuniversity.videolibrary.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "USER",schema="VIDEODB")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Ingrese un usuario")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Su usuario solo debe contener letras mayusculas y/o minusculas")
    private String username;

    @NotBlank(message="Ingrese un password")
    private String password;

    @Transient
    @NotBlank(message="Ingrese un password")
    private String passwordConfirm;

    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Role> roles;

    
}