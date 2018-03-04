package com.github.skyhawkb.lightningrods.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.Random;

public class TileEntityLightningRod extends TileEntity implements ITickable {
    private static int ticksToNextStrike = 2000;
    private static int energyStored = 0;
    private static final Random random = new Random();
    public void update() {
        if(!this.world.canBlockSeeSky(this.pos) || !this.world.isThundering()) return;

        ticksToNextStrike--;
        if(ticksToNextStrike > 0) return;

        int xOffset = random.nextInt(5) - 2;
        int zOffset = random.nextInt(5) - 2;
        world.spawnEntity(new EntityLightningBolt(world, this.pos.getX() + xOffset, this.pos.getY(), this.pos.getZ() + zOffset, false));

        energyStored = 1000000;
        ticksToNextStrike = 2000;
        markDirty();
        /*
        * TODO: make it give away RF
        * TODO: make it slowly lose RF
        */
    }

    protected int getEnergyStored() {
        return energyStored;
    }
}
