package net.thecorgi.upwego;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpWeGo implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("upwego");

    @Override
    public void onInitialize() {
        // ..yes
        LOGGER.info("Prepare to launch!");
    }
}
