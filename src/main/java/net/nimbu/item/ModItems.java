package net.nimbu.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nimbu.entity.ModEntities;
import net.nimbu.mercats.MerCats;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MerCats.MODID);

    public static final DeferredItem<Item> MERCAT_SPAWN_EGG = ITEMS.register("mer_cat_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.MERCAT, 0xFFD0D0, 0x444444, new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
