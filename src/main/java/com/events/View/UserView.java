package com.events.View;

import com.framework.Services.Layout;
import com.framework.SwingModules.Form;
import com.framework.SwingModules.FormBuilder;
import com.framework.SwingModules.TableList;

import javax.swing.*;

public class UserView {
    static String[] tableColumn = {"ID", "Nom"};

    // Core
    // public PageBtn carPageBtn = new PageBtn("Louer une trotinette");

    public JTextField name = new JTextField(25);
    public JComboBox type = new JComboBox();

    // Components
    public TableList userTableList;
    public Form userAdd;

    public UserView(Layout ly) {
        userTableList = new TableList(tableColumn, ly, null);
        type.addItem("ClassicUser");
        type.addItem("Student");
        userAdd = this.CREATE(ly);
    }

    public Form CREATE(Layout ly) {
        FormBuilder builder = (new FormBuilder(true))
            .addField("Name", name)
            .enableBackButton(ly, null)
            .disableListBtn();

        return builder.create(null);
    }
}
