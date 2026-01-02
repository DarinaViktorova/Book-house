package ua.house.book.productservice.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.house.book.productservice.dao.hql.ProductHQL;
import ua.house.book.productservice.domain.entity.Product;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Transactional(readOnly = true)
public class ProductSortDAOImpl implements ProductSortDAO {
    private final SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    @Override
    public List<Product> getAllProductsByAscendingOrder() {
        return currentSession().createQuery(ProductHQL.GET_ALL_PRODUCTS_ASC,Product.class).getResultList();
    }

    @Override
    public List<Product> getAllProductsByDescendingOrder() {
        return currentSession().createQuery(ProductHQL.GET_ALL_PRODUCTS_DESC,Product.class).getResultList();
    }
}
