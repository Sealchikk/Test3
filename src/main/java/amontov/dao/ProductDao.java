package amontov.dao;

import amontov.models.Product;

import java.util.HashMap;
import java.util.List;

public interface ProductDao {
    HashMap<Integer,String> showAll ();
    Product getOneProduct (int id);
    void addProduct (Product product);
}
