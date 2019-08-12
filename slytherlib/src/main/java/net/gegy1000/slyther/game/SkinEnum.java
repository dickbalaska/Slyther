package net.gegy1000.slyther.game;

public enum SkinEnum implements Skin {
	
    PURPLE_DEFAULT,
    BLUE_DEFAULT,
    CYAN_DEFAULT,
    LIME_DEFAULT,
    YELLOW_DEFAULT,
    ORANGE_DEFAULT,
    PINK_DEFAULT,
    RED_DEFAULT,
    MAGENTA_DEFAULT,
    AMERICA,
    BLUE_WHITE_RED_STRIPE,
    GERMANY,
    BULGARIA,
    FRANCE,
    WHITE_RED_STRIPE,
    RAINBOW,
    SWEDEN,
    BLUE_WHITE_STRIPE,
    RED_WHITE_STRIPE,
    WHITE,
    PURPLE_GREEN_STRIPE,
    YELLOW_GREEN_BLUE_STRIPE,
    D_IVOIRE,
    RED_YELLOW_BLUE_STRIPE,
    ARCADE_GO,
    ORANGE_PURPLE_STRIPE_ANTENNA,
    SILVER_GOLD_OUTLINE,
    GREEN_EYEBALL,
    RED_GREEN_YELLOW,
    BLACK_SMALL_YELLOW_STRIPE,
    LIGHT_BLUE_BLUE_STRIPE_STARS,
    LIGHT_BLUE_STARS,
    BLUE_STARS,
    BUMBLEBEE,
    COLORFUL_STRIPES,
    PINK_WHITE_RED_STRIPE,
    LIGHT_BLUE_WHITE_BLUE_STRIPE,
    BRIGHT_ORANGE,
    BRIGHT_YELLOW,
    PEWDIEPIE,
    JELLY,
    SLUG,
    GOOGLE_PLAY,
    UNITED_KINGDOM,
    SHINY_SILVER,
    CANADA,
    SWITZERLAND,
    MOLDOVA,
    VIETNAM,
    ARGENTINA,
    SHINY_BLUE,
    SHINY_RED,
    SHINY_YELLOW,
    SHINY_ORANGE,
    SHINY_PURPLE,
    SHINY_GREEN,
    
    CUSTOM;

	@Override
	public boolean isCustom() {
		return false;
	}

	@Override
	public int getHeadColor() {
		int color = SkinHandler.INSTANCE.getDetails(this).pattern[0].ordinal() % Color.values().length;
		return color;
	}
    
    
}
