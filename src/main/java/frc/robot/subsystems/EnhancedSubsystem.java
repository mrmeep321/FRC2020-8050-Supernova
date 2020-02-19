package frc.robot.subsystems;

import java.util.Vector;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class EnhancedSubsystem extends SubsystemBase {
    private static Vector<EnhancedSubsystem> registry = new Vector<>();
    
    private boolean isActive, interruptable;
    private Thread loopThread;

    public abstract void loop();

    public EnhancedSubsystem(boolean interruptable) {
        registry.add(this);
        
        this.interruptable = interruptable;
        isActive = false;

        loopThread = new Thread(() -> {
            while(isActive) {
                loop();
            }
        });
    }
    
    public void start() {
        isActive = true;
        loopThread.start();
    }

    public void postEnd() {};

    public void end() {
        isActive = false;
        if(interruptable) {loopThread.interrupt(); postEnd();};
    }

    public static void endAll() {
        for(EnhancedSubsystem i : registry) {
            i.end();
        }
    }
}