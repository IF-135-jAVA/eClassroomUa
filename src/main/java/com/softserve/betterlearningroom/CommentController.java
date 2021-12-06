package com.softserve.betterlearningroom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private final CommentDAO commentDAO;

    public CommentController(CommentDAO commentDAO) {

        this.commentDAO = commentDAO;
    }

    @GetMapping()
    public String readAll(Model model) {
        model.addAttribute("comments", commentDAO.readAll());
        return "comments/readAll-comments";
    }

    @GetMapping("/{id}")
    public String readById(@PathVariable("id") long id, Model model) {
        model.addAttribute("comment", commentDAO.readById(id));
        return "comments/readById-comment";
    }

    @GetMapping("/new")
    public String newComment(@ModelAttribute("comment") Comment comment) {

        return "comments/new-comment";
    }

    @PostMapping()
    public String create(@ModelAttribute("comment") Comment comment) {
        commentDAO.create(comment);
        return "redirect:/comments";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("comment", commentDAO.readById(id));
        return "comments/edit-comment";
    }

    //@PatchMapping("/{id}")
    @PostMapping("/{id}")
    public String update(@ModelAttribute("comment") Comment comment,
                         @PathVariable("id") long id) {
        commentDAO.update(id, comment);
        return "redirect:/comments";
    }

//    @DeleteMapping("/{id}")
    @PostMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        commentDAO.delete(id);
        return "redirect:/comment";
    }
}

