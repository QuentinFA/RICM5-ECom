/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.List;

import entities.Product;
import entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "ECOM_EJB")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UserFacade() {
        super(User.class);
    }
    public List<User> findUserByFirstName(String FirstName) {
        Query query = em.createQuery("SELECT u FROM User u where u.firstname = '"+FirstName+"' ");
        return (List<User>) query.getResultList();
    }
    
    public List<User> findUserByLastName(String LasttName) {
        Query query = em.createQuery("SELECT u FROM User u where u.lastname = '"+LasttName+"' ");
        return (List<User>) query.getResultList();
    }
    
    public List<User> findUserByEmail(String email) {
        Query query = em.createQuery("SELECT u FROM User u where u.email = '"+email+"' ");
        return (List<User>) query.getResultList();
    }
    
    public List<User> findUserByTelephone(String telephone) {
        Query query = em.createQuery("SELECT u FROM User u where u.telephone = '"+telephone+"' ");
        return (List<User>) query.getResultList();
    }
    
    public List<User> getAllUserActivated(boolean activated) {
        Query query = em.createQuery("SELECT u FROM User u where u.actived = '"+activated+"' ");
        return (List<User>) query.getResultList();
    }
}
