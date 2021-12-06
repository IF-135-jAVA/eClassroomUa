package com.softserve.betterlearningroom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/announcements")
public class AnnouncementController {
    private final AnnouncementDAO announcementDAO;

    public AnnouncementController(AnnouncementDAO announcementDAO) {
        this.announcementDAO = announcementDAO;
    }

    @GetMapping()
    public String readAll(Model model) {
        model.addAttribute("announcements", announcementDAO.readAll());
        return "announcements/readAll-announcements";
    }

    @GetMapping("/{id}")
    public String readById(@PathVariable("id") long id, Model model) {
        model.addAttribute("announcement", announcementDAO.readById(id));
        return "announcements/readById-announcement";
    }

    @GetMapping("/new")
    public String newAnnouncement(@ModelAttribute("announcement") Announcement announcement) {
        return "announcements/new-announcement";
    }

    @PostMapping()
    public String create(@ModelAttribute("announcement") Announcement announcement) {
        announcementDAO.create(announcement);
        return "redirect:/announcements";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("announcement", announcementDAO.readById(id));
        return "announcements/edit-announcement";
    }

    //@PatchMapping("/{id}")
    @PostMapping("/{id}")
    public String update(@ModelAttribute("announcement") Announcement announcement,
                         @PathVariable("id") long id) {
        announcementDAO.update(id, announcement);
        return "redirect:/announcements";
    }

//    @DeleteMapping("/{id}")
    @PostMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        announcementDAO.delete(id);
        return "redirect:/announcement";
    }
}

