
package com.swaroop.acharya.photo.clone.repository;

import com.swaroop.acharya.photo.clone.model.PhotoEntity;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<PhotoEntity,Integer> {
}
