package net.nimbu.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.CatModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CatCollarLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.nimbu.entity.custom.MerCat;
import net.nimbu.mercats.MerCats;

import java.util.Iterator;

public class MerCatRenderer extends MobRenderer<MerCat, MerCatModel<MerCat>> {
    public MerCatRenderer(EntityRendererProvider.Context context) {
        super(context, new MerCatModel<>(context.bakeLayer(MerCatModel.LAYER_LOCATION)), 0.4f);
        //this.addLayer(new CatCollarLayer(this, p_173943_.getModelSet()));
    }

    public ResourceLocation getTextureLocation(MerCat entity) {

        return ResourceLocation.fromNamespaceAndPath(MerCats.MODID, "textures/entity/mer_cat.png");
    }

//    protected void scale(Cat livingEntity, PoseStack poseStack, float partialTickTime) {
//        super.scale(livingEntity, poseStack, partialTickTime);
//        poseStack.scale(0.8F, 0.8F, 0.8F);
//    }

    protected void setupRotations(MerCat entity, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        super.setupRotations(entity, poseStack, bob, yBodyRot, partialTick, scale);
        float f = entity.getLieDownAmount(partialTick);
        if (f > 0.0F) {
            poseStack.translate(0.4F * f, 0.15F * f, 0.1F * f);
            poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.rotLerp(f, 0.0F, 90.0F)));
            BlockPos blockpos = entity.blockPosition();
            Iterator var9 = entity.level().getEntitiesOfClass(Player.class, (new AABB(blockpos)).inflate(2.0, 2.0, 2.0)).iterator();

            while (var9.hasNext()) {
                Player player = (Player) var9.next();
                if (player.isSleeping()) {
                    poseStack.translate(0.15F * f, 0.0F, 0.0F);
                    break;
                }
            }
        }

    }

    @Override
    public void render(MerCat entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

        if(entity.isBaby()){
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else{
            poseStack.scale(1f,1f,1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}