package com.nosiphus.yogmod.block;

import com.nosiphus.yogmod.creativetab.ModCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockMonitor extends BlockDirectional {

    private final boolean isLit;
    @SideOnly(Side.CLIENT)
    private IIcon iconFront;
    @SideOnly(Side.CLIENT)
    private IIcon iconSide;
    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    public BlockMonitor(boolean isLit) {
        super(Material.glass);
        this.isLit = isLit;
        this.setCreativeTab(ModCreativeTabs.YogTab);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.iconTop : (side == 0 ? this.iconTop : (meta == 2 && side == 2 ? this.iconFront : (meta == 3 && side == 5 ? this.iconFront : (meta == 0 && side == 3 ? this.iconFront : (meta == 1 && side == 4 ? this.iconFront : this.blockIcon)))));
    }

    @Override
    public void onBlockPlacedBy(World world, int posX, int posY, int posZ, EntityLivingBase entity, ItemStack stack)
    {
        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(posX, posY, posZ, l, 2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        if (this.isLit) {
            this.iconFront = register.registerIcon("yogmod:lit_monitor");
        } else {
            this.iconFront = register.registerIcon("yogmod:monitor");
        }
        this.iconTop = register.registerIcon("yogmod:monitor_end");
        this.blockIcon = register.registerIcon("yogmod:monitor_side");
    }

}
