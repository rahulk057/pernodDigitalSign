package com.example.Line_position.imageUpload;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ImageUploadRepository extends CrudRepository<ImageUpload,Integer> {
	
	@Modifying
	@Query(value="insert into image(image,line_id,date,file_name,file_type) values (?1,?2,?3,?4,?5)",nativeQuery=true)
	
 	 @Transactional
 	 public int insertData( byte[] image,String line_id,String date,String file_name,String file_type );
	
	
	
	@Query("select lp.id from ImageUpload lp where line_id=?1 or line_id=1 order by date desc")
	public List<Integer> getImageUpload1(String line_id);
	
	@Query("select lp from ImageUpload lp where line_id=?1 order by date desc")
	public List<ImageUpload> getImage(String line_id);
	
	
	
	@Query("select lp from ImageUpload lp where id=?1 order by date desc")
	public List<ImageUpload> getImage(int id);
	
	@Query("select lp from ImageUpload lp where id=?1 order by date desc")
	public ImageUpload getData(int id);

	
	@Modifying
	@Query(value="delete from image where id=?1",nativeQuery=true)
     @Transactional
      public int deleteData(int id);

	@Query("select lp from ImageUpload lp where line_id=?1 order by date desc")
	public List<ImageUpload> getVideo(String line_id);
}
