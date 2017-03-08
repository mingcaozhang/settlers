package com.example.models.gameModels;

import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Created by G on 17/03/02.
 */
public class CommodityCard extends StealableCard {
    private static final HashMap<CommodityType, Queue<CommodityCard>> aCommodities = new HashMap<CommodityType, Queue<CommodityCard>>();
    public enum CommodityType implements StealableCard.StealableType{
        Coin,Cloth,Paper
    }

    static{
        int maxCommodityCards = 12;
        for (CommodityType type : CommodityType.values()){
            Queue<CommodityCard> aCommodityCards = new LinkedList<CommodityCard>();
            for (int i = 0; i < maxCommodityCards; i++){
                aCommodityCards.add(new CommodityCard(type));
            }
            aCommodities.put(type, aCommodityCards);
        }
    }

    public static HashMap<CommodityType, Queue<CommodityCard>> getCommodities(){
        return aCommodities;
    }

    private CommodityCard(CommodityType pCommodityType){
        super(pCommodityType);
    }
}
