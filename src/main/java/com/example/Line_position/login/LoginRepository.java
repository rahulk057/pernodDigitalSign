package com.example.Line_position.login;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login,Integer> {

	@Modifying
	@Query(value="insert into login (username,password)values(?1,?2)",nativeQuery=true)
	
	@Transactional
	
	public int insertData(String username,String password);
	
	@Query("select ld from Login ld where username=?1 and password=?2")
	@Transactional
	public List<Login>getLogindata(String username,String Password);
	
	
	
}
