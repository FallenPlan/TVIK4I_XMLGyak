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
        Document document = documentBuilder.parse(xmlFile);
        //Normalizálás
        document.getDocumentElement().normalize();
        //Gyökérelement kiírása
        System.out.println(("Gyökérelement: " + document.getDocumentElement().getNodeName()));
        //Vásárló elementek nodeList-be illesztése
        NodeList nodeList = document.getElementsByTagName("vasarlo");
        System.out.println("----------------------------------------------");
        //Kiírás ciklusa, nodeList/nNode-on végighaladva az
        // elkészített algoritmusokkal kiírja a kért adatokat
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                String SzISz = element.getAttribute("SzISz");
                String OID = element.getAttribute("OID");
                //Adatok átadása a node-okba
                Node node1 = element.getElementsByTagName("nev").item(0);
                String nev = node1.getTextContent();
                Node node2 = element.getElementsByTagName("lakcim").item(0);
                String lakcim = node2.getTextContent();
                Node node3 = element.getElementsByTagName("bankszanlaSzam").item(0);
                String banksz = node3.getTextContent();
                //Kiíras
                System.out.println("Vásárló Szem. Igazolvány szám: " + SzISz);
                System.out.println("Neve: " + nev);
                System.out.println("Lakcíme: " + lakcim);
                System.out.println("Bankszámla száma: " + banksz + "\n");
                //Metódus hívások
                listOnlineAruhaz(document, OID);
                System.out.println("Raktár:\n" );
                listRaktar(document, SzISz);
                System.out.println(nev + " Terméke:\n" );
                listTermekek(document, SzISz);
                System.out.println("\n");
            }
        }
    }
    //Online Áruház adatainak kiírása az elõzõ módszer alapján
    public static void listOnlineAruhaz (Document document, String OID) {
        NodeList nodeList = document.getElementsByTagName("onlineAruhaz");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                if (element.getAttribute("OID").toString().equals(OID)) {
                    Node node1 = element.getElementsByTagName("nev").item(0);
                    String nev = node1.getTextContent();
                    Node node2 = element.getElementsByTagName("weboldal").item(0);
                    String weboldal = node2.getTextContent();
                    Node node3 = element.getElementsByTagName("tulaj").item(0);
                    String tulaj = node3.getTextContent();
                    System.out.println("Online áruház neve: " + nev);
                    System.out.println("Online áruház weboldala: " + weboldal);
                    System.out.println("Online áruház tulaja: " + tulaj + "\n");
                }
            }
        }
    }
    //Raktár adatainak kiírása az elõzõ módszer alapján
    public static void listRaktar (Document document, String SzISz) {
        NodeList nodeList = document.getElementsByTagName("raktar");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                if (element.getAttribute("SzISz").toString().equals(SzISz)) {
                    String RID = element.getAttribute("RID");
                    Node node1 = element.getElementsByTagName("uzemelteto").item(0);
                    String uzemelteto = node1.getTextContent();
                    Node node2 = element.getElementsByTagName("cim").item(0);
                    String cim = node2.getTextContent();
                    System.out.println("Raktár ID: " + RID);
                    System.out.println("Üzemeltető neve: " + uzemelteto);
                    System.out.println("Raktár címe: " + cim + "\n");
                }
            }
        }
    }
    //Termék(ek) adatainak kiírása az elõzõ módszer alapján
    public static void listTermekek (Document document, String SzISz) {
        NodeList nodeList = document.getElementsByTagName("termekek");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                if (element.getAttribute("SzISz").toString().equals(SzISz)) {
                    String cikkszam = element.getAttribute("cikkszam");
                    Node node1 = element.getElementsByTagName("afa").item(0);
                    String afa = node1.getTextContent();
                    Node node2 = element.getElementsByTagName("tipus").item(0);
                    String tipus = node2.getTextContent();
                    Node node3 = element.getElementsByTagName("ar").item(0);
                    String ar = node3.getTextContent();
                    Node node4 = element.getElementsByTagName("kiadasiEv").item(0);
                    String kiadasiEv = node4.getTextContent();
                    System.out.println("Termek cikkszáma: " + cikkszam);
                    System.out.println("Termék áfája: " + afa);
                    System.out.println("Termék tipusa: " + tipus);
                    System.out.println("Termék ára: " + ar);
                    System.out.println("Termék kiadási éve: " + kiadasiEv + "\n");
                }
            }
        }
    }
}
