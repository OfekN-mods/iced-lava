package com.ofek2608.icedlava;

import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class IcedLavaBlock extends HalfTransparentBlock {
	public IcedLavaBlock(Properties properties) {
		super(properties);
	}
	
	public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
		super.playerDestroy(level, player, pos, state, blockEntity, stack);
		if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SILK_TOUCH, stack) != 0) {
			return;
		}
		BlockState belowState = level.getBlockState(pos.below());
		if (belowState.blocksMotion() || belowState.liquid()) {
			level.setBlockAndUpdate(pos, Blocks.LAVA.defaultBlockState());
		}
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, level, tooltip, flag);
		tooltip.add(
						Component.translatable(
										"block.iced_lava.iced_lava.tooltip",
										Blocks.LAVA.getName().withStyle(ChatFormatting.DARK_RED)
						).withStyle(ChatFormatting.RED)
		);
	}
}
