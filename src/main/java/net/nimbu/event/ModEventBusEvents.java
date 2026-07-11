package net.nimbu.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.nimbu.entity.ModEntities;
import net.nimbu.entity.client.MerCatModel;
import net.nimbu.entity.custom.MerCat;
import net.nimbu.mercats.MerCats;

@EventBusSubscriber(modid = MerCats.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(MerCatModel.LAYER_LOCATION, MerCatModel::createBodyMesh);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.MERCAT.get(), MerCat.createAttributes().build());
    }
}
