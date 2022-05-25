 
package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.BlogsInfo;

@Repository
public interface BlogsInfoRepository extends JpaRepository<BlogsInfo,Long> {

	BlogsInfo findByBlogId(Long blogId);		
	
}






