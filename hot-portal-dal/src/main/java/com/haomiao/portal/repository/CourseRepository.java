package com.haomiao.portal.repository;

import com.haomiao.portal.domain.Course;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by LJH on 2016-07-20.
 */
public interface CourseRepository extends MongoRepository<Course,String> {
}
