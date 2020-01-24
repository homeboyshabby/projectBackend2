package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.OrderStatus;
import com.app.pojos.OrderType;
import com.app.pojos.Orders;
import com.app.pojos.UserType;
@Repository
@Transactional
public class DBoyDaoImpl implements IDBoyDao {
	@Autowired
	private SessionFactory sf;
	@Override
	public List<Orders> getOnlineOrderStatus() {
		String jpql = "select o from Orders o where o.orderType = '"+ OrderType.valueOf("HOMEDELIVERY") + "'";
		return sf.getCurrentSession().createQuery(jpql, Orders.class).getResultList();
	}
//	String jpql = "select e from Employee e where e.role = '"+ UserType.valueOf("MANAGER") + "' or e.role= '"+ UserType.valueOf("WAITER") + "'";
	@Override
	public List<Orders> getPendingOnlineOrderStatus() {
		String jpql = "select o from Orders o where o.orderType = '"+ OrderType.valueOf("HOMEDELIVERY") + "' and o.status = '"+ OrderStatus.valueOf("OUTFORDELIVERY") + "'";
		return sf.getCurrentSession().createQuery(jpql, Orders.class).getResultList();
	}

	@Override
	public List<Orders> getDeliveredOnlineOrderStatus() {
		String jpql = "select o from Orders o where o.orderType = '"+ OrderType.valueOf("HOMEDELIVERY") + "'and o.status = '"+ OrderStatus.valueOf("DELIVERED") + "'";
		return sf.getCurrentSession().createQuery(jpql, Orders.class).getResultList();
	}
	
	@Override
	public void changeOrderStatus(int orderId) {
		sf.getCurrentSession().get(Orders.class, orderId).setStatus(OrderStatus.valueOf("DELIVERED"));
	}

}
