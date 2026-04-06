package com.vcvo.fps;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.thread.ThreadExecutor;

public class FpsModClient implements ClientModInitializer {

    private static final String MOD_ID = "fps";

    @Override
    public void onInitializeClient() {
        // Register the HUD render callback
        HudRenderCallback.EVENT.register(this::renderFpsCounter);
    }

    private void renderFpsCounter(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();

        // Only render if the client is not null and the player is in-game (not in menus).
        // client.world != null indicates being in-game.
        // client.inGameHud != null ensures the HUD is ready.
        if (client == null || client.world == null || client.inGameHud == null) {
            return;
        }

        ThreadExecutor<?> renderThread = client.getRenderThread();
        if (renderThread == null || !renderThread.isOn </* @formatter:off */ Thread.currentThread() /* @formatter:on */ >()) {
            // Ensure we are on the render thread to avoid race conditions with FPS calculation
            return;
        }

        TextRenderer textRenderer = client.textRenderer;
        if (textRenderer == null) {
            return;
        }

        // Get FPS from MinecraftClient
        @SuppressWarnings("resource") // MinecraftClient is a singleton, no need to close
        int currentFps = MinecraftClient.getInstance().getCurrentFps();

        String fpsText = "FPS: " + currentFps;
        int x = 2; // Small padding from the left edge
        int y = 2; // Small padding from the top edge
        int color = 0xFFFFFFFF; // White color (ARGB: fully opaque, white)

        // Render the text
        // DrawContext.drawText does not require explicit background/shadow if not desired.
        context.drawText(textRenderer, fpsText, x, y, color, false);
    }
}
