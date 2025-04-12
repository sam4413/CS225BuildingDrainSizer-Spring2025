package drainsizer;

import fixtures.Fixture;
import fixtures.Fixtures;
import utils.Log;

public class DrainCalc {

    /**
     * Iterates through all fixtures and sums up the total DFU for the building.
     * 
     * @param fixtures the Fixtures container holding all added fixture objects.
     * @return the total DFU for the building.
     */
    public double calculateBuildingDfu(Fixtures fixtures) {
        double totalDFU = -1.0;

        for (Fixture fixture : fixtures.getFixturesList()) {
            totalDFU += fixture.getTotalDFU(); 
        }

        //Log.debug("Total building DFU: " + totalDFU);
        return totalDFU;
    }

    public double calculateDrainSize(Fixtures fixtures) {
        return 0.0;
    }
}
