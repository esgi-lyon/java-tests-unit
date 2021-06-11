package com.exam1.Controller;

import com.exam1.Model.MusicalEvent;
import com.exam1.Model.User;
import com.exam1.View.Home;
import com.exam1.View.MusicalEventView;
import com.framework.Controller.AbstractController;
import com.framework.Exception.EntityManagerException;
import com.framework.Exception.FormException;
import com.framework.Exception.InternalException;
import com.framework.Exception.RegisteryException;
import com.framework.Registery;
import com.framework.Services.Dao.EntityManager;
import com.framework.Services.IEntity;
import com.framework.Services.Layout;

import java.util.List;


public class MusicalEventController extends AbstractController {
    public final static String TITLE_ADD = "Louer une trottinette";
    public final static String TITLE_TERMINATE = "Terminer ma course";
    public final static String TITLE_TRACKING = "Suivi des location";

    private final EntityManager articleEntityManager;
    private final MusicalEventView musicalEventView;
    protected static String NUMBER_ERROR = "Le format du nombre n'est pas correct";

    public MusicalEventController(Registery registery) throws InternalException {
        super(registery);
        articleEntityManager = this.getEntityManager(MusicalEvent.class);
        musicalEventView = new MusicalEventView(this.getLayout());
        actions();
    }

    protected void openListAction() throws RegisteryException {
        Layout ly = this.getLayout();
        // Home access
        ly.home.page(Home.SERVICES_LIST).onOpen(e -> {
            ly.setPageTitle(MusicalEventController.TITLE_TRACKING);
            ly.openPage(musicalEventView.productTableList, Home.SERVICES_LIST);

            try {
                musicalEventView.productTableList.getDetails(this.articleEntityManager.getAll());
            } catch (EntityManagerException entityManagerProxy) {}
        });
    }

    protected void startAction() throws RegisteryException {
        Layout ly = this.getLayout();
        ly.home.page(Home.SERVICES_ADD).onOpen(e -> {
            try {
                ly.openPage(musicalEventView.serviceAdd.getPanel(), Home.SERVICES_ADD);
                musicalEventView.fillForm(this.getUsers());
            } catch (InternalException internalException) {
                internalException.printStackTrace();
            }
        });
    }

    protected void terminateAction() throws RegisteryException {
        Layout ly = this.getLayout();
        ly.home.page(Home.SERVICES_TERMINATE).onOpen(e -> {
            try {
                ly.openPage(musicalEventView.serviceTerminate.getPanel(), Home.SERVICES_TERMINATE);
                musicalEventView.fillForm(this.getUsers());
            } catch (InternalException internalException) {
                internalException.printStackTrace();
            }
        });
    }

    protected void terminateTravelSubmitAction() {
        musicalEventView.serviceTerminate.submit(e -> {
            try {
                musicalEventView.serviceTerminate.validate();
                User user = (User) musicalEventView.user.getSelectedItem();
                MusicalEvent musicalEvent = (MusicalEvent) this.articleEntityManager.getById(user.getMusicalEvent().getId());
                musicalEvent.setUsers(null);
                musicalEvent.setStatus(MusicalEvent.Status.done);
                musicalEventView.serviceAdd.reset(true);
            } catch (FormException formException) {
                musicalEventView.serviceAdd.errorDialog(formException.getMessage());
                return;
            } catch (NumberFormatException numException) {
                musicalEventView.serviceAdd.errorDialog(NUMBER_ERROR);
                return;
            } catch (EntityManagerException exception) {
                musicalEventView.serviceAdd.errorDialog(exception.getMessage());
            }
        });
    }

    protected void startTravelSubmitAction() {
        musicalEventView.serviceAdd.submit(e -> {
            try {
                musicalEventView.serviceAdd.validate();
                User user = (User) musicalEventView.user.getSelectedItem();
                MusicalEvent musicalEvent = new MusicalEvent(musicalEventView.intitule);
                this.articleEntityManager.persist(musicalEvent);
                musicalEvent.addUser(user);
                musicalEventView.serviceAdd.reset(true);
            } catch (FormException formException) {
                musicalEventView.serviceAdd.errorDialog(formException.getMessage());
                return;
            } catch (NumberFormatException numException) {
                musicalEventView.serviceAdd.errorDialog(NUMBER_ERROR);
                return;
            } catch (EntityManagerException exception) {
                musicalEventView.serviceAdd.errorDialog(exception.getMessage());
            }
        });
    }

    public List<IEntity> getUsers() throws InternalException {
        return this.getEntityManager(User.class).getAll();
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