package com.example.models.gameModels;

import java.util.HashMap;

public class PlayerTrade implements Trade{
    public PlayerTrade(Player pTrader1, Player pTrader2,
    HashMap<StealableCard.Resource, Integer> pResources1,
    HashMap<StealableCard.Resource, Integer> pResources2,
    HashMap<StealableCard.Commodity, Integer> pCommodities1,
    HashMap<StealableCard.Commodity, Integer> pCommodities2){
        for (StealableCard.Resource resource : StealableCard.Resource.values()){
            pTrader1.removeResource(resource, pResources1.get(resource));
            pTrader2.removeResource(resource, pResources2.get(resource));
            pTrader1.addResource(resource, pResources2.get(resource));
            pTrader2.addResource(resource, pResources1.get(resource));
        }
        for (StealableCard.Commodity commodity : StealableCard.Commodity.values()){
            pTrader1.removeCommodity(commodity, pCommodities1.get(commodity));
            pTrader2.removeCommodity(commodity, pCommodities2.get(commodity));
            pTrader1.addCommodity(commodity, pCommodities2.get(commodity));
            pTrader2.addCommodity(commodity, pCommodities1.get(commodity));
        }
    }
}
