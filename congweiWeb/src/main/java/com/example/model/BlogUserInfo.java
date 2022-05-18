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
public class BlogUserInfo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)		
	private Long id;
	private String username;
	private String password;
	
}	
   //此类中定义的就是表格中显示的列