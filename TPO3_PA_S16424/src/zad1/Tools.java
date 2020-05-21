///**
// *
// *  @author Podolska Anna S16424
// *
// */
//
package zad1;
//
////

import io.jenkins.plugins.casc.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

//
public class Tools {
    public Tools() {}

    static Options createOptionsFromYaml(String fileName) throws Exception {

        Yaml yaml = new Yaml();
        InputStream file = new FileInputStream(fileName);
        Object person = yaml.load(file);
        Map<String, Object> things = (Map<String, Object>) person;

        Options option = new Options(things.get("host").toString(), (int) things.get("port"),
                (boolean) things.get("concurMode"), (boolean) things.get("showSendRes"),
                (Map<String, List<String>>) things.get("clientsMap"));

        return option;
    }
}
