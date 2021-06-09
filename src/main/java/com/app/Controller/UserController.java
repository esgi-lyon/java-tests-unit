package com.app.Controller;

import com.app.Model.ClassicUser;
import com.app.Model.Student;
import com.app.Model.User;
import com.app.View.Home;
import com.app.View.UserView;
import com.framework.Controller.AbstractController;
import com.framework.Exception.EntityManagerException;
import com.framework.Exception.FormException;
import com.framework.Exception.InternalException;
import com.framework.Exception.RegisteryException;
import com.framework.Registery;
import com.framework.Services.EntityManager;
import com.framework.Services.IEntity;
import com.framework.Services.Layout;

import java.util.List;

public class UserController extends AbstractController {
    public final static String TITLE_LIST = "Utilisateurs";
    public final static String TITLE_ADD = "Inscrire un utilisateur";

    private final EntityManager userEntityManager;
    UserView userView;

    public UserController(Registery registery) throws InternalException {
        super(registery);
        userEntityManager = this.getEntityManager(User.class);
        userView = new UserView(this.getLayout());
        actions();
    }

    protected void submitUserAction() {
        userView.userAdd.submit(e -> {
            try {
                userView.userAdd.validate();
                User user;

                if (userView.type.getSelectedItem() == "Student") {
                    user = new Student(userView.name.getText());
                } else {
                    user = new ClassicUser(userView.name.getText());
                }

                this.userEntityManager.persist(user);
                userView.userAdd.reset(true);
            } catch (FormException formException) {
                userView.userAdd.errorDialog(formException.getMessage());
                return;
            } catch (EntityManagerException exception) {
                userView.userAdd.errorDialog(exception.getMessage());
            }
        });
    }

    public List<IEntity> getUsers() throws InternalException  {
        return this.getEntityManager(User.class).getAll();
    }

    protected void openListPage() throws RegisteryException {
        Layout ly = this.getLayout();

        ly.home.page(Home.USERS_LIST).onOpen(e -> {
            ly.setPageTitle(UserController.TITLE_LIST);

            ly.openPage(userView.userTableList, Home.USERS_LIST);
            try {
                userView.userTableList.getDetails(this.userEntityManager.getAll());
            } catch (EntityManagerException entityManagerProxy) {}
        });
    }

    protected void openFormPage() throws RegisteryException {
        Layout ly = this.getLayout();

        ly.home.page(Home.USERS_ADD).onOpen(e -> {
            ly.openPage(userView.userAdd.getPanel(), Home.USERS_ADD);
        });
    }

    @Override
    protected void actions() throws InternalException {
        this.openListPage();
        this.openFormPage();
        this.submitUserAction();
        // Back
        userView.userTableList.backButton.onClick(e -> {
            try {
                this.getLayout().openHome();
            } catch (RegisteryException registeryException) {
                registeryException.printStackTrace();
            }
        });
    }
}
