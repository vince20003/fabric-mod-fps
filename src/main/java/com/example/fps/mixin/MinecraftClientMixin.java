package com.example.fps.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Shadow public abstract int getCurrentFps();

    // This mixin is minimal for the requested functionality. The actual display logic
    // is handled in the HudRenderCallback in FpsModClient. We don't need to inject
    // anything specific here just for getting the FPS, as MinecraftClient already
    // provides the getCurrentFps() method. This mixin would be more relevant if we
    // needed to *modify* how FPS is calculated or rendered internally by Minecraft.
    // Since we're just *reading* it for display via a separate HUD event, this mixin
    // is technically not strictly required for the core functionality, but kept here
    // as an example and for potential future expansion if needed to interact with MC's
    // internal FPS logic.

    // For this mod, no specific injection into MinecraftClient is necessary beyond
    // what's already provided by the `getCurrentFps()` method, which is directly
    // accessible from the client mod initializer.

    // If you needed to, for example, log the FPS every frame, you might do something like this:
    // @Inject(method = "render", at = @At("HEAD"))
    // private void fpsMod$logFps(boolean tick, CallbackInfo ci) {
    //     System.out.println("Current FPS: " + getCurrentFps());
    // }
}
