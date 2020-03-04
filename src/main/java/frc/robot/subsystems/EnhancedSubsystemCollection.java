package frc.robot.subsystems;

import java.util.Vector;

public class EnhancedSubsystemCollection {
    private Vector<EnhancedSubsystem> systems;

    public EnhancedSubsystemCollection(EnhancedSubsystem... systemParam) {
        systems = new Vector<>();
        for(EnhancedSubsystem i  : systemParam) {
            systems.add(i);
        }
    }

    public void start() {
        for(EnhancedSubsystem i : systems) {
            i.start();
        }
    }

    public void end() {
        for(EnhancedSubsystem i : systems) {
            i.end();
        }
    }
}