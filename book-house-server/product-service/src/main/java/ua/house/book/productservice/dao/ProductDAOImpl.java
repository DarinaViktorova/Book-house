package ua.house.book.productservice.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.house.book.productservice.dao.hql.ProductHQL;
import ua.house.book.productservice.domain.entity.Product;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional(readOnly = true)
public class ProductDAOImpl implements ProductDAO {
    private final SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public Long saveProduct(Product product) {
        return currentSession().merge(product)
                               .getId();
    }

    @Override
    public Optional<Product> getProduct(Long idProduct) {
        return Optional.ofNullable(currentSession()
                .get(Product.class, idProduct));
    }

    @Transactional
    @Override
    public Long updateProduct(Product product) {
        return currentSession().merge(product)
                               .getId();
    }

    @Transactional
    @Override
    public void deleteProduct(Long idProduct) {
        var product = currentSession().get(Product.class, idProduct);
        currentSession().remove(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return currentSession()
                .createQuery(ProductHQL.GET_ALL_PRODUCTS, Product.class)
                .getResultList();
    }

    @Transactional
    @Override
    public void saveAllProducts(List<Product> productList) {
        for (var product : productList) {
            currentSession().merge(product);
        }
    }

    @Transactional
    @Override
    public void deleteAllProducts() {
        currentSession().createQuery(ProductHQL.DELETE_ALL_PRODUCTS)
                        .executeUpdate();
    }
}
