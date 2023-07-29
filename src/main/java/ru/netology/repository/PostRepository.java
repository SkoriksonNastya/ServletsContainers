package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {

    private final Map<Long, Post> POSTS;
    private final AtomicLong POST_ID;

    public PostRepository(){
        POSTS = new ConcurrentHashMap<>();
        POST_ID = new AtomicLong();
    }
    public List<Post> all() {
        return new ArrayList<>(POSTS.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(POSTS.get(id));
    }

    public Post save(Post post) {
        long postExistingID = post.getId();
        if (postExistingID > 0 && POSTS.containsKey(postExistingID)){
            POSTS.replace(postExistingID, post);
        } else {
            long newPostID = postExistingID == 0 ? POST_ID.incrementAndGet() : postExistingID;
            post.setId(newPostID);
            POSTS.put(newPostID, post);
        }
        return post;
    }

    public void removeById(long id) {
        POSTS.remove(id);
    }
}