package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.MapModel;

public class ActionBuild implements ActionListener {
    private MapModel map;

    public ActionBuild(GameCtrl ctrl) {
        map = ctrl.GetGameModel().GetMapModel();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            System.out.println("clicked Build button");
    }

}
