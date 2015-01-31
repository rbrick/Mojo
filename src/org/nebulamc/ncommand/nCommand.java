package org.nebulamc.ncommand;

import org.nebulamc.ncommand.annotations.Command;
import org.nebulamc.ncommand.annotations.Permission;

/**
 * Created by Ryan on 1/29/2015
 * <p/>
 * Project: nCommand
 */
public class nCommand {


    @Command(name="example", aliases = {"ex"})
    @Permission("example.*")
    public void example(String str) {
    }


}
