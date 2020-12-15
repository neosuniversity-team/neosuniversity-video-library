package com.neosuniversity.videolibrary.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = { "synopsis"})
@Table(name="MOVIE",schema="VIDEODB")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MOVIE",nullable=false,unique=false)
	private Long idMovie;
	
	@OneToOne(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
	@JoinColumn(name = "ID_TYPE_MOVIE")
	private TypeMovie typemovie;
	
	//add validation
    
	@NotBlank(message = "Ingrese un valor en el título")
	@Size(min =  1, max= 200, message = "La longitud máxima es de 200")
	@Column(name="TITLE",length=200,nullable=false)
	private String title;
	
	@NotNull(message = "Ingrese un valor al año")
	@Column(name="YEAR",nullable=false)
	private Integer year;
	
	@Column(name="SYNOPSIS",length=900,nullable=true)
	@NotBlank(message="Ingrese una descripción a la sinopsis")
	private String synopsis;
	
	@Column(name="DURATION",nullable=true)
	@Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Ingrese la duración en el formato HH:mm")
	private String duration;
	
	@ManyToMany(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
	@JoinTable(name="MOVIE_ACTOR",joinColumns=@JoinColumn(name="ID_MOVIE"),
	inverseJoinColumns=@JoinColumn(name="ID_ACTOR"))
	@OrderBy(value="idActor ASC")
	private Set<Actor> actors = new HashSet<>();

}