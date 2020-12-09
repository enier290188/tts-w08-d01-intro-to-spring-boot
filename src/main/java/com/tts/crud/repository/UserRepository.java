package com.tts.crud.repository;

import org.springframework.data.repository.CrudRepository;
import com.tts.crud.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
