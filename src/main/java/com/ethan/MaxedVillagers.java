package com.ethan;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaxedVillagers implements ModInitializer {
	public static final String MOD_ID = "maxedvillagers";

	@Override
	public void onInitialize() {
		LibrarianTrades.registerCustomTrades();
	}
}