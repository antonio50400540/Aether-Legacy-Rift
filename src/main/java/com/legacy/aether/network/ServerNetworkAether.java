package com.legacy.aether.network;

import java.util.function.BiConsumer;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.networking.CustomPayloadPacketRegistry;
import net.fabricmc.fabric.networking.PacketContext;
import net.minecraft.util.PacketByteBuf;

import com.legacy.aether.Aether;
import com.legacy.aether.api.AetherAPI;
import com.legacy.aether.api.player.IPlayerAether;

public class ServerNetworkAether
{

	public static void initializePacketHandler()
	{
		register("poison", (contextIn, byteBuf) -> onPlayerPoisoned(contextIn, byteBuf));
		register("cure", (contextIn, byteBuf) -> onPlayerCured(contextIn, byteBuf));
		register("open_accessories", (contextIn, byteBuf) -> onPlayerOpenedAccessoriesGUI(contextIn));
	}

	private static void register(String name, BiConsumer<PacketContext, PacketByteBuf> consumer)
	{
		CustomPayloadPacketRegistry.SERVER.register(Aether.locate(name), consumer);
	}

	private static void onPlayerPoisoned(PacketContext contextIn, PacketByteBuf byteBuf)
	{
		IPlayerAether playerAether = AetherAPI.get(contextIn.getPlayer());
		int poisonTicks = byteBuf.readInt();

		playerAether.inflictPoison(poisonTicks);
	}

	private static void onPlayerCured(PacketContext contextIn, PacketByteBuf byteBuf)
	{
		IPlayerAether playerAether = AetherAPI.get(contextIn.getPlayer());
		int poisonTicks = byteBuf.readInt();

		playerAether.inflictCure(poisonTicks);
	}

	private static void onPlayerOpenedAccessoriesGUI(PacketContext contextIn)
	{
		ContainerProviderRegistry.INSTANCE.openContainer(Aether.locate("accessories"), contextIn.getPlayer(), (buffer) -> {});
	}

}