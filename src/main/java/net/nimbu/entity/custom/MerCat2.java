package net.nimbu.entity.custom;

//public class MerCat extends TamableAnimal implements VariantHolder<Holder<CatVariant>> {
//    public static final double TEMPT_SPEED_MOD = 0.6;
//    public static final double WALK_SPEED_MOD = 0.8;
//    public static final double SPRINT_SPEED_MOD = 1.33;
//    private static final EntityDataAccessor<Holder<CatVariant>> DATA_VARIANT_ID;
//    private static final EntityDataAccessor<Boolean> IS_LYING;
//    private static final EntityDataAccessor<Boolean> RELAX_STATE_ONE;
//    private static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR;
//    private static final ResourceKey<CatVariant> DEFAULT_VARIANT;
//    @Nullable
//    private MerCat.CatAvoidEntityGoal<Player> avoidPlayersGoal;
//    @Nullable
//    private TemptGoal temptGoal;
//    private float lieDownAmount;
//    private float lieDownAmountO;
//    private float lieDownAmountTail;
//    private float lieDownAmountOTail;
//    private float relaxStateOneAmount;
//    private float relaxStateOneAmountO;
//
//    public static final AnimationState idleAnimationState = new AnimationState();
//    private int idleAnimationTimeout = 0;
//
//    public MerCat(EntityType<? extends MerCat> entityType, Level level) {
//        super(entityType, level);
//        this.reassessTameGoals();
//    }
//
//    public ResourceLocation getTextureId() {
//        return ((CatVariant)this.getVariant().value()).texture();
//    }
//
//    protected void registerGoals() {
//        this.temptGoal = new CatTemptGoal(this, 0.6, (p_335255_) -> {
//            return p_335255_.is(ItemTags.CAT_FOOD);
//        }, true);
//        this.goalSelector.addGoal(1, new FloatGoal(this));
//        this.goalSelector.addGoal(1, new TamableAnimal.TamableAnimalPanicGoal(this, 1.5));
//        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
//        this.goalSelector.addGoal(3, new CatRelaxOnOwnerGoal(this));
//        this.goalSelector.addGoal(4, this.temptGoal);
//        //this.goalSelector.addGoal(5, new CatLieOnBedGoal(this, 1.1, 8));
//        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0, 10.0F, 5.0F));
//        this.goalSelector.addGoal(7, new CatSitOnBlockGoal(this, 0.8));
//        this.goalSelector.addGoal(8, new LeapAtTargetGoal(this, 0.3F));
//        this.goalSelector.addGoal(9, new OcelotAttackGoal(this));
//        this.goalSelector.addGoal(10, new BreedGoal(this, 0.8));
//        this.goalSelector.addGoal(11, new WaterAvoidingRandomStrollGoal(this, 0.8, 1.0000001E-5F));
//        this.goalSelector.addGoal(12, new LookAtPlayerGoal(this, Player.class, 10.0F));
//        this.targetSelector.addGoal(1, new NonTameRandomTargetGoal(this, Rabbit.class, false, (Predicate)null));
//        this.targetSelector.addGoal(1, new NonTameRandomTargetGoal(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
//    }
//
//    public Holder<CatVariant> getVariant() {
//        return (Holder)this.entityData.get(DATA_VARIANT_ID);
//    }
//
//    public void setVariant(Holder<CatVariant> variant) {
//        this.entityData.set(DATA_VARIANT_ID, variant);
//    }
//
//    public void setLying(boolean lying) {
//        this.entityData.set(IS_LYING, lying);
//    }
//
//    public boolean isLying() {
//        return (Boolean)this.entityData.get(IS_LYING);
//    }
//
//    void setRelaxStateOne(boolean relaxStateOne) {
//        this.entityData.set(RELAX_STATE_ONE, relaxStateOne);
//    }
//
//    boolean isRelaxStateOne() {
//        return (Boolean)this.entityData.get(RELAX_STATE_ONE);
//    }
//
//    public DyeColor getCollarColor() {
//        return DyeColor.byId((Integer)this.entityData.get(DATA_COLLAR_COLOR));
//    }
//
//    private void setCollarColor(DyeColor color) {
//        this.entityData.set(DATA_COLLAR_COLOR, color.getId());
//    }
//
//    protected void defineSynchedData(SynchedEntityData.Builder builder) {
//        super.defineSynchedData(builder);
//        builder.define(DATA_VARIANT_ID, BuiltInRegistries.CAT_VARIANT.getHolderOrThrow(DEFAULT_VARIANT));
//        builder.define(IS_LYING, false);
//        builder.define(RELAX_STATE_ONE, false);
//        builder.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
//    }
//
//    public void addAdditionalSaveData(CompoundTag compound) {
//        super.addAdditionalSaveData(compound);
//        compound.putString("variant", ((ResourceKey)this.getVariant().unwrapKey().orElse(DEFAULT_VARIANT)).location().toString());
//        compound.putByte("CollarColor", (byte)this.getCollarColor().getId());
//    }
//
//    public void readAdditionalSaveData(CompoundTag compound) {
//        super.readAdditionalSaveData(compound);
//        Optional var10000 = Optional.ofNullable(ResourceLocation.tryParse(compound.getString("variant"))).map((p_335254_) -> {
//            return ResourceKey.create(Registries.CAT_VARIANT, p_335254_);
//        });
//        Registry var10001 = BuiltInRegistries.CAT_VARIANT;
//        Objects.requireNonNull(var10001);
//        var10000.flatMap(var10001::getHolder).ifPresent(this::setVariant);
//        if (compound.contains("CollarColor", 99)) {
//            this.setCollarColor(DyeColor.byId(compound.getInt("CollarColor")));
//        }
//
//    }
//
//    public void customServerAiStep() {
//        if (this.getMoveControl().hasWanted()) {
//            double d0 = this.getMoveControl().getSpeedModifier();
//            if (d0 == 0.6) {
//                this.setPose(Pose.CROUCHING);
//                this.setSprinting(false);
//            } else if (d0 == 1.33) {
//                this.setPose(Pose.STANDING);
//                this.setSprinting(true);
//            } else {
//                this.setPose(Pose.STANDING);
//                this.setSprinting(false);
//            }
//        } else {
//            this.setPose(Pose.STANDING);
//            this.setSprinting(false);
//        }
//
//    }
//
//    @Nullable
//    protected SoundEvent getAmbientSound() {
//        if (this.isTame()) {
//            if (this.isInLove()) {
//                return SoundEvents.CAT_PURR;
//            } else {
//                return this.random.nextInt(4) == 0 ? SoundEvents.CAT_PURREOW : SoundEvents.CAT_AMBIENT;
//            }
//        } else {
//            return SoundEvents.CAT_STRAY_AMBIENT;
//        }
//    }
//
//    public int getAmbientSoundInterval() {
//        return 120;
//    }
//
//    public void hiss() {
//        this.makeSound(SoundEvents.CAT_HISS);
//    }
//
//    protected SoundEvent getHurtSound(DamageSource damageSource) {
//        return SoundEvents.CAT_HURT;
//    }
//
//    protected SoundEvent getDeathSound() {
//        return SoundEvents.CAT_DEATH;
//    }
//
//    public static AttributeSupplier.Builder createAttributes() {
//        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.30000001192092896).add(Attributes.ATTACK_DAMAGE, 3.0);
//    }
//
//    protected void usePlayerItem(Player player, InteractionHand hand, ItemStack stack) {
//        if (this.isFood(stack)) {
//            this.playSound(SoundEvents.CAT_EAT, 1.0F, 1.0F);
//        }
//
//        super.usePlayerItem(player, hand, stack);
//    }
//
//    public void tick() {
//        super.tick();
//        if (this.temptGoal != null && this.temptGoal.isRunning() && !this.isTame() && this.tickCount % 100 == 0) {
//            this.playSound(SoundEvents.CAT_BEG_FOR_FOOD, 1.0F, 1.0F);
//        }
//
//        this.handleLieDown();
//    }
//
//    private void handleLieDown() {
//        if ((this.isLying() || this.isRelaxStateOne()) && this.tickCount % 5 == 0) {
//            this.playSound(SoundEvents.CAT_PURR, 0.6F + 0.4F * (this.random.nextFloat() - this.random.nextFloat()), 1.0F);
//        }
//
//        this.updateLieDownAmount();
//        this.updateRelaxStateOneAmount();
//    }
//
//    private void updateLieDownAmount() {
//        this.lieDownAmountO = this.lieDownAmount;
//        this.lieDownAmountOTail = this.lieDownAmountTail;
//        if (this.isLying()) {
//            this.lieDownAmount = Math.min(1.0F, this.lieDownAmount + 0.15F);
//            this.lieDownAmountTail = Math.min(1.0F, this.lieDownAmountTail + 0.08F);
//        } else {
//            this.lieDownAmount = Math.max(0.0F, this.lieDownAmount - 0.22F);
//            this.lieDownAmountTail = Math.max(0.0F, this.lieDownAmountTail - 0.13F);
//        }
//
//    }
//
//    private void updateRelaxStateOneAmount() {
//        this.relaxStateOneAmountO = this.relaxStateOneAmount;
//        if (this.isRelaxStateOne()) {
//            this.relaxStateOneAmount = Math.min(1.0F, this.relaxStateOneAmount + 0.1F);
//        } else {
//            this.relaxStateOneAmount = Math.max(0.0F, this.relaxStateOneAmount - 0.13F);
//        }
//
//    }
//
//    public float getLieDownAmount(float partialTicks) {
//        return Mth.lerp(partialTicks, this.lieDownAmountO, this.lieDownAmount);
//    }
//
//    public float getLieDownAmountTail(float partialTicks) {
//        return Mth.lerp(partialTicks, this.lieDownAmountOTail, this.lieDownAmountTail);
//    }
//
//    public float getRelaxStateOneAmount(float partialTicks) {
//        return Mth.lerp(partialTicks, this.relaxStateOneAmountO, this.relaxStateOneAmount);
//    }
//
//    @Nullable
//    public MerCat getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
//        MerCat cat = (MerCat) EntityType.CAT.create(level);
//        if (cat != null && otherParent instanceof MerCat cat1) {
//            if (this.random.nextBoolean()) {
//                cat.setVariant(this.getVariant());
//            } else {
//                cat.setVariant(cat1.getVariant());
//            }
//
//            if (this.isTame()) {
//                cat.setOwnerUUID(this.getOwnerUUID());
//                cat.setTame(true, true);
//                if (this.random.nextBoolean()) {
//                    cat.setCollarColor(this.getCollarColor());
//                } else {
//                    cat.setCollarColor(cat1.getCollarColor());
//                }
//            }
//        }
//
//        return cat;
//    }
//
//    public boolean canMate(Animal otherAnimal) {
//        if (!this.isTame()) {
//            return false;
//        } else {
//            boolean var10000;
//            if (otherAnimal instanceof MerCat) {
//                MerCat cat = (MerCat) otherAnimal;
//                var10000 = cat.isTame() && super.canMate(otherAnimal);
//            } else {
//                var10000 = false;
//            }
//
//            return var10000;
//        }
//    }
//
//    @Nullable
//    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
//        spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
//        boolean flag = level.getMoonBrightness() > 0.9F;
//        TagKey<CatVariant> tagkey = flag ? CatVariantTags.FULL_MOON_SPAWNS : CatVariantTags.DEFAULT_SPAWNS;
//        BuiltInRegistries.CAT_VARIANT.getRandomElementOf(tagkey, level.getRandom()).ifPresent(this::setVariant);
//        ServerLevel serverlevel = level.getLevel();
//        if (serverlevel.structureManager().getStructureWithPieceAt(this.blockPosition(), StructureTags.CATS_SPAWN_AS_BLACK).isValid()) {
//            this.setVariant((Holder)BuiltInRegistries.CAT_VARIANT.getHolderOrThrow(CatVariant.ALL_BLACK));
//            this.setPersistenceRequired();
//        }
//
//        return spawnGroupData;
//    }
//
//    public InteractionResult mobInteract(Player player, InteractionHand hand) {
//        ItemStack itemstack = player.getItemInHand(hand);
//        Item item = itemstack.getItem();
//        InteractionResult interactionresult;
//        if (this.isTame()) {
//            if (this.isOwnedBy(player)) {
//                if (item instanceof DyeItem) {
//                    DyeItem dyeitem = (DyeItem)item;
//                    DyeColor dyecolor = dyeitem.getDyeColor();
//                    if (dyecolor != this.getCollarColor()) {
//                        if (!this.level().isClientSide()) {
//                            this.setCollarColor(dyecolor);
//                            itemstack.consume(1, player);
//                            this.setPersistenceRequired();
//                        }
//
//                        return InteractionResult.sidedSuccess(this.level().isClientSide());
//                    }
//                } else if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
//                    if (!this.level().isClientSide()) {
//                        FoodProperties foodproperties = itemstack.getFoodProperties(this);
//                        this.heal(foodproperties != null ? (float)foodproperties.nutrition() : 1.0F);
//                        this.usePlayerItem(player, hand, itemstack);
//                    }
//
//                    return InteractionResult.sidedSuccess(this.level().isClientSide());
//                }
//
//                interactionresult = super.mobInteract(player, hand);
//                if (!interactionresult.consumesAction()) {
//                    this.setOrderedToSit(!this.isOrderedToSit());
//                    return InteractionResult.sidedSuccess(this.level().isClientSide());
//                }
//
//                return interactionresult;
//            }
//        } else if (this.isFood(itemstack)) {
//            if (!this.level().isClientSide()) {
//                this.usePlayerItem(player, hand, itemstack);
//                this.tryToTame(player);
//                this.setPersistenceRequired();
//            }
//
//            return InteractionResult.sidedSuccess(this.level().isClientSide());
//        }
//
//        interactionresult = super.mobInteract(player, hand);
//        if (interactionresult.consumesAction()) {
//            this.setPersistenceRequired();
//        }
//
//        return interactionresult;
//    }
//
//    public boolean isFood(ItemStack stack) {
//        return stack.is(ItemTags.CAT_FOOD);
//    }
//
//    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
//        return !this.isTame() && this.tickCount > 2400;
//    }
//
//    public void setTame(boolean tame, boolean applyTamingSideEffects) {
//        super.setTame(tame, applyTamingSideEffects);
//        this.reassessTameGoals();
//    }
//
//    protected void reassessTameGoals() {
//        if (this.avoidPlayersGoal == null) {
//            this.avoidPlayersGoal = new MerCat(this, Player.class, 16.0F, 0.8, 1.33);
//        }
//
//        this.goalSelector.removeGoal(this.avoidPlayersGoal);
//        if (!this.isTame()) {
//            this.goalSelector.addGoal(4, this.avoidPlayersGoal);
//        }
//
//    }
//
//    private void tryToTame(Player player) {
//        if (this.random.nextInt(3) == 0 && !EventHooks.onAnimalTame(this, player)) {
//            this.tame(player);
//            this.setOrderedToSit(true);
//            this.level().broadcastEntityEvent(this, (byte)7);
//        } else {
//            this.level().broadcastEntityEvent(this, (byte)6);
//        }
//
//    }
//
//    public boolean isSteppingCarefully() {
//        return this.isCrouching() || super.isSteppingCarefully();
//    }
//
//    static {
//        DATA_VARIANT_ID = SynchedEntityData.defineId(MerCat.class, EntityDataSerializers.CAT_VARIANT);
//        IS_LYING = SynchedEntityData.defineId(MerCat.class, EntityDataSerializers.BOOLEAN);
//        RELAX_STATE_ONE = SynchedEntityData.defineId(MerCat.class, EntityDataSerializers.BOOLEAN);
//        DATA_COLLAR_COLOR = SynchedEntityData.defineId(MerCat.class, EntityDataSerializers.INT);
//        DEFAULT_VARIANT = CatVariant.BLACK;
//    }
//
//    static class CatTemptGoal extends TemptGoal {
//        @Nullable
//        private Player selectedPlayer;
//        private final MerCat cat;
//
//        public CatTemptGoal(MerCat cat, double speedModifier, Predicate<ItemStack> items, boolean canScare) {
//            super(cat, speedModifier, items, canScare);
//            this.cat = cat;
//        }
//
//        public void tick() {
//            super.tick();
//            if (this.selectedPlayer == null && this.mob.getRandom().nextInt(this.adjustedTickDelay(600)) == 0) {
//                this.selectedPlayer = this.player;
//            } else if (this.mob.getRandom().nextInt(this.adjustedTickDelay(500)) == 0) {
//                this.selectedPlayer = null;
//            }
//
//        }
//
//        protected boolean canScare() {
//            return this.selectedPlayer != null && this.selectedPlayer.equals(this.player) ? false : super.canScare();
//        }
//
//        public boolean canUse() {
//            return super.canUse() && !this.cat.isTame();
//        }
//    }
//
//    static class CatRelaxOnOwnerGoal extends Goal {
//        private final MerCat cat;
//        @Nullable
//        private Player ownerPlayer;
//        @Nullable
//        private BlockPos goalPos;
//        private int onBedTicks;
//
//        public CatRelaxOnOwnerGoal(MerCat cat) {
//            this.cat = cat;
//        }
//
//        public boolean canUse() {
//            if (!this.cat.isTame()) {
//                return false;
//            } else if (this.cat.isOrderedToSit()) {
//                return false;
//            } else {
//                LivingEntity livingentity = this.cat.getOwner();
//                if (livingentity instanceof Player) {
//                    this.ownerPlayer = (Player)livingentity;
//                    if (!livingentity.isSleeping()) {
//                        return false;
//                    }
//
//                    if (this.cat.distanceToSqr(this.ownerPlayer) > 100.0) {
//                        return false;
//                    }
//
//                    BlockPos blockpos = this.ownerPlayer.blockPosition();
//                    BlockState blockstate = this.cat.level().getBlockState(blockpos);
//                    if (blockstate.is(BlockTags.BEDS)) {
//                        this.goalPos = (BlockPos)blockstate.getOptionalValue(BedBlock.FACING).map((p_28209_) -> {
//                            return blockpos.relative(p_28209_.getOpposite());
//                        }).orElseGet(() -> {
//                            return new BlockPos(blockpos);
//                        });
//                        return !this.spaceIsOccupied();
//                    }
//                }
//
//                return false;
//            }
//        }
//
//        private boolean spaceIsOccupied() {
//            Iterator var1 = this.cat.level().getEntitiesOfClass(MerCat.class, (new AABB(this.goalPos)).inflate(2.0)).iterator();
//
//            MerCat cat;
//            do {
//                do {
//                    if (!var1.hasNext()) {
//                        return false;
//                    }
//
//                    cat = (MerCat)var1.next();
//                } while(cat == this.cat);
//            } while(!cat.isLying() && !cat.isRelaxStateOne());
//
//            return true;
//        }
//
//        public boolean canContinueToUse() {
//            return this.cat.isTame() && !this.cat.isOrderedToSit() && this.ownerPlayer != null && this.ownerPlayer.isSleeping() && this.goalPos != null && !this.spaceIsOccupied();
//        }
//
//        public void start() {
//            if (this.goalPos != null) {
//                this.cat.setInSittingPose(false);
//                this.cat.getNavigation().moveTo((double)this.goalPos.getX(), (double)this.goalPos.getY(), (double)this.goalPos.getZ(), 1.100000023841858);
//            }
//
//        }
//
//        public void stop() {
//            this.cat.setLying(false);
//            float f = this.cat.level().getTimeOfDay(1.0F);
//            if (this.ownerPlayer.getSleepTimer() >= 100 && (double)f > 0.77 && (double)f < 0.8 && (double)this.cat.level().getRandom().nextFloat() < 0.7) {
//                this.giveMorningGift();
//            }
//
//            this.onBedTicks = 0;
//            this.cat.setRelaxStateOne(false);
//            this.cat.getNavigation().stop();
//        }
//
//        private void giveMorningGift() {
//            RandomSource randomsource = this.cat.getRandom();
//            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
//            blockpos$mutableblockpos.set(this.cat.isLeashed() ? this.cat.getLeashHolder().blockPosition() : this.cat.blockPosition());
//            this.cat.randomTeleport((double)(blockpos$mutableblockpos.getX() + randomsource.nextInt(11) - 5), (double)(blockpos$mutableblockpos.getY() + randomsource.nextInt(5) - 2), (double)(blockpos$mutableblockpos.getZ() + randomsource.nextInt(11) - 5), false);
//            blockpos$mutableblockpos.set(this.cat.blockPosition());
//            LootTable loottable = this.cat.level().getServer().reloadableRegistries().getLootTable(BuiltInLootTables.CAT_MORNING_GIFT);
//            LootParams lootparams = (new LootParams.Builder((ServerLevel)this.cat.level())).withParameter(LootContextParams.ORIGIN, this.cat.position()).withParameter(LootContextParams.THIS_ENTITY, this.cat).create(LootContextParamSets.GIFT);
//            ObjectListIterator var5 = loottable.getRandomItems(lootparams).iterator();
//
//            while(var5.hasNext()) {
//                ItemStack itemstack = (ItemStack)var5.next();
//                this.cat.level().addFreshEntity(new ItemEntity(this.cat.level(), (double)blockpos$mutableblockpos.getX() - (double)Mth.sin(this.cat.yBodyRot * 0.017453292F), (double)blockpos$mutableblockpos.getY(), (double)blockpos$mutableblockpos.getZ() + (double)Mth.cos(this.cat.yBodyRot * 0.017453292F), itemstack));
//            }
//
//        }
//
//        public void tick() {
//            if (this.ownerPlayer != null && this.goalPos != null) {
//                this.cat.setInSittingPose(false);
//                this.cat.getNavigation().moveTo((double)this.goalPos.getX(), (double)this.goalPos.getY(), (double)this.goalPos.getZ(), 1.100000023841858);
//                if (this.cat.distanceToSqr(this.ownerPlayer) < 2.5) {
//                    ++this.onBedTicks;
//                    if (this.onBedTicks > this.adjustedTickDelay(16)) {
//                        this.cat.setLying(true);
//                        this.cat.setRelaxStateOne(false);
//                    } else {
//                        this.cat.lookAt(this.ownerPlayer, 45.0F, 45.0F);
//                        this.cat.setRelaxStateOne(true);
//                    }
//                } else {
//                    this.cat.setLying(false);
//                }
//            }
//
//        }
//    }
//
//    static class CatAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
//        private final MerCat cat;
//
//        public CatAvoidEntityGoal(MerCat cat, Class<T> entityClassToAvoid, float maxDist, double walkSpeedModifier, double sprintSpeedModifier) {
//            Predicate var10006 = EntitySelector.NO_CREATIVE_OR_SPECTATOR;
//            Objects.requireNonNull(var10006);
//            super(cat, entityClassToAvoid, maxDist, walkSpeedModifier, sprintSpeedModifier, var10006::test);
//
//
//
//            this.cat = cat;
//        }
//
//        public boolean canUse() {
//            return !this.cat.isTame() && super.canUse();
//        }
//
//        public boolean canContinueToUse() {
//            return !this.cat.isTame() && super.canContinueToUse();
//        }
//    }
//}