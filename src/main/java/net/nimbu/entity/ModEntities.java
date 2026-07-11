package net.nimbu.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.nimbu.entity.custom.MerCat;
import net.nimbu.mercats.MerCats;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MerCats.MODID);

    public static final Supplier<EntityType<MerCat>> MERCAT =
            ENTITY_TYPES.register("mer_cat", () -> EntityType.Builder.of(MerCat::new, MobCategory.CREATURE)
                    .sized(0.1f, 0.7f).build("mer_cat"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
