package com.github.skyhawkb.lightningrods.blocks;

import net.minecraftforge.energy.EnergyStorage;

import java.util.Random;

public class LightingRodEnergyStorage extends EnergyStorage {
    LightingRodEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    void fillEnergy() {
        this.energy = 1000000;
    }
    void loseEnergy(Random random) {
        this.energy -= random.nextInt(500) + 500;
        if(this.energy < 0) this.energy = 0;
    }
    void setEnergy(int newEnergy) {
        this.energy = newEnergy;
    }
}
