package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogInfo {
				
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)		
		private Long 	blogId;
		private String 	title;
		private String 	description;
		private String  article;
		private String 	username;
	
}

