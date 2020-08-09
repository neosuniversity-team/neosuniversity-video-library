package com.neosuniversity.videolibrary.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name="MOVIE",schema="VIDEODB")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MOVIE",nullable=false,unique=false)
	private Long idMovie;
	
	@OneToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name = "ID_TYPE_MOVIE")
	private TypeMovie typemovie;
	
	
	@Column(name="TITLE",length=200,nullable=false)
	private String title;
	
	@Column(name="YEAR",nullable=false)
	private int year;
	
	@Column(name="SYNOPSIS",length=900,nullable=true)
	private String synopsis;
	
	@Column(name="DURATION",nullable=true)
	private Date duration;
	
	@Column(name="IMAGE_PATH",length=500,nullable=true)
	private String imagepath;


}
