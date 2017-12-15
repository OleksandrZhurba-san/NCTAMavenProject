package ua.edu.sumdu.ta.zhurba.pr8;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
//import ua.edu.sumdu.ta.zhurba.pr6.AbstractTaskList;
//import ua.edu.sumdu.ta.zhurba.pr6.ArrayTaskList;
//import ua.edu.sumdu.ta.zhurba.pr6.Task;

import java.io.File;
import java.io.IOException;

public class TaskXMLSerializer {


    public AbstractTaskList load(String path) {
        AbstractTaskList taskList;
        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            if (doc.getDocumentElement().getNodeName().contains("tasks")) {
                taskList = new ArrayTaskList();

                NodeList nodeList = doc.getElementsByTagName("task");
                Task[] ts = new Task[nodeList.getLength()];
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node nNode = nodeList.item(i);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) nNode;
//                        System.out.println(element.getAttribute("time"));
//                        System.out.println(element.getTextContent());
                        int time = Integer.parseInt(element.getAttribute("time"));
                        String title = element.getTextContent();
                        boolean isActive = Boolean.parseBoolean(element.getAttribute("active"));
                        if (element.getAttribute("start").trim().isEmpty()) {
                            ts[i] = new Task(title, time);

                        } else {
                            int startTime = Integer.parseInt(element.getAttribute("start"));
                            int endTime = Integer.parseInt(element.getAttribute("end"));
                            int repeatTime = Integer.parseInt(element.getAttribute("repeat"));
                            ts[i] = new Task(title, startTime, endTime, repeatTime);
                        }
                        ts[i].setActive(isActive);
                        taskList.add(ts[i]);
                        System.out.println(taskList.getTask(i).toString());

                    }
                }
                return taskList;
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(AbstractTaskList taskList, String file){

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("tasks");
            doc.appendChild(root);

            for (int i = 0; i < taskList.size(); i++) {

                Element task = doc.createElement("task");

                if (taskList.getTask(i).isRepeated()){
                    String isActive = String.valueOf(taskList.getTask(i).isActive());
                    String time = String.valueOf(taskList.getTask(i).getTime());
                    String startTime = time;
                    String endTime = String.valueOf(taskList.getTask(i).getEndTime());
                    String repeat = String.valueOf(taskList.getTask(i).getRepeatInterval());
                    String title = taskList.getTask(i).getTitle();
                    String isRepeated = String.valueOf(taskList.getTask(i).isRepeated());

                    task.setTextContent(title);
                    task.setAttribute("active",isActive);
                    task.setAttribute("time",time);
                    task.setAttribute("start",startTime);
                    task.setAttribute("end",endTime);
                    task.setAttribute("repeat",repeat);
                    task.setAttribute("repeated",isRepeated);

                } else{
                    String isActive = String.valueOf(taskList.getTask(i).isActive());
                    String time = String.valueOf(taskList.getTask(i).getTime());
                    String startTime = "";
                    String endTime = startTime;
                    String repeat = String.valueOf(taskList.getTask(i).getRepeatInterval());
                    String isRepeated = String.valueOf(taskList.getTask(i).isRepeated());
                    String title = taskList.getTask(i).getTitle();

                    task.setTextContent(title);
                    task.setAttribute("active",isActive);
                    task.setAttribute("time",time);
                    task.setAttribute("start",startTime);
                    task.setAttribute("end",endTime);
                    task.setAttribute("repeat",repeat);
                    task.setAttribute("repeated",isRepeated);
                }

                root.appendChild(task);
//                System.out.println(task.getAttribute("active") + task.getTextContent());
            }

            String path = "src\\test\\resources\\" + file;
            File fXmlFile = new File(path);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            Result output = new StreamResult(fXmlFile);
            Source input = new DOMSource(doc);

            transformer.transform(input, output);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}