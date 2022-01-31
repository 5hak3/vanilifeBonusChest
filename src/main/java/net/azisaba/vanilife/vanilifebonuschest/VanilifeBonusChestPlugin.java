package net.azisaba.vanilife.vanilifebonuschest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class VanilifeBonusChestPlugin extends JavaPlugin implements Listener {
    // ボーナスチェストを既に表示したプレイヤーのUUIDをStringで保持するリスト
    // UUIDのリストはyamlか何かしらで持つ(予定)
    private ArrayList<String> players = new ArrayList<>();

    @Override
    public void onEnable() {
    }

    @EventHandler
    public void onChestTouch(PlayerInteractEvent event) {
        /*
           TODO プレイヤーがあるメタデータを持ったチェストを右クリックした時，そのプレイヤーが過去に開いていなければ配布アイテムを表示
         */
        Player player = event.getPlayer();
        // プレイヤーが既にアイテム配布が行われているならば開かない
        if (players.contains(player.getUniqueId().toString())) return;

        // ボーナスチェストを作成してプレイヤーに表示
        Inventory chest =  Bukkit.createInventory(null, 27, ChatColor.GREEN + "ボーナスチェスト");
        chest.setItem(0, new ItemStack(Material.APPLE, 3));
        chest.setItem(1, new ItemStack(Material.BREAD, 3));
        chest.setItem(2, new ItemStack(Material.OAK_PLANKS, 4));
        chest.setItem(3, new ItemStack(Material.WOODEN_AXE, 1));
        player.openInventory(chest);

        event.setCancelled(true);
    }
}
