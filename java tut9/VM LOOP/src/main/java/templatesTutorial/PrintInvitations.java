package templatesTutorial;

import java.beans.XMLDecoder;
import java.io.*;
import java.util.ArrayList;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class PrintInvitations {

    public static void main(String[] args) throws Exception{
        XMLDecoder e = new XMLDecoder(new BufferedInputStream(new FileInputStream("friends.xml")));
        ArrayList<Person> persons = (ArrayList<Person>) e.readObject();
        e.close();

        VelocityContext context = new VelocityContext();
        Template template = Velocity.getTemplate("letter.vm");

        context.put("persons", persons);

        FileWriter out = new FileWriter("letters.html");
        template.merge(context, out);
        out.close();
    }
}