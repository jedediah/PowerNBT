package me.dpohvar.powernbt.command.action;

import me.dpohvar.powernbt.PowerNBT;
import me.dpohvar.powernbt.nbt.NBTBase;
import me.dpohvar.powernbt.nbt.NBTContainer;
import me.dpohvar.powernbt.nbt.NBTQuery;
import me.dpohvar.powernbt.utils.Caller;
import me.dpohvar.powernbt.utils.NBTViewer;

public class ActionCopy extends Action {

    private final Caller caller;
    private final Argument arg;

    public ActionCopy(Caller caller, String object, String query) {
        this.caller = caller;
        this.arg = new Argument(caller, object, query);
    }

    @Override
    public void execute() {
        if (arg.needPrepare()) {
            arg.prepare(this, null, null);
            return;
        }
        NBTContainer container = arg.getContainer();
        NBTQuery query = arg.getQuery();
        NBTBase base = container.getCustomTag(query);
        caller.setCustomTag(container.getCustomTag(query));
        caller.send(PowerNBT.plugin.translate("success_copied") + NBTViewer.getShortValueWithPrefix(base,false));
    }
}
