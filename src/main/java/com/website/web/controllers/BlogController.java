package com.website.web.controllers;

import com.website.web.models.Post;
import com.website.web.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }


    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";
    }


    //controller-ul dat preia valorile din pagina web si le salveaza in BD
    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, @RequestParam String comment,Model model){
        Post post = new Post(title, anons, full_text, comment);
        postRepository.save(post);
        return "redirect:/blog";
    }

    //afisarea fiecarui post
   @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") Integer id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("post", res);
        return "blog-details";
    }

    //metoda pentru a afisa o iregistrare existenta modificata
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") Integer id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("post", res);
        return "blog-edit";
    }

    //metoda care preia valorile modificate si le acualizeaza in baza de date
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") Integer id ,@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, @RequestParam String comment, Model model){
       Post post = postRepository.findById(id).orElseThrow();
       post.setTitle(title);
       post.setAnons(anons);
       post.setFull_text(full_text);
       post.setComment(comment);
       postRepository.save(post);

        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostRemove(@PathVariable(value = "id") Integer id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);

        return "redirect:/blog";
    }

    //metoda nefinalizata ce trebuie sa prelucreze adresa /about si sa deschida pagina about(inca nu are file de tip .hmtl)
    @GetMapping("/about")
    public String aboutMain(Model model){
        return "about-main";
    }
}
