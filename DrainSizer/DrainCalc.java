package drainsizer;

import fixtures.Fixture;
import fixtures.Fixtures;

public class DrainCalc {

    /**
     * Iterates through all fixtures and sums up the total DFU for the building.
     * 
     * @param fixtures the Fixtures container holding all added fixture objects.
     * @return the total DFU for the building.
     */
    public double calculateBuildingDfu(Fixtures fixtures) {
        double totalDFU = 0.0;

        for (Fixture fixture : fixtures.getFixturesList()) {
            totalDFU += fixture.getTotalDFU(); 
        }

        //Log.debug("Total building DFU: " + totalDFU);
        return totalDFU;
    }

    public double calculateDrainSize(Fixtures fixtures) {
        double totalDfu = calculateBuildingDfu(fixtures);
        // Example logic: pipe size increases with DFU
        if (totalDfu <= 10) {
            return 1; // 1-inch pipe
        } else if (totalDfu <= 20) {
            return 2; // 2-inch pipe
        } else {
            return 3; // 3-inch pipe
        }
    }
}
