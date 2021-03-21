package com.example.vacancydiary.service;

import com.example.vacancydiary.model.User;

import java.util.List;

public interface UserService {

    void create(User client);

    List<User> readAll();

    User read(long id);

    boolean update(User client, long id);

    boolean delete(long id);

}
