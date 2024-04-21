package com.website.web.repo;

import com.website.web.models.Post;
import org.springframework.data.repository.CrudRepository;

//crearea repozitoriului pentru tabelul PostS
public interface PostRepository extends CrudRepository<Post, Integer> {
}
