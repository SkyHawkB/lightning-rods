package com.github.skyhawkb.lightningrods.blocks;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.Random;

public class TileEntityLightningRod extends TileEntity implements ITickable {
    private int ticksToNextStrike = 1200;
    private int ticksToNextEnergyLoss = 200;
    private LightingRodEnergyStorage energyStorage;
    private static final Random random = new Random();

    public TileEntityLightningRod() {
        this.energyStorage = new LightingRodEnergyStorage(1000000, 0, 1000, 0);
    }
    public void update() {
        if(energyStorage.getEnergyStored() > 0) {
            outputEnergy();
        }

        if(!this.world.canBlockSeeSky(this.pos) || !this.world.isThundering()) return;

        ticksToNextStrike--;
        if(ticksToNextStrike > 0) return;

        int xOffset = random.nextInt(5) - 2;
        int zOffset = random.nextInt(5) - 2;
        world.spawnEntity(new EntityLightningBolt(world, this.pos.getX() + xOffset, this.pos.getY(), this.pos.getZ() + zOffset, false));

        energyStorage.fillEnergy();
        ticksToNextStrike = 2000;

        ticksToNextEnergyLoss--;
        if(ticksToNextEnergyLoss == 0) {
            energyStorage.loseEnergy(random);
            ticksToNextEnergyLoss = 200;
        }

        markDirty();
    }

    protected int getEnergyStored() {
        return energyStorage.getEnergyStored();
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) return true;
        return super.hasCapability(capability, facing);
    }
    @Override
    @Nullable
    @SuppressWarnings("unchecked")
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) return (T) energyStorage;
        return super.getCapability(capability, facing);
    }

    private void outputEnergy() {
        BlockPos pos = getPos().down();
        if(world.isBlockLoaded(pos)) {
            TileEntity tile = world.getTileEntity(pos);
            if(tile != null) {
                if(tile.hasCapability(CapabilityEnergy.ENERGY, EnumFacing.UP)) {
                    IEnergyStorage storage = tile.getCapability(CapabilityEnergy.ENERGY, EnumFacing.UP);
                    if (storage != null) {
                        int power = energyStorage.extractEnergy(Integer.MAX_VALUE, true);
                        int drained = storage.receiveEnergy(power, false);
                        energyStorage.extractEnergy(drained, false);
                    }
                }
            }
        }
    }
}
