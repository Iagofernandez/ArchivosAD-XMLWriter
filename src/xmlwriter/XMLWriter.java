
package xmlwriter;

import serializacion2.Serializacion2;
import serializacion2.Product;
import java.io.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XMLWriter {

    
    public static void main(String[] args) throws FileNotFoundException, IOException, XMLStreamException, ClassNotFoundException {
       
        
        //clase de lectura de un fichero
        FileInputStream fichero = new FileInputStream("/home/oracle/Desktop/AD/Eje12/productos.txt");
        ObjectInputStream fichero1 = new ObjectInputStream(fichero);
        
        //Escritura en el archivo
        File xmlFich = new File("/home/oracle/Desktop/AD/Eje16/productos.xml");
        FileWriter xmlWrit = new FileWriter(xmlFich);
        //Escritura en un output stream, permite en tipo XML
       XMLOutputFactory xmlFactor = XMLOutputFactory.newInstance();
       XMLStreamWriter xmlWriter = xmlFactor.createXMLStreamWriter(xmlWrit);
       
       //Declaracion de que version se usara
       xmlWriter.writeStartDocument("1.0");
       //Elemento raiz
       xmlWriter.writeStartElement("Productos");
       
       //Se tuvieron que crear libreiras del proyecto seraializacion2
       // para poder leer todos los componentes correctamente
       Product prod = (Product)fichero1.readObject();
       
       int datos = 0;
       String codigo;
       String descricion;
       String precio;
       
       while(prod != null){
           
           xmlWriter.writeStartElement("Producto_" + Integer.toString(datos));
           xmlWriter.writeStartElement("Codigo");
           xmlWriter.writeCharacters(prod.getCodigo());
           xmlWriter.writeEndElement();
           xmlWriter.writeStartElement("Descricion");
           xmlWriter.writeCharacters(prod.getDescricion());
           xmlWriter.writeEndElement();
           xmlWriter.writeStartElement("Precio");
           xmlWriter.writeCharacters(Double.toString(prod.getPrecio()));
           xmlWriter.writeEndElement();
           xmlWriter.writeEndElement();
           
           
           prod = (Product)fichero1.readObject();
           
           
           
       }
       xmlWriter.writeEndElement();
       xmlWriter.writeEndDocument();
         xmlWriter.close();
         fichero1.close();
       }
   
    
}
