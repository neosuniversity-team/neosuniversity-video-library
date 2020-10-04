package com.neosuniversity.videolibrary.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name="TYPE_MOVIE",schema="VIDEODB")
public class TypeMovie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TYPE_MOVIE",nullable=false)
	private Long idTypeMovie;
	
	@Column(name="TYPE_DESCRIPTION",length=100,nullable=false)
	@NotEmpty(message = "Please provide a description by type of movie")
	private String type;

}
