package hu.domparse.tvik4i;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMQueryTVIK4I {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        try{
        //Létrehozás
        File xmlFile = new File("src/hu/domparse/tvik4i/XMLTVIK4I.xml");
        //Building és konvertálás
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        //Normalizálás
        document.getDocumentElement().normalize();
        //Gyökérelement kiírása
        System.out.println(("Gyökérelement: " + document.getDocumentElement().getNodeName()));
        //Vásárló elementek nodeList-be illesztése
        NodeList nodeList = document.getElementsByTagName("vasarlo");
        NodeList nodeList2 = document.getElementsByTagName("onlineAruhaz");
        System.out.println("----------------------------------------------");
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node nNode = nodeList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                System.out.print("\n" + (temp+1) + ". Vásárló:");
                System.out.println(element.getAttribute("vasarlo"));
                NodeList vasarloNameList = element.getElementsByTagName("nev");

                for (int count = 0; count < vasarloNameList.getLength(); count++) {
                    Node node1 = vasarloNameList.item(count);

                    if (node1.getNodeType() == node1.ELEMENT_NODE) {
                        Element vasarlo = (Element) node1;
                        System.out.print("neve : ");
                        System.out.println(vasarlo.getTextContent());
                    }
                }
            }
        }
            for (int temp = 0; temp < nodeList2.getLength(); temp++) {
                Node nNode = nodeList2.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    System.out.print("\n" + (temp+1) + ". Online Áruház:");
                    System.out.println(element.getAttribute("onlineAruhaz"));
                    NodeList oAruhazNameList = element.getElementsByTagName("nev");
                    NodeList oAruhazWebList = element.getElementsByTagName("weboldal");

                    for (int count = 0; count < oAruhazNameList.getLength(); count++) {
                        Node node1 = oAruhazNameList.item(count);
                        Node node2 = oAruhazWebList.item(count);

                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element onlineAruhaz = (Element) node1;
                            System.out.print("neve : ");
                            System.out.println(onlineAruhaz.getTextContent());
                        }
                        if (node2.getNodeType() == node2.ELEMENT_NODE) {
                            Element onlineAruhaz = (Element) node2;
                            System.out.print("webcíme : ");
                            System.out.println(onlineAruhaz.getTextContent());
                        }
                    }
                }
            }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
