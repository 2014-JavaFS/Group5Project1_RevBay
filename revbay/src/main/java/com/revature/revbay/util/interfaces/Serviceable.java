package com.revature.revbay.util.interfaces;

public interface Serviceable<o> {
    o create(o newObject);
    o findById(int number);
    boolean delete(o deletedObject);
    boolean update(o updatedObject);
}
