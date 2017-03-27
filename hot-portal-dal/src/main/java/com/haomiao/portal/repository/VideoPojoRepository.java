package com.haomiao.portal.repository;

import com.haomiao.portal.domain.VideoPojo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by LJH on 2016-07-21.
 */
public interface VideoPojoRepository extends MongoRepository<VideoPojo,String> {
    List<VideoPojo> findByTid(int tid, Pageable pageable);
    List<VideoPojo> findByCat(int cat);
    List<VideoPojo> findByCatAndActorContaining(int cat,String name,Pageable pageable);
    List<VideoPojo> findByCatAndAreaContaining(int cat,String area,Pageable pageable);
}
