package com.example.models.gameModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by G on 17/03/02.
 */
public class ResourceCard extends StealableCard{
    private static final Map<ResourceType, ArrayList<ResourceCard>> aResources = new HashMap<ResourceType, ArrayList<ResourceCard>>();
    public enum ResourceType implements StealableCard.StealableType {
        Wool,Lumber,Ore,Brick,Grain
    }

    static{
        int maxResourceCards = 19;
        for (ResourceType type : ResourceType.values()){
            ArrayList<ResourceCard> aResourceCards = new ArrayList<ResourceCard>();
            for (int i = 0; i < maxResourceCards; i++){
                aResourceCards.add(new ResourceCard(type));
            }
            aResources.put(type, aResourceCards);
        }
    }

    private ResourceCard(ResourceType pResourceType){
        super(pResourceType);
    }
}
