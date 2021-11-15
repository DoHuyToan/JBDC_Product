package product.manager.service;

import product.manager.model.Product;

import java.util.List;

public interface IService<T> {
    List<T> showAll();
    void add(T t);
    boolean edit(T t);
    boolean remove(int id);
    Product findById(int id);
}
