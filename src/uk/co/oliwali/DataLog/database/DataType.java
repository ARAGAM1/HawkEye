package uk.co.oliwali.DataLog.database;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum DataType {
	
	BLOCK_BREAK(0, true, "block-break", true),
	BLOCK_PLACE(1, true, "block-place", true),
	SIGN_PLACE(2, true, "sign-place"),
	CHAT(3, "chat"),
	COMMAND(4, "command"),
	JOIN(5, "join"),
    QUIT(6, "quit"),
    TELEPORT(7, "teleport"),
    LAVA_BUCKET(8, true, "lava-bucket", true),
    WATER_BUCKET(9, true, "water-bucket", true),
    OPEN_CHEST(10, true, "open-chest"),
    DOOR_INTERACT(11, true, "door-interact"),
    PVP_DEATH(12, "pvp-death"),
	FLINT_AND_STEEL(13, true, "flint-steel"),
	LEVER(14, true, "lever"),
	STONE_BUTTON(15, true, "button"),
	OTHER(16, "other"),
	EXPLOSION(17, "explosion", true),
	BLOCK_BURN(18, "block-burn", true),
	SNOW_FORM(19, "snow-form", true),
	LEAF_DECAY(20, "leaf-decay", true),
	MOB_DEATH(21, "mob-death", true),
	OTHER_DEATH(22, "other-death"),
	CHEST_TRANSACTION(23, true, "chest-transaction", true),
	ITEM_DROP(24, "item-drop"),
	ITEM_PICKUP(25, "item-pickup");
	
	private int id;
	private boolean canHere;
	private String configName;
	private boolean canRollback;
	
	private static final Map<String, DataType> nameMapping = new HashMap<String, DataType>();
	private static final Map<Integer, DataType> idMapping = new HashMap<Integer, DataType>();
	
	static {
		for (DataType type : EnumSet.allOf(DataType.class)) {
			nameMapping.put(type.configName, type);
		}
		for (DataType type : EnumSet.allOf(DataType.class)) {
			idMapping.put(type.id, type);
		}
	}
	
	private DataType(int id, String configName) {
		this.id = id;
		this.canHere = false;
		this.configName = configName;
		this.canRollback = false;
	}
	private DataType(int id, String configName, boolean canRollback) {
		this.id = id;
		this.configName = configName;
		this.canRollback = canRollback;
	}
	private DataType(int id, boolean canHere, String configName) {
		this.id = id;
		this.canHere = canHere;
		this.configName = configName;
		this.canRollback = false;
	}
	private DataType(int id, boolean canHere, String configName, boolean canRollback) {
		this.id = id;
		this.canHere = canHere;
		this.configName = configName;
		this.canRollback = canRollback;
	}
	
	public int getId() {
		return id;
	}
	
	public String getConfigName() {
		return configName;
	}
	
	public static DataType fromName(String name) {
		return nameMapping.get(name);
	}
	
	public static DataType fromId(int id) {
		return idMapping.get(id);
	}
	
	public boolean canRollback() {
		return canRollback;
	}
	
	public boolean canHere() {
		return canHere;
	}

}
