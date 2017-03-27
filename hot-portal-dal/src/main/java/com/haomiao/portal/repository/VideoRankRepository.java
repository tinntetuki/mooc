package com.haomiao.portal.repository;

import com.haomiao.portal.domain.VideoRankPojo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by haomiao on 2016/7/21.
 */
public interface VideoRankRepository extends MongoRepository<VideoRankPojo,String>{
    List<VideoRankPojo> findByCat(int cat);
    List<VideoRankPojo> findByCat(int cat, Pageable pageable);
}
