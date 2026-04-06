package com.example.fps;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class FpsModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((guiGraphics, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null) {
                TextRenderer textRenderer = client.textRenderer;
                if (textRenderer != null) {
                    int currentFps = client.getCurrentFps();
                    String fpsText = "FPS: " + currentFps;
                    
                    // Render the FPS counter in the top-left corner
                    // The color is white (0xFFFFFF)
                    guiGraphics.drawText(textRenderer, fpsText, 2, 2, 0xFFFFFF, true);
                }
            }
        });
    }
}
