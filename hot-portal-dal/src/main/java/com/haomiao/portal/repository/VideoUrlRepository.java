package com.haomiao.portal.repository;

import com.haomiao.portal.domain.VideoUrlPojo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by haomiao on 2016/7/26.
 */
public interface VideoUrlRepository extends MongoRepository<VideoUrlPojo,String> {
}
