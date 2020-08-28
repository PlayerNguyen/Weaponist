package com.playernguyen.weaponist.command;

public interface SubCommand extends Command {

    Command getPreviousCommand();

}
