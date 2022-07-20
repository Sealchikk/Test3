package amontov.service;

import amontov.models.Product;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;

public interface ProductService {
    HashMap<Integer, String> showAll ();
    Product getOneProduct (int id);
    void addProduct (Product product);
}
