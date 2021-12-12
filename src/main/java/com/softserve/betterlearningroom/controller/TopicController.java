package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.entity.Topic;
import com.softserve.betterlearningroom.repository.TopicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/classrooms/topic/")
public class TopicController {

    @Autowired
    private TopicDAO topicDAO;

    public TopicController() {
        super();
    }
    /**
     * get all topic
     */
    @GetMapping
    public List<Topic> getAll() {

        return (List<Topic>) topicDAO.findAll();
    }
    /**
     *
     * get topic by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable(value = "id") final Integer topicId) {
        Topic topic = topicDAO.findById(topicId)
                .orElseThrow(() -> new NoSuchElementException("topic not available for Id :" + topicId));
        return ResponseEntity.ok().body(topic);
    }
    /**
     *
     * create topic
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTopic(@Valid @RequestBody Topic topic) {
        //System.out.println(topic); // Just to inspect values for demo
        topicDAO.save(topic);
    }
    /**
     * update table by id
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTopic(@PathVariable("id") final int id, @RequestBody final Topic topic) {
        Topic findTopic = topicDAO.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Topic not available for Id :" + id));
        topicDAO.save(topic);
    }
    /**
     *
     * delete by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopic(@PathVariable final int id) {
        Topic topic = topicDAO.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Topic not available for Id :" + id));
        topicDAO.removeById(id);
    }
}
