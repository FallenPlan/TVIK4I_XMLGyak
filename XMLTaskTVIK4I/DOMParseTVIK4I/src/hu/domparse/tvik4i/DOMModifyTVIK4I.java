package hu.domparse.tvik4i;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DOMModifyTVIK4I {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
        //Az eredeti XML fájl és a megváltoztatott xml fájl megadása
        File xmlFile = new File("src/hu/domparse/tvik4i/XMLTVIK4I.xml");
        File xmlFileMODIFIED = new File("src/hu/domparse/tvik4i/XMLTVIK4IMODIFIED.xml");
        //Scanner nyitása a  beolvasáshoz
        Scanner sc = new Scanner(System.in);
        //Builder + konvertálás
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        //Normalizálás
        document.getDocumentElement().normalize();
        //A vásárló elemek kiválasztása.
        NodeList nodeList = document.getElementsByTagName("vasarlo");
        NodeList nodeList2 = document.getElementsByTagName("termekek");
//        NodeList nodeList3 = document.getElementsByTagName("vasarlo");
        //ciklus a változtatásokhoz
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            Node nNode2 = nodeList2.item(i);
            Element element = (Element) nNode;
            Element element2 = (Element) nNode2;
            //Név bekérése
            Node node1 = element.getElementsByTagName("nev").item(0);
            String nev = node1.getTextContent();
            Node node2 = element2.getElementsByTagName("tipus").item(0);
            String tipus = node2.getTextContent();
            Node node3 = element2.getElementsByTagName("ar").item(0);
            String ar = node3.getTextContent();
            //Átadás a node-nak
            System.out.println("A vásárló jelenlegi neve:" + nev);
            System.out.println("Add meg a vásárló új nevét: \n");
            //Bekérés billentyûzeten
            String modifiedname = sc.nextLine();
            //Beállítás node-on keresztül
            node1.setTextContent(modifiedname);

            //Termék típusának és árának módosítása
            System.out.println("Termék jelenlegi típusa:" + tipus);
            System.out.println("Add meg a termék új típusát:");
            String newTipus = sc.nextLine();
            node2.setTextContent(newTipus);
            System.out.println("Termék jelenlegi ára:" + ar);
            System.out.println("Add meg a termék új árát:");
            String newAr = sc.nextLine();
            node3.setTextContent(newAr);

        }

        //Scanner zárása
        sc.close();
        //transformer és domsource használatával változtatjuk a fájlt
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        //A változtatás a result-ba került
        StreamResult result = new StreamResult(xmlFileMODIFIED);
        //Beírásra kerül a módosított fájlba a módosítás
        transformer.transform(source, result);
    }
}
