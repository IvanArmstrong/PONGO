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
import javax.swing.JComponent;
import javax.imageio.ImageIO;
import java.io.File;

class Nungus extends JComponent {
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 400, 24);
        g.setColor(Color.RED);
        g.drawString("PONGOPONGOPONGOPONGOPONGOPONGOPONGOPONGOPONGOPONGO", 0, 16);
    }

    @Override
    public int getWidth() {
        return 400;
    }

    @Override
    public int getHeight() {
        return 24;
    }

    protected void paintComponent(Graphics g) {
        // Call paintComponent from parent class
        super.paintComponent(g);

        // Draw a square
        g.setColor(Color.RED);
        g.fillRect(0, 0, 50, 50);
        g.setColor(Color.BLACK);
        g.drawString("Square", 0, 0);
    }
}

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

class PongoIcon implements Icon {
//    final Image image = new File("C:/Users/IvanArmstrong/Pictures/monke.png")
    PongoIcon() {
        super();
    }
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
//        g.setColor(Color.BLUE);
//        g.fillRect(0, 0, 80, 24);
//        g.setColor(Color.RED);
//        g.drawString("PONGO", 0, 16);
    }

    @Override
    public int getIconWidth() {
        return 80;
    }

    @Override
    public int getIconHeight() {
        return 16;
    }
}

/**
 * This is the Designer-scope module hook.  The minimal implementation contains a startup method.
 */
public class ExperimentationDesignerHook extends AbstractDesignerModuleHook {

    // override additonal methods as requried
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private DesignerContext context;

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

        logger.info("AAAAAAAAAAA IT BURNS AAAAAAAAAAAAA");
        StatusBar bar = context.getStatusBar();
        bar.setMessage("AAAAAAAAAAA IT BURNS AAAAAAAAAAAAA");
        bar.addDisplay(new Nungus(), 0);
    }
}
