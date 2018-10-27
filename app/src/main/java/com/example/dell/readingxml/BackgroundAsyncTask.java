package com.example.dell.readingxml;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static com.example.dell.readingxml.MainActivity.context;

public class BackgroundAsyncTask
{
  //  ProgressDialog dialog = new ProgressDialog();

 /*   @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        dialog.dismiss();
        MainActivity.txtMsg.setText(result.toString());
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
*/
  //  @Override
    protected String doInBackground(String... params) {
        return useW3CParser(params);
    }// doInBackground

    private String useW3CParser(String... params) {
// params contains: xml-file-name followed by <element>s
// for example: "Golfers.xml", "Name", "Phone"
// CAUTION: XML is case-sensitive.
        int n = params.length; // total number of parameters
        String xmlFileName = params[0]; // xml file name
        String[] elementName = new String[n - 1]; // element names
        for (int i = 0; i < n - 1; i++) elementName[i] = params[i + 1];
        StringBuilder str = new StringBuilder();
        try {
            String kmlFile = xmlFileName;
            InputStream is = new FileInputStream(kmlFile);
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document document = docBuilder.parse(is);
            if (document == null) {
                Log.v("REALLY BAD!!!!", "document was NOT made by parser");
                return "BAD-ERROR";
            }


        // backgroundAsyncTa
        NodeList[] elementList = new NodeList[n];
        for (int i = 0; i < n - 1; i++) {
//make a collection of <elements> for each name in params[i+1]
            elementList[i] = document.getElementsByTagName(elementName[i]);
//dissect node elementList[i] looking for its enclosed attributes and text
            str.append(getTextAndAttributesFromNode(elementList[i], elementName[i]));
        }
    } catch(
    FileNotFoundException e)

    {
        Log.e("W3C Error", e.getMessage());
    } catch(
    ParserConfigurationException e)

    {
        Log.e("W3C Error", e.getMessage());
    } catch(
    SAXException e)

    {
        Log.e("W3C Error", e.getMessage());
    } catch(
    IOException e)

    {
        Log.e("W3C Error", e.getMessage());
    }
return str.toString();
}
// useW3cOrgDocumentBuilder
    private Object getTextAndAttributesFromNode(NodeList list, String strElementName) {
        StringBuilder str = new StringBuilder();
// dealing with the <strElementName> tag
        str.append("\n\nNodeList for: <" + strElementName + "> Tag");
        for (int i = 0; i < list.getLength(); i++) {
// extract TEXT enclosed inside <element> tags
            Node node = list.item(i);
            String text = node.getTextContent();
            str.append("\n " + i + ": " + text);
// get ATTRIBUTES inside the current element
            int size = node.getAttributes().getLength();
            for (int j = 0; j < size; j++) {
                String attrName = node.getAttributes().item(j).getNodeName();
                String attrValue = node.getAttributes().item(j).getNodeValue();
                str.append("\n attr. info-" + i + "-" + j + ": " + attrName
                        + " " + attrValue);
            }
        }
        return str;
    }//getAllDataFromNodeList
}// ActivityMain
