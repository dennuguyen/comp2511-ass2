package unsw.gloriaromanus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import unsw.gloriaromanus.util.Util;

public class World {

    // private enum ProvinceName {
    // BRITANNIA,
    // LUGDUNENSIS,
    // BELGICA,
    // GERMANIA_INFERIOR,
    // AQUITANIA,
    // GERMANIA_SUPERIOR,
    // ALPES_GRAIAE_ET_POENINAE,
    // XI,
    // ALPES_COTTIAE,
    // ALPES_MARITIMAE,
    // IX,
    // NARBONENSIS,
    // TARRACONENSIS,
    // BAETICA,
    // LUSITANIA,
    // RAETIA,
    // NORICUM,
    // X,
    // VIII,
    // VII,
    // VI,
    // IV,
    // V,
    // I,
    // III,
    // SICILIA,
    // PANNONIA_SUPERIOR,
    // PANNONIA_INFERIOR,
    // DALMATIA,
    // II,
    // SARDINIA_ET_CORSICA,
    // MOESIA_SUPERIOR,
    // DACIA,
    // MOESIA_INFERIOR,
    // THRACIA,
    // MACEDONIA,
    // ACHAIA,
    // BITHYNIA_ET_PONTUS,
    // CILICIA,
    // CRETA_ET_CYRENE,
    // CYPRUS,
    // AEGYPTUS,
    // ARABIA,
    // IUDAEA,
    // SYRIA,
    // AFRICA_PROCONSULARIS,
    // NUMIDIA,
    // MAURETANIA_CAESARIENSIS,
    // MAURETANIA_TINGITANA,
    // GALATIA_ET_CAPPADOCIA,
    // LYCIA_ET_PAMPHYLIA,
    // ASIA,
    // ARMENIA_MESOPOTAMIA,
    // }

    private Map<String, Map<String, Boolean>> matrix; // adjacency matrix
    private Map<String, Province> provinces; // provinces

    public World(String JSONFile) {
        this.createWorld(JSONFile);
        this.createProvinces(this.matrix.keySet());
        // System.out.println(this.provinces);
    }

    private void createWorld(String JSONFile) {
        System.out.println("Creating world...");
        JSONObject jsonObject = Util.parseJsonFile(JSONFile);
        try {
            this.matrix = new ObjectMapper().readValue(jsonObject.toString(), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createProvinces(Set<String> provinceKeys) {
        System.out.println("Initialising provinces...");
        for (String provinceKey : provinceKeys)
            this.provinces.put(provinceKey, new Province(provinceKey));
    }
}
