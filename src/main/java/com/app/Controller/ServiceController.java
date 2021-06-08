package com.app.Controller;

import com.app.Exceptions.EntityManagerException;
import com.app.Exceptions.FormException;
import com.app.Exceptions.InternalException;
import com.app.Exceptions.RegisteryException;
import com.app.Framework.Registery;
import com.app.Model.Scooter;
import com.app.Model.User;
import com.app.Services.EntityManager;
import com.app.Services.IEntity;
import com.app.Services.Layout;
import com.app.View.Home;
import com.app.View.ServiceView;
import java.util.List;

/**
 * List / READ ONE cars (cars are provided by there builder so search cars in builders)
 */
public class ServiceController extends AbstractController {
    public final static String TITLE_ADD = "Louer une trottinette";
    public final static String TITLE_TRACKING = "Suivi des location";

    private final EntityManager articleEntityManager;
    private final ServiceView serviceView;
    protected static String NUMBER_ERROR = "Le format du nombre n'est pas correct";

    public ServiceController(Registery registery) throws InternalException {
        super(registery);
        articleEntityManager = this.getEntityManagerProxy(Scooter.class);
        serviceView = new ServiceView(this.getLayout());
        actions();
    }
    protected void openListAction() throws RegisteryException {
        Layout ly = this.getLayout();
        // Home access
        ly.home.page(Home.SERVICES_LIST).onOpen(e -> {
            ly.setPageTitle(ServiceController.TITLE_TRACKING);
            ly.openPage(serviceView.productTableList, Home.SERVICES_LIST);

            try {
                serviceView.productTableList.getDetails(this.articleEntityManager.getAll());
            } catch (EntityManagerException entityManagerProxy) {}
        });
    }

    protected void openAddAction() throws RegisteryException {
        Layout ly = this.getLayout();
        ly.home.page(Home.SERVICES_ADD).onOpen(e -> {
            try {
                ly.openPage(serviceView.serviceAdd.getPanel(), Home.SERVICES_ADD);
                serviceView.fillForm(this.getUsers());
            } catch (InternalException internalException) {
                internalException.printStackTrace();
            }
        });
    }

    protected void submitArticleAction() {
        serviceView.serviceAdd.submit(e -> {
            try {
                serviceView.serviceAdd.validate();
                User user = (User) serviceView.user.getSelectedItem();
                Scooter scooter = new Scooter(
                    serviceView.intitule,
                    (float) serviceView.prixHT,
                    null
                );
                this.articleEntityManager.persist(scooter);
                scooter.setUser(user);
                serviceView.serviceAdd.reset(true);
            } catch (FormException formException) {
                serviceView.serviceAdd.errorDialog(formException.getMessage());
                return;
            } catch (NumberFormatException numException) {
                serviceView.serviceAdd.errorDialog(NUMBER_ERROR);
                return;
            } catch (EntityManagerException exception) {
                serviceView.serviceAdd.errorDialog(exception.getMessage());
            }
        });
    }

    public List<IEntity> getUsers() throws InternalException {
        return this.getEntityManagerProxy(User.class).getAll();
    }

    @Override
    protected void actions() throws InternalException {
        this.submitArticleAction();
        this.openListAction();
        this.openAddAction();
    }
}