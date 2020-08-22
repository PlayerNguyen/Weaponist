package com.playernguyen.command;

public interface SubCommand extends Command {

    Command getPreviousCommand();

}
