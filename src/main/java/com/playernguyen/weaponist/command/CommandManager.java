package com.playernguyen.weaponist.command;

import com.playernguyen.weaponist.manager.ManagerSet;

import java.util.List;
import java.util.stream.Collectors;

public class CommandManager extends ManagerSet<Command> {

    public boolean hasCommand(String command) {
        return getCommand(command) != null;
    }

    /**
     * Get the list of name of command
     *
     * @return List name of command
     */
    public List<String> toList() {
        return getContainer()
                .stream()
                .map(Command::getCommand)
                .collect(Collectors.toList());
    }

    public Command getCommand(String command) {
        return getContainer()
                .stream()
                .filter(e -> e.getCommand().equalsIgnoreCase(command))
                .findAny()
                .orElse(null);
    }

}
