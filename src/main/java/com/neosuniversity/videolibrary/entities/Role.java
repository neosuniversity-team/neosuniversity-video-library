package com.neosuniversity.videolibrary.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ROLE_TABLE",schema="VIDEODB")
public class Role implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_ROLE",nullable=false)
    private Long idRole;

	@Column(name="NAME_ROLE",length=100,nullable=false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


}
