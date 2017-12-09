package ua.edu.sumdu.ta.zhurba.pr8;

import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class XMLTest {

    @Test
    public void readXml() throws IOException, SAXException, ParserConfigurationException {


        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        TaskXMLSerializer myXml = new TaskXMLSerializer();
        parser.parse("src\\test\\resources\\tasks.xml", myXml);
    }


}
