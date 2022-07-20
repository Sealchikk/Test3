package amontov.service;

import amontov.models.Product;
import amontov.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public HashMap<Integer, String> showAll() {
        List<Product> list = productRepository.findAll();
        HashMap<Integer, String> map = new HashMap<>();
        for (Product product : list) {
            map.put(product.getId(),product.getName());
        }
        return map;
    }

    @Override
    public Product getOneProduct(int id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
