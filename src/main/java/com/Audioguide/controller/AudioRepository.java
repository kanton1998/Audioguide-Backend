package com.Audioguide.controller;

import com.Audioguide.model.Audio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AudioRepository extends CrudRepository<Audio, Integer> {
    Optional<Audio> findByFileName(String fileName);
}
