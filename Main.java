public class Main extends JavaPlugin {
    private int interval;
    private List<String> messages;
    private BukkitTask task;

    @Override
    public void onEnable() {
        ConfigManager configManager = new ConfigManager(this);
        interval = configManager.getInt("interval");
        messages = configManager.getStringList("messages");

        task = Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            int index = 0;

            @Override
            public void run() {
                if (index >= messages.size()) {
                    index = 0;
                }
                String message = messages.get(index);
                MessageSender.sendMessageToAllPlayers(message);
                index++;
            }
        }, interval * 20L, interval * 20L);
    }

    @Override
    public void onDisable() {
        task.cancel();
    }
}
