package si.red.dragons.utils;

public class CarbonEmission {
    private CarbonEmission(){

    }

    public static float calcualteEmission(String fuel_type, Float consumption, Float kilometers){
        Float kgsPerLiter;
        if(fuel_type == "petrol"){
            kgsPerLiter = 2.39f;
        }
        else{
            kgsPerLiter = 2.62f;
        }

        return (kilometers/100)*consumption*kgsPerLiter;
    }
}
