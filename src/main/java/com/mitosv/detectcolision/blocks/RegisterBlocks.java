package com.mitosv.detectcolision.blocks;

import com.mitosv.detectcolision.DetectColision;
import com.mitosv.detectcolision.blocks.custom.BaseBlock;
import com.mitosv.detectcolision.blocks.custom.SuperiorBlock;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RegisterBlocks {

    private static final CreateRegistrate REGISTRATE = DetectColision.registrate()
            .creativeModeTab(()->ItemGroupMod.Collision);


    public static final Block SuperiorBlock = registerBlock("superior_block",
            new SuperiorBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()), ItemGroupMod.Collision);

    public static final Block InferiorBlock = registerBlock("inferior_block",
            new SuperiorBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()), ItemGroupMod.Collision);

    public static final Block BaseBlock = registerBlock("base_block",
            new BaseBlock(FabricBlockSettings.of(Material.METAL).nonOpaque()), ItemGroupMod.Collision);



    private static Block registerBlock(String name, Block block, ItemGroup group, String tooltipKey) {
        registerBlockItem(name, block, group, tooltipKey);
        return Registry.register(Registry.BLOCK, new Identifier(DetectColision.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block, ItemGroup group, String tooltipKey) {
        Registry.register(Registry.ITEM, new Identifier(DetectColision.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)) {
                    @Override
                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                        tooltip.add(Text.translatable(tooltipKey));
                    }
                });

    }

    private static Block registerBlockWithoutBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.BLOCK, new Identifier(DetectColision.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(DetectColision.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(DetectColision.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerModBlocks() {
        System.out.println("Registering ModBlocks for " + DetectColision.MOD_ID);
    }

}
