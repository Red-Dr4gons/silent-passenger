package si.red.dragons.utils;

public class Trees {
    private Trees(){}

    public static float treesFromEmissions(float CO2kg){
        float CO2tonnes = CO2kg/1000;

        float trees = (CO2tonnes/144.64f)*2500;
        return trees;
    }
}
