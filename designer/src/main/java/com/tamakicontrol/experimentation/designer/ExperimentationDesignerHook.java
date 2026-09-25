package com.tamakicontrol.experimentation.designer;

import com.inductiveautomation.ignition.common.licensing.LicenseState;
import com.inductiveautomation.ignition.designer.model.AbstractDesignerModuleHook;
import com.inductiveautomation.ignition.designer.model.DesignerContext;
import com.inductiveautomation.ignition.designer.gui.StatusBar;

import com.jidesoft.action.CommandBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.util.List;

import java.awt.*;
import javax.swing.*;

class TestAction extends AbstractAction {
    private final Logger logger;
    private final DesignerContext context;
    TestAction(Logger logger, DesignerContext context) throws MalformedURLException {
//        super("PONGO", new PongoIcon());
        super("PONGO", new ImageIcon("C:/Users/IvanArmstrong/Pictures/monke.png"));
        this.logger = logger;
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 100; i++) {
            logger.error("PONGO");
        }
        StatusBar statusBar = context.getStatusBar();
        statusBar.setErrorMessage("PONGO KNOWS ALL. PONGO SEES ALL.");
    }
}

/**
 * This is the Designer-scope module hook.  The minimal implementation contains a startup method.
 */
public class ExperimentationDesignerHook extends AbstractDesignerModuleHook {

    // override additonal methods as requried
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private DesignerContext context;

    private DoomHud hud;

    @Override
    public List<CommandBar> getModuleToolbars() {
        List<CommandBar> commandBars = super.getModuleToolbars();
        CommandBar bar = new CommandBar();
        try {
            bar.add(new TestAction(logger, context));
        } catch (MalformedURLException e) {
            logger.error("PONGO IS TOO STRONG: {}", String.valueOf(e));
        }
        bar.setKey("Thingus");
        commandBars.add(bar);

        return commandBars;
    }

    @Override
    public void startup(DesignerContext context, LicenseState activationState) throws Exception {
        this.context = context;
        logger.info("Experimentation Module Initializing");

        // Set up status bar
        StatusBar bar = context.getStatusBar();
        bar.setPreferredSize(new Dimension(1920, 173));

        // Initialize hud
        hud = new DoomHud(logger);
        bar.addDisplay(hud, 0);

        // Add hud as a mouse motion event listener
        Toolkit tk = Toolkit.getDefaultToolkit();
        tk.addAWTEventListener(hud, AWTEvent.MOUSE_MOTION_EVENT_MASK);
    }

    @Override
    public void shutdown() {
        Toolkit.getDefaultToolkit().removeAWTEventListener(hud);
    }
}
