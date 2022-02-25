package com.example.Line_position.linePosition;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LinePositionRepository extends CrudRepository<LinePosition,Integer> {
	@Modifying
	@Query(value="insert into lineposition(line_no,position,text_language,text,date) values(?1,?2,?3,?4,?5)",nativeQuery=true)
	
	@Transactional
	public int insertData(String line_no,String position,String text_language,String text,String date);
	

	@Query("select lp from LinePosition lp where line_no=?1 order by date desc")
	public List<LinePosition> getLinePosition(String line_no);
}
