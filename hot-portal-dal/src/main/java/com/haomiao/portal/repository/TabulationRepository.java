package com.haomiao.portal.repository;

import com.haomiao.portal.domain.Tabulation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by LJH on 2016-07-20.
 */
public interface TabulationRepository extends MongoRepository<Tabulation,String> {
    Tabulation findByType(String type);
}
