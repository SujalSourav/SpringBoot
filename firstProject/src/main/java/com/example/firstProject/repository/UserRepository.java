package com.example.firstProject.repository;

import com.example.firstProject.entity.JournalEntry;
import com.example.firstProject.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String username);


}
