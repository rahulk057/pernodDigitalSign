package com.example.Line_position.yarnDropping;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YarnDroppingRepository extends CrudRepository<YarnDropping,Integer> {
@Modifying
@Query(value="insert into yarndropping(line_id,current_position,date,time)values(?1,?2,?3,?4)",nativeQuery=true)

@Transactional
public int insertData(String line_id,String current_position,String date,String time);

	@Query("select yd from YarnDropping yd where line_id=?1" )
	
	@Transactional
	public List<YarnDropping>getYarnData(String line_id);
	
	@Modifying
	@Query(value="update yarndropping set current_position=?2,date=?3,time=?4 where line_id=?1",nativeQuery=true)
	
	@Transactional
	public int updateData(String line_id,String current_position,String date,String time);
	
}
