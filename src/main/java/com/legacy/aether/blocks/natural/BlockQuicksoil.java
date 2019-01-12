package com.legacy.aether.blocks.natural;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;

public class BlockQuicksoil extends Block
{

	public static final BooleanProperty DOUBLE_DROP = BooleanProperty.create("double_drop");

	public BlockQuicksoil()
	{
		super(FabricBlockSettings.of(Material.SAND).strength(0.5F, -1.0F).friction(1.1F).sounds(BlockSoundGroup.SAND).build());

		this.setDefaultState(this.getDefaultState().with(DOUBLE_DROP, true));
	}

	@Override
	public void appendProperties(StateFactory.Builder<Block, BlockState> propertyBuilderIn)
	{
		propertyBuilderIn.with(DOUBLE_DROP);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context)
	{
		return super.getPlacementState(context).with(DOUBLE_DROP, false);
	}
}