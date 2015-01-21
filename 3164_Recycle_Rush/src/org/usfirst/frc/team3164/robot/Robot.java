//This code was written for FRC Team 3164 by Jaxon Brown and Brendan Gregos.
//For a list of port assignments, please refer to the Wiki on Github.

package org.usfirst.frc.team3164.robot;

import org.usfirst.frc.team3164.lib.baseComponents.Watchcat;
import org.usfirst.frc.team3164.lib.baseComponents.motors.IMotor; 
import org.usfirst.frc.team3164.lib.baseComponents.motors.JagMotor;
import org.usfirst.frc.team3164.lib.robot.FRC2015.DriveTrain;
import org.usfirst.frc.team3164.lib.robot.FRC2015.JSRobot;
import org.usfirst.frc.team3164.lib.robot.FRC2015.LiftMech;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends JSRobot {
	
	//List of all declared robot parts
    Joystick stick1;
    Gyro drivegyro;
    
    // The channel on the driver station that the joystick is connected to
    final int joystickChannel	= 0;
    
    //Constructor
    public Robot() {
    	//Disable robot if no comms fail- prevent Charlie drivers!
    	Watchcat.init();
        //Setup new drivetrain
    	drivegyro=new Gyro(0);
        stick1 = new Joystick(joystickChannel);
    }
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
    	drivegyro.initGyro();
    }
    /**
     * This function is called when autonomous starts
     */
    @Override
    public void autonomousInit() {
    	
    }
    
    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
    	Watchcat.feed();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
    	
    	////Wheel movement/////
    	driveTrain.mecanumDrive_Cartesian(stick1.getX(), stick1.getY(), stick1.getZ(), drivegyro.getAngle() );
    	
    	//emergency gyro reset during match
    	if(stick1.getRawButton(10)){ drivegyro.initGyro(); }
    	
    	Watchcat.feed();
    }
    
    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
    	drivegyro.initGyro(); //Reset Gyro when robot placed into test mode.
    	Watchcat.feed();
    }
    
}
