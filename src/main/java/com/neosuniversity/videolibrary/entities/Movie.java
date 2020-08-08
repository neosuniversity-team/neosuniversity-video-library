package com.neosuniversity.videolibrary.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

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
	
	@Column(name="TITLE",length=200,nullable=false)
	private String title;
	
	@Column(name="YEAR",nullable=false)
	private int year;
	
	@Column(name="SYNOPSIS",length=900,nullable=true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String synopsis;
	
	@DateTimeFormat(pattern = "HH:mm:ss", iso = ISO.DATE_TIME)
	@JsonFormat( shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
	@Column(name="DURATION",nullable=true)
	private Date duration;
	
	@Column(name="IMAGE_PATH",length=500,nullable=true)
	private String imagepath;


}
