 
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.BlogInfo;

@Repository
public interface BlogInfoRepository extends JpaRepository<BlogInfo,Long> {

	BlogInfo findByBlogId(Long blogId);		
	
}






