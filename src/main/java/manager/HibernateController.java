package manager;

import lombok.Getter;

import javax.persistence.*;

@Getter
public class HibernateController {
    private static HibernateController controller;
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private EntityManager manager;
    private EntityTransaction transaction;

    private HibernateController() {
    }

    public static HibernateController getInstance() {
        if (controller == null) {
            controller = new HibernateController();
        }
        return controller;
    }

    public void open() {
        if (entityManagerFactory == null || !entityManagerFactory.isOpen())
            entityManagerFactory = Persistence.createEntityManagerFactory("default");
        if (manager == null || !manager.isOpen())
            manager = entityManagerFactory.createEntityManager();

        transaction = manager.getTransaction();
    }

    public void close() {
        manager.close();
        entityManagerFactory.close();
    }
}
