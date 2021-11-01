package domTVIK4I1026;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomWriteTVIK4I {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();

        Element root = doc.createElementNS("domkprlnb", "users");
        doc.appendChild(root);

        root.appendChild(createUser(doc, "1", "Bence", "Csomor", "student"));
        root.appendChild(createUser(doc, "2", "Pista", "Kiss", "festo"));
        root.appendChild(createUser(doc, "3", "Erzsi", "Kaszas", "kasszas"));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http//:xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);

        File myFile = new File("user1.xml");

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(myFile);

        transf.transform(source, console);
        transf.transform(source, file);

        private static Node createUser(Document doc, String id, String firstName, String lastName, String profession) {
            Element user = doc.createElement("user");

            user.setAttribute("id", id);
            user.appendChild(createUserElement(doc, "firstname", firstName));
            user.appendChild(createUserElement(doc, "lastname", lastName));
            user.appendChild(createUserElement(doc, "profession", profession));

            private static  Node createUserElement(Document do, String name, String value) {

                Element
            }
        }
    }

}
