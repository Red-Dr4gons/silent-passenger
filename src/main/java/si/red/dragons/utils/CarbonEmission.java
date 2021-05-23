package si.red.dragons.utils;

public class CarbonEmission {
    private CarbonEmission(){

    }

    public static float calcualteEmission(String fuelType, Float consumption, Float kilometers){
        Float kgsPerLiter;
        if(fuelType.equals("petrol")){
            kgsPerLiter = 2.39f;
        }
        else if (fuelType.equals("electric")){
            return 0;
        }
        else{
            kgsPerLiter = 2.62f;
        }

        return (kilometers/100)*consumption*kgsPerLiter;
    }
}
