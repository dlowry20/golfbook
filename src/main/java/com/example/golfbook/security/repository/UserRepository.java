package com.example.golfbook.security.repository;

import com.example.golfbook.security.model.GolfBookUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<GolfBookUser, String> {
}
