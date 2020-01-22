package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Bills;
import com.app.pojos.MenuItems;
import com.app.pojos.OrderType;
import com.app.pojos.UserType;
@Repository
@Transactional
public class ManagerDaoImpl implements IManagerDao {
	@Autowired
	private SessionFactory sf;
	@Override
	public List<Bills> getAllBills() {
		String jpql = "select b from Bills b";
		return sf.getCurrentSession().createQuery(jpql,Bills.class).getResultList();
	}
	@Override
	public List<Integer> getOrderIdForHomeDeliverySaleReport() {
		String jpql = "select o.orderId from Orders o where o.orderType = '"+ OrderType.valueOf("HOMEDELIVERY") + "'";
		return sf.getCurrentSession().createQuery(jpql,Integer.class).getResultList();
	}
	@Override
	public List<Double> getOrderAmtForSaleReport(int id) {
		String jpql = "select o.billAmt from Bills o where o.order.orderId = :id";
		return sf.getCurrentSession().createQuery(jpql,Double.class).setParameter("id", id).getResultList();
	}
	@Override
	public List<Integer> getOrderIdForDineInSaleReport() {
		String jpql = "select o.orderId from Orders o where o.orderType = '"+ OrderType.valueOf("DINEIN") + "'";
		return sf.getCurrentSession().createQuery(jpql,Integer.class).getResultList();
	}
}
