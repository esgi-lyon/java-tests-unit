package com.app.Services;

import com.app.Exceptions.EntityManagerException;
import com.app.Framework.Registery;
import com.app.Model.Scooter;

import java.util.ArrayList;
import java.util.HashMap;

public class Fixtures implements com.app.Framework.Service {
    public static String DEMO_TEXT_OFF = "Désactiver la démo";
    public static String DEMO_TEXT_ON = "Activer la démo";
    public boolean ENABLE = false;
    private boolean loaded = false;

    private HashMap<String, ArrayList<IEntity>> map = new HashMap<>();

    public Fixtures() {
        // this.map.put(Magasin.class.getSimpleName(), this.getFakeMagasin());
        this.map.put(Scooter.class.getSimpleName(), this.getFakeServices());
        load();
    }

    public void bootFixtures(Registery registery) throws Exception {
        ENABLE = !ENABLE;
        EntityManager serviceManager = (EntityManager) registery.get(Scooter.class.getSimpleName());

        if (ENABLE == false) {
            managerRemoveFixtures(serviceManager);
            return;
        };

        managerSetFixtures(serviceManager);
    }

    protected void managerSetFixtures(EntityManager manager) throws Exception {
        for (IEntity en : this.map.get(manager.getEntityClass().getSimpleName())) {
            manager.persist(en);
        }
    }

    protected void managerRemoveFixtures(EntityManager manager) {
        try {
            manager.hqlTruncate();
        } catch (EntityManagerException exception) {
            exception.printStackTrace();
        }
    }
    /*
    private ArrayList<IEntity> getFakeMagasin() {
        ArrayList<IEntity> arr = new ArrayList<>();

        Magasin b1 = new Magasin("0778452313", "12 chemin du four", 79000);
        b1.addArticle((Service) this.getFakeArticles().get(0));
        b1.addArticle((Service) this.getFakeArticles().get(2));
        b1.addArticle((Service) this.getFakeArticles().get(3));

        Magasin b2 = new Magasin("0618452312", "23 rue de l'abbé", 69100);
        b2.addArticle((Service) this.getFakeArticles().get(3));
        b2.addArticle((Service) this.getFakeArticles().get(4));

        Magasin b3 = new Magasin("0421452512", "45 rue garibaldi", 75100);
        b3.addArticle((Service) this.getFakeArticles().get(0));

        Magasin b4 = new Magasin("0721402501", "8 cour suchet", 69007);
        b4.addArticle((Service) this.getFakeArticles().get(0));

        arr.add(b1);
        arr.add(b2);
        arr.add(b3);
        arr.add(b4);

        return arr;
    }
    */
    private ArrayList<IEntity> getFakeServices() {
        ArrayList<IEntity> arr = new ArrayList<>();

        Scooter c1 = new Scooter("Trotinette", (float) 1, null);

        arr.add(c1);

        return arr;
    }

    public String demoText() {
        return ENABLE ? DEMO_TEXT_OFF : DEMO_TEXT_ON;
    }

    @Override
    public void load() {
        loaded = true;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }
}
