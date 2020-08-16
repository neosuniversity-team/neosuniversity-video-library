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
	
	@Column(name="TITLE",length=200,nullable=false)
	private String title;
	
	@Column(name="YEAR",nullable=false)
	private int year;
	
	@Column(name="SYNOPSIS",length=900,nullable=true)
	private String synopsis;
	
	@Column(name="DURATION",nullable=true)
	private String duration;
	
	@ManyToMany(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
	@JoinTable(name="MOVIE_ACTOR",joinColumns=@JoinColumn(name="ID_MOVIE"),
	inverseJoinColumns=@JoinColumn(name="ID_ACTOR"))
	@OrderBy(value="idActor ASC")
	private Set<Actor> actors = new HashSet<>();

}