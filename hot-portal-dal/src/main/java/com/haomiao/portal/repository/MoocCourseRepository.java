package com.haomiao.portal.repository;

import com.haomiao.portal.domain.MoocCourse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by LJH on 2016-07-20.
 */
public interface MoocCourseRepository extends MongoRepository<MoocCourse,String> {

    MoocCourse findById(String id);

    List<MoocCourse> findByTitleLike(String title);

    List<MoocCourse> findByCourseContentLike(String content);


}
