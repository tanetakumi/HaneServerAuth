package net.serveron.hane.haneserverauth;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

@OnlyIn(Dist.CLIENT)
@Mod("hane")
public class HaneServerAuth {
    private final Minecraft mc = Minecraft.getInstance();

    public HaneServerAuth() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent e) {
        if(e.getMessage().getString().equalsIgnoreCase("Authentication")){
            if(mc.player!=null){
                mc.player.sendChatMessage("/auth "+getModList()+getResourcesList());
            }
        }
    }

    private String getModList(){
        StringBuilder result = new StringBuilder();
        String[] resourcesList = new File(mc.gameDir.getAbsolutePath(),"mods").list();
        if(resourcesList!=null){
            if(resourcesList.length!=0){
                for(String item : resourcesList){
                    String text = item.replaceAll("[0-9]", "")
                            .replace(" ","")
                            .replace(".","")
                            .replace("-","")
                            .replace("(","")
                            .replace(")","")
                            .replace("jar","");
                    result.append(text).append(":");
                }
            }
        }
        return result.toString();
    }

    private String getResourcesList(){
        StringBuilder result = new StringBuilder();
        String[] resourcesList = new File(mc.gameDir.getAbsolutePath(),"resourcepacks").list();
        if(resourcesList!=null){
            if(resourcesList.length!=0){
                for(String item : resourcesList){
                    String text = item.replaceAll("[0-9]", "")
                            .replace(" ","")
                            .replace(".","")
                            .replace("-","")
                            .replace("(","")
                            .replace(")","")
                            .replace("zip","");
                    result.append(text).append(":");
                }
            }
        }
        return result.toString();
    }
}
