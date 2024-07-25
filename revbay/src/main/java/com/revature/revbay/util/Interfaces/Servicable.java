package com.revature.revbay.util.Interfaces;

import java.util.List;

public interface Servicable<O> {
    List<O> findAll();
    O create(O newObject);
    O findById(int number);
}
