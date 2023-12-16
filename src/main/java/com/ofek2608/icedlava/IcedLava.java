package com.ofek2608.icedlava;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(IcedLava.ID)
public class IcedLava {
	public static final String ID = "iced_lava";
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ID);

	public static final RegistryObject<Block> ICED_LAVA_BLOCK = BLOCKS.register("iced_lava", () -> new IcedLavaBlock(BlockBehaviour.Properties.of()
					.mapColor(MapColor.TERRACOTTA_ORANGE)
					.friction(0.98F)
					.strength(0.5F)
					.sound(SoundType.GLASS)
					.noOcclusion()
					.isValidSpawn((a, b, c, d) -> false)
					.isRedstoneConductor((a, b, c) -> false)
	));
	public static final RegistryObject<Item> ICED_LAVA_ITEM = ITEMS.register("iced_lava", () -> new BlockItem(ICED_LAVA_BLOCK.get(), new Item.Properties()));

	public IcedLava() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);

		modEventBus.addListener(this::addCreative);
	}

	private void addCreative(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
			event.accept(ICED_LAVA_ITEM);
	}
}
