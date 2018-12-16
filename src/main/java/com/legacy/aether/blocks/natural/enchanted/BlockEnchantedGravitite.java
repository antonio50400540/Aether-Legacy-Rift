package com.legacy.aether.blocks.natural.enchanted;

import com.legacy.aether.blocks.BlockFloating;

import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class BlockEnchantedGravitite extends BlockFloating
{

	public BlockEnchantedGravitite()
	{
		super(Block.Settings.of(Material.METAL).strength(5.0F, -1.0F).sound(SoundType.METAL), false);
	}

}