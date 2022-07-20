package amontov.dao;

import amontov.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class HibernateProductDao implements ProductDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Session getSession () {
      return sessionFactory.getCurrentSession();
    }
    @Override
    public HashMap<Integer, String> showAll () {
        List<Product> list = getSession().createQuery("from Product",Product.class).list();
        HashMap<Integer, String> map = new HashMap<>();
        for (Product product : list) {
            map.put(product.getId(), product.getName());
        }
        return map;
    }
    @Override
    public Product getOneProduct (int id) {
        Query<Product> productQuery = getSession().createQuery(
                "from Product where id = :id",Product.class );
        productQuery.setParameter("id", id);
        return productQuery.getSingleResult();
    }
    @Override
    public void addProduct (Product product) {
        getSession().save(product);
    }
}
