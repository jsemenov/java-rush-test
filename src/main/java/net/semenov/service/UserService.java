package net.semenov.service;

import net.semenov.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Date;
import java.util.List;


@Service("userService")
@Transactional
public class UserService {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    public List<User> getAll() {
        logger.debug("Retrieving all persons");

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Create a Hibernate query (HQL)
        Query query = session.createQuery("FROM  User");

        // Retrieve all
        return query.list();
    }


    public User get(Integer id) {
        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing user first
        User user = (User) session.get(User.class, id);

        return user;
    }


    public List<User> getByName(String name) {

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        session.createCriteria(User.class).list();
        List<User> userList = (List<User>) session
                .createCriteria(User.class)
                .add(Restrictions.eq("name", name))
                .list();

        return userList;
    }


    public void add(User user) {
        logger.debug("Adding new user");

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        user.setCreatedDate(Date.from(Instant.now()));
        // Save
        session.save(user);
    }

    public void delete(Integer id) {
        logger.debug("Deleting existing user");

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing user first
        User user = (User) session.get(User.class, id);

        // Delete
        session.delete(user);
    }


    public void edit(User user) {
        logger.debug("Editing existing user");

        // Retrieve session from Hibernate
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing user via id
        User existingUser = (User) session.get(User.class, user.getId());

        // Assign updated values to this user
        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        existingUser.setIsAdmin(user.getIsAdmin());

        // Save updates
        session.save(existingUser);
    }
}
