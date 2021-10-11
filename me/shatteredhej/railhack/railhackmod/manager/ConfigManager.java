//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.manager;

import me.shatteredhej.railhack.railhackmod.util.crystal.chat.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import java.nio.charset.*;
import com.google.common.reflect.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import java.io.*;
import java.util.*;
import com.google.gson.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.render.components.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class ConfigManager
{
    private final String MAIN_FOLDER = "RailHack/";
    private final String CONFIGS_FOLDER = "RailHack/configs/";
    private String ACTIVE_CONFIG_FOLDER;
    private final String CLIENT_FILE = "client.json";
    private final String CONFIG_FILE = "config.txt";
    private final String DRAWN_FILE = "drawn.txt";
    private final String EZ_FILE = "ez.txt";
    private final String NAME_FILE = "clientname.txt";
    private final String ENEMIES_FILE = "enemies.json";
    private final String FRIENDS_FILE = "friends.json";
    private final String HUD_FILE = "hud.json";
    private final String BINDS_FILE = "binds.txt";
    private final String CLIENT_DIR = "RailHack/client.json";
    private final String CONFIG_DIR = "RailHack/config.txt";
    private final String DRAWN_DIR = "RailHack/drawn.txt";
    private final String EZ_DIR = "RailHack/ez.txt";
    private final String NAME_DIR = "RailHack/ez.txt";
    private final String ENEMIES_DIR = "RailHack/enemies.json";
    private final String FRIENDS_DIR = "RailHack/friends.json";
    private final String HUD_DIR = "RailHack/hud.json";
    private String CURRENT_CONFIG_DIR;
    private String BINDS_DIR;
    private final Path MAIN_FOLDER_PATH;
    private final Path CONFIGS_FOLDER_PATH;
    private Path ACTIVE_CONFIG_FOLDER_PATH;
    private final Path CLIENT_PATH;
    private final Path CONFIG_PATH;
    private final Path DRAWN_PATH;
    private final Path EZ_PATH;
    private final Path ENEMIES_PATH;
    private final Path FRIENDS_PATH;
    private final Path HUD_PATH;
    private Path BINDS_PATH;
    private Path CURRENT_CONFIG_PATH;
    
    public ConfigManager() {
        this.ACTIVE_CONFIG_FOLDER = "RailHack/configs/default/";
        this.CURRENT_CONFIG_DIR = "RailHack/RailHack/configs/" + this.ACTIVE_CONFIG_FOLDER;
        this.BINDS_DIR = this.CURRENT_CONFIG_DIR + "binds.txt";
        this.MAIN_FOLDER_PATH = Paths.get("RailHack/", new String[0]);
        this.CONFIGS_FOLDER_PATH = Paths.get("RailHack/configs/", new String[0]);
        this.ACTIVE_CONFIG_FOLDER_PATH = Paths.get(this.ACTIVE_CONFIG_FOLDER, new String[0]);
        this.CLIENT_PATH = Paths.get("RailHack/client.json", new String[0]);
        this.CONFIG_PATH = Paths.get("RailHack/config.txt", new String[0]);
        this.DRAWN_PATH = Paths.get("RailHack/drawn.txt", new String[0]);
        this.EZ_PATH = Paths.get("RailHack/ez.txt", new String[0]);
        this.ENEMIES_PATH = Paths.get("RailHack/enemies.json", new String[0]);
        this.FRIENDS_PATH = Paths.get("RailHack/friends.json", new String[0]);
        this.HUD_PATH = Paths.get("RailHack/hud.json", new String[0]);
        this.BINDS_PATH = Paths.get(this.BINDS_DIR, new String[0]);
        this.CURRENT_CONFIG_PATH = Paths.get(this.CURRENT_CONFIG_DIR, new String[0]);
    }
    
    public boolean set_active_config_folder(final String folder) {
        if (folder.equals(this.ACTIVE_CONFIG_FOLDER)) {
            return false;
        }
        this.ACTIVE_CONFIG_FOLDER = "RailHack/configs/" + folder;
        this.ACTIVE_CONFIG_FOLDER_PATH = Paths.get(this.ACTIVE_CONFIG_FOLDER, new String[0]);
        this.CURRENT_CONFIG_DIR = "RailHack/RailHack/configs/" + this.ACTIVE_CONFIG_FOLDER;
        this.CURRENT_CONFIG_PATH = Paths.get(this.CURRENT_CONFIG_DIR, new String[0]);
        this.BINDS_DIR = this.CURRENT_CONFIG_DIR + "binds.txt";
        this.BINDS_PATH = Paths.get(this.BINDS_DIR, new String[0]);
        this.load_settings();
        return true;
    }
    
    private void save_ezmessage() throws IOException {
        final FileWriter writer = new FileWriter("RailHack/ez.txt");
        try {
            writer.write(EzMessageUtil.get_message());
        }
        catch (Exception ignored) {
            writer.write("test message");
        }
        writer.close();
    }
    
    private void load_ezmessage() throws IOException {
        final StringBuilder sb = new StringBuilder();
        for (final String s : Files.readAllLines(this.EZ_PATH)) {
            sb.append(s);
        }
        EzMessageUtil.set_message(sb.toString());
    }
    
    private void save_drawn() throws IOException {
        final FileWriter writer = new FileWriter("RailHack/drawn.txt");
        for (final String s : DrawnUtil.hidden_tags) {
            writer.write(s + System.lineSeparator());
        }
        writer.close();
    }
    
    private void load_drawn() throws IOException {
        DrawnUtil.hidden_tags = Files.readAllLines(this.DRAWN_PATH);
    }
    
    private void save_friends() throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String json = gson.toJson((Object)FriendUtil.friends);
        final OutputStreamWriter file = new OutputStreamWriter(new FileOutputStream("RailHack/friends.json"), StandardCharsets.UTF_8);
        file.write(json);
        file.close();
    }
    
    private void load_friends() throws IOException {
        final Gson gson = new Gson();
        final Reader reader = Files.newBufferedReader(Paths.get("RailHack/friends.json", new String[0]));
        FriendUtil.friends = (ArrayList<FriendUtil.Friend>)gson.fromJson(reader, new TypeToken<ArrayList<FriendUtil.Friend>>() {}.getType());
        reader.close();
    }
    
    private void save_enemies() throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final String json = gson.toJson((Object)EnemyUtil.enemies);
        final OutputStreamWriter file = new OutputStreamWriter(new FileOutputStream("RailHack/enemies.json"), StandardCharsets.UTF_8);
        file.write(json);
        file.close();
    }
    
    private void load_enemies() throws IOException {
        final Gson gson = new Gson();
        final Reader reader = Files.newBufferedReader(Paths.get("RailHack/enemies.json", new String[0]));
        EnemyUtil.enemies = (ArrayList<EnemyUtil.Enemy>)gson.fromJson(reader, new TypeToken<ArrayList<EnemyUtil.Enemy>>() {}.getType());
        reader.close();
    }
    
    private void save_hacks() throws IOException {
        for (final Module hack : RailHack.get_hack_manager().get_array_hacks()) {
            final String file_name = this.ACTIVE_CONFIG_FOLDER + hack.get_tag() + ".txt";
            final Path file_path = Paths.get(file_name, new String[0]);
            this.delete_file(file_name);
            this.verify_file(file_path);
            final File file = new File(file_name);
            final BufferedWriter br = new BufferedWriter(new FileWriter(file));
            for (final Setting setting : RailHack.getSettingManager().get_settings_with_hack(hack)) {
                final String get_type = setting.get_type();
                switch (get_type) {
                    case "button": {
                        br.write(setting.get_tag() + ":" + setting.getValue(true) + "\r\n");
                        continue;
                    }
                    case "combobox": {
                        br.write(setting.get_tag() + ":" + setting.get_current_value() + "\r\n");
                        continue;
                    }
                    case "label": {
                        br.write(setting.get_tag() + ":" + setting.getValue("") + "\r\n");
                        continue;
                    }
                    case "doubleslider": {
                        br.write(setting.get_tag() + ":" + setting.getValue(1.0) + "\r\n");
                        continue;
                    }
                    case "integerslider": {
                        br.write(setting.get_tag() + ":" + setting.getValue(1) + "\r\n");
                        continue;
                    }
                }
            }
            br.close();
        }
    }
    
    private void load_hacks() throws IOException {
        for (final Module hack : RailHack.get_hack_manager().get_array_hacks()) {
            final String file_name = this.ACTIVE_CONFIG_FOLDER + hack.get_tag() + ".txt";
            final File file = new File(file_name);
            final FileInputStream fi_stream = new FileInputStream(file.getAbsolutePath());
            final DataInputStream di_stream = new DataInputStream(fi_stream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(di_stream));
            final List<String> bugged_lines = new ArrayList<String>();
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    final String colune = line.trim();
                    final String tag = colune.split(":")[0];
                    final String value = colune.split(":")[1];
                    final Setting setting = RailHack.getSettingManager().get_setting_with_tag(hack, tag);
                    try {
                        final String get_type = setting.get_type();
                        switch (get_type) {
                            case "button": {
                                setting.setValue(Boolean.parseBoolean(value));
                                continue;
                            }
                            case "label": {
                                setting.setValue(value);
                                continue;
                            }
                            case "doubleslider": {
                                setting.setValue(Double.parseDouble(value));
                                continue;
                            }
                            case "integerslider": {
                                setting.setValue(Integer.parseInt(value));
                                continue;
                            }
                            case "combobox": {
                                setting.set_current_value(value);
                                continue;
                            }
                        }
                    }
                    catch (Exception e) {
                        bugged_lines.add(colune);
                        RailHack.sendMinecraftLog("Error loading '" + value + "' to setting '" + tag + "'");
                        break;
                    }
                }
                catch (Exception ex) {}
            }
            br.close();
        }
    }
    
    private void save_client() throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final JsonParser parser = new JsonParser();
        final JsonObject main_json = new JsonObject();
        final JsonObject config = new JsonObject();
        final JsonObject gui = new JsonObject();
        config.add("name", (JsonElement)new JsonPrimitive(RailHack.get_name()));
        config.add("version", (JsonElement)new JsonPrimitive(RailHack.get_version()));
        config.add("user", (JsonElement)new JsonPrimitive(RailHack.get_actual_user()));
        config.add("prefix", (JsonElement)new JsonPrimitive(CommandManager.getPrefix()));
        for (final WurstplusFrame frames_gui : RailHack.clickGui.get_array_frames()) {
            final JsonObject frame_info = new JsonObject();
            frame_info.add("name", (JsonElement)new JsonPrimitive(frames_gui.get_name()));
            frame_info.add("tag", (JsonElement)new JsonPrimitive(frames_gui.get_tag()));
            frame_info.add("x", (JsonElement)new JsonPrimitive((Number)frames_gui.get_x()));
            frame_info.add("y", (JsonElement)new JsonPrimitive((Number)frames_gui.get_y()));
            gui.add(frames_gui.get_tag(), (JsonElement)frame_info);
        }
        main_json.add("configuration", (JsonElement)config);
        main_json.add("gui", (JsonElement)gui);
        final JsonElement json_pretty = parser.parse(main_json.toString());
        final String json = gson.toJson(json_pretty);
        final OutputStreamWriter file = new OutputStreamWriter(new FileOutputStream("RailHack/client.json"), StandardCharsets.UTF_8);
        file.write(json);
        file.close();
    }
    
    private void load_client() throws IOException {
        final InputStream stream = Files.newInputStream(this.CLIENT_PATH, new OpenOption[0]);
        final JsonObject json_client = new JsonParser().parse((Reader)new InputStreamReader(stream)).getAsJsonObject();
        final JsonObject json_config = json_client.get("configuration").getAsJsonObject();
        final JsonObject json_gui = json_client.get("gui").getAsJsonObject();
        CommandManager.setPrefix(json_config.get("prefix").getAsString());
        for (final WurstplusFrame frames : RailHack.clickGui.get_array_frames()) {
            final JsonObject frame_info = json_gui.get(frames.get_tag()).getAsJsonObject();
            final WurstplusFrame frame_requested = RailHack.clickGui.get_frame_with_tag(frame_info.get("tag").getAsString());
            frame_requested.set_x(frame_info.get("x").getAsInt());
            frame_requested.set_y(frame_info.get("y").getAsInt());
        }
        stream.close();
    }
    
    private void save_hud() throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final JsonParser parser = new JsonParser();
        final JsonObject main_json = new JsonObject();
        final JsonObject main_frame = new JsonObject();
        final JsonObject main_hud = new JsonObject();
        main_frame.add("name", (JsonElement)new JsonPrimitive(RailHack.clickHud.get_frame_hud().get_name()));
        main_frame.add("tag", (JsonElement)new JsonPrimitive(RailHack.clickHud.get_frame_hud().get_tag()));
        main_frame.add("x", (JsonElement)new JsonPrimitive((Number)RailHack.clickHud.get_frame_hud().get_x()));
        main_frame.add("y", (JsonElement)new JsonPrimitive((Number)RailHack.clickHud.get_frame_hud().get_y()));
        for (final WurstplusPinnable pinnables_hud : RailHack.get_hud_manager().get_array_huds()) {
            final JsonObject frame_info = new JsonObject();
            frame_info.add("title", (JsonElement)new JsonPrimitive(pinnables_hud.get_title()));
            frame_info.add("tag", (JsonElement)new JsonPrimitive(pinnables_hud.get_tag()));
            frame_info.add("state", (JsonElement)new JsonPrimitive(Boolean.valueOf(pinnables_hud.is_active())));
            frame_info.add("dock", (JsonElement)new JsonPrimitive(Boolean.valueOf(pinnables_hud.get_dock())));
            frame_info.add("x", (JsonElement)new JsonPrimitive((Number)pinnables_hud.get_x()));
            frame_info.add("y", (JsonElement)new JsonPrimitive((Number)pinnables_hud.get_y()));
            main_hud.add(pinnables_hud.get_tag(), (JsonElement)frame_info);
        }
        main_json.add("frame", (JsonElement)main_frame);
        main_json.add("hud", (JsonElement)main_hud);
        final JsonElement pretty_json = parser.parse(main_json.toString());
        final String json = gson.toJson(pretty_json);
        this.delete_file("RailHack/hud.json");
        this.verify_file(this.HUD_PATH);
        final OutputStreamWriter file = new OutputStreamWriter(new FileOutputStream("RailHack/hud.json"), StandardCharsets.UTF_8);
        file.write(json);
        file.close();
    }
    
    private void load_hud() throws IOException {
        final InputStream input_stream = Files.newInputStream(this.HUD_PATH, new OpenOption[0]);
        final JsonObject main_hud = new JsonParser().parse((Reader)new InputStreamReader(input_stream)).getAsJsonObject();
        final JsonObject main_frame = main_hud.get("frame").getAsJsonObject();
        final JsonObject main_huds = main_hud.get("hud").getAsJsonObject();
        RailHack.clickHud.get_frame_hud().set_x(main_frame.get("x").getAsInt());
        RailHack.clickHud.get_frame_hud().set_y(main_frame.get("y").getAsInt());
        for (final WurstplusPinnable pinnables : RailHack.get_hud_manager().get_array_huds()) {
            final JsonObject hud_info = main_huds.get(pinnables.get_tag()).getAsJsonObject();
            final WurstplusPinnable pinnable_requested = RailHack.get_hud_manager().get_pinnable_with_tag(hud_info.get("tag").getAsString());
            pinnable_requested.set_active(hud_info.get("state").getAsBoolean());
            pinnable_requested.set_dock(hud_info.get("dock").getAsBoolean());
            pinnable_requested.set_x(hud_info.get("x").getAsInt());
            pinnable_requested.set_y(hud_info.get("y").getAsInt());
        }
        input_stream.close();
    }
    
    private void save_binds() throws IOException {
        final String file_name = this.ACTIVE_CONFIG_FOLDER + "BINDS.txt";
        final Path file_path = Paths.get(file_name, new String[0]);
        this.delete_file(file_name);
        this.verify_file(file_path);
        final File file = new File(file_name);
        final BufferedWriter br = new BufferedWriter(new FileWriter(file));
        for (final Module modules : RailHack.get_hack_manager().get_array_hacks()) {
            br.write(modules.get_tag() + ":" + modules.get_bind(1) + ":" + modules.isActive() + "\r\n");
        }
        br.close();
    }
    
    private void load_binds() throws IOException {
        final String file_name = this.ACTIVE_CONFIG_FOLDER + "BINDS.txt";
        final File file = new File(file_name);
        final FileInputStream fi_stream = new FileInputStream(file.getAbsolutePath());
        final DataInputStream di_stream = new DataInputStream(fi_stream);
        final BufferedReader br = new BufferedReader(new InputStreamReader(di_stream));
        String line;
        while ((line = br.readLine()) != null) {
            try {
                final String colune = line.trim();
                final String tag = colune.split(":")[0];
                final String bind = colune.split(":")[1];
                final String active = colune.split(":")[2];
                final Module module = RailHack.get_hack_manager().getModuleWithTag(tag);
                module.set_bind(Integer.parseInt(bind));
                module.setActive(Boolean.parseBoolean(active));
            }
            catch (Exception ex) {}
        }
        br.close();
    }
    
    public void save_settings() {
        try {
            this.verify_dir(this.MAIN_FOLDER_PATH);
            this.verify_dir(this.CONFIGS_FOLDER_PATH);
            this.verify_dir(this.ACTIVE_CONFIG_FOLDER_PATH);
            this.save_hacks();
            this.save_binds();
            this.save_friends();
            this.save_enemies();
            this.save_client();
            this.save_drawn();
            this.save_ezmessage();
            this.save_hud();
        }
        catch (IOException e) {
            RailHack.sendMinecraftLog("Something has gone wrong while saving settings");
            RailHack.sendMinecraftLog(e.toString());
        }
    }
    
    public void load_settings() {
        try {
            this.load_binds();
            this.load_client();
            this.load_drawn();
            this.load_enemies();
            this.load_ezmessage();
            this.load_friends();
            this.load_hacks();
            this.load_hud();
        }
        catch (IOException e) {
            RailHack.sendMinecraftLog("Something has gone wrong while loading settings");
            RailHack.sendMinecraftLog(e.toString());
        }
    }
    
    public boolean delete_file(final String path) throws IOException {
        final File f = new File(path);
        return f.delete();
    }
    
    public void verify_file(final Path path) throws IOException {
        if (!Files.exists(path, new LinkOption[0])) {
            Files.createFile(path, (FileAttribute<?>[])new FileAttribute[0]);
        }
    }
    
    public void verify_dir(final Path path) throws IOException {
        if (!Files.exists(path, new LinkOption[0])) {
            Files.createDirectory(path, (FileAttribute<?>[])new FileAttribute[0]);
        }
    }
}
