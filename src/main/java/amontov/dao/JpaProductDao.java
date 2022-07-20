package amontov.dao;

import amontov.controllers.ProductDao;
import amontov.models.Product;
import com.mysql.cj.xdevapi.JsonArray;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
@Component
@Transactional(readOnly = true)
public class JpaProductDao implements ProductDao {
    private EntityManager entityManager;
    @PersistenceContext(unitName = "entityManagerFactoryBean")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public HashMap<Integer, String> showAll() {
        List<Product> list = entityManager.createQuery(
                "select p from Product p", Product.class).getResultList();
        HashMap<Integer, String> map = new HashMap<>();
        for (Product product : list) {
            map.put(product.getId(), product.getName());
        }
        return map;
    }

    @Override
    public Product getOneProduct(int id) {
        TypedQuery<Product> q = entityManager.createQuery
                ("select p from Product p where p.id = :id", Product.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        entityManager.persist(product);
    }
}
