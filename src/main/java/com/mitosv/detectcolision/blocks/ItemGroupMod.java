package com.mitosv.detectcolision.blocks;

import com.mitosv.detectcolision.DetectColision;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ItemGroupMod {

    public static final ItemGroup Collision = FabricItemGroupBuilder.build(new Identifier(DetectColision.MOD_ID,
                    "collision"),
            () -> new ItemStack(RegisterBlocks.SuperiorBlock.asItem()));

}
