package com.app.View;

import com.app.Framework.Services.IEntity;
import com.app.Framework.Services.Layout;
import com.app.View.SwingModules.FormBuilder;
import com.app.Exceptions.InternalException;
import com.app.View.SwingModules.Form;
import com.app.View.SwingModules.TableList;

import javax.swing.*;
import java.util.List;

public class ScooterView {
    static String[] tableColumn = {"Référence", "Nom", "PrixHT", "PrixTTC", "Quantitée"};

    public String intitule = "Trotinette";
    public double prixPerMinutes = 2.00;
    
    public JComboBox user = new JComboBox();

    // Components
    public TableList productTableList;
    public Form serviceAdd;
    public Form serviceTerminate;

    public ScooterView(Layout ly) throws InternalException {
        productTableList = new TableList(tableColumn, ly, null);
        serviceAdd = this.startForm(ly);
        serviceTerminate = this.terminateForm(ly);
    }

    public void fillForm(List<IEntity> builders) {
        user.removeAllItems();
        user.addItem(FormBuilder.NO_SELECT);
        // The solution
        for (IEntity builder : builders) {
        	user.addItem(builder);
        }
    }

    public Form terminateForm(Layout ly) {
        FormBuilder builder = (new FormBuilder(true))
                .addField("User", user)
                .enableBackButton(ly, null)
                .disableListBtn();

        return builder.create(null);
    }

    public Form startForm(Layout ly) {
        FormBuilder builder = (new FormBuilder(true))
            .addField("User", user)
            .enableBackButton(ly, null)
            .disableListBtn();

        return builder.create(null);
    }
}
