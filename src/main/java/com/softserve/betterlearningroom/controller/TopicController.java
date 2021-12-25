package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.TopicDTO;
import com.softserve.betterlearningroom.entity.Topic;
import com.softserve.betterlearningroom.service.impl.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/classrooms/topics/")
@AllArgsConstructor
public class TopicController {

    private TopicService topicService;

    /**
     * get all topic
     */
    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAll() {
        List<TopicDTO> topic = topicService.findAll();
        return ResponseEntity.ok().body(topic);
    }

    /**
     * get topic by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getById(@PathVariable Integer id) {
        Topic topic = topicService.findById(id);
        return ResponseEntity.ok().body(topic);
    }

    /**
     * create topic
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createTopic(@Valid @RequestBody TopicDTO topicDTO) {
        topicService.save(topicDTO);
        // TODO: 25.12.2021 please, return created object
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * update table by id
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody final TopicDTO topicDTO) {
        topicService.update(topicDTO);
        // TODO: 25.12.2021 please, return updated object
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * delete by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final int id) {

        topicService.removeById(id);
    }
}
