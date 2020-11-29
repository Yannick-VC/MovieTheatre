package com.example.demo.domain;

public interface UserRepository{
    public User findByUsername(String username);
    public void save(User user);
}