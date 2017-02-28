package com.example.models.gameModels;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by G on 17/02/28.
 */
public class Settlement extends IntersectionUnit {
    private static final List<Settlement> aSettlements = new ArrayList<Settlement>();

    static {
        int maxSettlements = 16;
        for (int i = 0; i < maxSettlements; i++) {
            Settlement newSettlement = new Settlement();
        }
    }

    private Settlement() {
    }

    @Override
    public List<Settlement> getUnits() {
        List<Settlement> aCopy = new ArrayList<Settlement>();
        aCopy = aSettlements;
        return aCopy;
    }
}