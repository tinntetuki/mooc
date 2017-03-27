package com.haomiao.portal.repository;

import com.haomiao.portal.domain.SchoolResources;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by LJH on 2016-07-20.
 */
public interface SchoolResourcesRepository extends MongoRepository<SchoolResources,String> {

    SchoolResources findById(String id);

    List<SchoolResources> findByTitleLike(String title);

}
