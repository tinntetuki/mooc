package com.haomiao.portal.repository;

import com.haomiao.portal.domain.HomeCourse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by LJH on 2016-07-20.
 */
public interface MoocHomeRepository extends MongoRepository<HomeCourse,String> {
    List<HomeCourse> findByType(String type, PageRequest request);
}
