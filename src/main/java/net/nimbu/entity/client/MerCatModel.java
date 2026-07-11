package net.nimbu.entity.client;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.nimbu.entity.custom.MerCat;
import net.nimbu.mercats.MerCats;

import javax.swing.text.html.parser.Entity;

public class MerCatModel<T extends MerCat> extends OcelotModel<T> {

    private float lieDownAmount;
    private float lieDownAmountTail;
    private float relaxStateOneAmount;

    protected final ModelPart tail3;

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MerCats.MODID, "mer_cat"), "main");

//    private final ModelPart allTail;
//    private final ModelPart tail;
//    private final ModelPart allTail2;
//    private final ModelPart tail2;
//    private final ModelPart tail3;


    public MerCatModel(ModelPart root) {
        super(root);
        this.tail3 = root.getChild("tail3");
    }

    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
        this.lieDownAmount = entity.getLieDownAmount(partialTick);
        this.lieDownAmountTail = entity.getLieDownAmountTail(partialTick);
        this.relaxStateOneAmount = entity.getRelaxStateOneAmount(partialTick);
        if (this.lieDownAmount <= 0.0F) {
            this.head.xRot = 0.0F;
            this.head.zRot = 0.0F;
            this.leftFrontLeg.xRot = 0.0F;
            this.leftFrontLeg.zRot = 0.0F;
            this.rightFrontLeg.xRot = 0.0F;
            this.rightFrontLeg.zRot = 0.0F;
            this.rightFrontLeg.x = -1.2F;
            this.leftHindLeg.xRot = 0.0F;
            this.rightHindLeg.xRot = 0.0F;
            this.rightHindLeg.zRot = 0.0F;
            this.rightHindLeg.x = -1.1F;
            this.rightHindLeg.y = 18.0F;
        }

        this.body.y = 12.0F;
        this.body.z = -10.0F;
        this.head.y = 15.0F;
        this.head.z = -9.0F;
        this.tail1.y = 15.0F;
        this.tail1.z = 8.0F;
        this.tail2.y = 20.0F;
        this.tail2.z = 14.0F;
        this.leftFrontLeg.y = 14.1F;
        this.leftFrontLeg.z = -5.0F;
        this.rightFrontLeg.y = 14.1F;
        this.rightFrontLeg.z = -5.0F;
        this.leftHindLeg.y = 18.0F;
        this.leftHindLeg.z = 5.0F;
        this.rightHindLeg.y = 18.0F;
        this.rightHindLeg.z = 5.0F;
        this.tail1.xRot = 0.9F;
        ModelPart var10000;
        if (entity.isCrouching()) {
            ++this.body.y;
            var10000 = this.head;
            var10000.y += 2.0F;
            ++this.tail1.y;
            var10000 = this.tail2;
            var10000.y += -4.0F;
            var10000 = this.tail2;
            var10000.z += 2.0F;
            this.state = 0;
        } else if (entity.isSprinting()) {
            this.tail2.y = this.tail1.y;
            var10000 = this.tail2;
            var10000.z += 2.0F;
            this.state = 2;
        } else {
            this.state = 1;
        }

        if (entity.isInSittingPose()) {
            this.body.xRot = 0.7853982F;
            var10000 = this.body;
            var10000.y += -4.0F;
            var10000 = this.body;
            var10000.z += 5.0F;
            var10000 = this.head;
            var10000.y += -3.3F;
            ++this.head.z;
            var10000 = this.tail1;
            var10000.y += 8.0F;
            var10000 = this.tail1;
            var10000.z += -2.0F;
            var10000 = this.tail2;
            var10000.y += 2.0F;
            var10000 = this.tail2;
            var10000.z += -0.8F;
            this.tail1.xRot = 0.0F;
            this.tail2.xRot = 0.0F;
            this.leftFrontLeg.xRot = -0.15707964F;
            this.leftFrontLeg.y = 16.1F;
            this.leftFrontLeg.z = -7.0F;
            this.rightFrontLeg.xRot = -0.15707964F;
            this.rightFrontLeg.y = 16.1F;
            this.rightFrontLeg.z = -7.0F;
            this.leftHindLeg.xRot = -1.5707964F;
            this.leftHindLeg.y = 21.0F;
            this.leftHindLeg.z = 1.0F;
            this.rightHindLeg.xRot = -1.5707964F;
            this.rightHindLeg.y = 21.0F;
            this.rightHindLeg.z = 1.0F;
            this.state = 3;
        }
    }

    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        if (this.lieDownAmount > 0.0F) {
            this.head.zRot = ModelUtils.rotlerpRad(this.head.zRot, -1.2707963F, this.lieDownAmount);
            this.head.yRot = ModelUtils.rotlerpRad(this.head.yRot, 1.2707963F, this.lieDownAmount);
            this.leftFrontLeg.xRot = -1.2707963F;
            this.rightFrontLeg.xRot = -0.47079635F;
            this.rightFrontLeg.zRot = -0.2F;
            this.rightFrontLeg.x = -0.2F;
            this.leftHindLeg.xRot = -0.4F;
            this.rightHindLeg.xRot = 0.5F;
            this.rightHindLeg.zRot = -0.5F;
            this.rightHindLeg.x = -0.3F;
            this.rightHindLeg.y = 20.0F;
        }

        if (this.relaxStateOneAmount > 0.0F) {
            this.head.xRot = ModelUtils.rotlerpRad(this.head.xRot, -0.58177644F, this.relaxStateOneAmount);
        }

    }

    public static LayerDefinition createBodyMesh() {
        CubeDeformation cubeDeformation = new CubeDeformation(0.0f);

        MeshDefinition meshdefinition = new MeshDefinition();

        PartDefinition partdefinition = meshdefinition.getRoot();
        CubeDeformation cubedeformation = new CubeDeformation(-0.02F);
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(20, 0).addBox(-2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(16, 33).addBox(-1.5F, -0.02F, -4.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(6, 34).addBox(-2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 9).addBox(1.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -9.0F));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 3.0F, -8.0F, 4.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, -10.0F, 1.5708F, 0.0F, 0.0F));

        //PartDefinition allTail = partdefinition.addOrReplaceChild("allTail", CubeListBuilder.create(), PartPose.offset(0.0F, 16.5F, 9.0F));
        partdefinition.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(20, 9).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 8.0F, 4.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, 1.5708F, 0.0F, 0.0F));
        //PartDefinition allTail2 = allTail.addOrReplaceChild("allTail2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 7.0F));
        partdefinition.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 34).addBox(-0.5F, -2.5F, 0.0F, 1.0F, 5.0F, 2.0F, cubeDeformation), PartPose.offset(0.0F, 0.0F, 8.0F));
        partdefinition.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(20, 21).addBox(-1.0F, -1.0F, -2.5F, 2.0F, 9.0F, 3.0F, cubeDeformation), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));


        CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(8, 13).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, cubeDeformation);
        partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(30, 21).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.1F, 18.0F, 6.0F));
        partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(30, 29).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.1F, 18.0F, 6.0F));
        CubeListBuilder cubelistbuilder1 = CubeListBuilder.create().texOffs(40, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, cubeDeformation);
        partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.1F, 14.1F, -5.0F));
        partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(8, 22).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.1F, 14.1F, -5.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.leftHindLeg, this.rightHindLeg, this.leftFrontLeg, this.rightFrontLeg, this.tail1, this.tail2, this.tail3);
    }


//    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
//    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MerCats.MODID, "mercat"), "main");
//
//
//    public MerCatModel(ModelPart root) {
//        super(root);
//        this.head = root.getChild("head");
//        this.body = root.getChild("body");
//        this.front_left_leg = root.getChild("front_left_leg");
//        this.front_right_leg = root.getChild("front_right_leg");
//        this.back_left_leg = root.getChild("back_left_leg");
//        this.back_right_leg = root.getChild("back_right_leg");
//        this.allTail = root.getChild("allTail");
//        this.tail = this.allTail.getChild("tail");
//        this.allTail2 = this.allTail.getChild("allTail2");
//        this.tail3 = this.allTail2.getChild("tail3");
//        this.tail2 = this.allTail2.getChild("tail2");
//    }
//
//    public static LayerDefinition createBodyLayer() {
//        MeshDefinition meshdefinition = new MeshDefinition();
//        PartDefinition partdefinition = meshdefinition.getRoot();
//
//        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(20, 0).addBox(-2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
//                .texOffs(16, 33).addBox(-1.5F, -0.02F, -4.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
//                .texOffs(6, 34).addBox(-2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
//                .texOffs(34, 9).addBox(1.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -9.0F));
//
//        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 3.0F, -8.0F, 4.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, -10.0F, 1.5708F, 0.0F, 0.0F));
//
//        PartDefinition front_left_leg = partdefinition.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.1F, 14.1F, -5.0F));
//
//        PartDefinition front_right_leg = partdefinition.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(8, 22).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.1F, 14.1F, -5.0F));
//
//        PartDefinition back_left_leg = partdefinition.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(30, 21).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.1F, 18.0F, 6.0F));
//
//        PartDefinition back_right_leg = partdefinition.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(30, 29).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.1F, 18.0F, 6.0F));
//
//        PartDefinition allTail = partdefinition.addOrReplaceChild("allTail", CubeListBuilder.create(), PartPose.offset(0.0F, 16.5F, 9.0F));
//
//        PartDefinition tail = allTail.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(20, 9).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, 1.5708F, 0.0F, 0.0F));
//
//        PartDefinition allTail2 = allTail.addOrReplaceChild("allTail2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 7.0F));
//
//        PartDefinition tail3 = allTail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 34).addBox(-0.5F, -2.5F, 0.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));
//
//        PartDefinition tail2 = allTail2.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(20, 21).addBox(-1.0F, -1.0F, -2.5F, 2.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
//
//        return LayerDefinition.create(meshdefinition, 64, 64);
//    }
//
//    @Override
//    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//
//    }
//
//    private void applyHeadRotation(float headYaw, float headPitch){
//        headYaw = Mth.clamp(headYaw, -30f, 30f);
//        headPitch
//    }
//
//    @Override
//    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
//        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
//        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
//        front_left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
//        front_right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
//        back_left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
//        back_right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
//        allTail.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
//    }
//
//    @Override
//    public ModelPart root() {
//        return null;
//    }
}