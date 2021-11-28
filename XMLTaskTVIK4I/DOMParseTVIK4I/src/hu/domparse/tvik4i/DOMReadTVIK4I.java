package hu.domparse.tvik4i;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMReadTVIK4I {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        //Létrehozás
        File xmlFile = new File("src/hu/domparse/tvik4i/XMLTVIK4I.xml");
        //Building és konvertálás
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(xmlFile);
        //Normalizálás
        doc.getDocumentElement().normalize();
        //Gyökérelem kiírása
        System.out.println(("Gyökérelem: " + doc.getDocumentElement().getNodeName()));
        //Vásárló elemek nodeList-be illesztése
        NodeList nodeList = doc.getElementsByTagName("vasarlo");
        System.out.println("----------------------------------------------");
        //Kiírás ciklusa, nodeList/nNode-on végighaladva az
        // elkészített algoritmusokkal kiírja a kért adatokat
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                String SzISz = element.getAttribute("SzISz");
                //Adatok átadása a node-okba
                Node node1 = element.getElementsByTagName("nev").item(0);
                String nev = node1.getTextContent();
                Node node2 = element.getElementsByTagName("lakcim").item(0);
                String lakcim = node1.getTextContent();
                Node node3 = element.getElementsByTagName("bankszanlaSzam").item(0);
                String banksz = node1.getTextContent();
                //Kiíras
                System.out.println("Vásárló Szem. Igazolvány szám: " + SzISz);
                System.out.println("Neve: " + nev);
                System.out.println("Lakcíme: " + lakcim);
                System.out.println("Bankszámla száma: " + banksz);
                //Metódus hívások
            }
        }
    }
}
