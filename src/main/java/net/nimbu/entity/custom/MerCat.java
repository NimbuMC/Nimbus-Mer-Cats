package net.nimbu.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;

public class MerCat extends Cat {


    public MerCat(EntityType<? extends Cat> entityType, Level level) {
        super(entityType, level);
    }
}
