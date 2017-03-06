package com.example.models.gameModels;

import java.util.*;

/**
 * Created by G on 17/03/02.
 */
public class ResourceCard extends StealableCard{
    private static final Map<ResourceType, Queue<ResourceCard>> aResources = new HashMap<ResourceType, Queue<ResourceCard>>();
    public enum ResourceType implements StealableCard.StealableType {
        Wool,Lumber,Ore,Brick,Grain
    }

    static{
        int maxResourceCards = 19;
        for (ResourceType type : ResourceType.values()){
            LinkedList<ResourceCard> aResourceCards = new LinkedList<>();
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