/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swap.gerbilannotatorsample;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aksw.gerbil.transfer.nif.Document;
import org.aksw.gerbil.transfer.nif.Marking;
import org.aksw.gerbil.transfer.nif.Span;
import org.aksw.gerbil.transfer.nif.TurtleNIFDocumentCreator;
import org.aksw.gerbil.transfer.nif.TurtleNIFDocumentParser;
import org.aksw.gerbil.transfer.nif.data.NamedEntity;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 *
 * @author pierpaolo
 */
public class GerbilAnnotator extends ServerResource {

    private static final Logger LOG = Logger.getLogger(GerbilAnnotator.class.getName());

    private final TurtleNIFDocumentParser parser = new TurtleNIFDocumentParser();

    private final TurtleNIFDocumentCreator creator = new TurtleNIFDocumentCreator();

    @Post
    public String accept(Representation request) {
        Reader inputReader;
        try {
            inputReader = request.getReader();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Exception to read request", ex);
            return "";
        }
        Document document;
        try {
            document = parser.getDocumentFromNIFReader(inputReader);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Exception to read request.", ex);
            return "";
        }
        String text = document.getText();
        //TO DO annotate text
        List<Span> esl = document.getMarkings(Span.class);
        // Annotator should return marking
        List<Marking> entities=new ArrayList<>();
        // example of marking (start, length, uri)
        entities.add(new NamedEntity(43, 10, "uri"));
        document.setMarkings(entities);
        //return document
        String nifDocument = creator.getDocumentAsNIFString(document);
        return nifDocument;
    }

}
