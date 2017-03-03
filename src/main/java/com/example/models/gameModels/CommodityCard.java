package com.example.models.gameModels;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by G on 17/03/02.
 */
public class CommodityCard extends StealableCard {
    private static final Map<CommodityType, ArrayList<CommodityCard>> aCommodities = new HashMap<CommodityType, ArrayList<CommodityCard>>();
    public enum CommodityType implements StealableCard.StealableType{
        Coin,Cloth,Paper
    }

    static{
        int maxCommodityCards = 12;
        for (CommodityType type : CommodityType.values()){
            ArrayList<CommodityCard> aCommodityCards = new ArrayList<CommodityCard>();
            for (int i = 0; i < maxCommodityCards; i++){
                aCommodityCards.add(new CommodityCard(type));
            }
            aCommodities.put(type, aCommodityCards);
        }
    }

    private CommodityCard(CommodityType pCommodityType){
        super(pCommodityType);
    }
}
