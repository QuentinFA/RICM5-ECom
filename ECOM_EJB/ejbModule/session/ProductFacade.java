/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.List;

import entities.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "ECOM_EJB")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
 
    public ProductFacade() {
        super(Product.class);
    }

    @SuppressWarnings("unchecked")
	public List<Product> findProductByTitle(String title) {
      Query query = em.createQuery("SELECT p FROM Product p where p.title = '"+title+"' ");
      return (List<Product>) query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Product> findProductByType(String type) {
        Query query = em.createQuery("SELECT p FROM Product p where p.type = '"+type+"' ");
        return (List<Product>) query.getResultList();
      }
    
    @SuppressWarnings("unchecked")
	public List<Product> findProductPriceRange(int startPrice, int endPrice) {
        Query query = em.createQuery("SELECT p FROM Product p where p.price >= '"+startPrice+"' AND p.price <= '"+endPrice+"' ");
        return (List<Product>) query.getResultList();
      }
    
    @SuppressWarnings("unchecked")
	public List<Product> findProductByIdUserAndTitle(String idUser, String title) {
        Query query = em.createQuery("SELECT p FROM Product p where p.idUser = '"+idUser+"' AND p.title = '"+title+"' ");
        return (List<Product>) query.getResultList();
      }
    
}
