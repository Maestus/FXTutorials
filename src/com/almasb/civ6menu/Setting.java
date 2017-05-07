package com.almasb.civ6menu;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Setting {

	File config = new File(System.getProperty("user.dir") + "/configuration.xml");
	Element haut, bas, gauche, droite, attaque;
	
	
	public Setting(){
	    try {

	    	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	
			if(!config.exists()) { 
				
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("configuration");
				doc.appendChild(rootElement);
				
				haut = doc.createElement("Haut");
				rootElement.appendChild(haut);
				
				haut.setAttribute("key", "z");
				
				bas = doc.createElement("Bas");
				rootElement.appendChild(bas);
				
				bas.setAttribute("key", "s");
				
				gauche = doc.createElement("Gauche");
				rootElement.appendChild(gauche);
	
				gauche.setAttribute("key", "q");
	
				droite = doc.createElement("Droite");
				rootElement.appendChild(droite);
	
				droite.setAttribute("key", "d");
	
				attaque = doc.createElement("Attaquer");
				rootElement.appendChild(attaque);
	
				attaque.setAttribute("key", "p");
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(config);
				
				transformer.transform(source, result);
	
			} else {
			
				Document doc = docBuilder.parse(config);
				
				doc.getDocumentElement().normalize();
		
				Node root = doc.getDocumentElement();
				
				NodeList nList = root.getChildNodes();
					
				haut = (Element) nList.item(0);
				
				bas = (Element) nList.item(1);
				
				gauche = (Element) nList.item(2);
				
				droite = (Element) nList.item(3);
				
				attaque = (Element) nList.item(4);
					
				for (int temp = 0; temp < nList.getLength(); temp++) {
	
					Node nNode = nList.item(temp);
	
					System.out.println("Element :" + nNode.getNodeName());
	
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						
						Element eElement = (Element) nNode;
	
						System.out.println("value : " + eElement.getAttribute("key"));
					}
				}
				
			}
	    }catch(Exception e){
				e.printStackTrace();
		}
	}
	
	
	public void remap(SettingItem items[]){
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(config);
			
			Node root = doc.getDocumentElement();
			
			NodeList nList = root.getChildNodes();
				
			haut = (Element) nList.item(0);
	
			NamedNodeMap attr = haut.getAttributes();
			Node nodeAttr = attr.getNamedItem("key");
			nodeAttr.setTextContent(items[0].value.getText());
	
			bas = (Element) nList.item(1);
	
			attr = bas.getAttributes();
			nodeAttr = attr.getNamedItem("key");
			nodeAttr.setTextContent(items[1].value.getText());
				
			gauche = (Element) nList.item(2);
				
			attr = gauche.getAttributes();
			nodeAttr = attr.getNamedItem("key");
			nodeAttr.setTextContent(items[2].value.getText());
				
			droite = (Element) nList.item(3);
			
			attr = droite.getAttributes();
			nodeAttr = attr.getNamedItem("key");
			nodeAttr.setTextContent(items[3].value.getText());
			
			attaque = (Element) nList.item(4);
			
			attr = attaque.getAttributes();
			nodeAttr = attr.getNamedItem("key");
			nodeAttr.setTextContent(items[4].value.getText());
							
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(config);
			transformer.transform(source, result);
	
	   } catch (Exception e) {
		e.printStackTrace();
	   }
	}
	
}
