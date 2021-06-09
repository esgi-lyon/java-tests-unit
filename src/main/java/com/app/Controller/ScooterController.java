package com.app.Controller;

import com.framework.Exception.EntityManagerException;
import com.framework.Exception.FormException;
import com.framework.Exception.InternalException;
import com.framework.Exception.RegisteryException;
import com.framework.Controller.AbstractController;
import com.framework.Registery;
import com.app.Model.Scooter;
import com.app.Model.User;
import com.framework.Services.EntityManager;
import com.framework.Services.IEntity;
import com.framework.Services.Layout;
import com.app.View.Home;
import com.app.View.ScooterView;
import java.util.List;

/**
 * List / READ ONE cars (cars are provided by there builder so search cars in builders)
 */
public class ScooterController extends AbstractController {
    public final static String TITLE_ADD = "Louer une trottinette";
    public final static String TITLE_TERMINATE = "Terminer ma course";
    public final static String TITLE_TRACKING = "Suivi des location";

    private final EntityManager articleEntityManager;
    private final ScooterView scooterView;
    protected static String NUMBER_ERROR = "Le format du nombre n'est pas correct";

    public ScooterController(Registery registery) throws InternalException {
        super(registery);
        articleEntityManager = this.getEntityManagerProxy(Scooter.class);
        scooterView = new ScooterView(this.getLayout());
        actions();
    }

    protected void openListAction() throws RegisteryException {
        Layout ly = this.getLayout();
        // Home access
        ly.home.page(Home.SERVICES_LIST).onOpen(e -> {
            ly.setPageTitle(ScooterController.TITLE_TRACKING);
            ly.openPage(scooterView.productTableList, Home.SERVICES_LIST);

            try {
                scooterView.productTableList.getDetails(this.articleEntityManager.getAll());
            } catch (EntityManagerException entityManagerProxy) {}
        });
    }

    protected void startAction() throws RegisteryException {
        Layout ly = this.getLayout();
        ly.home.page(Home.SERVICES_ADD).onOpen(e -> {
            try {
                ly.openPage(scooterView.serviceAdd.getPanel(), Home.SERVICES_ADD);
                scooterView.fillForm(this.getUsers());
            } catch (InternalException internalException) {
                internalException.printStackTrace();
            }
        });
    }

    protected void terminateAction() throws RegisteryException {
        Layout ly = this.getLayout();
        ly.home.page(Home.SERVICES_TERMINATE).onOpen(e -> {
            try {
                ly.openPage(scooterView.serviceTerminate.getPanel(), Home.SERVICES_TERMINATE);
                scooterView.fillForm(this.getUsers());
            } catch (InternalException internalException) {
                internalException.printStackTrace();
            }
        });
    }

    protected void terminateTravelSubmitAction() {
        scooterView.serviceTerminate.submit(e -> {
            try {
                scooterView.serviceTerminate.validate();
                User user = (User) scooterView.user.getSelectedItem();
                Scooter scooter = (Scooter) this.articleEntityManager.getById(user.getService().getId());
                scooter.setUser(null);
                scooter.setStatus(Scooter.Status.done);
                scooterView.serviceAdd.reset(true);
            } catch (FormException formException) {
                scooterView.serviceAdd.errorDialog(formException.getMessage());
                return;
            } catch (NumberFormatException numException) {
                scooterView.serviceAdd.errorDialog(NUMBER_ERROR);
                return;
            } catch (EntityManagerException exception) {
                scooterView.serviceAdd.errorDialog(exception.getMessage());
            }
        });
    }

    protected void startTravelSubmitAction() {
        scooterView.serviceAdd.submit(e -> {
            try {
                scooterView.serviceAdd.validate();
                User user = (User) scooterView.user.getSelectedItem();
                Scooter scooter = new Scooter(scooterView.intitule, (float) scooterView.prixPerMinutes);
                this.articleEntityManager.persist(scooter);
                scooter.setUser(user);
                scooterView.serviceAdd.reset(true);
            } catch (FormException formException) {
                scooterView.serviceAdd.errorDialog(formException.getMessage());
                return;
            } catch (NumberFormatException numException) {
                scooterView.serviceAdd.errorDialog(NUMBER_ERROR);
                return;
            } catch (EntityManagerException exception) {
                scooterView.serviceAdd.errorDialog(exception.getMessage());
            }
        });
    }

    public List<IEntity> getUsers() throws InternalException {
        return this.getEntityManagerProxy(User.class).getAll();
    }

    @Override
    protected void actions() throws InternalException {
        this.openListAction();
        this.startAction();
        this.startTravelSubmitAction();
        this.terminateAction();
        this.terminateTravelSubmitAction();
    }
}