package com.example.models.gameModels;

/**
 * Created by G on 17/03/03.
 */
public class TradeCard extends ProgressCard {
    public enum TradeType implements ProgresssType{
        CommercialHarbour, MasterMerchant, Merchant,MerchantFleet, ResourceMonopoly, TradeMonopoly
    }
    public TradeCard(TradeType pTradeType,String pDescription){super(pTradeType,pDescription);}
}
