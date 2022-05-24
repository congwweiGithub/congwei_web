
package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.BlogUserInfo;

@Repository
public interface BlogUserInfoRepository extends JpaRepository<BlogUserInfo,Long> {
	
	BlogUserInfo findByUsername(String username);

}