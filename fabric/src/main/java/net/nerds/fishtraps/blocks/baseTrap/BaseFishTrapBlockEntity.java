package net.nerds.fishtraps.blocks.baseTrap;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.nerds.fishtraps.Fishtraps;
import net.nerds.fishtraps.config.FishTrapValues;
import net.nerds.fishtraps.items.FishingBait;

import java.util.Iterator;
import java.util.List;

public abstract class BaseFishTrapBlockEntity extends BlockEntity implements Tickable, SidedInventory {

    private long tickCounter = 0; //counter to validate if waited
    private long tickValidator; //how many ticks to wait
    private long tickValidatorPenalty;
    private long tickCounterChecker;
    private int lureLevel;
    private int luckOfTheSeaLevel;
    private boolean shouldPenalty;
    private int maxStorage = 46;
    private boolean showFishBait = false;
    public DefaultedList<ItemStack> inventory;

    public BaseFishTrapBlockEntity(BlockEntityType blockEntityType, int fishDelay, int lureLevel, int luckOfTheSeaLevel) {
        super(blockEntityType);
        inventory = DefaultedList.ofSize(maxStorage, ItemStack.EMPTY);
        this.tickValidator = (long)fishDelay;
        this.tickValidatorPenalty = this.tickValidator * Fishtraps.fishTrapsConfig.getProperty(FishTrapValues.PENALTY_MULTIPLIER_AMOUNT);
        this.lureLevel = lureLevel;
        this.luckOfTheSeaLevel = luckOfTheSeaLevel;
        this.shouldPenalty = Fishtraps.fishTrapsConfig.getBooleanProperty(FishTrapValues.SHOULD_PENALTY_MULTIPLIER);
        this.tickCounterChecker = this.shouldPenalty ? this.tickValidatorPenalty  : this.tickValidator;
    }

    @Override
    public void tick() {
        if(tickCounter >= getValidationNumber()) {
            tickCounter = 0;
            validateWaterAndFish();
        } else {
            tickCounter++;
        }
    }

    private long getValidationNumber() {
        showFishBait = this.inventory.get(0).getCount() > 0;
        if(!showFishBait && this.shouldPenalty) {
            return this.tickValidatorPenalty;
        } else {
            return this.tickValidator;
        }
    }

    /**
     * Try to fish
     */
    private void validateWaterAndFish() {
        if(!world.isClient) {
            boolean isSurroundedByWater = true;
            Iterable<BlockPos> waterCheckInterator = BlockPos.iterate(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1),
                    new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1));
            for(BlockPos blockPos : waterCheckInterator) {
                Block block = world.getBlockState(blockPos).getBlock();
                if(world.getBlockEntity(pos) != null && (block != Blocks.WATER && !(block instanceof BaseFishTrapBlock))) {
                    isSurroundedByWater = false;
                    break;
                }
            }
            if(isSurroundedByWater) {
                fish();
            }
        }
    }

    /**
     * fish for loot
     */
    private void fish() {
        ItemStack itemStack = new ItemStack(Items.FISHING_ROD);
        itemStack.addEnchantment(Enchantments.LURE, this.lureLevel);
        itemStack.addEnchantment(Enchantments.LUCK_OF_THE_SEA, this.luckOfTheSeaLevel);
        LootContext.Builder lootContextBuilder = (new LootContext.Builder((ServerWorld)this.world))
                .put(LootContextParameters.POSITION, new BlockPos(pos))
                .put(LootContextParameters.TOOL, itemStack)
                .setRandom(world.random).setLuck((float)this.lureLevel);
        LootTable lootTable = this.world.getServer().getLootManager().getSupplier(LootTables.FISHING_GAMEPLAY);
        List<ItemStack> list = lootTable.getDrops(lootContextBuilder.build(LootContextTypes.FISHING));
        addItemsToInventory(list);
        if(showFishBait) {
            ItemStack fishBait = inventory.get(0);
            if(fishBait.getItem() instanceof FishingBait) {
                if(fishBait.damage(1, world.random, null)){
                    inventory.set(0, ItemStack.EMPTY);
                    markDirty();
                }
            }
        }
    }

    private void addItemsToInventory(List<ItemStack> itemStackList) {
        for(ItemStack itemStack : itemStackList) {
            //loop through inventory looking for space. start at 1 to avoid bait space
            for(int i = 1; i < inventory.size(); i++) {
                if(inventory.get(i).isEmpty()) {
                    inventory.set(i, itemStack);
                    markDirty();
                    break;
                } else if(inventory.get(i).isItemEqual(itemStack) &&
                        (inventory.get(i).getCount() + itemStack.getCount() <= itemStack.getMaxCount()) &&
                        itemStack.isStackable()) {
                    inventory.set(i, new ItemStack(itemStack.getItem(), itemStack.getCount() + inventory.get(i).getCount()));
                    markDirty();
                    break;
                }
            }
        }
    }

    @Override
    public void fromTag(CompoundTag nbt) {
        super.fromTag(nbt);
        inventory = DefaultedList.ofSize(maxStorage, ItemStack.EMPTY);
        Inventories.fromTag(nbt, this.inventory);
    }

    @Override
    public CompoundTag toTag(CompoundTag nbt) {
        super.toTag(nbt);
        Inventories.toTag(nbt, this.inventory);
        return nbt;
    }

    @Override
    public int getInvSize() {
        return inventory.size();
    }

    @Override
    public boolean isInvEmpty() {
        Iterator var1 = this.inventory.iterator();
        ItemStack itemStack_1;
        do {
            if (!var1.hasNext()) {
                return true;
            }
            itemStack_1 = (ItemStack)var1.next();
        } while(itemStack_1.isEmpty());
        return false;
    }

    @Override
    public ItemStack getInvStack(int i) {
        return inventory.get(i);
    }

    @Override
    public ItemStack takeInvStack(int i, int i1) {
        return Inventories.splitStack(this.inventory, i, i1);
    }

    @Override
    public ItemStack removeInvStack(int i) {
        return Inventories.removeStack(inventory, i);
    }

    @Override
    public void setInvStack(int i, ItemStack itemStack) {
        inventory.set(i, itemStack);
        this.markDirty();
    }

    @Override
    public boolean canPlayerUseInv(PlayerEntity playerEntity) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        } else {
            return playerEntity.squaredDistanceTo((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clear() {
        inventory.clear();
    }


    public boolean showFishBait() {
        return showFishBait;
    }

    @Override
    public int[] getInvAvailableSlots(Direction direction) {
        int[] arr = new int[46];
        for(int i = 0; i < 46; i++) {
            arr[i] = i;
        }
        return arr;
    }

    @Override
    public boolean canInsertInvStack(int i, ItemStack itemStack, Direction direction) {
        return i == 0 && itemStack.getItem() instanceof FishingBait;
    }

    @Override
    public boolean canExtractInvStack(int i, ItemStack itemStack, Direction direction) {
        if (direction == Direction.DOWN && i > 0) {
            return true;
        }
        return false;
    }
}
