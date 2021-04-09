package com.app.Services;

import com.app.Exceptions.EntityManagerProxyException;
import com.app.Framework.Registery;
import com.app.Model.Service;
import com.app.Model.Magasin;
import java.util.ArrayList;
import java.util.HashMap;

public class Fixtures implements com.app.Framework.Service {
    public static String DEMO_TEXT_OFF = "Désactiver la démo";
    public static String DEMO_TEXT_ON = "Activer la démo";
    public boolean ENABLE = false;
    private boolean loaded = false;

    private HashMap<String, ArrayList<IEntity>> map = new HashMap<>();

    public Fixtures() {
        this.map.put(Magasin.class.getSimpleName(), this.getFakeMagasin());
        this.map.put(Service.class.getSimpleName(), this.getFakeArticles());
        load();
    }

    public void bootFixtures(Registery registery) throws Exception {
        ENABLE = !ENABLE;
        EntityManagerProxy magasinManager = (EntityManagerProxy) registery.get(Magasin.class.getSimpleName());
        EntityManagerProxy articleManager = (EntityManagerProxy) registery.get(Service.class.getSimpleName());

        if (ENABLE == false) {
            managerRemoveFixtures(magasinManager);
            managerRemoveFixtures(articleManager);
            return;
        };

        managerSetFixtures(magasinManager);
        managerSetFixtures(articleManager);
    }

    protected void managerSetFixtures(EntityManagerProxy manager) throws Exception {
        for (IEntity en : this.map.get(manager.getEntityClass().getSimpleName())) {
            manager.persist(en);
        }
    }

    protected void managerRemoveFixtures(EntityManagerProxy manager) {
        try {
            manager.hqlTruncate();
        } catch (EntityManagerProxyException exception) {
            exception.printStackTrace();
        }
    }

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

    private ArrayList<IEntity> getFakeArticles() {
        ArrayList<IEntity> arr = new ArrayList<>();

        Service c1 = new Service("Huitres", (float) 10.99, 100);
        Service c2 = new Service("Moutardes", (float) 3.49, 120);
        Service c3 = new Service("Steak", (float) 2.99, 76);
        Service c4 = new Service("Haricots", (float) 3.99, 29);
        Service c5 = new Service("Melon", (float) 3.99, 40);

        arr.add(c1);
        arr.add(c2);
        arr.add(c3);
        arr.add(c4);
        arr.add(c5);

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
