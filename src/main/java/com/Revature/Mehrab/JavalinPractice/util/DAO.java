package com.Revature.Mehrab.JavalinPractice.util;

import java.util.Optional;

public interface DAO <T>{
    public Iterable<T> getAll();
    public Optional<T> getById(int id);
    public Iterable<String> getAllNames();
    public String insert(T object);
}
