package cheaters.get.banned.stats;

import cheaters.get.banned.Shady;
import cheaters.get.banned.utils.HttpUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.utils.URIBuilder;

import java.util.UUID;

public class Analytics {

    private static final String whyCollectAnalyics = "I collect analytics for my own personal data projects. All data is stored anonymously. Your username is only sent to verify your data with Mojang's servers. See https://wiki.vg/Protocol_Encryption#Authentication for more information.";
    private static final String howAreTheySent = "This system allows Shady to verify the authenticity of your data WITHOUT your session ID. This is the same process that Minecraft servers (net.minecraft.client.network.NetHandlerLoginClient) use to make sure you are who you say you are. Optifine (net.optifine.gui.GuiScreenCapeOF) and Skytils (skytils.skytilsmod.features.impl.handlers.MayorInfo.kt) do the exact same thing.";
    private static final String pleaseDontCollectAnalytics = "In exchange for your safe, free, and relatively high-quality block game cheats, I'd like to collect a little information for personal data science projects. Sorry.";

    public static void collect(String key, String value) {
        new Thread(() -> {
            String serverId = UUID.randomUUID().toString().replace("-", "");

            try {
                Shady.mc.getSessionService().joinServer(Shady.mc.getSession().getProfile(), Shady.mc.getSession().getToken(), serverId);
            } catch(Exception ignored) {
                return;
            }

            try {
                URIBuilder url = new URIBuilder("https://cheatersgetbanned.me/api/analytics")
                        .addParameter("username", Shady.mc.getSession().getUsername())
                        .addParameter("server_id", serverId)
                        .addParameter("hashed_uuid", DigestUtils.sha256Hex(Shady.mc.getSession().getProfile().getId().toString().replace("-", "")))
                        .addParameter(key, value);

                HttpUtils.fetch(url.toString());
            } catch(Exception ignored) {}
        }, "ShadyAddons-Analytics").start();
    }

}