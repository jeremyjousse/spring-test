package com.decathlon.stringtest;


import org.springframework.data.repository.CrudRepository;

public interface TutorialRepository extends CrudRepository<Tutorial, Integer> {

    Tutorial findById(Integer id);
}
