package com.app.View;

import com.app.Services.IEntity;
import com.app.Services.Layout;
import com.app.View.SwingModules.FormBuilder;
import com.app.Controller.ServiceController;
import com.app.Exceptions.InternalException;
import com.app.View.SwingModules.Form;
import com.app.View.SwingModules.TableList;
import com.app.View.SwingModules.PageBtn;

import javax.swing.*;
import java.util.List;

public class ServiceView {
    static String[] tableColumn = {"Référence", "Nom", "PrixHT", "PrixTTC", "Quantitée"};

    // Core
    public PageBtn carPageBtn = new PageBtn("Louer une trotinette");

    public String intitule = "Trotinette";
    public double prixHT = 2.00;
    
    public JComboBox user = new JComboBox();

    // Components
    public TableList productTableList;
    public Form serviceAdd;

    public ServiceView(Layout ly) throws InternalException {

        productTableList = new TableList(tableColumn, ly, null);

        serviceAdd = this.CREATE(ly);
    }

    public void fillForm(List<IEntity> builders) {
        user.removeAllItems();
        user.addItem(FormBuilder.NO_SELECT);
        // The solution
        for (IEntity builder : builders) {
        	user.addItem(builder);
        }
    }

    public Form CREATE(Layout ly) {
        FormBuilder builder = (new FormBuilder(true))
            .addField("User", user)
            .enableBackButton(ly, null)
            .disableListBtn();

        return builder.create(null);
    }
}
