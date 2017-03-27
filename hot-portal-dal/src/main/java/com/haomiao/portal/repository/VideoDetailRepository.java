package com.haomiao.portal.repository;

import com.haomiao.portal.domain.VideoDetailPojo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by LJH on 2016-07-21.
 */
public interface VideoDetailRepository extends MongoRepository<VideoDetailPojo,String>{
    List<VideoDetailPojo> findByCat(int cat);
}
