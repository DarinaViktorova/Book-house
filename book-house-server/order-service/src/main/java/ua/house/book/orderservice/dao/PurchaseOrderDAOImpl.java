package ua.house.book.orderservice.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.house.book.orderservice.dao.hql.PurchaseHQL;
import ua.house.book.orderservice.domain.entity.Order;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Transactional(readOnly = true)
public class PurchaseOrderDAOImpl implements PurchaseOrderDAO {
    private final SessionFactory sessionFactory;
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public void createListOrder(List<Order> orderList) {
        orderList
                .forEach(order -> currentSession().merge(order));
    }

    @Override
    public List<Order> getListOrder(Long idAccount) {
        return currentSession()
                .createQuery(PurchaseHQL.GET_ALL_ORDER_BY_ACCOUNT_ID, Order.class)
                .setParameter("idAccount", idAccount)
                .getResultList();
    }
}
