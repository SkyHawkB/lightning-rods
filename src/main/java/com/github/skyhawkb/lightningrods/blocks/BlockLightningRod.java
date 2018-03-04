package com.github.skyhawkb.lightningrods.blocks;

import com.github.skyhawkb.lightningrods.LightningRods;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLightningRod extends Block {
    public BlockLightningRod() {
        super(Material.IRON);
        setUnlocalizedName(LightningRods.MODID + ".lightning_rod");
        setRegistryName("lightning_rod");
        setHarvestLevel("pickaxe", 2);
        setCreativeTab(LightningRods.MOD_TAB);
    }

    public Item getItemBlock() {
        return new ItemBlock(this).setRegistryName("lightning_rod");
    }
    public void registerItemModel(Item itemBlock) {
        LightningRods.proxy.registerItemRenderer(itemBlock, 0, "lightning_rod");
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }
    @Override
    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public TileEntityLightningRod createTileEntity(World world, IBlockState state) {
        return new TileEntityLightningRod();
    }
    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    public Class<TileEntityLightningRod> getTileEntityClass() {
        return TileEntityLightningRod.class;
    }
    private TileEntity getTileEntity(IBlockAccess world, BlockPos pos) {
        return world.getTileEntity(pos);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!playerIn.isSneaking()) {
            playerIn.sendStatusMessage(new TextComponentString("RF stored: " + Integer.toString(((TileEntityLightningRod) this.getTileEntity(worldIn, pos)).getEnergyStored())), false);
            return true;
        } else {
            return false;
        }
    }

    /*
    * TODO: make it need a solid block beneath (at all times)
    */
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.875D, 0.75D);
    }
}
