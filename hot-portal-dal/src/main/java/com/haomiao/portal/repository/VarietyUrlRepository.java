package com.haomiao.portal.repository;

import com.haomiao.portal.domain.VarietyUrlPojo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by haomiao on 2016/7/28.
 */
public interface VarietyUrlRepository extends MongoRepository<VarietyUrlPojo,String>{
}
