package kz.zorg.chef.operation;

import kz.zorg.chef.entity.Dressing;
import kz.zorg.chef.entity.Ingredient;
import kz.zorg.chef.entity.Seasoning;
import kz.zorg.chef.entity.Vegetable;
import kz.zorg.chef.entity.Salad;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReadXMLFile {
    private static final String FILENAME = "salads.xml";
    private static final String VEGETABLE = "vegetable";
    private static final String DRESSING = "dressing";
    private static final String SEASONING = "seasoning";
    private static final String FILE_PATH = System.getProperty("user.dir")+"/src/main/resources";

    private enum tags{
        SALAD,
        ID,
        NAME,
        CALORICVALUE,
        WEIGHT,
        UNIT,
        PRELIMINARYPROCESSING,
        LENTEN,
        HOTLEVEL
    }

    public static Salad makeSalad(int id) {
        String name="";
        List<Ingredient> products = new ArrayList<Ingredient>();

        SAXBuilder saxBuilder = new SAXBuilder();
        File xmlFile = new File(FILE_PATH + File.separator + FILENAME);

        try {
            Document document = saxBuilder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> firstLevel = rootNode.getChildren(tags.SALAD.toString().toLowerCase());
            for (Element fl: firstLevel) {
                if (id == Integer.parseInt(fl.getAttribute(tags.ID.toString().toLowerCase()).getValue())) {
                    name = String.valueOf(fl.getAttribute(tags.NAME.toString().toLowerCase()).getValue());
                    List<Element> secondLevel = fl.getChildren();
                    for (Element sl : secondLevel) {
                        List<Element> thirdLevel = sl.getChildren();
                        for (Element tl : thirdLevel) {
                            switch (tl.getName()) {
                                case VEGETABLE:
                                    products.add(new Vegetable(
                                            tl.getChildText(tags.NAME.toString().toLowerCase()),
                                            Integer.parseInt(tl.getChildText(tags.CALORICVALUE.toString().toLowerCase())),
                                            Integer.parseInt(tl.getChildText(tags.WEIGHT.toString().toLowerCase())),
                                            tl.getChildText(tags.UNIT.toString().toLowerCase()),
                                            Boolean.valueOf(tl.getChildText(tags.PRELIMINARYPROCESSING.toString().toLowerCase()))
                                    ));
                                    break;
                                case DRESSING:
                                    products.add(new Dressing(
                                            tl.getChildText(tags.NAME.toString().toLowerCase()),
                                            Integer.parseInt(tl.getChildText(tags.CALORICVALUE.toString().toLowerCase())),
                                            Integer.parseInt(tl.getChildText(tags.WEIGHT.toString().toLowerCase())),
                                            tl.getChildText(tags.UNIT.toString().toLowerCase()),
                                            Boolean.valueOf(tl.getChildText(tags.LENTEN.toString().toLowerCase()))
                                    ));

                                    break;
                                case SEASONING:
                                    products.add(new Seasoning(
                                            tl.getChildText(tags.NAME.toString().toLowerCase()),
                                            Integer.parseInt(tl.getChildText(tags.CALORICVALUE.toString().toLowerCase())),
                                            Integer.parseInt(tl.getChildText(tags.WEIGHT.toString().toLowerCase())),
                                            tl.getChildText(tags.UNIT.toString().toLowerCase()),
                                            Integer.parseInt(tl.getChildText(tags.HOTLEVEL.toString().toLowerCase()))
                                    ));
                                    break;

                            }
                        }
                    }

                }
            }
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(ReadXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        Salad salad = new Salad(name, products);
        return salad;
    }


}
