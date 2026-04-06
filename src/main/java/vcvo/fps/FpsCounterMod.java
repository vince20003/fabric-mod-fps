package vcvo.fps;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class FpsCounterMod implements ClientModInitializer {
    public static final String MOD_ID = "fps_counter_mod";

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(this::renderFpsCounter);
    }

    private void renderFpsCounter(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.options.debugEnabled) {
            // Don't render if client is null or F3 debug screen is active
            return;
        }

        TextRenderer textRenderer = client.textRenderer;
        if (textRenderer == null) {
            return;
        }

        // Get FPS from MinecraftClient
        @SuppressWarnings("resource") // client is not owned by this class, no need to close
        int fps = client.getCurrentFps();
        String fpsText = "FPS: " + fps;

        // Color white (ARGB format, FF for full opacity)
        int color = 0xFFFFFFFF;

        // Draw at top-left corner (0,0 with default font size)
        drawContext.drawText(
                textRenderer,
                Text.of(fpsText),
                1, // X position: 1 pixel from left edge
                1, // Y position: 1 pixel from top edge
                color,
                false // No shadow
        );
    }
}
