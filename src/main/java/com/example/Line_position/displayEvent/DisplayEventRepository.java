package com.example.Line_position.displayEvent;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DisplayEventRepository extends CrudRepository<DisplayEvent,Integer> {

	@Modifying
	@Query(value="insert into displayevent(line_id,position,event_name,date)values(?1,?2,?3,?4)",nativeQuery=true)
	
	@Transactional
	public int insertData(String line_id,String position,String event_name,String date);
	
	@Query("select de from DisplayEvent de where line_id=?1")
	@Transactional
	public List<DisplayEvent>getDisplayEvent(String line_id);
	
	
	@Modifying
	@Query(value="update displayevent set position=?2, event_name=?3,date=?4 where line_id=?1",nativeQuery=true)
	
	@Transactional
	public int updateData(String line_id,String position,String event_name,String date);
	
	@Query("Select t from DisplayEvent t where date=?1")
	public String getDate(String date);
}
