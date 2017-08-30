/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swap.gerbilannotatorsample;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.restlet.Component;
import org.restlet.data.Protocol;

/**
 *
 * @author pierpaolo
 */
public class GerbilService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Create a new Component.
            Component component = new Component();

            // Add a new HTTP server listening.
            component.getServers().add(Protocol.HTTP, args[0], Integer.parseInt(args[1]));

            // Attach the sample application.
            component.getDefaultHost().attach("/gerbil", new GerbilApplication());

            // Start the component.
            component.start();
        } catch (Exception ex) {
            Logger.getLogger(GerbilService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
