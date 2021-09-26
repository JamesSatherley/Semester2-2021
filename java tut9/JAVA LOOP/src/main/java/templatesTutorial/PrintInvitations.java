package templatesTutorial;

import java.beans.XMLDecoder;
import java.io.*;
import java.util.ArrayList;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class PrintInvitations {
    static ArrayList<Person> persons;
    static XMLDecoder e;

    public static void main(String[] args) {
        try {
            e = new XMLDecoder(new BufferedInputStream(new FileInputStream("friends.xml")));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        persons = (ArrayList<Person>) e.readObject();
        e.close();

        VelocityContext context = new VelocityContext();
        Template template = Velocity.getTemplate("letter.vm");

        FileWriter out = null;

        try {
            out = new FileWriter("Letters.html");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (Person p:persons) {
            context.put("person", p);
            template.merge(context, out);
        }

        try {
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
