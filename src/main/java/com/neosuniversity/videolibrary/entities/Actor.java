package com.neosuniversity.videolibrary.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "ACTOR", schema = "VIDEODB")
@Entity
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ACTOR", nullable = false)
	private Long idActor;

	@Column(name = "NAME", length = 200, nullable = false)
	private String name;

	@Column(name = "LAST_NAME", length = 200, nullable = false)
	private String lasname;

	@Column(name = "AGE", nullable = true)
	private int age;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ACTOR")
	private List<Address> addresses= new ArrayList<>();
	

}
