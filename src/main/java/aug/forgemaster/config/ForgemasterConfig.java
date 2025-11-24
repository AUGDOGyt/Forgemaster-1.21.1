package aug.forgemaster.config;

import me.fzzyhmstrs.fzzy_config.annotations.Action;
import me.fzzyhmstrs.fzzy_config.annotations.RequiresAction;
import me.fzzyhmstrs.fzzy_config.config.Config;

import static aug.forgemaster.Forgemaster.MOD_ID;
import static aug.forgemaster.Forgemaster.id;

public class ForgemasterConfig extends Config {
    public ForgemasterConfig() {
        super(id(MOD_ID));
    }

    @RequiresAction(action = Action.RESTART)
    public boolean spawnCraters = true;
}
