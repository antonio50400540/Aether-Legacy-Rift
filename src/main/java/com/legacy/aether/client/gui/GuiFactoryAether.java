package com.legacy.aether.client.gui;

import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.container.ContainerFactory;
import net.minecraft.client.gui.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

import com.legacy.aether.Aether;
import com.legacy.aether.blocks.entity.AetherBlockEntity;
import com.legacy.aether.blocks.entity.TreasureChestBlockEntity;
import com.legacy.aether.client.gui.container.GuITreasureChest;
import com.legacy.aether.client.gui.container.GuiAccessories;
import com.legacy.aether.client.gui.container.GuiEnchanter;
import com.legacy.aether.client.gui.container.GuiFreezer;
import com.legacy.aether.client.gui.container.GuiIncubator;

@SuppressWarnings("rawtypes")
public class GuiFactoryAether implements ContainerFactory<ContainerScreen>
{

	private static final GuiFactoryAether INSTANCE = new GuiFactoryAether();

	public static void registerGUIs()
	{
		ScreenProviderRegistry.INSTANCE.registerFactory(Aether.locate("accessories"), INSTANCE);
		ScreenProviderRegistry.INSTANCE.registerFactory(Aether.locate("enchanter"), INSTANCE);
		ScreenProviderRegistry.INSTANCE.registerFactory(Aether.locate("freezer"), INSTANCE);
		ScreenProviderRegistry.INSTANCE.registerFactory(Aether.locate("incubator"), INSTANCE);
		ScreenProviderRegistry.INSTANCE.registerFactory(Aether.locate("treasure_chest"), INSTANCE);
	}

	@Override
	public ContainerScreen<?> create(int syncId, Identifier identifierIn, PlayerEntity playerIn, PacketByteBuf byteBuf)
	{
		String guiName = identifierIn.getPath();

		if ("accessories".equals(guiName))
		{
			return new GuiAccessories(syncId, playerIn);
		}
		else if ("enchanter".equals(guiName))
		{
			return new GuiEnchanter(syncId, playerIn.inventory, (AetherBlockEntity) playerIn.world.getBlockEntity(byteBuf.readBlockPos()));
		}
		else if ("freezer".equals(guiName))
		{
			return new GuiFreezer(syncId, playerIn.inventory, (AetherBlockEntity) playerIn.world.getBlockEntity(byteBuf.readBlockPos()));
		}
		else if ("incubator".equals(guiName))
		{
			return new GuiIncubator(syncId, playerIn.inventory, (AetherBlockEntity) playerIn.world.getBlockEntity(byteBuf.readBlockPos()));
		}
		else if ("treasure_chest".equals(guiName))
		{
			return new GuITreasureChest(syncId, playerIn.inventory, (TreasureChestBlockEntity) playerIn.world.getBlockEntity(byteBuf.readBlockPos()));
		}

		return null;
	}

}