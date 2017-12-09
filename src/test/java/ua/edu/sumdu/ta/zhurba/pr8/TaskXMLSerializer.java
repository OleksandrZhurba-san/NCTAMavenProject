package ua.edu.sumdu.ta.zhurba.pr8;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.edu.sumdu.ta.zhurba.pr4.Task;
import ua.edu.sumdu.ta.zhurba.pr4.AbstractTaskList;
import ua.edu.sumdu.ta.zhurba.pr4.ArrayTaskList;

public class TaskXMLSerializer extends DefaultHandler {

    AbstractTaskList myTaskList;
    Task myTask;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if ("task".equals(qName)){
            myTask =  new Task("",Integer.parseInt(attributes.getValue("time")));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void startDocument() throws SAXException {
        myTaskList = new ArrayTaskList();
        myTaskList.add(myTask);
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}